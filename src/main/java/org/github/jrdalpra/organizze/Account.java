package org.github.jrdalpra.organizze;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true, callSuper = true)
@Builder(builderMethodName = "with", buildMethodName = "get")
public class Account extends Resource {
    Integer id;
    String name, description;
    Boolean archived;
    ZonedDateTime created_at, updated_at;
    @Getter(onMethod = @__({@JsonProperty("default")}))
    Boolean defaultOne;
    String type;
}
