package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class UserDAO {
	DataSource dataSource; 
	
	
	
	public UserDAO(){
		try {
			InitialContext initCtx = new InitialContext();
		 	Context envContext = (Context)initCtx.lookup("java:/comp/env");
		 	dataSource  = (DataSource)envContext.lookup("jdbc/UserChat");
		}
		catch (Exception e) {
			e.printStackTrace( );
		}
	}
	
	public int	login(String userID, String userPassword) {
		
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String SQL = "SELECT * FROM USER WHERE userID =?"; 
				try {  
					
					conn= dataSource.getConnection();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, userID);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						if(rs.getString("userPassword").equals(userPassword)) {
							return 1; //로그인 성공
						}
						return 2; //비밀번호 틀림
					}else {
						return 0; //해당 유저가 존재하지 않음
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
			return -1; //데이터 베이스 오류를 뜻함
			
		}
		
	
	public int	registerCheck (String userID  ) {
		
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String SQL = "SELECT * FROM USER WHERE userID =?"; 
				try {  
					
					conn= dataSource.getConnection();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, userID);
					rs = pstmt.executeQuery();
					if(rs.next() ||userID.equals("")) {
						return 0; //이미 존재하는 회원
					}
					else { //가입가능한 회원아이디
						return 1;
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
			return -1; //데이터 베이스 오류를 뜻함
			
		}
	
	public int	register (String userID ,String userPassword, String userName, String userAge, String userGender, String userEmail, String userProfile ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO USER VALUES (?,?,?,?,?,?,?)";
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userName);
			pstmt.setInt(4, Integer.parseInt(userAge));
			pstmt.setString(5, userGender);
			pstmt.setString(6, userEmail);
			pstmt.setString(7, userProfile);
			return pstmt.executeUpdate(); // insert 문이므로  executequery가 아님
		
			

    	} catch (Exception e) {
        	e.printStackTrace();
    	}finally {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return -1; //데이터 베이스 오류를 뜻함
	
}
	
	
	public UserDTO	getUser (String userID  ) {
		UserDTO user = new UserDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM USER WHERE userID =?"; 
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserID(userID);
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserProfile(rs.getString("userProfile"));
			
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
	return user;
	
}
	
	
public int	update(String userID ,String userPassword, String userName, String userAge, String userGender, String userEmail ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE USER SET userPassword =? , userName =? , userAge=? , userGender=?, userEmail=? WHERE userID=?";
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userPassword);
			pstmt.setString(2, userName);
			pstmt.setInt(3, Integer.parseInt(userAge));
			pstmt.setString(4, userGender);
			pstmt.setString(5, userEmail);
			pstmt.setString(6, userID);
			return pstmt.executeUpdate(); // insert 문이므로  executequery가 아님
		
			

    	} catch (Exception e) {
        	e.printStackTrace();
    	}finally {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
	return -1; //데이터 베이스 오류를 뜻함
	
}


public int	profile(String userID ,String userProfile ) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String SQL = "UPDATE USER SET userProfile =? WHERE userID =? ";
	try {  
		
		conn= dataSource.getConnection();
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, userProfile);
		pstmt.setString(2, userID);
	
		return pstmt.executeUpdate(); // insert 문이므로  executequery가 아님
	
		

	} catch (Exception e) {
    	e.printStackTrace();
	}finally {
	try {
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}catch(Exception ee) {
		ee.printStackTrace();
	}
}
return -1; //데이터 베이스 오류를 뜻함

}

public String getProfile (String userID  ) {
	//특정한 유저의 사진경로를 반환
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String SQL = "SELECT userProfile FROM USER WHERE userID =?"; 
	try {  
		
		conn= dataSource.getConnection();
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, userID);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getString("userProfile").equals("")) {
				return "http://localhost:8022/UserChat/images/icon.png";
			}
			
			return "http://localhost:8022/UserChat/upload/"+ rs.getString("userProfile");
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
return "http://localhost:8022/UserChat/images/icon.png"; //데이터 베이스 오류.. 인데 그냥 기본아이콘 반환

}
	
}
