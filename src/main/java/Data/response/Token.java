package Data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Token(@JsonProperty("token") String token,
                    @JsonProperty("expires") String expires,
                    @JsonProperty("status") String status,
                    @JsonProperty("result") String result) {
}
