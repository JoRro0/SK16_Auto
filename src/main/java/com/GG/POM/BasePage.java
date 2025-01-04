package com.GG.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    final String BASE_URL = "http://training.skillo-bg.com:4300";
    WebDriver driver;
    WebDriverWait wait;
    Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitAndClickOnWebElement(WebElement elm) {
        wait.until(ExpectedConditions.visibilityOf(elm));
        wait.until(ExpectedConditions.elementToBeClickable(elm));
        elm.click();
        waitPageTobeFullyLoaded();
    }

    public void waitAndTypeTextInField(WebElement textField, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(textField));
        textField.clear();
        textField.sendKeys(inputText);

        waitPageTobeFullyLoaded();
    }

    public String requestedUrl(String pageSuffix ) {
        return BASE_URL+pageSuffix;
    }

    public void navigateTo(String pageURLSuffix) {
        String currentURL = BASE_URL + pageURLSuffix;

        driver.get(currentURL.toLowerCase());
        log.info("CONFIRM # The user has navigated to: " +currentURL);

        waitPageTobeFullyLoaded();
    }

    public boolean isURLLoaded(String pageURL) {
        waitPageTobeFullyLoaded();
        return wait.until(ExpectedConditions.urlContains(pageURL));
    }

    public void waitPageTobeFullyLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public boolean isPresented(WebElement elm) {
        boolean isWebElmShown = false;
        String li = locatorInfo(elm);

        log.info("ACTION @ The user is verifying if the web element with locator info: " + li);
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            log.info("# SHOWN  # Web element is shown with locator info" + li);
            isWebElmShown = true;
        } catch (TimeoutException e) {
            log.error("# NOT SHOWN ! # Web element is NOT shown with locator info" + li);
            isWebElmShown = false;
        }
        return isWebElmShown;
    }

    private String locatorInfo(WebElement elm){
        String[] rawWebElmInfo = elm.toString().split("->");
        String[] webElmInfo = rawWebElmInfo[1].split(":");
        String locatorStrategy = webElmInfo[0];
        String locatorExpression = webElmInfo[1];
        String info  = "LOCATOR STRATEGY BY: "+locatorStrategy.toUpperCase()+" LOCATOR EXPRESSION: "+locatorExpression;
        return  info;
    }

}