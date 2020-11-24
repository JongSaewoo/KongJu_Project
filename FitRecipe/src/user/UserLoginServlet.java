package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		if(userID ==null || userID.equals("")|| userPassword ==null || userPassword.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է����ּ���");
			response.sendRedirect("login.jsp");
			return;
		}
		
		int result = new UserDAO().login(userID,userPassword);
		
		if(result ==1) {
			request.getSession().setAttribute("userID", userID);
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "�α��ο� �����߽��ϴ�.");
			response.sendRedirect("index.jsp");
			return;
		}else if(result ==2) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "��й�ȣ�� �ٽ� Ȯ���ϼ���.");
			response.sendRedirect("login.jsp");
			return;
		}else if(result ==0) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "���̵� �������� �ʽ��ϴ�.");
			response.sendRedirect("login.jsp");
			return;
		}
		else if(result ==-1) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "������ ���̽� �����Դϴ�.");
			response.sendRedirect("login.jsp");
			return;
		}
	}
}
