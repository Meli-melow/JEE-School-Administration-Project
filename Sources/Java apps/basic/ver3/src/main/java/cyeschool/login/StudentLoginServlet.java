package cyeschool.login;

import entities.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.StudentService;

import java.io.IOException;

@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        if(mail!=null && password!=null){
            StudentService service = new StudentService();
            Student student = service.getStudentByMail(mail);
            if(password.equals(student.getPswd())){
                request.getSession().setAttribute("mail", mail);
                request.getRequestDispatcher("/student/studentHome.jsp").forward(request, response);
            }
        }
        request.getRequestDispatcher("/login/student_login").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
