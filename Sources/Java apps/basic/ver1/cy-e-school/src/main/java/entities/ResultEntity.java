package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "result", schema = "cy_e_admin")
public class ResultEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "field")
    private String field;
    @Basic
    @Column(name = "course_unit")
    private String courseUnit;
    @Basic
    @Column(name = "unit_coeff")
    private BigDecimal unitCoeff;
    @Basic
    @Column(name = "id_student")
    private int idStudent;
    @OneToMany(mappedBy = "assessmentField")
    private List<AssessmentEntity> assessments;
    @ManyToOne
    @JoinColumn(name = "id_student", referencedColumnName = "id", nullable = false)
    private StudentEntity student;

    public ResultEntity(String field, String courseUnit, BigDecimal unitCoeff, int idStudent,
    List<AssessmentEntity> assessments, StudentEntity student) {
        this.field = field;
        this.courseUnit = courseUnit;
        this.unitCoeff = unitCoeff;
        this.idStudent = idStudent;
        this.assessments = assessments;
        this.student = student;
    }

    public ResultEntity() {}

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

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public BigDecimal getUnitCoeff() {
        return unitCoeff;
    }

    public void setUnitCoeff(BigDecimal unitCoeff) {
        this.unitCoeff = unitCoeff;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultEntity that = (ResultEntity) o;

        if (id != that.id) return false;
        if (idStudent != that.idStudent) return false;
        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        if (courseUnit != null ? !courseUnit.equals(that.courseUnit) : that.courseUnit != null) return false;
        if (unitCoeff != null ? !unitCoeff.equals(that.unitCoeff) : that.unitCoeff != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (courseUnit != null ? courseUnit.hashCode() : 0);
        result = 31 * result + (unitCoeff != null ? unitCoeff.hashCode() : 0);
        result = 31 * result + idStudent;
        return result;
    }

    public List<AssessmentEntity> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<AssessmentEntity> assessments) {
        this.assessments = assessments;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
