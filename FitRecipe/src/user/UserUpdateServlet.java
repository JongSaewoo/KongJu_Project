package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");
		
		if(userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("")
				||userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("")
				||userAge == null || userAge.equals("") || userGender == null || userGender.equals("")
				||userEmail == null || userEmail.equals("") )
		{
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���");
			
			response.sendRedirect("update.jsp");
			return;
		}
		//���ǰ� ���� ����
		HttpSession session = request.getSession();
		if(!userID.equals((String) session.getAttribute("userID"))){
			session.setAttribute("messageType", "���� �޼���");
	   		session.setAttribute("messageContent", "���� �� �� �����ϴ�.");
	   		response.sendRedirect("index.jsp");
	   		
		}
		
				
		if(!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "��й�ȣ�� ���� �ٸ��ϴ�");
			response.sendRedirect("update.jsp");
			return;
		}
		int result =new UserDAO().update(userID, userPassword1, userName, userAge, userGender, userEmail);
		if(result==1) {
			request.getSession().setAttribute("userID", userID); //ȸ������ ������ �ڵ� ���� �������� , �α���
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "ȸ������ ������ �����߽��ϴ�");
			response.sendRedirect("index.jsp");
			return;
		}
		else {
			request.getSession().setAttribute("messageType", "�����޼���");
			request.getSession().setAttribute("messageContent", "�̹� �����ϴ� ȸ�� �Ǵ� �����ͺ��̽� ������ �߻��߽��ϴ�.");
			response.sendRedirect("update.jsp");
			return;
			
		}
		
	}

}
