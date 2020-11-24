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
			pstmt.setInt(4, getMenuPrice(menuName));   //��Ż ���� �������� �������� �Լ� getMenuPrice(menuName)
			pstmt.setInt(5, 0);
			pstmt.setString(6, menuProfile);
			pstmt.setString(7, menuRealProfile);
			

			return pstmt.executeUpdate(); // insert ���̹Ƿ� executequery�� �ƴ�

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
		return -1; // ������ ���̽� ������ ����

	}
	
public int getMenuPrice(String menuName) {  
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String SQL ="SELECT * FROM COMPONENT WHERE comName = ? ";  //�ش� menuName�� ��� ������Ҹ� �ҷ��´�. 
			
				
		
			
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
				return result; //�� ���� ��ȯ 
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

public int getMenuPrice(String itemName, int gram) {  //�����ε�
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="SELECT itemGram FROM ITEM WHERE itemName = ? ";
		
	
		try {
			if(itemName==null)
				return 0; // �������� null��, �� ���ٸ�  0���� ��ȯ ��Ų��.
			if(itemName.equals(""))
				return 0; // �������� ���鰪, �� ���ٸ�  0���� ��ȯ ��Ų��. //�̰�ó�� ���ϸ�  ���� �˻������ɸ�..
				
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
	
	public MenuDTO getMenuName(String menuName) { //���� ���� ����,,  ó������ menuNum�̾ƴ� menuName���� �ؾ߉����.
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
		//Ư���� ������ ������θ� ��ȯ
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
	return "http://localhost:8022/UserChat/images/icon.png"; //������ ���̽� ����.. �ε� �׳� �⺻������ ��ȯ

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
			pstmt.setInt(3, getMenuPrice(menuName));   //��Ż ���� �������� �������� �Լ� getMenuPrice(menuName)
			pstmt.setString(4, menuProfile);
			pstmt.setString(5, menuRealProfile);
			pstmt.setInt(6, Integer.parseInt(menuNum));

			return pstmt.executeUpdate(); // insert ���̹Ƿ� executequery�� �ƴ�

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
		return -1; // ������ ���̽� ������ ����

	}
	
	public int delete(String menuNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "DELETE FROM MENU WHERE menuNum = ?" ;
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, menuNum);

			return pstmt.executeUpdate(); // insert ���̹Ƿ� executequery�� �ƴ�

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
		return -1; // ������ ���̽� ������ ����

	}
	

}