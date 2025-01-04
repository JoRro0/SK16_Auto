package gui.login;

import com.GG.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.annotations.Test;

public class LoginPageLayoutTest extends BaseTest {

    @Test
    public void verifyLoginPageLayout(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();

        // Login page Title

        //Login Page form submit button - text/clickable

        //Login page input fields

        //Login page remember me

        //Login page -> link to registration page
    }

}
