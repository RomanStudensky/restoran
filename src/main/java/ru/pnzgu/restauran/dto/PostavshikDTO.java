package ru.pnzgu.restauran.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostavshikDTO extends DtoParent {
    private String namePost;
    private String address;
    private String inn;
}
