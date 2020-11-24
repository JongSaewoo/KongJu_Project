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
				
				if (i==0) {  //ó���ø�... getItemSize���� ���� ��Ӿ��ϱ�..
					size = Integer.parseInt(itemDAO.getItemSize());
				}
				
				int result =itemDAO.write(itemDTO.getItemName());
				

				System.out.println("������"+ i+"/"+ size+"�ܰ�");
				
			}
			
			if(size==0) {
				response.getWriter().write("���� �� �ڷᰡ �����ϴ�");
			}else {
			  response.getWriter().write("��"+size+"��  ���� �Ϸ�");
			}
			
		
		}catch(Exception e) {
			response.getWriter().write("item ������ ���̽� ����");
		}
		
		return ;
		
//		if(userID == null || userID.equals("")) { 
//		 response.getWriter().write("-1");
//		}
//		
//		response.getWriter().write(new UserDAO().registerCheck(userID) +"");
	}
}
