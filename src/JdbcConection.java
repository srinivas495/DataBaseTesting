import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class JdbcConection {

	public static void main(String[] args) throws SQLException, InterruptedException {

		String host = "localhost";
		String port = "3306";

		//"jdbc:mysql://"+host+":"+port+"/databsename"

		Connection con = DriverManager.getConnection("jdbc:mysql://"+ host +":"+ port +"/QadbtBank", "root", "9871536225sS");
		Statement s = con.createStatement();
		ResultSet rs= s.executeQuery("select * from credential where scenario = 'zerobalancecard'");

		while(rs.next()) 
		{

			String projectPath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", projectPath +"/drivers/chromedriver/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(1000);	
			driver.get("https://login.salesforce.com/?locale=eu");

			driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("username"));
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("password"));

			System.out.println(rs.getString("username"));
			System.out.println(rs.getString("password"));
		}

	}

}
