package ru.pnzgu.restauran.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class SotrudnikDTO extends DtoParent {
    private String fio;
    private String role;
}
