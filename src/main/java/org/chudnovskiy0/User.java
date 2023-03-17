package org.chudnovskiy0;

import lombok.*;


@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class User {
    private String login;
    private String password;
}
