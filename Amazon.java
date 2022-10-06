package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//01. Launch Chome
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver =  new ChromeDriver();
		//02 Load https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//03 Type "Bags" in the Search box
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Bags");
		//04 Choose the displayed item in the result with bags for boys
		driver.findElement(By.xpath("//div[@aria-label='bags for boys']")).click();
		//05 Print the total number of results (like 30000)
		String Totalitems = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']")).getText();
		System.out.println(Totalitems);
		//06) Select the first 2 brands in the left menu
		driver.findElement(By.xpath("(//div[contains(@class,'a-checkbox a-checkbox-fancy')]//i)[3]")).click();
		driver.findElement(By.xpath("//li[@id='p_89/Generic']/span[1]/a[1]/div[1]/label[1]/i[1]")).click();
		//07 Confirm the results have got reduced like 5000 &30,000 
		String Totalitems_compare = driver.findElement(By.xpath("//span[text()='1-48 of over 5,000 results for']")).getText();
		
		if(!Totalitems.equals(Totalitems_compare))
		{
			System.out.println("results have got reduced like 5000");
		}
		
		//08) Choose New Arrivals (Sort)
		driver.findElement(By.xpath("//span[text()='Featured']")).click();
	
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Newest Arrivals']")).click();
	
		//09) Print the first resulting bag info (name, discounted price)
		String Details = driver.findElement(By.xpath("(//span[contains(@class,'a-size-base-plus a-color-base')])[2]")).getText();
		System.out.println(Details);
		//10) Take screenshot and close
		File source = driver.getScreenshotAs(OutputType.FILE);
		//Create physicalFile
		File dest=new File("./snaps/screenshort.png");
		//copy the source to destination
		FileUtils.copyFile(source, dest);
		driver.quit();

}
}
