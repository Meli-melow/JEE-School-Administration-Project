package cyeschool.login;

import entities.Admin;
import entities.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AdminService;
import services.TeacherService;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.IOException;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
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

        if(mail!=null && password!=null){
            if(m.matches()&&password.length()>7){ //Vérifie que l'email correspond au motif "prenom@nom@cyu.fr" et le mdp est plus long que 8 characteres
                AdminService service = new AdminService();
                Admin admin = service.getAdminByMail(mail); //On récupère l'admin avec son email
                if(password.equals(admin.getPswd())){
                    //Si le mot de passe est bon, on lui permet d'accéder à son espace
                    request.getRequestDispatcher("/admin/main_admin.jsp").forward(request, response);
                }
            }
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
