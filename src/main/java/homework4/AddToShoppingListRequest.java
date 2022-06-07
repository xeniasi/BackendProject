package homework4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "item",
        "aisle"
})

public class AddToShoppingListRequest {

    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;

}
