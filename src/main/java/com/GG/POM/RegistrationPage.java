package com.GG.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage  extends BasePage {
    //1. CONST
    public static final String REGISTARATION_PAGE_URL = "/users/register";
    //2.UI MAP

    //3 CONSTRUCTOR
    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //3.1. NAVIGATION
    public void navigateToRegPage(){
        navigateTo(REGISTARATION_PAGE_URL);
    }

    //4 USER ACTION

    //5. SUPPORT METHODS

    //6.Verifications


}
