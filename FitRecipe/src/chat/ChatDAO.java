package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatDAO {

	DataSource dataSource;
	
	public ChatDAO(){
		try {
			InitialContext initCtx = new InitialContext();
		 	Context envContext = (Context)initCtx.lookup("java:/comp/env");
		 	dataSource  = (DataSource)envContext.lookup("jdbc/UserChat");
		}
		catch (Exception e) {
			e.printStackTrace( );
		}
	}
	
	public ArrayList<ChatDTO> getChatListByID(String fromID, String toID, String chatID){
		ArrayList<ChatDTO> chatList = null;
		//하나하나 채팅내용을 저장
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID =? AND toID = ?)) AND chatID> ? ORDER BY chatTime";
		
		//보낸사람이든 받는사람이든 모두 가져옴
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				//chat.setChatContent(rs.getString("chatContent"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,	13));
				String timeType ="오전";
				if(chatTime >= 12) {
					timeType ="오후";
					chatTime -=12;
				}
				
				chat.setChatTime(rs.getString("chatTime").substring(0, 11)+" "+timeType+" "+ chatTime+":"+rs.getString("chatTime").substring(14,16)+"");
				chatList.add(chat);
				
			}
			

    	} catch (Exception e) {
        	e.printStackTrace();
    	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return chatList; //리스트 반환
	
}	
	
	public ArrayList<ChatDTO> getChatListByRecent(String fromID, String toID, int number ){
		ArrayList<ChatDTO> chatList = null;
		//하나하나 채팅내용을 저장
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID =? AND toID = ?)) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT WHERE(fromID = ? and toID = ?) OR (fromID= ? AND toID =?)) ORDER BY chatTime";
			//대화 내역중 최근의 몇개만 가져온다   //마지막 where 절은 많은 대화 내역중에 두사람 대화 내용만 가져오도록 한다.
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			pstmt.setString(6, fromID);
			pstmt.setString(7, toID);
			pstmt.setString(8, toID);
			pstmt.setString(9, fromID);
			
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				//chat.setChatContent(rs.getString("chatContent"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,	13));
				String timeType ="오전";
				if(chatTime >= 12) {
					timeType ="오후";
					chatTime -=12;
				}
				
				chat.setChatTime(rs.getString("chatTime").substring(0, 11)+" "+timeType+" "+ chatTime+":"+rs.getString("chatTime").substring(14,16)+"");
				chatList.add(chat);
				
			}
			

    	} catch (Exception e) {
        	e.printStackTrace();
    	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return chatList; //리스트 반환
}	
	
	public ArrayList<ChatDTO> getBox(String userID ){
		ArrayList<ChatDTO> chatList = null;
		//하나하나 채팅내용을 저장
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE toID = ? OR fromID =? GROUP BY fromID, toID)";
		
		//해당 userID와 관련된 최신메세지를 불러온다.
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userID);
			
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll("<", "&gt;").replaceAll("\n", "<br>"));
				//chat.setChatContent(rs.getString("chatContent"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,	13));
				String timeType ="오전";
				if(chatTime >= 12) {
					timeType ="오후";
					chatTime -=12;
				}
				
				chat.setChatTime(rs.getString("chatTime").substring(0, 11)+" "+timeType+" "+ chatTime+":"+rs.getString("chatTime").substring(14,16)+"");
				chatList.add(chat);
				
			}
			
			
			
			for(int i = 0; i< chatList.size(); i++) {
				ChatDTO x = chatList.get(i);
				for(int j = 0; j<chatList.size(); j++) {
					ChatDTO y = chatList.get(j);
					if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID()) ) {
						if(x.getChatID() < y.getChatID()) {
						chatList.remove(x);
						i--;
						//겹치는 경우 지워준다.
						break;
						}else {
						chatList.remove(y);
						j--;
						}
					}
				}
			}

    	} catch (Exception e) {
        	e.printStackTrace();
    	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return chatList; //리스트 반환
}	
	
	public int submit(String fromID, String toID, String chatContent){
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "INSERT INTO CHAT VALUES (NULL, ?, ? , ?, NOW(), 0)";
			//null 값을 넣음으로 자동적으로 다른값이 증가되며 삽입,  (데이터베이스 만들때 기본조건에 의해)
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();  //성공하면 1이 반환된다
			
    	} catch (Exception e) {
        	e.printStackTrace();
    	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return -1; //오류
}	
	public int readChat(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "UPDATE CHAT SET chatRead = 1 WHERE(fromID = ? AND toID =?)";
			try {
				conn= dataSource.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, toID);
				pstmt.setString(2, fromID);
				return pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null)rs.close();
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
				return -1; //데이터베이스 오류
	}
	
	
	public int getAllUnReadChat(String userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT COUNT(chatID) FROM	CHAT WHERE toID =? AND chatRead = 0";
			try {
				conn= dataSource.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return rs.getInt("COUNT(chatID)");
				}
				return 0;
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null)rs.close();
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
				return -1; //데이터베이스 오류
	}
	
	public int getUnReadChat(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT COUNT(chatID) FROM	CHAT WHERE toID =? AND toID=? AND chatRead = 0";
			try {
				conn= dataSource.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, fromID);
				pstmt.setString(2, toID);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					return rs.getInt("COUNT(chatID)");
				}
				return 0;
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null)rs.close();
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
				return -1; //데이터베이스 오류
	}
	
	
}
