package cli;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 */
public class QuotationClient {

    private final Client client;
    private final WebTarget pricing;

    public QuotationClient(Client client, String baseUri) {
        if (client == null) {
            throw new IllegalArgumentException("client is mandatory");
        }
        if (baseUri == null) {
            throw new IllegalArgumentException("baseUri is mandatory");
        }

        this.client = client;
        this.pricing = client.target(baseUri);
    }

    public Quotation price(String origin, String destination) {
        return pricing.path("booking/quotation")
            .queryParam("origin", origin)
            .queryParam("destination", destination)
            .request(MediaType.APPLICATION_JSON)
            .get(Quotation.class);
    }

}
