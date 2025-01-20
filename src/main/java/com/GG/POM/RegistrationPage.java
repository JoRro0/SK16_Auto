package com.GG.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage  extends BasePage {
    //1. CONST
    public static final String REGISTARATION_PAGE_URL = "/users/register";
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement userNameInputField;
    @FindBy(xpath = "//input[@placeholder='email']")
    private WebElement emailInputField;
    @FindBy(xpath = "//input[@placeholder='Birth date']")
    private WebElement birthDateInputField;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInputField;
    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement confirmPasswordInputField;
    @FindBy(xpath = "//textarea[@placeholder='Public info']")
    private WebElement publicInfoInputField;
    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;
    @FindBy(xpath = "//h4[text()='Sign up']")
    private WebElement registrationPageTitle;
    @FindBy(xpath = "//div[text()=' Successful register! ']")
    private WebElement registerMSG;
    @FindBy(id = "nav-link-login")
    private WebElement navBarLogin;

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void navigateToRegPage(){
        navigateTo(REGISTARATION_PAGE_URL);
    }

    public void inputUserName(String userName){
        isPresented(userNameInputField);
        waitAndTypeTextInField(userNameInputField,userName);
    }
    public void inputEmail(String email){
        isPresented(emailInputField);
        waitAndTypeTextInField(emailInputField,email);
    }
    public void inputBirthDate(String birthDate){
        isPresented(birthDateInputField);
        waitAndTypeTextInField(birthDateInputField,birthDate);
    }
    public void inputPassword(String password){
        isPresented(passwordInputField);
        waitAndTypeTextInField(passwordInputField,password);
    }
    public void inputConfirmPassword(String confirmPassword){
        isPresented(confirmPasswordInputField);
        waitAndTypeTextInField(confirmPasswordInputField,confirmPassword);
    }
    public void inputPublicInfo(String publicInfo){
        isPresented(publicInfoInputField);
        waitAndTypeTextInField(publicInfoInputField,publicInfo);
    }
    public void clickOnRegistrationFormSubmitButton(){
        isPresented(signInButton);
        waitAndClickOnWebElement(signInButton);
    }
    public boolean isRegistrationTitleFormTitleShown() {
        return isPresented(registrationPageTitle);
    }
    public String getRegisterActionMessage(){
        wait.until(ExpectedConditions.visibilityOf(registerMSG));
        String msg = registerMSG.getText();
        return msg;
    }
    public boolean isNavLoginLinkShown() {
        return isPresented(navBarLogin);
    }
}
