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
		response.getWriter().write("�̸�Ʈ�˻���� : "+itemDTO.itemComment +"\n100g�� ���� : "+itemDTO.itemGram+"��" );
		}catch(Exception e) {
			response.getWriter().write("�����ͺ��̽� ���� ��ᰡ ��ϵǾ����� �ʽ��ϴ�.\n ������ ����� ���ּ���.");
		}
		
		return ;
		
//		if(userID == null || userID.equals("")) { 
//		 response.getWriter().write("-1");
//		}
//		
//		response.getWriter().write(new UserDAO().registerCheck(userID) +"");
	}
}
