package Data.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateToken(@JsonProperty("userName")  String userName,
                          @JsonProperty("password") String password) {
}
