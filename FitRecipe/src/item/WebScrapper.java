package item;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebScrapper {
	
	public WebDriver driver = new FirefoxDriver();
	static String comment="";   
	static int price;
	 //최종  리턴값  아이템실제이름, 그람당 가격
	
	/**
	 * Open the test website.
	 */
	public void openTestSite(String product) {
		driver.navigate().to("http://m.ssg.com/search.ssg?query=" + product);
		// 크기가 적은 모바일 홈페이지로 대체, 검색포털 설정
		// "http://www.ssg.com/search.ssg?target=all&query="+product+"&filterSiteNo=6001" -> pc용 사이트

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
			//셀레니움 시작 시간
			web.openTestSite(product);
			
			String parsing = driver.findElement(By.xpath("//div[@class='tx_thmb']//following::*"))
					.getAttribute("innerHTML");	
			try {
				//ssg moblie 홈페이지, 검색결과 1위의  xpath 		
				String parsing5 = parsing.split("div class=\"unit_price\">")[1].split("</div>")[0].replaceAll("\\s","");
				String parsingName5 = parsing.split("<span class=\"goods_tit\">")[1].split("</span>")[0].replaceAll("\\s","");
				
				System.out.println(parsingName5);
				System.out.println(returnGram(parsing5) + "원임");
				System.out.println(System.currentTimeMillis() - Start_time + "세컨");

				comment = parsingName5;
				price = returnGram(parsing5);
				web.closeBrowser ( );
				return 0;
			} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
				// 1번째 메뉴에 g당 가격이 없는경우, xpath범위를 전체로 확장, 시간이 더걸려도 다른순위에 g당 가격이 있으면 크롤링 해온다.

				try {
					String secondParsing = driver.findElement(By.xpath("//div[@class='m_ssg_lst']//following::*")).getAttribute("innerHTML");
					String parsingName5 = secondParsing.split("<span class=\"goods_tit\">")[1].split("</span>")[0].replaceAll("\\s","");
					String secondParsing5;
					secondParsing5 = secondParsing.split("div class=\"unit_price\">")[1].split("</div>")[0]
							.replaceAll("\\s", "");
					
					System.out.println(returnGram(secondParsing5) + "원임2");
					System.out.println(System.currentTimeMillis() - Start_time + "세컨");
				
					comment = parsingName5;
					price = returnGram(secondParsing5);
					web.closeBrowser ( );
					return 0;
					
				} catch (Exception e2) {
					// 쓸때없는 키워드를 입력해서 g 가격 자체가 없는경우
					System.out.println(-20+"키워드 에러");
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

			System.out.println("셀리늄 에러");
			price = -40;
			return -40;
		

	}

	public int returnGram(String parsing) {
		String text = parsing.split("당:")[1].split("원")[0];

		text = text.replaceAll(",", "");  //1,450 원 같은 ,제거 
	
		if (parsing.contains("100g당")) {

			return Integer.parseInt(text);
		}
		if (parsing.contains("100ml당")) {

			return Integer.parseInt(text);
		}
		if (parsing.contains("1kg당")) {

			return (int) (Integer.parseInt(text) * 0.1);
		}

		if (parsing.contains("10g당")) {

			return Integer.parseInt(text) * 10;
		}
		if (parsing.contains("10ml당")) {

			return Integer.parseInt(text) * 10;
		}
		if (parsing.contains("1ea당")) {

			return Integer.parseInt(text) * 1;
		}
	
		
		
		
		System.out.println("return gram 에러");
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
		//드라이버 설정, 무조건 메인함수인 여기에 현 위치 고정,  생성자함수, 다른 함수에서 설정 안됨

		
	}

}