package ru.pnzgu.restauran.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AktDTO extends DtoParent {
    private LocalDate dateAkt;
    private SotrudnikDTO sotrud;
    private Double summa;
    private List<SpisProductDTO> spisProducts;
}
