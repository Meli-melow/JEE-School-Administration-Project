package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "assessment", schema = "cy_e_admin")
public class AssessmentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "assess_name")
    private String assessName;
    @Basic
    @Column(name = "coefficient")
    private BigDecimal coefficient;
    @Basic
    @Column(name = "test_date")
    private Date testDate;
    @Basic
    @Column(name = "grade")
    private BigDecimal grade;
    @Basic
    @Column(name = "id_result")
    private int idResult;
    @ManyToOne
    @JoinColumn(name = "id_result", referencedColumnName = "id", nullable = false)
    private ResultEntity assessmentField;

    public AssessmentEntity(String assessName, BigDecimal coefficient, Date testDate,
    BigDecimal grade, int idResult, ResultEntity assessmentField) {
        this.assessName = assessName;
        this.coefficient = coefficient;
        this.testDate = testDate;
        this.grade = grade;
        this.idResult = idResult;
        this.assessmentField = assessmentField;
    }

    public AssessmentEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssessmentEntity that = (AssessmentEntity) o;

        if (id != that.id) return false;
        if (idResult != that.idResult) return false;
        if (assessName != null ? !assessName.equals(that.assessName)
                : that.assessName != null) return false;
        if (coefficient != null ? !coefficient.equals(that.coefficient)
                : that.coefficient != null) return false;
        if (testDate != null ? !testDate.equals(that.testDate)
                : that.testDate != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (assessName != null ? assessName.hashCode() : 0);
        result = 31 * result + (coefficient != null ? coefficient.hashCode() : 0);
        result = 31 * result + (testDate != null ? testDate.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + idResult;
        return result;
    }

    public ResultEntity getAssessmentField() {
        return assessmentField;
    }

    public void setAssessmentField(ResultEntity assessmentField) {
        this.assessmentField = assessmentField;
    }
}
