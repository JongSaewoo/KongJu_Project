package item;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/ItemListServlet")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		
		int size=0;
	
		
		try {
			
			ArrayList<ItemDTO> itemList = new ItemDAO().getList();
			
			for (int i= 0 ; i< itemList.size(); i++) {
				ItemDTO itemDTO = itemList.get(i);
				ItemDAO itemDAO= new ItemDAO();
				
				if (i==0) {  //처음시만... getItemSize쓰면 쿼리 계속쓰니까..
					size = Integer.parseInt(itemDAO.getItemSize());
				}
				
				int result =itemDAO.write(itemDTO.getItemName());
				

				System.out.println("진행중"+ i+"/"+ size+"단계");
				
			}
			
			if(size==0) {
				response.getWriter().write("갱신 할 자료가 없습니다");
			}else {
			  response.getWriter().write("총"+size+"개  갱신 완료");
			}
			
		
		}catch(Exception e) {
			response.getWriter().write("item 데이터 베이스 오류");
		}
		
		return ;
		
//		if(userID == null || userID.equals("")) { 
//		 response.getWriter().write("-1");
//		}
//		
//		response.getWriter().write(new UserDAO().registerCheck(userID) +"");
	}
}
