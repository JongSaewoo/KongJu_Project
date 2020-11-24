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
import user.UserDAO;


public class MenuWriteServlet extends HttpServlet {
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
			response.sendRedirect("menuWrite.jsp");
			return;
		}
		String userID = multi.getParameter("userID");
		//세션 검증, 타인에 대한 접근 방지
		HttpSession session = request.getSession();
		if(!userID.equals((String) session.getAttribute("userID"))){
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다.");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		String menuName = multi.getParameter("menuName");
		String menuRecipe = multi.getParameter("menuRecipe");
		
		if(menuName == null || menuName.trim().equals("") || menuRecipe == null || menuRecipe.trim().equals("")) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "글 제목과 글 내용을 모두 채워주세요");
	   		response.sendRedirect("menuWrite.jsp");
	   		return;
	   		
		}
		
		String menuProfile="";
		String menuRealProfile="";
		
		File file = multi.getFile("menuProfile");
		if(file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif") ) {
				String prev = new UserDAO().getUser(userID).getUserProfile();
				
				File preFile = new File(savePath + "/"+ prev);
				if(preFile.exists()) { //해당파일이 존재하는경우
					preFile.delete();
				}
				menuProfile = file.getName(); //파일 이름을 프로필파일명으로 설정
				menuRealProfile= file.getName();
				
			}else {
				session.setAttribute("messageType", "오류 메세지");
		   		session.setAttribute("messageContent", "이미지 파일만 업로드가 가능합니다.");
		   		response.sendRedirect("menuWrite.jsp");
		   		return;
			}
		}
		
		
				System.out.println("받아온 comItem1"+multi.getParameter("comItem1")); //작동됨
			//	System.out.println("2"+request.getParameter("comItem1")); //multipart/form-data방식이므로 request는 적용안된다. 일단 이거는 값이 없으면 null값이 아닌 공백값을 가져오는듯함
		
		
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
		System.out.println("comGram7 공백입력시 값"+ comGram7);
		System.out.println("comGram1 반환값"+comGram1 );	
	
		
		
		
		MenuDAO menuDAO = new MenuDAO();
		ComponentDAO comDAO = new ComponentDAO();
		int result2= comDAO.write(menuName, comItem1, comGram1, comItem2, comGram2, comItem3, comGram3, comItem4, comGram4, comItem5, comGram5, comItem6, comGram6, comItem7, comGram7);
		int result = menuDAO.write(userID, menuName, menuRecipe, menuProfile, menuRealProfile);
		//순서중요. 컴포넌트DAO가 먼저생성되야함
		
		
		
		if(result==-2) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "중복된 메뉴이름이 있습니다.<br> 메뉴이름을 바꿔주세요");
	   		response.sendRedirect("menuWrite.jsp");
	   		return;	
		}
		else if(result==-1) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "글쓰기에 실패하셨습니다.");
	   		response.sendRedirect("menuWrite.jsp");
	   		return;		
			
		}else {
			
			if(result2==-1) {
				session.setAttribute("messageType", "오류 메세지");
		   		session.setAttribute("messageContent", "글쓰기에 실패하셨습니다.(컴포넌트 오류)");
		   		response.sendRedirect("menuWrite.jsp");
		   		return;	
			
			}else {
				session.setAttribute("messageType", "성공 메세지");
		   		session.setAttribute("messageContent", "성공적으로 게시물을 작성했습니다.");
		   		response.sendRedirect("menuView.jsp");
		   		return;	
			}
			
		}
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
