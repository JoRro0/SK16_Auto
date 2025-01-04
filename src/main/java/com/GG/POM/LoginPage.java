package com.GG.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
   public static final String LOGIN_PAGE = "/users/login";

    @FindBy(css = "p.h4")
    private WebElement loginFormHeaderTitle;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement loginFormUserNameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement loginFormPasswordInputField;
    @FindBy (xpath = "//span[contains(text(),'Remember me')]")
    private WebElement loginFormRememberMeInputField;
    @FindBy (xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement loginFormRememberMeCheckBoxLabelText;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy (xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegisterPageLink;
    @FindBy (css = ".toast-message.ng-star-inserted")
    private WebElement loginFormToastMessage;
    @FindBy (xpath = "//div[contains(text(),' User not found ')]")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToLoginPage(){
        navigateTo(LOGIN_PAGE);
    }

    public void provideUserName(String userName) {
        isPresented(loginFormUserNameInputField);
        waitAndTypeTextInField(loginFormUserNameInputField,userName);
    }

    public void providePassword(String password) {
        isPresented(loginFormUserNameInputField);
        waitAndTypeTextInField(loginFormPasswordInputField,password);
    }

    public void clickOnLoginButton(){
        waitAndClickOnWebElement(loginFormSubmitButton);
    }

    public void loginWithUSerAndPassword(String user, String password){
        provideUserName(user);
        providePassword(password);
        clickOnLoginButton();
    }

    public String getLoginPageFormTitle(){
        wait.until(ExpectedConditions.visibilityOf(loginFormHeaderTitle));
        String actualTitleText = loginFormHeaderTitle.getText();

        return actualTitleText;
    }

    public String getLoginActionMessage(){
        wait.until(ExpectedConditions.visibilityOf(loginFormToastMessage));
        String msg = loginFormToastMessage.getText();
        return msg;
    }

}
