package ru.pnzgu.restauran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO extends DtoParent {
    private LocalDate dateOrder;
    private PostavshikDTO postavshik;
    private DogovorDTO dogovor;

}
