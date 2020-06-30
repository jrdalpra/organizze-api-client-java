package org.github.jrdalpra.organizze;

import java.time.LocalDate;

import lombok.*;
import org.github.jrdalpra.organizze.utils.ErrorAware;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true, callSuper = true)
@Builder(builderMethodName = "with", buildMethodName = "get")
public class Budget extends ErrorAware implements Resource {

    Integer id, activity_type, category_id;
    Long amount_in_cents, total, predicted_total;
    LocalDate date;
    String percentage;

}
