package cli;

import java.util.List;

/**
 *
 */
public interface Quotation {

    String getOrigin();

    String getDestination();

    int getPrice();

    List<String> getRecommendations();

}
