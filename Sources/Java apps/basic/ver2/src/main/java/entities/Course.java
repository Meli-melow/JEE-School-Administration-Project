package entities;

import jakarta.persistence.*;

import java.sql.Date;

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

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "course_unit", nullable = false)
    private String courseUnit;

    @Column(name = "school_year", nullable = false)
    private String schoolYear;

    //TODO : CASCADING for deleting
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_teacher", referencedColumnName = "id", nullable = false)
    private Teacher teacher;

    //TODO : STUDENT CASCADING -> PERSIST, MERGE (?), REFRESH OU SAVE_UPDATE

    public Course(String field, Date slot, String duration, String courseUnit, String schoolYear,
    Teacher teacher) {
        this.field = field;
        this.slot = slot;
        this.duration = duration;
        this.courseUnit = courseUnit;
        this.schoolYear = Course.this.schoolYear;
        this.teacher = teacher;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public String getStudents() {
        return schoolYear;
    }

    public void setStudents(String schoolYear) {
        this.schoolYear = Course.this.schoolYear;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

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
     * For tests purposes
     * */
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
     * For tests purposes
     */
    @Override
    public String toString() {
        return "Course instance\n" +
               "id : " + id + "; field : " + field + "; slot : " + slot + "; duration : " + duration
               + ";\ncourse unit : " + courseUnit + "; schoolYear : " + schoolYear + "\n";
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
