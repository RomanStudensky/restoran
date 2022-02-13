package ru.pnzgu.restauran.dto;

import lombok.*;
import ru.pnzgu.restauran.store.Role;
import ru.pnzgu.restauran.store.Status;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDto extends DtoParent {

    private Long id;
    private String fio;
    private String username;
    private String password;
    private Role role;
    private Status status = Status.ACTIVE;
}
