package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ItemDAO {
	
	DataSource dataSource; 
	
	public ItemDAO() {
		
		try {
			InitialContext initCtx = new InitialContext();
		 	Context envContext = (Context)initCtx.lookup("java:/comp/env");
		 	dataSource  = (DataSource)envContext.lookup("jdbc/UserChat");
		}
		catch (Exception e) {
			e.printStackTrace( );
		}
	}
	
	public String getDate() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT current_date()";
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {  //sql ���� ����� ���� rs�� ���� �ִ� ���
				
				
				
				return rs.getString(1);  //������ ��¥�� ��ȯ
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if( rs != null)rs.close();
				if( pstmt != null)pstmt.close();
				if( conn != null)conn.close();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return ""; //������ ���̽� ������ ����
		
	}
	
	public String getItemSize() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT COUNT(*) FROM ITEM WHERE DATE(itemDate) <= DATE(subdate(now(), INTERVAL 30 DAY))";
		//������� 30�� ���� �����۵��� �� ���� 
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {  //sql ���� ����� ���� rs�� ���� �ִ� ���
				
				return rs.getString(1);  //������ ��¥�� ��ȯ
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if( rs != null)rs.close();
				if( pstmt != null)pstmt.close();
				if( conn != null)conn.close();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return ""; //������ ���̽� ������ ����
		
	}
	
	public String getItemComment(String itemName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String SQL = "SELECT itemComment FROM ITEM WHERE itemName = ?";
		
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, itemName);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {  //sql ���� ����� ���� rs�� ���� �ִ� ���
				
				return rs.getString(1);  //������ �����ȯ
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if( rs != null)rs.close();
				if( pstmt != null)pstmt.close();
				if( conn != null)conn.close();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return "-1"; //������ ���̽� ������ ����
		
	}
	
	
	public ItemDTO getItem(String itemName) {
		  //�Խñ� �����ֱ� 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM ITEM WHERE itemName = ? "  ;
		try { 
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, itemName);
			rs = pstmt.executeQuery(); 
			
		        if(rs.next()) {  
		        	ItemDTO item = new ItemDTO();
		        	
		        	item.setItemName(rs.getString(1));
		        	item.setItemComment(rs.getString(2));		        	
		        	item.setItemGram(rs.getInt(3));	        
		        	item.setItemDate(rs.getString(4));
		        	
		        	
					return item;
		        	 
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if( rs != null)rs.close();
				if( pstmt != null)pstmt.close();
				if( conn != null)conn.close();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return null; 
}
	
	public ArrayList<ItemDTO> getList() {

		ArrayList<ItemDTO> itemList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="SELECT * FROM ITEM where date(itemDate) <= date(subdate(now(), INTERVAL 30 DAY))";
		
		//30�� ���� ������DTO���� ����Ʈ 
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			
			itemList = new ArrayList<ItemDTO>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemDTO itemDTO = new ItemDTO();
				
				itemDTO.setItemName(rs.getString(1));
				itemDTO.setItemComment(rs.getString(2));		        	
				itemDTO.setItemGram(rs.getInt(3));	        
				itemDTO.setItemDate(rs.getString(4));
				
				
				itemList.add(itemDTO);
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
		return itemList;

	}
	
	
	
	
	public int write(String itemName) {   //�۾��� 
		String SQL ="SELECT itemDate FROM ITEM WHERE itemName = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM");
		try { 
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, itemName );
			rs = pstmt.executeQuery(); 
			
			 System.out.println(date.format(today).replace("/", "") + "��/");
			if(rs.next()) {  						
				if(rs.getString(1).replace("-", "").substring(0,6).equals(date.format(today).replace("/", "")) ) {
					System.out.println("����");
					return 0; //�̹� �����Ͱ� �����ϰ� ������Ʈ �޿��� �������  ������Ʈ�� ���� �ʴ´�.
				}
				
				
				int result =write2(itemName);  // �����Ͱ� �����ϳ� ������Ʈ ������ �ٸ���� -> ����
				
				if(result==-1) {  //write2 �����ͺ��̽� ����/â ���ϱ� ���°�쵵 ����
					return -3;
				}
				else if(result==-2) {  //�������� ������ ���, ���Ľ� ����� �����ΰ�� �����߻�
					return -4;
				}
				else if(result==-3) {  //�������� ������ ���, ���Ľ� ����� �����ΰ�� �����߻�
					return -5;
				}
				
				
				return 1; //����.
				
				
			}
			
			int result =write2(itemName); //�ش����� �˻������ ���°��,  ���Ľ� ����  , �Ϲ����� ����
			if(result==-1) {  //write2 �����ͺ��̽� ����/â ���ϱ� ���°�쵵 ����
				return -3;
			}
			else if(result==-2) {  //�������� ������ ���, ���Ľ� ����� �����ΰ�� �����߻�
				return -4;
			}
			else if(result==-3) {  //�������� ������ ���, ���Ľ� ����� �����ΰ�� �����߻�
				return -5;
			}
			
			
			
			
			return 1; //����
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if( rs != null)rs.close();
				if( pstmt != null)pstmt.close();
				if( conn != null)conn.close();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; //������ ���̽� ������ ����
		
		
	}
	
	public int write2(String itemName) {   
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "REPLACE INTO ITEM VALUES(?,?,?,?)"; //�߰� ������ ���� ���÷��̽��� �Ѵ�.
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
			WebScrapper web = new WebScrapper();
		
		
			web.getText(itemName, web);
		    
			if(web.price ==-10) {//   �׶��� ���ݴ����� ���°�� �ַ� �� ������ �̷�
				
				return -2;
			}
			if(web.price ==-20) {//   xpath �Ľ��� ���ϴ°��,  �ַ� SSG Ȩ������ �ҽ��ڵ尡 �ٲ��� �̷�
				
				return -3;
			}
						
			pstmt.setString(1, itemName);  //��� �̸�
			pstmt.setString(2, web.comment);    //��� ���� ��ǰ�̸�		
			pstmt.setInt(3, web.price);  //��� g�� ����
			pstmt.setString(4, getDate());  //��� �Ľ� ��¥
			return pstmt.executeUpdate();  //insert  pstmt.executeQuery(); �� �ƴ� executeUpdate()
			
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if( pstmt != null)
					pstmt.close();
				if( conn != null)
					conn.close();
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
		}
		return -1; //������ ���̽� ������ ����
		
	}
	
	public static void main(String[] args) {
		System.out.println("����ũ���� ����");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		WebScrapper web = new WebScrapper();
		
	}
	
	
}
