package site.steps;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.data.CalculatorFormFieldData;
import site.data.RegistrationErrorData;
import site.data.RegistrationFieldData;
import site.pages.RegistrationPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Steps for Registration Page
 */
@Component
public class RegistrationPageSteps {

    private static final Logger LOGGER = Logger.getLogger(RegistrationPageSteps.class.getName());

    @Autowired
    private RegistrationPage registrationPage;

    /**
     * Check is registration page opened
     */
    public void pageShouldBeOpened() {
        LOGGER.info("Step start: registration page should be opened");
        assertTrue("Registration page should be opened",
                registrationPage.isPageOpened());
    }

    /**
     * Click Submit button
     */
    public void clickSubmitButton() {
        registrationPage.clickSubmitButton();
    }

    /**
     * Check is Error Message Present
     * @param errorData errorMessageType
     */
    public void errorShouldBePresent(RegistrationErrorData errorData) {
        assertTrue(String.format("Error <%s> should be present", errorData.name()),
                registrationPage.isErrorPresent(errorData));
    }

    /**
     * Set field value
     * @param fieldData field
     * @param s value
     */
    public void setField(RegistrationFieldData fieldData, String s) {
        registrationPage.setField(fieldData, s);
    }

    /**
     * Click Edit button
     */
    public void clickEditButton() {
        registrationPage.clickEditButton();
    }

    /**
     * Check Credit Info value
     * @param fieldData field
     * @param s value
     */
    public void creditInfoShouldBeSameAs(CalculatorFormFieldData fieldData, String s) {
        assertEquals(String.format("Wrong <%s> field value", fieldData.name()),
                s,
                registrationPage.getCreditInfoValue(fieldData));
    }
}