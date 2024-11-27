package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {

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
    //TODO : REGEX in servlet
    @Column(name = "pswd", nullable = false)
    private String pswd;

    /**
     * Cannot be NULL (see SQL file)
     */
    @Column(name = "field", nullable = false)
    private String field;

    @OneToMany(mappedBy = "teacher")
    private List<Course> timetable;

    public Teacher(String firstname, String lastname, String mail, String pswd, String field,
    List<Course> timetable) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.pswd = pswd;
        this.field = field;
        this.timetable = timetable;
    }

    public Teacher() {}

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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (id != teacher.id) return false;
        if (firstname != null ? !firstname.equals(teacher.firstname) : teacher.firstname != null) return false;
        if (lastname != null ? !lastname.equals(teacher.lastname) : teacher.lastname != null) return false;
        if (mail != null ? !mail.equals(teacher.mail) : teacher.mail != null) return false;
        if (pswd != null ? !pswd.equals(teacher.pswd) : teacher.pswd != null) return false;
        if (field != null ? !field.equals(teacher.field) : teacher.field != null) return false;

        return true;
    }

    /**
     * For tests purposes
     * */
    public boolean equals(Object o, int id) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (this.id != id) return false;
        if (firstname != null ? !firstname.equals(teacher.firstname) : teacher.firstname != null) return false;
        if (lastname != null ? !lastname.equals(teacher.lastname) : teacher.lastname != null) return false;
        if (mail != null ? !mail.equals(teacher.mail) : teacher.mail != null) return false;
        if (pswd != null ? !pswd.equals(teacher.pswd) : teacher.pswd != null) return false;
        if (field != null ? !field.equals(teacher.field) : teacher.field != null) return false;

        return true;
    }

    /**
     * For tests purposes
     */
    @Override
    public String toString() {
        return "Teacher instance\n" +
                "id : " + id + "; firstname : " + firstname + "; lastname : " + lastname + "; mail : " + mail
                + ";\npassword : " + pswd + "; field : " + field + "\n";
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (pswd != null ? pswd.hashCode() : 0);
        result = 31 * result + (field != null ? field.hashCode() : 0);
        return result;
    }

    public List<Course> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<Course> timetable) {
        this.timetable = timetable;
    }
}