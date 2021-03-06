package framework.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    static WebDriver driver;

    public static String driverType;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void startAUT() throws MalformedURLException {
        if (driverType.equals("android")) {
            DesiredCapabilities androidCapabilities = new DesiredCapabilities();
            androidCapabilities.setCapability("deviceName", DriverConfig.ANDROID_EMULATOR_NAME);
            androidCapabilities.setCapability("platformName", DriverConfig.ANDROID_PLATFORM_NAME);
            androidCapabilities.setCapability("app", System.getProperty("user.dir") + DriverConfig.ANDROID_APP_PATH);
            androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, DriverConfig.ANDROID_PACKAGE_NAME);
            androidCapabilities.setCapability(MobileCapabilityType.NO_RESET, DriverConfig.ANDROID_APP_NO_RESET);
            androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, DriverConfig.ANDROID_ACTIVITY_NAME);
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), androidCapabilities);
            WebDriverWait androidWait = new WebDriverWait(driver, 30);
        } else if (driverType.equals("ios")) {
            DesiredCapabilities iosCapabilities = new DesiredCapabilities();
            iosCapabilities.setCapability("deviceName", DriverConfig.IOS_SIMULATOR_NAME);
            iosCapabilities.setCapability("platformName", DriverConfig.IOS_PLATFORM_NAME);
            iosCapabilities.setCapability("platformVersion", DriverConfig.IOS_PLATFORM_VERSION);
            iosCapabilities.setCapability("automationName", DriverConfig.IOS_AUTOMATION_NAME);
            iosCapabilities.setCapability("udid", DriverConfig.IOS_SIMULATOR_UDID);
            iosCapabilities.setCapability(MobileCapabilityType.NO_RESET, DriverConfig.IOS_APP_NO_RESET);
            iosCapabilities.setCapability("app", System.getProperty("user.dir") + DriverConfig.IOS_APP_PATH);
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), iosCapabilities);
            WebDriverWait iOSWait = new WebDriverWait(driver, 30);
        } else if (driverType.equals("web")) {
            String os = System.getProperty("os.name");
            if (os.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + DriverConfig.CHROME_DRIVER_MAC);
            } else if (os.contains("windows")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + DriverConfig.CHROME_DRIVER_WINDOWS);
            } else if (os.contains("unix")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + DriverConfig.CHROME_DRIVER_LINUX);
            }

            String strBrowserType = System.getProperty("browser");
            if(strBrowserType.toLowerCase().equals("firefox")) {
                driver = new FirefoxDriver();
            }
            else if(strBrowserType.toLowerCase().equals("chrome")) {
                driver = new ChromeDriver();
            }
            driver.get(DriverConfig.APP_URL);
        } else {
            System.out.println("platform is not passed");
        }
    }
}
