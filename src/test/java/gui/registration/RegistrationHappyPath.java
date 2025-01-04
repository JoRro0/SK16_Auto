package gui.registration;

import com.GG.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

public class RegistrationHappyPath extends BaseTest {

    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver,log);

         String USERNAME = RegistrationDataGenerator.createUser() ;
         String EMAIL = RegistrationDataGenerator.createEmail();

        registrationPage.navigateToRegPage();

        //TO DO ReGISTRATION PAGE

        Thread.sleep(4444);


    }
}
