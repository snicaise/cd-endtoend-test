import com.thoughtworks.gauge.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.QuotationPage;

/**
 *
 */
public class QuotationSpec {

    private final WebDriver driver;

    public QuotationSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("Sur la page de devis")
    public void navigateToCustomersPage() {
        try {
            driver.get(QuotationPage.Url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Demander un devis pour un <origin> <destination>")
    public void askQuotation(String origin, String destination) {
        QuotationPage quotationPage = PageFactory.initElements(driver, QuotationPage.class);
        quotationPage.quoteTrip(origin, destination);
    }

    @Step("Obtenir un prix")
    public void verifyPrice() {
        QuotationPage quotationPage = PageFactory.initElements(driver, QuotationPage.class);
        quotationPage.checkPrice();
    }

    @Step("Obtenir une liste d'hotels recommandés")
    public void verifyHotelsRecommendations() {
        QuotationPage quotationPage = PageFactory.initElements(driver, QuotationPage.class);
        quotationPage.checkRecomendations();
    }

}