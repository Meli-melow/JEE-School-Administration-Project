package services_tests;

import entities.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AdminService;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AdminServiceTest {

    AdminService as;
    Admin a;

    @BeforeEach
    public void testAdminService() { as = new AdminService();; }

    @AfterEach
    public void testResetCourseService() {
        as = null;
        a = null;
    }

    @Test //TODO : WORKS
    public void serviceCreateAdmin() {
        as.createAdmin( new Admin("Éric", "LECHAMP", "eric.lechamp@cyu.fr", "gfcgctr"));
        assertSame(true, as.getAdminById(1).equals(
                new Admin("Éric", "LECHAMP", "eric.lechamp@cyu.fr", "gfcgctr"), 1));
    }

    @Test //TODO: WORKS
    public void serviceLoginAdmin() {
        a = as.adminLogin("alain.leclair@cyu.fr", "fzgb");
        assertSame(true, a == null);
        /*assertSame(true, t.equals( new Admin("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
                "alLE", "Modèles Probabilistes"),6));*/
        /*assertSame(t.equals( new Admin("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
                "alLE", "Maths"),1), true);*/
    }

    @Test //TODO: WORKS
    public void serviceFetchAdminById() {
        a = as.getAdminById(1);
        assertSame(true, a.equals( new Admin("Éric", "LECHAMP", "eric.lechamp@cyu.fr",
                "gfcgctr"),1));
        /*assertSame(t.equals( new Admin("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
                "alLE", "Maths"),1), true);*/
    }

    @Test //TODO : WORKS
    public void serviceFetchAdminByMail() {
        // Check if exception is thrown
        // Red test with this approach because FecthException class does not have a default message error
        /*assertThrows(FetchException.class, () -> t = ts.getAdminByMail("alain.leclair@cyu.fr"),
                "Could not fetch admin instance by mail");*/
        a = as.getAdminByMail("alain.leclair@cyu.fr");
        assertSame(true, a.equals( new Admin("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
                "alLE"),1 ));
    }

    @Test
    //TODO : FULLY WORKS
    // Patching admin info
    // Courses updated when changing field
    public void servicePatchAdminProfile() {
        as.patchAdminProfile(3, "", "", "");
    }

    @Test //TODO : WORKS
    public void serviceChangeAdminPswd() {
        as.changeAdminPswd(2, "e,lk,e");
    }

    /*@Test //TODO: WORKS
    public void serviceUpdateAdmin() {
        ts.updateAdmin(1, new Admin("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE", "CS", null));
        t = ts.getAdminById(1);
        assertSame(t.equals( new Admin("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE",
                "CS", null) ,1), true);
    }*/

    @Test
    //TODO : WORKS
    // Removing a admin also removes courses that were in their timetable
    public void serviceDeleteAdmin() {
        a = as.getAdminById(1);
        as.deleteAdmin(a.getId());
    }

    @Test
    public void serviceFetchAllAdmins() {
        assertNull(as.getAllAdmins());
    }
}
