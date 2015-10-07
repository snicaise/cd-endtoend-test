package pages;

import com.thoughtworks.gauge.Gauge;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 *
 */
public class QuotationPage extends BasePage {

    public static String Url = URL;

    @FindBy(xpath = "//paper-input[@id='origin']/paper-input-container/div[2]/div/input")
    private WebElement inputOrigin;

    @FindBy(xpath = "//paper-input[@id='destination']/paper-input-container/div[2]/div/input")
    private WebElement inputDestination;

    @FindBy(xpath = "//form[@id='quotation-form']/paper-card/div[3]/paper-button/paper-material")
    private WebElement submitQuote;

    @FindBy(xpath = "//div[@id='mainContainer']/div/iron-pages/section/quote-trip/paper-material[2]/paper-card/div[2]/b/span")
    private WebElement resultPrice;

    @FindBy(xpath = "//div[@id='mainContainer']/div/iron-pages/section/quote-trip/paper-material[2]/paper-card/div[2]/ul")
    private WebElement resultRecommendations;

    public void quoteTrip(String origin, String destination) {
        inputOrigin.sendKeys(origin);
        inputDestination.sendKeys(destination);

        submitQuote.click();
    }

    public void checkPrice() {
        String price = resultPrice.getText();
        Gauge.writeMessage("price: " + price);

        assertThat(price).as("price").isNotEmpty();
        assertThat(Integer.valueOf(price)).as("price").isGreaterThan(0);
    }

    public void checkRecomendations() {
        List<WebElement> allRecommendations = resultRecommendations.findElements(By.xpath("li"));
        for (WebElement recommendation: allRecommendations) {
            Gauge.writeMessage("recommendation: " + recommendation.getText());
        }

        assertThat(allRecommendations).as("recommendations")
            .isNotEmpty()
            .doesNotContainNull()
            .doesNotHaveDuplicates();
    }

}
