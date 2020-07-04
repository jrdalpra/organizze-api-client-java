package org.github.jrdalpra.organizze;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode
public class User extends Resource {
    Integer id;
    String name, email, role;
}
