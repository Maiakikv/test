package Data.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreatedUserResponse(@JsonProperty ("userId") String userId,
                                  @JsonProperty ("username")String username,
                                  @JsonProperty ("books") List<BooksResponse> books) {
}
