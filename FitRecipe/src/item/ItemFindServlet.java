package item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ItemFindServlet")
public class ItemFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		
		if(userID == null || userID.trim().equals("")) { 
		 response.getWriter().write("-1");
		 return;
		}
		
		ItemDAO itemDAO = new ItemDAO();
		ItemDTO itemDTO = itemDAO.getItem(userID);
		
		try {
		response.getWriter().write("이마트검색결과 : "+itemDTO.itemComment +"\n100g당 가격 : "+itemDTO.itemGram+"원" );
		}catch(Exception e) {
			response.getWriter().write("데이터베이스 내에 재료가 등록되어있지 않습니다.\n 아이템 등록을 해주세요.");
		}
		
		return ;
		
//		if(userID == null || userID.equals("")) { 
//		 response.getWriter().write("-1");
//		}
//		
//		response.getWriter().write(new UserDAO().registerCheck(userID) +"");
	}
}
