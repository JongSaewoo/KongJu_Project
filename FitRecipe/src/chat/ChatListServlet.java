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


@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String fromID =request.getParameter("fromID");
		String toID =request.getParameter("toID");
		String listType = request.getParameter("listType");
			
		if(fromID == null || fromID.equals("") ||toID == null || toID.equals("")
			||listType == null || listType.equals("")	) {
			response.getWriter().write("");
		}else if(listType.equals("ten")) response.getWriter().write(getTen(URLDecoder.decode(fromID ,"UTF-8"),URLDecoder.decode(toID,"UTF-8")));      //한글인 경우를 생각해 디코더    
		else {
			try {
				
				//세션값 본인 검증
				HttpSession session = request.getSession();
				if(!URLDecoder.decode(fromID, "UTF-8").equals((String) session.getAttribute("userID"))){
					response.getWriter().write("");
					return;
				}
				
				response.getWriter().write(getID(URLDecoder.decode(fromID ,"UTF-8"), URLDecoder.decode(toID,"UTF-8"), listType));
				//특정한 아이디 기준으로 대화정보를 가져온다.
				
			}catch (Exception e) {
				response.getWriter().write("");
			}
		}
		
	}

	public String getTen(String fromID, String toID) {  //JSON 형태  //10개까지 가져오도록, 이었는데... 100개로 늘리자
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100);
		if(chatList.size() == 0 ) {  //챗 리스트가 비워있는경우
			return "";
		}
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\":\"" + chatList.get(i).getFromID()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime()+ "\"}]");
			
			if(i != chatList.size() -1 ) result.append(",");
		}
		result.append("], \"last\":\""+ chatList.get(chatList.size() -1).getChatID()+"\"}");
		
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}
	
	public String getID(String fromID, String toID, String chatID) {  
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		if(chatList.size() == 0 ) {  
			return "";
		}
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\":\"" + chatList.get(i).getFromID()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getToID()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatContent()+ "\"},");
			result.append("{\"value\":\"" + chatList.get(i).getChatTime()+ "\"}]");
			
			if(i != chatList.size() -1 ) result.append(",");
		}
		result.append("], \"last\":\""+ chatList.get(chatList.size() -1).getChatID()+"\"}");
		
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}
}
