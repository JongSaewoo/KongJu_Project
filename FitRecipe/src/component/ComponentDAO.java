package component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ComponentDAO {

	DataSource dataSource; 
	
	
	public ComponentDAO() {
		
		try {
			InitialContext initCtx = new InitialContext();
		 	Context envContext = (Context)initCtx.lookup("java:/comp/env");
		 	dataSource  = (DataSource)envContext.lookup("jdbc/UserChat");
		}
		catch (Exception e) {
			e.printStackTrace( );
		}
	}
	
	
	
	
	
	
	public int write(String comName, String comItem1, int comGram1, String comItem2, int comGram2 ,String comItem3, int comGram3,String comItem4, int comGram4,String comItem5, int comGram5,String comItem6, int comGram6,String comItem7, int comGram7) {   //글쓰기 
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "REPLACE INTO COMPONENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			
			pstmt.setString(1, comName);    
			pstmt.setString(2, comItem1);    
			pstmt.setInt(3, comGram1);    
			pstmt.setString(4, comItem2);    
			pstmt.setInt(5, comGram2);  
			pstmt.setString(6, comItem3);    
			pstmt.setInt(7, comGram3);  
			pstmt.setString(8, comItem4);    
			pstmt.setInt(9, comGram4);  
			pstmt.setString(10, comItem5);    
			pstmt.setInt(11, comGram5);  
			pstmt.setString(12, comItem6);    
			pstmt.setInt(13, comGram6);  
			pstmt.setString(14, comItem7);    
			pstmt.setInt(15, comGram7);  
	 
			return pstmt.executeUpdate();  //insert  pstmt.executeQuery(); 이 아닌 executeUpdate()
			
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; //데이터 베이스 오류를 뜻함
		
	}
	
	
	 
	public ComponentDTO getComponent(String comName) {
		  //게시글 보여주기 
		String SQL = "SELECT * FROM COMPONENT WHERE comName = ?"  ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, comName );
			rs = pstmt.executeQuery(); 
			
		        if(rs.next()) {  
		        	ComponentDTO component = new ComponentDTO();
					component.setComName(rs.getString(1));
					component.setComItem1(rs.getString(2));
					component.setComGram1(rs.getInt(3));
					component.setComItem2(rs.getString(4));
					component.setComGram2(rs.getInt(5));
					component.setComItem3(rs.getString(6));
					component.setComGram3(rs.getInt(7));
					component.setComItem4(rs.getString(8));
					component.setComGram4(rs.getInt(9));
					component.setComItem5(rs.getString(10));
					component.setComGram5(rs.getInt(11));
					component.setComItem6(rs.getString(12));
					component.setComGram6(rs.getInt(13));
					component.setComItem7(rs.getString(14));
					component.setComGram7(rs.getInt(15));
							        			 
					return component;
		        	 
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return null; 
	}
	
	public ArrayList<ComponentDTO> getList(String comName) {

		ArrayList<ComponentDTO> comList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="SELECT * FROM COMPONENT WHERE comItem1 LIKE ? OR comItem2 LIKE ? OR comItem3 LIKE ? OR comItem4 LIKE ? OR comItem5 LIKE ? OR comItem6 LIKE ? OR comItem7 LIKE ?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, comName );
			pstmt.setString(2, comName );
			pstmt.setString(3, comName );
			pstmt.setString(4, comName );
			pstmt.setString(5, comName );
			pstmt.setString(6, comName );
			pstmt.setString(7, comName );
			
			comList = new ArrayList<ComponentDTO>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ComponentDTO component = new ComponentDTO();
				component.setComName(rs.getString(1));
				component.setComItem1(rs.getString(2));
				component.setComGram1(rs.getInt(3));
				component.setComItem2(rs.getString(4));
				component.setComGram2(rs.getInt(5));
				component.setComItem3(rs.getString(6));
				component.setComGram3(rs.getInt(7));
				component.setComItem4(rs.getString(8));
				component.setComGram4(rs.getInt(9));
				component.setComItem5(rs.getString(10));
				component.setComGram5(rs.getInt(11));
				component.setComItem6(rs.getString(12));
				component.setComGram6(rs.getInt(13));
				component.setComItem7(rs.getString(14));
				component.setComGram7(rs.getInt(15));
				
				
				comList.add(component);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return comList;

	}
	
	public ComponentDTO getComponentMenu(String comItem) {
		  //게시글 보여주기 
		String SQL ="SELECT * FROM COMPONENT WHERE comItem1 LIKE %?% OR comItem2 LIKE %?% OR comItem3 LIKE %?% OR comItem4 LIKE %?% OR comItem5 LIKE %?% OR comItem6 LIKE %?% OR comItem7 LIKE %?%"; 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, comItem );
			pstmt.setString(2, comItem );
			pstmt.setString(3, comItem );
			pstmt.setString(4, comItem );
			pstmt.setString(5, comItem );
			pstmt.setString(6, comItem );
			pstmt.setString(7, comItem );
			rs = pstmt.executeQuery(); 
			
		        if(rs.next()) {  
		        	ComponentDTO component = new ComponentDTO();
					component.setComName(rs.getString(1));
					component.setComItem1(rs.getString(2));
					component.setComGram1(rs.getInt(3));
					component.setComItem2(rs.getString(4));
					component.setComGram2(rs.getInt(5));
					component.setComItem3(rs.getString(6));
					component.setComGram3(rs.getInt(7));
					component.setComItem4(rs.getString(8));
					component.setComGram4(rs.getInt(9));
					component.setComItem5(rs.getString(10));
					component.setComGram5(rs.getInt(11));
					component.setComItem6(rs.getString(12));
					component.setComGram6(rs.getInt(13));
					component.setComItem7(rs.getString(14));
					component.setComGram7(rs.getInt(15));
							        			 
					return component;
		        	 
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return null; 
	}
	
	
	
	public int update(String newName, String comName, String comItem1, int comGram1, String comItem2, int comGram2 ,String comItem3, int comGram3,
			String comItem4, int comGram4,String comItem5, int comGram5,String comItem6, int comGram6,String comItem7, int comGram7) {   //글 수정하기
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE COMPONENT SET comName = ? ,comItem1 = ? , comGram1= ?, comItem2 = ? , comGram2= ?, comItem3 = ? ,  comGram3= ?, comItem4 = ? ,  comGram4 = ?, comItem5 = ? ,  comGram5 = ?, comItem6 = ? , comGram6 = ?, comItem7 = ? , comGram7 = ? WHERE comName = ?	";
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			
			pstmt.setString(1,newName);
			pstmt.setString(2,comItem1);
			pstmt.setInt(3,comGram1);
			pstmt.setString(4,comItem2);
			pstmt.setInt(5,comGram2);
			pstmt.setString(6,comItem3);
			pstmt.setInt(7,comGram3);
			pstmt.setString(8,comItem4);
			pstmt.setInt(9,comGram4);
			pstmt.setString(10,comItem5);
			pstmt.setInt(11,comGram5);
			pstmt.setString(12,comItem5);
			pstmt.setInt(13,comGram6);
			pstmt.setString(14,comItem7);
			pstmt.setInt(15,comGram7);
			pstmt.setString(16,comName);				        			 
					
			
			return pstmt.executeUpdate();  //insert  pstmt.executeQuery(); 이 아닌 executeUpdate()
			
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; //데이터 베이스 오류를 뜻함
		
	}
	
	public int delete(int menuNum) {  //글 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE MENU SET bbsAvailable = 0 WHERE menuNum = ?	";
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
	
			pstmt.setInt(1, menuNum);    //제목
			
			return pstmt.executeUpdate();  //insert  pstmt.executeQuery(); 이 아닌 executeUpdate()
			
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; //데이터 베이스 오류를 뜻함
	}
}