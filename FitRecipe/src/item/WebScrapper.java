package item;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebScrapper {
	
	public WebDriver driver = new FirefoxDriver();
	static String comment="";   
	static int price;
	 //����  ���ϰ�  �����۽����̸�, �׶��� ����
	
	/**
	 * Open the test website.
	 */
	public void openTestSite(String product) {
		driver.navigate().to("http://m.ssg.com/search.ssg?query=" + product);
		// ũ�Ⱑ ���� ����� Ȩ�������� ��ü, �˻����� ����
		// "http://www.ssg.com/search.ssg?target=all&query="+product+"&filterSiteNo=6001" -> pc�� ����Ʈ

	} 

	/**
	 * 
	 * @param username
	 * @param Password
	 * 
	 *                 Logins into the website, by entering provided username and
	 *                 password
	 */

	public int getText(String product, WebScrapper web) throws IOException {
		
			Long Start_time = System.currentTimeMillis();
			//�����Ͽ� ���� �ð�
			web.openTestSite(product);
			
			String parsing = driver.findElement(By.xpath("//div[@class='tx_thmb']//following::*"))
					.getAttribute("innerHTML");	
			try {
				//ssg moblie Ȩ������, �˻���� 1����  xpath 		
				String parsing5 = parsing.split("div class=\"unit_price\">")[1].split("</div>")[0].replaceAll("\\s","");
				String parsingName5 = parsing.split("<span class=\"goods_tit\">")[1].split("</span>")[0].replaceAll("\\s","");
				
				System.out.println(parsingName5);
				System.out.println(returnGram(parsing5) + "����");
				System.out.println(System.currentTimeMillis() - Start_time + "����");

				comment = parsingName5;
				price = returnGram(parsing5);
				web.closeBrowser ( );
				return 0;
			} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
				// 1��° �޴��� g�� ������ ���°��, xpath������ ��ü�� Ȯ��, �ð��� ���ɷ��� �ٸ������� g�� ������ ������ ũ�Ѹ� �ؿ´�.

				try {
					String secondParsing = driver.findElement(By.xpath("//div[@class='m_ssg_lst']//following::*")).getAttribute("innerHTML");
					String parsingName5 = secondParsing.split("<span class=\"goods_tit\">")[1].split("</span>")[0].replaceAll("\\s","");
					String secondParsing5;
					secondParsing5 = secondParsing.split("div class=\"unit_price\">")[1].split("</div>")[0]
							.replaceAll("\\s", "");
					
					System.out.println(returnGram(secondParsing5) + "����2");
					System.out.println(System.currentTimeMillis() - Start_time + "����");
				
					comment = parsingName5;
					price = returnGram(secondParsing5);
					web.closeBrowser ( );
					return 0;
					
				} catch (Exception e2) {
					// �������� Ű���带 �Է��ؼ� g ���� ��ü�� ���°��
					System.out.println(-20+"Ű���� ����");
					web.closeBrowser ( );
					price = -20;
					return -20;
				}
			}

			catch (Exception ee) {
				
				ee.printStackTrace();
				System.out.println(-30+"");
				price = -30;
				
				web.closeBrowser ( );
				
			}

			System.out.println("������ ����");
			price = -40;
			return -40;
		

	}

	public int returnGram(String parsing) {
		String text = parsing.split("��:")[1].split("��")[0];

		text = text.replaceAll(",", "");  //1,450 �� ���� ,���� 
	
		if (parsing.contains("100g��")) {

			return Integer.parseInt(text);
		}
		if (parsing.contains("100ml��")) {

			return Integer.parseInt(text);
		}
		if (parsing.contains("1kg��")) {

			return (int) (Integer.parseInt(text) * 0.1);
		}

		if (parsing.contains("10g��")) {

			return Integer.parseInt(text) * 10;
		}
		if (parsing.contains("10ml��")) {

			return Integer.parseInt(text) * 10;
		}
		if (parsing.contains("1ea��")) {

			return Integer.parseInt(text) * 1;
		}
	
		
		
		
		System.out.println("return gram ����");
		return -10;
	}

	/**
	 * Saves the screenshot
	 * 
	 * @throws IOException
	 */
	public void closeBrowser() {
		driver.close();
	}

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		//����̹� ����, ������ �����Լ��� ���⿡ �� ��ġ ����,  �������Լ�, �ٸ� �Լ����� ���� �ȵ�

		
	}

}