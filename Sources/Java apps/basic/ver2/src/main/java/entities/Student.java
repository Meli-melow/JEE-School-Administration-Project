package entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    /**
     * Cannot be NULL (see SQL file)
     */
    @Column(name = "firstname", nullable = false)
    private String firstname;

    /**
     * Cannot be NULL (see SQL file)
     */
    @Column(name = "lastname", nullable = false)
    private String lastname;

    /**
     * Is unique (see SQL file)
     */
    //TODO : REGEX
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    /**
     * Cannot be NULL (see SQL file)
     */
    //TODO : REGEX
    @Column(name = "pswd", nullable = false)
    private String pswd;

    /**
     * Cannot be NULL (see SQL file)
     */
    @Column(name = "birth", nullable = false)
    private Date birth;

    /**
     * Cannot be NULL (see SQL file)
     */
    @Column(name = "school_year", nullable = false)
    private String schoolYear;

    @OneToMany(mappedBy = "student")
    private Set<Result> results;

    //TODO : see if accessing and saving can be done without using an association with the Course Entity
    //TODO : STUDENT CASCADING -> PERSIST, MERGE (?), REFRESH OU SAVE_UPDATE
    @Transient
    private List<Course> timetable;

    public Student(String firstname, String lastname, String mail, String pswd, Date birth,
    String schoolYear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.pswd = pswd;
        this.birth = birth;
        this.schoolYear = schoolYear;
        results = new HashSet<>();
        timetable = new ArrayList<>();
    }

    public Student() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public List<Course> getTimetable() { return timetable; }

    public void setTimetable(List<Course> timetable) { this.timetable = timetable; }

    public void addCourse(Course courseToAdd) {
        if(!timetable.contains(courseToAdd))
            timetable.add(courseToAdd);
    }

    public void removeCourse(Course courseToRemove) {
        if (!timetable.isEmpty())
            timetable.remove(courseToRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (firstname != null ? !firstname.equals(student.firstname) : student.firstname != null) return false;
        if (lastname != null ? !lastname.equals(student.lastname) : student.lastname != null) return false;
        if (mail != null ? !mail.equals(student.mail) : student.mail != null) return false;
        if (pswd != null ? !pswd.equals(student.pswd) : student.pswd != null) return false;
        if (birth != null ? !birth.equals(student.birth) : student.birth != null) return false;
        if (schoolYear != null ? !schoolYear.equals(student.schoolYear) : student.schoolYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (pswd != null ? pswd.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
        return result;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
}