package org.github.jrdalpra.organizze;

import lombok.*;
import org.github.jrdalpra.organizze.utils.ErrorAware;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode
public class User extends ErrorAware {
    Integer id;
    String name, email, role;
}
