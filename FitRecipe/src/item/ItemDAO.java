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
			
			if(rs.next()) {  //sql 쿼리 결과를 담은 rs에 값이 있는 경우
				
				
				
				return rs.getString(1);  //현재의 날짜를 반환
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
		return ""; //데이터 베이스 오류를 뜻함
		
	}
	
	public String getItemSize() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT COUNT(*) FROM ITEM WHERE DATE(itemDate) <= DATE(subdate(now(), INTERVAL 30 DAY))";
		//등록한지 30일 지난 아이템들의 총 개수 
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {  //sql 쿼리 결과를 담은 rs에 값이 있는 경우
				
				return rs.getString(1);  //현재의 날짜를 반환
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
		return ""; //데이터 베이스 오류를 뜻함
		
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
			
			if(rs.next()) {  //sql 쿼리 결과를 담은 rs에 값이 있는 경우
				
				return rs.getString(1);  //아이템 설명반환
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
		return "-1"; //데이터 베이스 오류를 뜻함
		
	}
	
	
	public ItemDTO getItem(String itemName) {
		  //게시글 보여주기 
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
		
		//30일 지난 아이템DTO들의 리스트 
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
	
	
	
	
	public int write(String itemName) {   //글쓰기 
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
			
			 System.out.println(date.format(today).replace("/", "") + "ㅇ/");
			if(rs.next()) {  						
				if(rs.getString(1).replace("-", "").substring(0,6).equals(date.format(today).replace("/", "")) ) {
					System.out.println("종료");
					return 0; //이미 데이터가 존재하고 업데이트 달월이 같은경우  업데이트를 하지 않는다.
				}
				
				
				int result =write2(itemName);  // 데이터가 존재하나 업데이트 월수가 다른경우 -> 갱신
				
				if(result==-1) {  //write2 데이터베이스 오류/창 급하기 끄는경우도 포함
					return -3;
				}
				else if(result==-2) {  //실질적인 아이템 등록, 웹파싱 결과가 에러인경우 에러발생
					return -4;
				}
				else if(result==-3) {  //실질적인 아이템 등록, 웹파싱 결과가 에러인경우 에러발생
					return -5;
				}
				
				
				return 1; //종료.
				
				
			}
			
			int result =write2(itemName); //해당쿼리 검색결과가 없는경우,  웹파싱 실행  , 일반적인 실행
			if(result==-1) {  //write2 데이터베이스 오류/창 급하기 끄는경우도 포함
				return -3;
			}
			else if(result==-2) {  //실질적인 아이템 등록, 웹파싱 결과가 에러인경우 에러발생
				return -4;
			}
			else if(result==-3) {  //실질적인 아이템 등록, 웹파싱 결과가 에러인경우 에러발생
				return -5;
			}
			
			
			
			
			return 1; //종료
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
		return -1; //데이터 베이스 오류를 뜻함
		
		
	}
	
	public int write2(String itemName) {   
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "REPLACE INTO ITEM VALUES(?,?,?,?)"; //추가 갱신을 위해 리플레이스로 한다.
		try {
			conn= dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
			WebScrapper web = new WebScrapper();
		
		
			web.getText(itemName, web);
		    
			if(web.price ==-10) {//   그람당 가격단위가 없는경우 주로 막 눌러서 이럼
				
				return -2;
			}
			if(web.price ==-20) {//   xpath 파싱을 못하는경우,  주로 SSG 홈페이지 소스코드가 바뀐경우 이럼
				
				return -3;
			}
						
			pstmt.setString(1, itemName);  //재료 이름
			pstmt.setString(2, web.comment);    //재료 실제 제품이름		
			pstmt.setInt(3, web.price);  //재료 g당 가격
			pstmt.setString(4, getDate());  //재료 파싱 날짜
			return pstmt.executeUpdate();  //insert  pstmt.executeQuery(); 이 아닌 executeUpdate()
			
			
			
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
		return -1; //데이터 베이스 오류를 뜻함
		
	}
	
	public static void main(String[] args) {
		System.out.println("웹스크리퍼 가동");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		WebScrapper web = new WebScrapper();
		
	}
	
	
}
