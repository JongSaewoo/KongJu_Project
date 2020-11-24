package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	DataSource dataSource;

	public BoardDAO() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envContext = (Context) initCtx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/UserChat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int write(String userID, String boardTitle, String boardContent, String boardFile, String boardRealFile, int boarPass) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO BOARD SELECT ?, IFNULL((SELECT MAX(boardID) +1 FROM BOARD),1),?, ?,now(),0, ?,?,IFNULL((SELECT MAX(boardGroup) + 1 FROM BOARD) ,0),0,0,1,?";
		// 오라클 INSERT INTO BOARD VALUES (?, NVL((SELECT MAX(boardID) + 1 FROM BOARD),
		// 1),?, ?, sysdate, 0, ?, ?, NVL((SELECT MAX(boardGroup) + 1 FROM BOARD), 0),
		// 0, 0)
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, boardContent);
			pstmt.setString(4, boardFile);
			pstmt.setString(5, boardRealFile);
			pstmt.setInt(6, boarPass);

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

	public BoardDTO getBoard(String boardID) {
		BoardDTO board = new BoardDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM BOARD WHERE boardID =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setUserID(rs.getString("userID"));
				board.setBoardID(rs.getInt("boardID"));							
				board.setBoardTitle(rs.getString("boardTitle").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				
				
				
				
				
				board.setBoardContent(rs.getString("boardContent"));
				board.setBoardDate(rs.getString("boardDate").substring(0, 11));
				board.setBoardHit(rs.getInt("boardHit"));
				board.setBoardFile(rs.getString("boardFile"));
				board.setBoardRealFile(rs.getString("boardRealFile"));
				board.setBoardGroup(rs.getInt("boardGroup"));
				board.setBoardSequence(rs.getInt("boardSequence"));
				board.setBoardLevel(rs.getInt("boardLevel"));
				board.setBoardAvailable(rs.getInt("boardAvailable"));
				board.setBoardPass(rs.getInt("boardPass"));
				
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
		return board;

	}

	public ArrayList<BoardDTO> getList(String pageNumber) {

		ArrayList<BoardDTO> boardList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM BOARD WHERE boardGroup  > (SELECT MAX(boardGroup) FROM BOARD) -? AND boardGroup <= (SELECT MAX(boardGroup)FROM Board) -? ORDER BY boardGroup DESC, boardSequence ASC";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(pageNumber)* 10);
			pstmt.setInt(2, (Integer.parseInt(pageNumber)-1)* 10);
			boardList = new ArrayList<BoardDTO>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setUserID(rs.getString("userID"));
				board.setBoardID(rs.getInt("boardID"));
				board.setBoardTitle(rs.getString("boardTitle").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				board.setBoardContent(rs.getString("boardContent"));
				
				//.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll(">", "&gt;").replaceAll("\n", "<br>")
				board.setBoardDate(rs.getString("boardDate").substring(0, 11));
				board.setBoardHit(rs.getInt("boardHit"));
				board.setBoardFile(rs.getString("boardFile"));
				board.setBoardRealFile(rs.getString("boardRealFile"));
				board.setBoardGroup(rs.getInt("boardGroup"));
				board.setBoardSequence(rs.getInt("boardSequence"));
				board.setBoardLevel(rs.getInt("boardLevel"));
				board.setBoardAvailable(rs.getInt("boardAvailable"));
				board.setBoardPass(rs.getInt("boardPass"));
				boardList.add(board);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(boardList.size());
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
		return boardList;

	}

	public int hit(String boardID) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE BOARD SET boardHit = boardHit+1 WHERE boardID = ?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardID);

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

	public String getFile(String boardID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT boardFile FROM BOARD WHERE boardID =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardID);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getString("boardFile");

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
	
	public boolean nextPage(String pageNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM BOARD WHERE boardGroup >=?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(pageNumber)*10);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return true;  // 다음게시글이 한개라도 존재한다면 트루값을 반환한다.

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
		return false;
	}
	
	public int targetPage(String pageNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT COUNT(boardGroup) FROM BOARD WHERE boardGroup >? ";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (Integer.parseInt(pageNumber)-1)*10);  //페이지 넘버에 -1 뺴고 10을 곱함
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getInt(1)/10;  

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
		return 0;
	}
	
	
	public String getRealFile(String boardID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT boardRealFile FROM BOARD WHERE boardID =?";
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardID);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getString("boardRealFile");

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
	
	
	public int update(String boardID, String boardTitle, String boardContent, String boardFile, String boardRealFile) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE BOARD SET boardTitle = ? , boardContent =? , boardFile = ?, boardRealFile =? WHERE boardID = ?" ;
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardFile);
			pstmt.setString(4, boardRealFile);
			pstmt.setInt(5, Integer.parseInt(boardID));

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
	
	public int delete(String boardID) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE BOARD set boardAvailable = 0 WHERE boardID = ?" ;
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardID);

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
	
	
	public int reply(String userID, String boardTitle, String boardContent, String boardFile, String boardRealFile, BoardDTO parent) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO BOARD SELECT ?, IFNULL((SELECT MAX(boardID) +1 FROM BOARD),1),?, ?,now(),0, ?,?,? ,?,?,1,0";

		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, boardContent);
			pstmt.setString(4, boardFile);
			pstmt.setString(5, boardRealFile);
			pstmt.setInt(6, parent.getBoardGroup());  //댓글은 부모와 동일한 그룹을 가진다
			pstmt.setInt(7, parent.getBoardSequence()+1);
			pstmt.setInt(8, parent.getBoardLevel()+1);  //부모글보다 들여쓰기가 되야함

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
	
	
	public int replyUpdate(BoardDTO parent) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE BOARD SET boardSequence = boardSequence +1 WHERE  boardGroup =? AND boardSequence > ?" ;
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, parent.getBoardGroup());
			pstmt.setInt(2, parent.getBoardSequence());
			
		

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
