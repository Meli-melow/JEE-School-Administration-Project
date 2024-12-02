package cyeschool.login;

import entities.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TeacherService;

import java.io.IOException;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        if(mail!=null && password!=null){
            //TODO: Replace TeacherService by AdminService
            TeacherService service = new TeacherService();
            Teacher teacher = service.getTeacherByMail(mail);
            if(password.equals(teacher.getPswd())){
                request.getRequestDispatcher("/professor/professorHome.jsp").forward(request, response);
            }
        }
        request.getRequestDispatcher("/login/teacher_login").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
