package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavNakladDTO extends DtoParent {
    private LocalDate dateNak;
    private PostavshikDTO postavshik;
    private DogovorDTO dogovor;
    private BigDecimal summa;

}
