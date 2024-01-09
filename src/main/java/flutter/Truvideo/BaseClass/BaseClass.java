package flutter.Truvideo.BaseClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import flutter.Truvideo.Pages.DealerCodePage;
import flutter.Truvideo.Utils.CapabilityReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
public class BaseClass {
	public static AppiumDriver driver;
	public static WebDriver webDriver;
	AppiumDriverLocalService service;
	public Logger log = LogManager.getLogger(this.getClass().getName());
	
	public static String userForLogin_Order ="RahulTest test";
	public static String userForLogin_Sales ="disha gupta";
	public static String capabilityName = System.getProperty("capabilityName", "Galaxy S21 5G");
	public static String browserName = System.getProperty("browser", "Chrome");
	public static String applicationBuild = "1.5.20(949)";
	public static String opeartingSystem;

	public AppiumDriver setUpApplication() throws MalformedURLException, Exception {
		System.out.println(capabilityName);
		startAppiumService();
		try {
			// driver = new AppiumDriver(new
			 //URL("http://127.0.0.1:4723"),CapabilityReader.getDesiredCapabilities(capabilityName,"./Capabilities.json"));
			driver = new AppiumDriver(service,
					CapabilityReader.getDesiredCapabilities(capabilityName, "./Capabilities.json"));
			log.info("Capability file read....");
		} catch (Exception e) {
			log.info("Error in Reading Capabilities from Json File ");
			e.printStackTrace();
		}
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			log.info("Connected to Appium Server, Launching Application. . .");
			return driver;
		} else {
			log.error("Driver is null. Unable to connect to Appium Server or Launch Application.");
		}
		opeartingSystem=driver.getCapabilities().getPlatformName().toString();
		return driver;
	}

	public void tearDown() {
		driver.quit();
	}
	
	public static WebDriver setDashboardBrowser() {
		if (browserName.equalsIgnoreCase("chrome")) {
			webDriver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			webDriver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			webDriver = new SafariDriver();
		}
		webDriver.get("https://rc.truvideo.com/login");
		webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		webDriver.manage().window().maximize();
		return webDriver;
	}

	private void startAppiumService() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingAnyFreePort();
		builder.withArgument(GeneralServerFlag.USE_PLUGINS,"element-wait");
		//builder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		//service.clearOutPutStreams();
	}
	
	public DealerCodePage loadDealerCodePage() {
		DealerCodePage dealerCodePage=new DealerCodePage(driver);
		return dealerCodePage;
	}
	
}
