package cyeschool;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CourseService;
import services.TeacherService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/CreateCourseServlet")
public class CreateCourseServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        //Récupération des paramètres
        String slot = request.getParameter("slot");
        String hour = request.getParameter("hour");
        String duration = request.getParameter("duration");
        String school_year = request.getParameter("school_year");
        String teacher_mail = request.getParameter("teacher");

        Date date = Date.valueOf(slot);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Creating Course...</h1>");
        CourseService service = new CourseService();
        String msg=service.createCourse(date,hour,duration,school_year,teacher_mail); //On utilise le service pour créer le cours dans la base de données avec les paramètres données par l'admin
        out.println("<h1>"+msg+"</h1>"); //affichage du message de succès ou d'erreur de l'opération
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
