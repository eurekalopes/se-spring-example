package site.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;
import site.data.CalculatorFormSliderData;
import site.data.SliderDirectionData;
import site.pages.Page;

/**
 * Calculator Popup Element
 */
@Component
public class CalculatorPopupElement extends Page{

    private final String calculatorPopupLocator = "//*[@id='modal-calculator'][not(contains(@style, 'display: none;'))]";
    private final String sliderWithValueLocator = calculatorPopupLocator + "//*[@class='handle-value'][text()='%s']/ancestor::*[@id='%s']";
    private final String sliderLocator = calculatorPopupLocator + "//*[@name='%s']//*[contains(@class,'ui-slider-handle')]";
    private final String inputFieldLocator = calculatorPopupLocator + "//input[@id='%s']";
    private final String submitButtonLocator = calculatorPopupLocator + "//button[contains(@class,'apply_calculator')]";
    private final String cancelButtonLocator = calculatorPopupLocator + "//button[contains(@class,'btn-default')]";
    private final String sliderButtonLocator = calculatorPopupLocator + "//*[@data-type='change' and @data-slider='%s'][contains(@class,'%s')]";
    private final String termLinkLocator = calculatorPopupLocator + "//a[contains(@class,'terms-link')][@href='/legal-terms/info?amount=%s&term=%s']";

    /**
     * Check if Calculator Popup present
     * @return boolean
     */
    public boolean isPopupPresent() {
        return waitForElementPresent(By.xpath(calculatorPopupLocator));
    }

    /**
     * Check if Calculator Popup not present
     * @return boolean
     */
    public boolean isPopupNotPresent() {
        return waitForElementNotPresent(By.xpath(calculatorPopupLocator));
    }

    /**
     * Move slider left or right
     * @param sliderData slider
     * @param i amount
     */
    public void moveSlider(CalculatorFormSliderData sliderData, int i) {
        String locator = String.format(sliderLocator, sliderData.getSelector());
        (new Actions(getWebDriver())).dragAndDropBy($(By.xpath(locator)), i, 0).perform();
    }

    /**
     * Click Submit button
     */
    public void clickSubmitButton() {
        $(By.xpath(submitButtonLocator)).click();
    }

    /**
     * Set input field
     * @param calculatorFormFieldData field
     * @param value value
     */
    public void setField(CalculatorFormSliderData calculatorFormFieldData, String value) {
        String locator = String.format(inputFieldLocator, calculatorFormFieldData.name().toLowerCase());
        typeInto($(By.xpath(locator)), value);
    }

    /**
     * Get field value
     * @param fieldData field
     * @return value
     */
    public String getFieldValue(CalculatorFormSliderData fieldData) {
        String locator = String.format(inputFieldLocator, fieldData.name().toLowerCase());
        return $(By.xpath(locator)).getAttribute("value");
    }

    /**
     * Check is slider with value present
     * @param formSliderData slider
     * @param s value
     * @return boolean
     */
    public boolean isSliderWithValuePresent(CalculatorFormSliderData formSliderData, String s) {
        String locator = String.format(sliderWithValueLocator, s, formSliderData.getSelector());
        return waitForElementPresent(By.xpath(locator));
    }

    /**
     * Click for slider button
     * @param formFieldData slider
     * @param direction plus or minus
     */
    public void clickSliderButton(CalculatorFormSliderData formFieldData, SliderDirectionData direction) {
        String locator = String.format(sliderButtonLocator, formFieldData.name().toLowerCase(), direction.name().toLowerCase());
        $(By.xpath(locator)).click();
    }

    /**
     * Check if term link present with parameters
     * @param amount amount
     * @param term term
     * @return boolean
     */
    public boolean isTermLinkPresent(String amount, String term) {
        String locator = String.format(termLinkLocator, amount, term);
        return waitForElementPresent(By.xpath(locator));
    }
}

