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
		doPost(request, response); //get방식이라 시작은 get방식
		
	}
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session =request.getSession();  //세션값에서 userID 를 가져온다.
		String userID = (String)session.getAttribute("userID");
		String menuNum = request.getParameter("menuNum");
		
	
		if(menuNum ==null || menuNum.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메세지");
			request.getSession().setAttribute("messageContent", "접근할수 없습니다. id공백");
			response.sendRedirect("index.jsp");
			return;
		}
		
		MenuDAO menuDAO = new MenuDAO();
		MenuDTO menu = menuDAO.getMenu(menuNum);
		
		if(!userID.equals(menu.getUserID())) {
			request.getSession().setAttribute("messageType", "오류 메세지");
			request.getSession().setAttribute("messageContent", "접근할수 없습니다. 삭제권한 없음");
			response.sendRedirect("index.jsp");
			return;
		}
		
		String savePath	=request.getRealPath("/upload").replaceAll("\\\\", "/");
		// String prev = menuDAO.getRealFile(menuNum);
		int result = menuDAO.delete(menuNum);
		if(result == -1) {
			request.getSession().setAttribute("messageType", "오류 메세지");
			request.getSession().setAttribute("messageContent", "게시글 삭제에 실패하였습니다.");
			response.sendRedirect("index.jsp");
			return;
			
		}else {
			
			/*
			File prevFile = new File(savePath + "/" + prev) ;
			if(prevFile.exists()) { //해당파일이 존재한다면 삭제
				prevFile.delete();
			} */   //서버내 사진삭제 오류로 미구현
			request.getSession().setAttribute("messageType", "성공 메세지");
			request.getSession().setAttribute("messageContent", "게시글 삭제에 성공하였습니다.");
			response.sendRedirect("menuView.jsp");
			return;
			
			
		}
		
	}
}
