package ru.pnzgu.restauran.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogovorDTO extends DtoParent {
    private LocalDate dateDogovor;
    private PostavshikDTO postavshik;
    private String sostav;
}
