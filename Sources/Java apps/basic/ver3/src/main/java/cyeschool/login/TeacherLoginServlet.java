package cyeschool.login;

import entities.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TeacherService;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/TeacherLoginServlet")
public class TeacherLoginServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //On récupère les paramètres
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        String regex = "\\b[a-z][.][a-z][@cyu.fr]*\\b";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);

        if(m.matches() && password!=null){
            if(m.matches()&&password.length()>7) { //Vérifie que l'email correspond au motif "prenom@nom@cyu.fr" et le mdp est plus long que 8 characteres
                TeacherService service = new TeacherService();
                Teacher teacher = service.getTeacherByMail(mail); //On récupère le prof avec son email
                if (password.equals(teacher.getPswd())) {
                    //Si le mot de passe est bon, on lui permet d'accéder à son espace
                    request.getRequestDispatcher("/professor/professorHome.jsp").forward(request, response);
                }
            }
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
