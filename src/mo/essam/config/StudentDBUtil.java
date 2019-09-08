package mo.essam.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import mo.essam.models.Student;

public class StudentDBUtil {

	private DataSource dataSource;

	public StudentDBUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public StudentDBUtil() {
		super();
	}

	public List<Student> getStudents() throws Exception {

		ResultSet Data = getData();

		return fillList(Data);
	}

	private List<Student> fillList(ResultSet data) {
		List<Student> students = new ArrayList<Student>();

		try {
			while (data.next()) {
				int id = data.getInt("id");
				String firstname = data.getString("first_name");
				String lastname = data.getString("last_name");
				String email = data.getString("email");

				students.add(new Student(id, firstname, lastname, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	private ResultSet getData() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			connection = dataSource.getConnection();

			statement = connection.createStatement();

			String Query = "select  * from student order by last_name";

			resultSet = statement.executeQuery(Query);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultSet;

	}

}
