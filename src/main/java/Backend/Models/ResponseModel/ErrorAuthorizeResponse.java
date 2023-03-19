package Backend.Models.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorAuthorizeResponse(@JsonProperty("code") int code,
                                     @JsonProperty ("message") String message) {
}
