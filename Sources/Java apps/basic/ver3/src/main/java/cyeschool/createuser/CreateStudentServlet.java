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

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String birth = request.getParameter("birth");
        String schoolYear = request.getParameter("school_year");

        Date birthday = Date.valueOf(birth);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Creating Student...</h1>");
        if(firstname != null && lastname != null && mail != null && password != null && birthday != null && schoolYear != null){
            Student student = new Student(firstname, lastname, mail, password, birthday, schoolYear);
            StudentService service = new StudentService();
            String msg =service.createStudent(student);
            out.println("<h1>"+msg+"</h1>");
        }
        else{
            out.println("Couldn't create student");
            out.println("</body></html>");
            try {
                request.getRequestDispatcher("/admin/objectforms/new_student.jsp").forward(request, response);
            }
            catch (ServletException e) {}

        }

        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
