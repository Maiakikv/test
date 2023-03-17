package Data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorAuthorizeResponse(@JsonProperty("code") int code,
                                     @JsonProperty ("message") String message) {
}
