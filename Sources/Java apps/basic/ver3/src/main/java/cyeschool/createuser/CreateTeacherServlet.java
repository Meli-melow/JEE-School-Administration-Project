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

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String field = request.getParameter("field");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if(firstname != null && lastname != null && mail != null && password != null && field != null){
            Teacher teacher = new Teacher(firstname, lastname, mail, password,field);
            TeacherService service = new TeacherService();
            String msg = service.createTeacher(teacher);
            out.println("<h1>"+msg+"</h1>");
        }
        else{
            out.println("<h1>Error</h1>");
            out.println("</body></html>");
            try {
                request.getRequestDispatcher("/admin/objectforms/new_teacher.jsp").forward(request, response);
            }
            catch (ServletException e) {}

        }
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
