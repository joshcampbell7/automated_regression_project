package com.automation.api.utils;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class DriverUtils {
    @Getter
    @Setter
    private WebDriver driver;
    public WebDriver initDriver(){
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        setDriver(driver);
        return getDriver();
    }

    public WebDriver getChromeDriver(){
        return getDriver();
    }
}
