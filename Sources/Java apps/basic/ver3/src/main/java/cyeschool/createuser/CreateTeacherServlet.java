package cyeschool.createuser;

import entities.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TeacherService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CreateTeacherServlet")
public class CreateTeacherServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //Récupération des données
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String field = request.getParameter("field");

        String mail = firstname+"."+lastname+"@cyu.fr";

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Creating Teacher...</h1>");

        if(password.length()>7) { //Vérification de la longueur du mot de passe
            Teacher teacher = new Teacher(firstname, lastname, mail, password, field); //Création du professeur avec les données fournies par l'admin
            TeacherService service = new TeacherService();
            String msg = service.createTeacher(teacher); //Appel du service pour créer le professeur dans la base de données

            out.println("<h1>" + msg + "</h1>"); //affichage du message de succès ou d'erreur de l'opération
        }
        else{out.println("<h1>Password too short!</h1>");}

        out.println("<a href='"+request.getContextPath()+"/admin/objectforms/new_teacher.jsp'>Exit</a>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
