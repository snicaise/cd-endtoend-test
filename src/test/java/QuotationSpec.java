import cli.Quotation;
import cli.QuotationClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.thoughtworks.gauge.BeforeClassSteps;
import com.thoughtworks.gauge.Step;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class QuotationSpec {

    private QuotationClient client;
    private Quotation price;

    @BeforeClassSteps
    public void setUp () {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new MrBeanModule());

        JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJsonProvider.setMapper(mapper);

        Client jerseyClient = ClientBuilder.newClient()
            .register(jacksonJsonProvider)
            .register(new LoggingFilter(Logger.getLogger("jersey"), true));

        client = new QuotationClient(jerseyClient, System.getenv("web_base_uri"));
    }

    @Step("Demander un devis pour un <origin> <destination>")
    public void askQuotation(String origin, String destination) {
        price = client.price(origin, destination);
    }

    @Step("Obtenir un prix")
    public void verifyPrice() {
        assertThat(price.getPrice()).as("prix").isGreaterThan(0);
    }

    @Step("Obtenir une liste d'hotels recommandés")
    public void verifyHotelsRecommendations() {
        assertThat(price.getRecommendations()).as("recommendations").isNotEmpty().doesNotContainNull();
    }

}