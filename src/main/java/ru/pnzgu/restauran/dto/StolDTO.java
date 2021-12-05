package ru.pnzgu.restauran.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StolDTO extends DtoParent {
    private Long countPlace;
    private List<ReservDTO> reservs = new ArrayList<>();
}
