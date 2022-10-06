package marathon2;

import java.io.File;
import java.time.Duration;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookMyShow {

	public static void main(String[] args) throws InterruptedException, IOException {
		//01. Launch Chome
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver =  new ChromeDriver(options);
		//02 Load https://www.amazon.in/
		driver.get("https://in.bookmyshow.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//03 Type the city as "Hyderabad" in Select City
		WebElement cityname = driver.findElement(By.xpath("//input[@placeholder='Search for your city']"));
		Thread.sleep(3000);
		cityname.sendKeys("Hyderabad");
		driver.findElement(By.xpath("//span[@class='sc-fihHvN fUfZof']")).click();
	
		//04) Confirm the URL has got loaded with Hyderabad 
		String Url = driver.getCurrentUrl();
		System.out.println(Url);
		//05 Search for your favorite movie 
		 driver.findElement(By.xpath("//a[@href='/explore/movies-hyderabad']")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//img[@alt='Ponniyin Selvan - Part 1']")).click();
		// 06 Click Book Tickets 
		 driver.findElement(By.xpath("//span[text()='Book tickets']")).click();
		  driver.findElement(By.xpath("(//div[@class='sc-vhz3gb-3 dRokFO'])[2]")).click();
		 //07 Print the name of the theater displayed first
		  String text = driver.findElement(By.xpath("//a[@class='__venue-name']")).getText();
		  System.out.println(text);
		  //08 Click on the info of the theater   
		  driver.findElement(By.xpath("//div[@class='venue-info-text']")).click();
		  //09 Confirm if there is a parking facility in the theater
		  String parking = driver.findElement(By.xpath("//div[text()='            Parking Facility        ']")).getText();
		  if (parking.contains("Parking Facility")) {
			  System.out.println("Parking Facility is available");
		  }
		  else
		  {
			  System.out.println("Parking Facility is not available");
		  }
		 //10) Close the theater popup
		 driver.findElement(By.xpath("//div[@class='cross-container']")).click();
		  //11) Click on the first green (available) timing
		  driver.findElement(By.xpath("(//div[@class='__details']//div)[3]")).click();
		  //12) Click Accept
		  driver.findElement(By.xpath("//div[@id='btnPopupAccept']")).click();
		  //13) Choose 1 Seat and Click Select Seats
		  driver.findElement(By.xpath("//li[@id='pop_1']")).click();
		  driver.findElement(By.xpath("//div[@id='proceed-Qty']")).click();
		  //14) Choose any available ticket and Click Pay
		  driver.findElement(By.xpath("//div[@id='B_5_0215']/a[1]")).click();
		  driver.findElement(By.xpath("//div[contains(@class,'__seat-action btn-bar')]//a[1]")).click();
		  //15) Print the sub total
		  String subTotal = driver.findElement(By.xpath("//span[@id='subTT']")).getText();
		  System.out.println(subTotal);
		  // 16) Take screenshot and close browser
		  File source = driver.getScreenshotAs(OutputType.FILE);
		  //Create physicalFile
		  File dest=new File("./snaps/screenshort.png");
		  //copy the source to destination
		  FileUtils.copyFile(source, dest);
		  driver.quit();
		
		    
		  
		  
		  
		  
		    
		}
		
		
		
		
	}


