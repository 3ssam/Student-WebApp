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
			DispalyStudents(request,response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void DispalyStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("Students", DBObject.getStudents());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		
		dispatcher.forward(request, response);
	}

}
