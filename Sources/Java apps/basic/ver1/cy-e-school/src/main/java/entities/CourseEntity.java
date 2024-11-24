package entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "course", schema = "cy_e_admin")
public class CourseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "field")
    private String field;
    @Basic
    @Column(name = "slot")
    private Date slot;
    @Basic
    @Column(name = "duration")
    private String duration;
    @Basic
    @Column(name = "course_unit")
    private String courseUnit;
    @Basic
    @Column(name = "students")
    private String students;
    @Basic
    @Column(name = "id_teacher")
    private int idTeacher;
    @ManyToOne
    @JoinColumn(name = "id_teacher", referencedColumnName = "id", nullable = false)
    private TeacherEntity teacher;

    public CourseEntity(String field, Date slot, String duration, String courseUnit,
    String students, int idTeacher, TeacherEntity teacher) {
        this.field = field;
        this.slot = slot;
        this.duration = duration;
        this.courseUnit = courseUnit;
        this.students = students;
        this.idTeacher = idTeacher;
        this.teacher = teacher;
    }

    public CourseEntity() {}

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
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
        if (idTeacher != that.idTeacher) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (slot != null ? !slot.equals(that.slot) : that.slot != null) return false;
        if (duration != null ? !duration.equals(that.duration)
                : that.duration != null) return false;
        if (courseUnit != null ? !courseUnit.equals(that.courseUnit)
                : that.courseUnit != null) return false;
        if (students != null ? !students.equals(that.students)
                : that.students != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (slot != null ? slot.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (courseUnit != null ? courseUnit.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + idTeacher;
        return result;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }
}
