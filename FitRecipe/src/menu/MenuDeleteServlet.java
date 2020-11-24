package menu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MenuDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); //get����̶� ������ get���
		
	}
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session =request.getSession();  //���ǰ����� userID �� �����´�.
		String userID = (String)session.getAttribute("userID");
		String menuNum = request.getParameter("menuNum");
		
	
		if(menuNum ==null || menuNum.equals("")) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "�����Ҽ� �����ϴ�. id����");
			response.sendRedirect("index.jsp");
			return;
		}
		
		MenuDAO menuDAO = new MenuDAO();
		MenuDTO menu = menuDAO.getMenu(menuNum);
		
		if(!userID.equals(menu.getUserID())) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "�����Ҽ� �����ϴ�. �������� ����");
			response.sendRedirect("index.jsp");
			return;
		}
		
		String savePath	=request.getRealPath("/upload").replaceAll("\\\\", "/");
		// String prev = menuDAO.getRealFile(menuNum);
		int result = menuDAO.delete(menuNum);
		if(result == -1) {
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "�Խñ� ������ �����Ͽ����ϴ�.");
			response.sendRedirect("index.jsp");
			return;
			
		}else {
			
			/*
			File prevFile = new File(savePath + "/" + prev) ;
			if(prevFile.exists()) { //�ش������� �����Ѵٸ� ����
				prevFile.delete();
			} */   //������ �������� ������ �̱���
			request.getSession().setAttribute("messageType", "���� �޼���");
			request.getSession().setAttribute("messageContent", "�Խñ� ������ �����Ͽ����ϴ�.");
			response.sendRedirect("menuView.jsp");
			return;
			
			
		}
		
	}
}
