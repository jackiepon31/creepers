package com.fosun.fc.projects.creepers.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstWebDriver {

    public static void main(String[] args) {

        String url = "www.baidu.com";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.navigate().to(url);
        System.out.println(Double.toString(Math.random()));
    }
}
