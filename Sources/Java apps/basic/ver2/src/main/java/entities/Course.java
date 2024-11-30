package entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Every field cannot be NULL (see SQL file)
 */
@Entity
public class Course {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "field", nullable = false)
    private String field;

    @Column(name = "slot", nullable = false)
    private Date slot;

    @Column(name = "hour", nullable = false)
    private String hour;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "school_year", nullable = false)
    private String schoolYear;

    @Column(name = "course_unit", nullable = false)
    private String courseUnit;

    //TODO : CASCADING for deleting

    // No PERSIST cascading because Teacher instances must be created before Course instances
    //so Teacher instances are not transient while Course ones are
    // MERGE cascading not required because .merge() is called during transactions
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;

    //TODO : STUDENT CASCADING -> MERGE, REFRESH
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "student_courses",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id")}
    )
    /**
     * Association table student_courses.
     * Course table is equivalent to the host.
     */
    private Set<Student> students;

    public Course(String field, Date slot, String hour, String duration, String courseUnit, String schoolYear,
    Teacher teacher) {
        this.field = field;
        this.slot = slot;
        this.hour = hour;
        this.duration = duration;
        this.courseUnit = courseUnit;
        this.schoolYear = schoolYear;
        this.teacher = teacher;
        students = new HashSet<>();
    }

    public Course() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Date getSlot() {
        return slot;
    }

    public void setSlot(Date slot) {
        this.slot = slot;
    }

    public String getHour() { return hour; }

    public void setHour(String hour) { this.hour = hour; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSchoolYear() { return schoolYear; }

    public void setSchoolYear(String schoolYear) { this.schoolYear = schoolYear; }

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() { return students; }

    public void setStudents(Set<Student> students) { this.students = students; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (field != null ? !field.equals(course.field) : course.field != null) return false;
        if (slot != null ? !slot.equals(course.slot) : course.slot != null) return false;
        if (duration != null ? !duration.equals(course.duration) : course.duration != null) return false;
        if (courseUnit != null ? !courseUnit.equals(course.courseUnit) : course.courseUnit != null) return false;
        if (schoolYear != null ? !schoolYear.equals(course.schoolYear) : course.schoolYear != null) return false;

        return true;
    }

    /**
     * For tests purposes.<br>
     * Because ids are generated automatically, no constructor with an id as a parameter is required.
     * Since test data is known, the corresponding id has to be specified.
     */
    public boolean equals(Object o, int id) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (this.id != id) return false;
        if (field != null ? !field.equals(course.field) : course.field != null) return false;
        if (slot != null ? !slot.equals(course.slot) : course.slot != null) return false;
        if (duration != null ? !duration.equals(course.duration) : course.duration != null) return false;
        if (courseUnit != null ? !courseUnit.equals(course.courseUnit) : course.courseUnit != null) return false;
        if (schoolYear != null ? !schoolYear.equals(course.schoolYear) : course.schoolYear != null) return false;

        return true;
    }

    /**
     * For debugging.
     */
    @Override
    public String toString() {
        return "Course instance\n" +
               "id : " + id + "; field : " + field + "; slot : " + slot.toString() + "; duration : " + duration
               + ";\ncourse unit : " + courseUnit + "; schoolYear : " + schoolYear + "; teacher : " + teacher.getFirstname()
               + " " + teacher.getLastname() + "\n"
               /*+ courseStudentsToString()*/;
    }

    /**
     * For debugging.<br>
     * Be careful when calling .courseStudentToString() in .toString() while debugging any operations linked to an entity relation to Course.<br>
     * It will overlap with the other entity .toString() because each instance will print the other one infinitely.<br>
     * For instance, with .studentTimetableToString() from Student entity.<br>
     * Do not call .courseStudentsToString() when debugging IS NOT focused on a course instance.
     */
    public String courseStudentsToString() {
        String courseStudents = "";
        for (Student signedUpStudent : students)
            courseStudents += "Student instance\nid : " + signedUpStudent.getId() + "; firstname : " + signedUpStudent.getFirstname()
                    + "; lastname : " + signedUpStudent.getLastname() + "\n";

        return "Course students :\n" + courseStudents;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (slot != null ? slot.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (courseUnit != null ? courseUnit.hashCode() : 0);
        result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
        return result;
    }


}
