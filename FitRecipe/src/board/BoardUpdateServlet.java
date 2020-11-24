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


public class BoardUpdateServlet extends HttpServlet {
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
			response.sendRedirect("index.jsp");
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
		String boardID =multi.getParameter("boardID"); 
		if(boardID == null || boardID.equals("")) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다(boardID 없음)");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		
		
		String boardTitle = multi.getParameter("boardTitle");
		String boardContent = multi.getParameter("boardContent");
		
		if(boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "글 제목과 글 내용을 모두 채워주세요");
	   		response.sendRedirect("boardWrite.jsp");
	   		return;
	   		
		}
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = boardDAO.getBoard(boardID);
		if(!userID.equals(board.getUserID())) {  //작성자가 일치하지 않는경우
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다(글쓴이가 일치하지 않음)");
	   		response.sendRedirect("index.jsp");
	   		return;
		}
		
		String boardFile="";
		String boardRealFile="";
		
		
		File file = multi.getFile("boardFile");
		if(file != null) {  //파일이 새롭게 등록된경우
			boardFile = multi.getOriginalFileName("boardFile");
			boardRealFile = file.getName();
			String prev = boardDAO.getRealFile(boardID);   //기존 파일을 지운다.
			File prevFile = new File(savePath + "/"+ prev);  
			if(prevFile.exists()) {
				prevFile.delete();
			}
			
		}else {  //새롭게 파일을 등록하는경우 
			boardFile = boardDAO.getFile(boardID);
			boardRealFile =boardDAO.getRealFile(boardID);
			
		}
		
		boardDAO.update(boardID, boardTitle, boardContent, boardFile, boardRealFile);
		
		session.setAttribute("messageType", "성공 메세지");
   		session.setAttribute("messageContent", "성공적으로 게시물을 수정했습니다.");
   		response.sendRedirect("boardView.jsp");
   		return;		
				
	}

}
