package menu;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import component.ComponentDAO;


public class MenuUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MultipartRequest multi =  null;
		int fileMaxSize = 10* 1024 * 1024;		
		String savePath	=request.getRealPath("/upload").replaceAll("\\\\", "/");
		try {
			multi = new MultipartRequest(request, savePath,fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		}catch (Exception e) {
			request.getSession().setAttribute("messageType", "오류 메세지");
			request.getSession().setAttribute("messageContent", "파일은 10mb를 넘을 수 없습니다.");
			response.sendRedirect("menuView.jsp");
			return;
		}
		String userID = multi.getParameter("userID");
		
		HttpSession session = request.getSession();
		if(!userID.equals((String) session.getAttribute("userID"))){
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다.");
	   		response.sendRedirect("index.jsp");
	   		return;
	   		
		}
		String menuNum =multi.getParameter("menuNum"); 
		if(menuNum == null || menuNum.equals("")) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다(menuNum 없음)");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		
		
		String menuName = multi.getParameter("menuName");
		String menuRecipe = multi.getParameter("menuRecipe");
		
		if(menuName == null || menuName.trim().equals("") || menuRecipe == null || menuRecipe.trim().equals("")) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "글 제목과 글 내용을 모두 채워주세요");
	   		response.sendRedirect("boardWrite.jsp");
	   		return;
	   		
		}
		MenuDAO menuDAO = new MenuDAO();
		MenuDTO menu = menuDAO.getMenu(menuNum);
		ComponentDAO comDAO = new ComponentDAO();
		
		if(!userID.equals(menu.getUserID())) {  //작성자가 일치하지 않는경우
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다(글쓴이가 일치하지 않음)");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		
		String menuProfile="";
		String menuRealProfile="";
		
		
		File file = multi.getFile("menuProfile");
		if(file != null) {  //파일이 새롭게 등록된경우
			menuProfile = multi.getOriginalFileName("menuProfile");
			menuRealProfile = file.getName();
			String prev = menuDAO.getRealProfile(menuNum);   //기존 파일을 지운다.
			File prevFile = new File(savePath + "/"+ prev);  
			if(prevFile.exists()) {
				prevFile.delete();
			}
			
		}else {  //새롭게 파일을 등록하는경우 
			menuProfile = menuDAO.getFile(menuNum);  //업데이트는 getProfile 이 아닌 getFile로 새로 만듬
			menuRealProfile =menuDAO.getRealProfile(menuNum);
			
		}
		
		
		String comItem1 = multi.getParameter("comItem1"); 
		String comItem2 = multi.getParameter("comItem2"); 
		String comItem3 = multi.getParameter("comItem3"); 
		String comItem4 = multi.getParameter("comItem4"); 	
		String comItem5 = multi.getParameter("comItem5"); 
		String comItem6 = multi.getParameter("comItem6"); 
		String comItem7 = multi.getParameter("comItem7"); 
		
		if(comItem1 == null || comItem1.trim().equals("") ) {
			comItem1="";
		}
		if(comItem2 == null || comItem2.trim().equals("") ) {
			comItem2="";
		}
		if(comItem3 == null || comItem3.trim().equals("") ) {
			comItem3="";
		}
		if(comItem4 == null || comItem4.trim().equals("") ) {
			comItem4="";
		}
		if(comItem5 == null || comItem5.trim().equals("") ) {
			comItem5="";
		}
		if(comItem6 == null || comItem6.trim().equals("") ) {
			comItem6="";
		}
		if(comItem7 == null || comItem7.trim().equals("") ) {
			comItem7="";
		}
		
							
		int comGram1 = trans(multi.getParameter("comGram1"));
		int comGram2 = trans(multi.getParameter("comGram2"));
		int comGram3 = trans(multi.getParameter("comGram3"));
		int comGram4 = trans(multi.getParameter("comGram4"));
		int comGram5 = trans(multi.getParameter("comGram5"));
		int comGram6 = trans(multi.getParameter("comGram6"));
		int comGram7 = trans(multi.getParameter("comGram7"));
		
		// menuName: 바꿀려는 이름 menu.getMenuName() : 기존 이름 
		comDAO.update(menuName, menu.getMenuName(), comItem1, comGram1, comItem2, comGram2, comItem3, comGram3, comItem4, comGram4, comItem5, comGram5, comItem6, comGram6, comItem7, comGram7);
		menuDAO.update(menuNum, menuName, menuRecipe, menuProfile, menuRealProfile);
		
		session.setAttribute("messageType", "성공 메세지");
   		session.setAttribute("messageContent", "성공적으로 게시물을 수정했습니다.");
   		response.sendRedirect("menuView.jsp");
   		return;		
				
	}
	
		private int trans(String gram) {
		
		//널포인트 오류나면 0으로 반환
		try {
		return Integer.parseInt(gram);
		}catch (NumberFormatException e) {
			return 0;
		}catch(Exception ee) {
			ee.printStackTrace();
			return 0;
		}
		}

}
