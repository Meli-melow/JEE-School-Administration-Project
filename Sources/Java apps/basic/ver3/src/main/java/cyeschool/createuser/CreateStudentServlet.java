package cyeschool.createuser;

import entities.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.StudentService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/CreateStudentServlet")
public class CreateStudentServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //Récupération des données
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String birth = request.getParameter("birth");
        String schoolYear = request.getParameter("school_year");

        String mail = firstname+"."+lastname+"@cyu.fr";
        Date birthday = Date.valueOf(birth);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.print("<body>");
        out.println("<h1>Creating Student...</h1>");

        if(password.length()>7) { //Vérification de la longueur du mot de passe
            Student student = new Student(firstname, lastname, mail, password, birthday, schoolYear); //Création d'un étudiant avec les données fournies par l'admin
            StudentService service = new StudentService();
            String msg = service.createStudent(student); //appel du service pour créer l'étudiant dans la base de données
            out.println("<h1>" + msg + "</h1>"); //affichage du message de succès ou d'erreur de l'opération
        }
        else{out.println("<h1>Password too short!</h1>");}
        out.println("<a href='"+request.getContextPath()+"/admin/objectforms/new_student.jsp'>Exit</a>");

        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
