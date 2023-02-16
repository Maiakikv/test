package Data.response;

import Utils.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BooksResponse(@JsonProperty("isbn") String isbn,
                            @JsonProperty("title") String title,
                            @JsonProperty("subTitle") String subTitle,
                            @JsonProperty("author") String author,
                            @JsonProperty("publish_date") LocalDateTimeDeserializer publishDate,
                            @JsonProperty("publisher") String publisher,
                            @JsonProperty("pages") int pages,
                            @JsonProperty("description") String description,
                            @JsonProperty("website") String website) {
}
