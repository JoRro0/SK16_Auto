package com.GG.POM;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {
    public static final String HOME_PAGE_URL = "/posts/all";

    @FindBy(id = "nav-link-home")
    private WebElement navBarHome;
    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPost;
    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfile;
    @FindBy(id = "nav-link-login")
    private WebElement navBarLogin;
    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-lg']")
    private WebElement navBarLogOut;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        navigateTo(HOME_PAGE_URL);
    }

    public boolean isNavHomeShown() {
        return isPresented(navBarHome);
    }

    public boolean isNavLoginShown() {
        return isPresented(navBarLogin);
    }

    public void clickOnNavBarLogin() {
        waitAndClickOnWebElement(navBarLogin);
    }

    public void clickOnNavBarProfile() {
        waitAndClickOnWebElement(navBarProfile);
    }

    public void clickOnNavBarNewPost() {
        waitAndClickOnWebElement(navBarNewPost);
    }

    public boolean isNavProfileShown() {
        return isPresented(navBarProfile);
    }

    public boolean isNavNewPostShown() {
        return isPresented(navBarNewPost);
    }

    public boolean isNavLogInLinkVisible() {
        boolean isLogInLinkVisible = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(navBarLogin)).isDisplayed();
            log.info("CONFIRMATION # The LogIn link is displayed.");
            isLogInLinkVisible = true;
        } catch (TimeoutException e) {
            log.error("ERROR : The LogIn link is NOT displayed!");
        }
        return isLogInLinkVisible;
    }
    public void clickOnLogOutButton() {
        waitAndClickOnWebElement(navBarLogOut);
    }

    public boolean isNavLogOutLinkShown() {
        return isPresented(navBarLogOut);
    }
}