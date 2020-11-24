package board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BoardWriteServlet extends HttpServlet {
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
			e.setStackTrace(null);
			
			response.sendRedirect("index.jsp");
			
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
		String boardTitle = multi.getParameter("boardTitle");
		String boardContent = multi.getParameter("boardContent");
		
		if(boardTitle == null || boardTitle.trim().equals("") || boardContent == null || boardContent.trim().equals("")) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "글 제목과 글 내용을 모두 채워주세요");
	   		response.sendRedirect("boardWrite.jsp");
	   		return;
		}
		
		String boardFile="";
		String boardRealFile="";
		
		
		File file = multi.getFile("boardFile");
		if(file != null) {
			boardFile = multi.getOriginalFileName("boardFile");
			boardRealFile = file.getName();
			
			
		}
		int boardPass = 0;
		try {		
		if( 1==Integer.parseInt(multi.getParameter("pass"))) {
			boardPass = 1;
		}
		}catch(Exception e) {
			//
		}
		
		System.out.println(multi.getParameter("pass"));
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.write(userID, boardTitle, boardContent, boardFile, boardRealFile,boardPass );
		
		
		session.setAttribute("messageType", "성공 메세지");
   		session.setAttribute("messageContent", "성공적으로 게시물을 작성했습니다.");
   		response.sendRedirect("boardView.jsp");
   		return;		
				
	}

}
