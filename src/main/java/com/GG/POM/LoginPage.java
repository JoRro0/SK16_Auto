package com.GG.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public static final String LOGIN_PAGE = "/users/login";
    public static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "User not found";

    @FindBy(css = "p.h4")
    private WebElement loginFormHeaderTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement loginFormUserNameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement loginFormPasswordInputField;
    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement loginFormRememberMeInputField;
    @FindBy(xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement loginFormRememberMeCheckBoxLabelText;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy(xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegisterPageLink;
    @FindBy(css = ".toast-message.ng-star-inserted")
    private WebElement loginFormToastMessage;
    @FindBy(xpath = "//div[contains(text(),' User not found ')]")
    private WebElement loginErrorMessage;
    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfile;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        navigateTo(LOGIN_PAGE);
    }

    public void provideUserName(String userName) {
        waitAndTypeTextInField(loginFormUserNameInputField, userName);
    }

    public void providePassword(String password) {
        waitAndTypeTextInField(loginFormPasswordInputField, password);
    }

    public void clickOnLoginButton() {
        waitAndClickOnWebElement(loginFormSubmitButton);
    }

    public void loginWithUSerAndPassword(String user, String password) {
        provideUserName(user);
        providePassword(password);
        clickOnLoginButton();
    }

    public String getLoginPageFormTitle() {
        wait.until(ExpectedConditions.visibilityOf(loginFormHeaderTitle));
        String actualTitleText = loginFormHeaderTitle.getText();

        return actualTitleText;
    }

    public String getLoginActionMessage() {
        wait.until(ExpectedConditions.visibilityOf(loginFormToastMessage));
        String msg = loginFormToastMessage.getText();
        return msg;
    }
    public boolean isLoginButtonShown() {
        return isPresented(loginFormSubmitButton);
    }
    public boolean isNavProfileShown() {
        return isPresented(navBarProfile);
    }
    public boolean isProfileLinkVisible() {
        boolean isLogInLinkVisible = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(navBarProfile)).isDisplayed();
            log.info("CONFIRMATION # The Profile link is displayed.");
            isLogInLinkVisible = true;
        } catch (TimeoutException e) {
            log.error("ERROR : The Profile link is NOT displayed!");
        }
        return isLogInLinkVisible;
    }
}
