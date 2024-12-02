package dao;

import entities.Course;
import entities.Student;
import entities.Teacher;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.sql.Date;
import java.util.List;

public class CourseDao {

    public String daoAddCourse(Date courseDay, String courseHour, String courseDuration, String courseSchoolYear,
    String teacherMail)
    {
        String serviceMessage = "Course created successfully !\n";
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
                Teacher teacher = new TeacherDao().daoFetchTeacherByEmail(teacherMail);
                if (teacher != null) {
                    // Attach again the teacher instance to the persistence context
                    em.merge(teacher);
                    Course course = new Course(teacher.getField(), courseDay, courseHour, courseDuration, courseSchoolYear, teacher);
                    teacher.getTimetable().add(course);
                    course.setTeacher(teacher);
                    // Add the course to the student having the corresponding school year
                    String hql = "SELECT s FROM Student s WHERE s.schoolYear = :school_year";
                    TypedQuery<Student> query = em.createQuery(hql, Student.class);
                    query.setParameter("school_year", courseSchoolYear);
                    try {
                        List<Student> classStudents = query.getResultList();

                        course.setStudents(classStudents);
                        for (Student classStudent : course.getStudents()) {
                            em.merge(classStudent);
                            classStudent.getTimetable().add(course);
                            // If the student instance is not managed
                            if (!em.contains(classStudent))
                                return "Course creation failed. Could not sign up existing students to the course during course creation\n";
                        }
                    } catch (NoResultException nrex) {
                        // There are no students in the given school year
                        em.persist(course);
                        em.flush();
                        return serviceMessage;
                    }
                    em.persist(course);
                    serviceMessage += "Students were automatically signed up\n";
                    // Force Hibernate to synchronise changes
                    em.flush();
                } else {
                    return "Course creation failed. Please make sure teacher mail exists\n";
                }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed");
        }
        return serviceMessage;
    }

    public Course daoFetchCourseById(int courseId) throws FetchException, ExceptionInInitializerError {

        Course course;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            course = em.find(Course.class, courseId);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return course;
    }


    public String daoPatchCourse(int courseId, Date newCourseDay, String newCourseHour,
    String newCourseDuration, String newCourseSchoolYear, String newTeacherMail) {

        String serviceMessage = "";
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Course course = em.find(Course.class, courseId);
            // Update information related to the new teacher
            if (newTeacherMail != null && newTeacherMail != "") {
                Teacher newTeacher = new TeacherDao().daoFetchTeacherByEmail(newTeacherMail);
                // Make sure hibernate manages changes brought to both new and previous teacher instances
                em.merge(newTeacher);
                em.merge(course.getTeacher());
                course.getTeacher().getTimetable().remove(course);
                newTeacher.getTimetable().add(course);
                course.setTeacher(newTeacher);
                // If none of the teacher instances are managed
                if (!em.contains(newTeacher) || !em.contains(course.getTeacher()))
                    return "Course update failed. Could not change course teacher\n";

                // Change course field to the field of the new teacher
                if (!course.getField().equals(newTeacher.getField()))
                    course.setField(newTeacher.getField());

                serviceMessage += "Updated course teacher.";
            }

            if (!newCourseDay.equals(java.sql.Date.valueOf("1900-01-01")) && newCourseDay != course.getDay())
                course.setDay(newCourseDay);

            if (newCourseHour != null && newCourseHour != "" && !newCourseHour.equals(course.getHour()))
                course.setHour(newCourseHour);

            if (newCourseDuration != null && newCourseDuration != "" && !newCourseDuration.equals(course.getDuration()))
                course.setDuration(newCourseDuration);

            serviceMessage = "Course updated successfully ! " + serviceMessage;
            if (newCourseSchoolYear != null && newCourseSchoolYear != "" && !newCourseSchoolYear.equals(course.getSchoolYear())) {
                if (!course.getStudents().isEmpty()) {
                    // Remove students from the former prom
                    for (Student formerStudent : course.getStudents()) {
                        // Make sure hibernate manages changes brought to students in the former prom
                        em.merge(formerStudent);
                        course.getStudents().remove(formerStudent);
                        formerStudent.getTimetable().remove(course);
                        // If students instances are not managed
                        if (!em.contains(formerStudent))
                            return "Could not propagate modifications correctly. Could not unsubscribe up previous students" +
                                    "to course\n";
                    }
                    serviceMessage += " Removed students from the previous school year.";
                }
                // Add the course to the student having the corresponding school year
                String hql = "SELECT s FROM Student s WHERE s.schoolYear = :school_year";
                TypedQuery<Student> query = em.createQuery(hql, Student.class);
                query.setParameter("school_year", newCourseSchoolYear);
                try {
                    List<Student> classStudents = query.getResultList();
                    course.setStudents(classStudents);
                    for (Student classStudent : classStudents) {
                        // Make sure hibernate manages changes brought to student instances
                        em.merge(classStudent);
                        classStudent.getTimetable().add(course);
                        // If students instances are not managed
                        if (!em.contains(classStudent)) {
                            em.getTransaction().rollback();
                            return "Course update failed. Students with the same school year new could not" +
                                    "be signed up to course\n";
                        }
                    }
                } catch (NoResultException nrex) {
                    // There are no students having the new school year
                    return serviceMessage;
                }
                course.setSchoolYear(newCourseSchoolYear);
                serviceMessage += " Students from the new school year were automatically signed up\n";
            }
        em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return serviceMessage;
    }

    public String daoDeleteCourseById(int courseId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Course course = em.find(Course.class, courseId);
            Teacher teacher = course.getTeacher();
            // Make sure hibernate retrieves the course from its teacher's timetable
            em.merge(teacher);
            teacher.getTimetable().remove(course);
            // If the teacher instance is not managed
            if (!em.contains(teacher))
                return "Could not remove course from teacher timetable\n";

            em.remove(course);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Course deleted successfully !\n";
    }


    public List<Course> daoGetAllCourses() {

        List<Course> coursesList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            String hql = "SELECT c FROM Course c";
            TypedQuery<Course> query = em.createQuery(hql, Course.class);
            try {
                coursesList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return coursesList;
    }


    public List<Course> daoGetAllCourses(Date day, String hour, String schoolYear, String field, String duration)
    {
        List<Course> coursesList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            // the school year is assumed to be used for filtering
            String hql = "SELECT c FROM Course c WHERE c.schoolYear = :school_year";
            // Filtering courses by field criteria
            if (hour != null && !hour.equals(""))
                hql += " AND c.hour = :hour";
            if (day != null && !day.equals(""))
                hql += " AND c.day = :day";
            if (field != null && !field.equals(""))
                hql += " AND c.field = :field";
            if (duration != null && !duration.equals(""))
                hql += " AND c.duration = :duration";

            TypedQuery<Course> query = em.createQuery(hql, Course.class);
            query.setParameter("day", day);
            query.setParameter("hour", hour);
            query.setParameter("field", field);
            query.setParameter("duration", duration);
            try {
                coursesList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return coursesList;
    }

    //TODO: Patch version
    // The goal was to optimise the fetching of entities fields which are updated but this sort of process
    // should be done to servlets
    /*public void daoPatchCourse(int courseId, HashMap<String, Object> requestFields) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            try {
                Course course = em.find(Course.class, courseId);
                // Iterate data entries and update them dynamically with Java Reflection
                requestFields.forEach((dataName, newValue) -> {
                    try {
                        Field entityField = Course.class.getDeclaredField(dataName);
                        entityField.setAccessible(true);
                        entityField.set(course, newValue);
                        entityField.setAccessible(false);
                    } catch (NoSuchFieldException | IllegalArgumentException e) {
                        throw new RuntimeException("Error while updating a course instance, unvalid entity field found : " + dataName
                        + e);
                    }
                });
            } catch (UpdateCascadeException dex) {
                em.getTransaction().rollback();
                throw new UpdateCascadeException("Could not propagate modifications correctly : " + dex);
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }*/

}
