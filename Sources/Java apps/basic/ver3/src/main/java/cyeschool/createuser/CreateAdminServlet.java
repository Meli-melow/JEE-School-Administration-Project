package cyeschool.createuser;

import entities.Admin;
import entities.Teacher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AdminService;
import services.TeacherService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CreateAdminServlet")
public class CreateAdminServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //Récupération des données
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");

        String mail = firstname+"."+lastname+"@cyu.fr";

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Creating Admin...</h1>");

        if(password.length()>7) { //Vérification de la longueur du mot de passe
            Admin admin = new Admin(firstname, lastname, mail, password); //Création de l'admin avec les données fournies par l'admin
            AdminService service = new AdminService();
            String msg = service.createAdmin(admin); //Appel du service pour créer l'admin dans la base de données

            out.println("<h1>" + msg + "</h1>"); //affichage du message de succès ou d'erreur de l'opération
        }
        else{out.println("<h1>Password too short!</h1>");}
        out.println("<a href='"+request.getContextPath()+"/admin/objectforms/new_admin.jsp'>Exit</a>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
