package login_logout;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "khcafe";
		String password = "khcafe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			
			//login 
			// memail 	�α��� �� �� ���̵� ������ ����� �̸���
			// mno 		�α��� �� �� ��й�ȣ ������ ����� �ѹ�
			String memail = request.getParameter("memail");
			String mno = request.getParameter("mno");
			
			// select ��ġ�ϴ� ������ �����ϴ��� Ȯ��
			String sql = "SELECT * FROM MemberInfo WHERE Memail = ? AND MNO = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,  memail);
			preparedStatement.setString(2, mno);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			//�α����� �ϴ� ������ 1���̱� ������
			// if ���� Ȱ���Ͽ� �ϳ��� ������ ��ȸ�Ѵ�.
			if (resultSet.next()) {
				// ��ġ�ϴ� ���� �����ϸ� �α��� ������ ���� ������ ������ ��
				// HttpSession 
				// ������ Ŭ���̾�Ʈ�� ���� ���� ������ �����ϰ� �����ϴµ� ���
				
				// ���� http ��û�� ���� ������ ������ ��
				// ó�� http session ��û�� ó�� �� ��
				// ������ ������ ���ο� ������ �����ϰ�
				// �̹� ������ �����ϸ� �ش� ������ ������ ��
				HttpSession session = request.getSession();
				session.setAttribute("mno", resultSet.getString("MNO"));
				session.setAttribute("mname", resultSet.getString("MNAME"));
				session.setAttribute("memail", resultSet.getString("MEMAIL"));
				session.setAttribute("mbirth", resultSet.getDate("MBirth"));
				
				// �α��� �ð��� 30������ ����
				// Inactive : ��Ȱ��ȭ Ȱ������ �ʴ� ����
				// Interval : ����
				session.setMaxInactiveInterval(1800);
				
				
				// �α��ο� �����ϸ� ������ ���� �����͸� login_success�� ��������
				response.sendRedirect("login_success.jsp");
				
			} else {
				// ���� �������� ������ �α��� ����
				
				// ���࿡ �α����� �ȵȴٸ�
				// �Ӽ��� loginError �̸����� �Ӽ��� �����ϰ�, �α��ο����� true��
				// ������ �߱� ������ �α��� ������ �߻������� ��Ÿ����
				// �Ӽ��̸��� �Ӽ����� �߰�
				request.setAttribute("loginError", "true");
				//						�׳��̸�		true������
				
				request.getRequestDispatcher("home.jsp").forward(request, response);
				// request.getRequestDispatcher("���") : �츮�� ������ ��η� �̵��ϱ� ���� ��ü ��ȯ
				// forward(request, response) : ���� �������� ������ �ߴܵ�
				// ���ݱ��� ������ �մ� �����͸� Ŭ���̾�Ʈ���� �������� ������ ����� ǥ����
				
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
