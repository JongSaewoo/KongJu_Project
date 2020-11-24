package menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MenuDAO {

		
	DataSource dataSource;

	public MenuDAO() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envContext = (Context) initCtx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/UserChat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int write(String userID ,String menuName, String menuContent, String menuProfile, String menuRealProfile) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO MENU SELECT IFNULL((SELECT MAX(menuNum) +1 FROM MENU),1),?,?, ?,?,?,?,? ";
	
		
		try {
			String a=" ";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, menuName);
			pstmt.setString(3, menuContent);
			pstmt.setInt(4, getMenuPrice(menuName));   //토탈 가격 가져오는 내가만든 함수 getMenuPrice(menuName)
			pstmt.setInt(5, 0);
			pstmt.setString(6, menuProfile);
			pstmt.setString(7, menuRealProfile);
			

			return pstmt.executeUpdate(); // insert 문이므로 executequery가 아님

		}
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류를 뜻함

	}
	
public int getMenuPrice(String menuName) {  
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String SQL ="SELECT * FROM COMPONENT WHERE comName = ? ";  //해당 menuName의 모든 구성요소를 불러온다. 
			
				
		
			
			try {
				conn = dataSource.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1,menuName);
				rs = pstmt.executeQuery(); 
				
				while(rs.next()) { 
				int result = 0;			
   		        String temp2= rs.getString(2);
				String temp4= rs.getString(4);
				String temp6= rs.getString(6);
				String temp8= rs.getString(8);
				String temp10= rs.getString(10);
				String temp12= rs.getString(12);
				String temp14= rs.getString(14);
				int temp3 = rs.getInt(3);
				int temp5 = rs.getInt(5);
				int temp7 = rs.getInt(7);
				int temp9 = rs.getInt(9);
				int temp11 = rs.getInt(11);
				int temp13 = rs.getInt(13);
				int temp15 = rs.getInt(15);

				result +=  getMenuPrice(temp2 , temp3);
				result +=  getMenuPrice(temp4 , temp5);
				result +=  getMenuPrice(temp6 , temp7);
				result +=  getMenuPrice(temp8 , temp9);
				result +=  getMenuPrice(temp10 , temp11 );
				result +=  getMenuPrice(temp12 , temp13 );
				result +=  getMenuPrice(temp14 , temp15 );
				return result; //총 가격 반환 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	finally {
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
			return 0;
	}

public int getMenuPrice(String itemName, int gram) {  //오버로딩
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="SELECT itemGram FROM ITEM WHERE itemName = ? ";
		
	
		try {
			if(itemName==null)
				return 0; // 아이템이 null값, 즉 없다면  0원을 반환 시킨다.
			if(itemName.equals(""))
				return 0; // 아이템이 공백값, 즉 없다면  0원을 반환 시킨다. //이거처리 안하면  데베 검색오래걸림..
				
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,itemName);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) { 
			return (int) (((double)gram/100) * rs.getInt(1));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}		
		return 0;
}
	

	public MenuDTO getMenu(String menuNum) {
		MenuDTO menu = new MenuDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM MENU WHERE menuNum =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				menu.setMenuNum(rs.getInt("menuNum"));
				menu.setUserID(rs.getString("userID"));
				menu.setMenuName(rs.getString("menuName").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				menu.setMenuRecipe(rs.getString("menuRecipe").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				menu.setMenuPrice(rs.getInt("menuPrice"));
				menu.setMenuAvailable(rs.getInt("menuAvailable"));
				
				menu.setMenuProfile(rs.getString("menuProfile"));
				menu.setMenuRealProfile("menuRealProfile");

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
		return menu;

	}
	
	public MenuDTO getMenuName(String menuName) { //데베 설계 오류,,  처음부터 menuNum이아닌 menuName으로 해야됬었다.
		MenuDTO menu = new MenuDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM MENU WHERE menuName =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				menu.setMenuNum(rs.getInt("menuNum"));
				menu.setUserID(rs.getString("userID"));
				menu.setMenuName(rs.getString("menuName").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				menu.setMenuRecipe(rs.getString("menuRecipe").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				menu.setMenuPrice(rs.getInt("menuPrice"));
				menu.setMenuAvailable(rs.getInt("menuAvailable"));
				
				menu.setMenuProfile(rs.getString("menuProfile"));
				menu.setMenuRealProfile("menuRealProfile");

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
		return menu;

	}
	
	

	public ArrayList<MenuDTO> getList() {

		ArrayList<MenuDTO> menuList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM MENU ORDER BY menuNum DESC";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			menuList = new ArrayList<MenuDTO>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MenuDTO menu = new MenuDTO();
				menu.setMenuNum(rs.getInt("menuNum"));
				menu.setUserID(rs.getString("userID"));
				menu.setMenuName(rs.getString("menuName").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				menu.setMenuRecipe(rs.getString("menuRecipe").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				menu.setMenuPrice(rs.getInt("menuPrice"));
				menu.setMenuAvailable(rs.getInt("menuAvailable")); 
				
				menu.setMenuProfile(rs.getString("menuProfile"));
				menu.setMenuRealProfile(rs.getString("menuRealProfile"));
				menuList.add(menu);
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
		return menuList;

	}
	
	
	

	public String getProfile (String menuNum) {
		//특정한 유저의 사진경로를 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT menuProfile FROM MENU WHERE menuNum =?"; 
		try {  
			
			conn= dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("menuProfile").equals("")) {
					return "http://localhost:8022/UserChat/images/icon.png";
				}
				
				return "http://localhost:8022/UserChat/upload/"+ rs.getString("menuProfile");
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
	
	public String getFile(String menuNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT menuProfile FROM MENU WHERE menuNum =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getString("menuProfile");

			}
			return "";
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
		return "";
	}
	
	public String getRealProfile(String menuNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT menuRealProfile FROM MENU WHERE menuNum =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getString("menuRealProfile");

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
		return "";
	}
	
	
	public int update(String menuNum, String menuName, String menuRecipe, String menuProfile, String menuRealProfile) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE MENU SET menuName = ? , menuRecipe =? ,menuPrice=?, menuProfile = ?, menuRealProfile = ?  WHERE menuNum = ?" ;
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuName);
			pstmt.setString(2, menuRecipe);
			pstmt.setInt(3, getMenuPrice(menuName));   //토탈 가격 가져오는 내가만든 함수 getMenuPrice(menuName)
			pstmt.setString(4, menuProfile);
			pstmt.setString(5, menuRealProfile);
			pstmt.setInt(6, Integer.parseInt(menuNum));

			return pstmt.executeUpdate(); // insert 문이므로 executequery가 아님

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류를 뜻함

	}
	
	public int delete(String menuNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "DELETE FROM MENU WHERE menuNum = ?" ;
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuNum);

			return pstmt.executeUpdate(); // insert 문이므로 executequery가 아님

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; // 데이터 베이스 오류를 뜻함

	}
	

}