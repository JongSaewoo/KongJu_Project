package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChatBoxServlet")
public class ChatBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	//form 태그에서 전송할떄는 RLDecoder.decode가 필요 없다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		
		if(userID == null ||userID.equals("")) {
			response.getWriter().write("");
		}else {
			try {
				
				//세션값 본인 검증
				HttpSession session = request.getSession();
				if(!URLDecoder.decode(userID, "UTF-8").equals((String) session.getAttribute("userID"))){
					response.getWriter().write("");
					return;
				}
				
				userID = URLDecoder.decode(userID, "UTF-8");
				response.getWriter().write(getBox(userID));
				//특정한 사용자가 가진 모든 메세지 리스트를 반환
				
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
	}
	
	public String getBox(String userID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getBox(userID);
		if(chatList.size() == 0 ) {  
			return "";
		}
		for(int i=chatList.size() - 1; i>=0 ; i--) {   //가장빠른시간이 먼저 나오기 위해  i++ 이 아닌 i -- 역순
			String unread ="";
			if(userID.equals(chatList.get(i).getToID()) ){
				unread = chatDAO.getUnReadChat(chatList.get(i).getFromID(), userID)+"";
			}
			
			
			result.append("[{\"value\":\"" + chatList.get(i).getFromID()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime()+ "\"},");
			result.append("{\"value\":\"" + unread+"\"}]");
		
			if(i != 0 ) result.append(",");
		}
		result.append("], \"last\":\""+ chatList.get(chatList.size() -1).getChatID()+"\"}");
		
		return result.toString();
	}

}
