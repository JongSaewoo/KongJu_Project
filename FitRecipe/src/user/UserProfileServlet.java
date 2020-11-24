package user;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
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
			response.sendRedirect("profileUpdate.jsp");
			return;
		}
		String userID = multi.getParameter("userID");
		//세션 검증, 타인에 대한 접근 방지
		HttpSession session = request.getSession();
		if(!userID.equals((String) session.getAttribute("userID"))){
			session.setAttribute("messageType", "오류 메세지");
	   		session.setAttribute("messageContent", "접근 할 수 없습니다.");
	   		response.sendRedirect("index.jsp");
	   		
		}
		
		String fileName="";
		File file = multi.getFile("userProfile");
		if(file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif") ) {
				String prev = new UserDAO().getUser(userID).getUserProfile();
				
				File preFile = new File(savePath + "/"+ prev);
				if(preFile.exists()) { //해당파일이 존재하는경우
					preFile.delete();
				}
				fileName = file.getName(); //파일 이름을 프로필파일명으로 설정
				
			}else {
				session.setAttribute("messageType", "오류 메세지");
		   		session.setAttribute("messageContent", "이미지 파일만 업로드가 가능합니다.");
		   		response.sendRedirect("profileUpdate.jsp");
		   		return;
			}
		}
		
		new UserDAO().profile(userID, fileName);
		session.setAttribute("messageType", "성공 메세지");
   		session.setAttribute("messageContent", "성공적으로 프로필 업로드를 하였습니다.");
   		response.sendRedirect("profileUpdate.jsp");
   		return;		
				
	}

}
