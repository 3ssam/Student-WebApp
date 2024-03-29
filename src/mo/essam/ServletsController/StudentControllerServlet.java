package mo.essam.ServletsController;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mo.essam.config.StudentDBUtil;
import mo.essam.models.Student;
import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDBUtil DBObject;
	
	@Resource(name="jdbc/student-crud-operation")
	private DataSource dataSource;
		
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			DBObject = new StudentDBUtil(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String Command = "LIST";
			
			if(request.getParameter("command") != null)
				Command = request.getParameter("command");
			
			switch (Command) {
			case "ADD":
				AddStudent(request,response);
				break;
			case "UPDATE":
				UpdateStudent(request,response);
				break;
			case "DELETE":
				DeleteStudent(request,response);
				break;
			case "LIST":
				DispalyStudents(request, response);
				break;
			case "LOAD":
				ShowDetialsOfStudent(request, response);
				break;
			default:
				DispalyStudents(request, response);
				break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void ShowDetialsOfStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("StudentID");
		
		Student student = DBObject.getStudent(id);
		
		request.setAttribute("Student", student);
		
		Displaynow(request, response, "/Update-Student-Form.jsp");
	}

	private void DeleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("StudentID"));
		
		boolean check = DBObject.DeleteStudent(id);
		
		DispalyStudents(request, response);	
	}

	private void UpdateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int id = Integer.parseInt(request.getParameter("studentId"));
		
		Student s = new Student(id,firstName,lastName,email);
		
		boolean check = DBObject.UpdateStudent(s);
		
		DispalyStudents(request, response);
		
	}

	private void AddStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		DBObject.AddStudent(new Student(firstName,lastName,email));
		
		try {
			DispalyStudents(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void DispalyStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("Students", DBObject.getStudents());
		
		Displaynow(request,response,"/list-students.jsp");
		
	}

	private void Displaynow(HttpServletRequest request, HttpServletResponse response,String nextPage) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		
		dispatcher.forward(request, response);
	}

}
