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
public class DogovorDTO extends DtoParent {
    private LocalDate dateDogovor;
    private PostavshikDTO postavshik;
    private String sostav;
}
