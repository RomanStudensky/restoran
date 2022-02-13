package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pnzgu.restauran.util.mapping.DateOptions;
import ru.pnzgu.restauran.util.mapping.TimeOptions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdazaDTO extends DtoParent {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateOptions.PATTERN)
    private LocalDate dateProd = LocalDate.now();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TimeOptions.PATTERN)
    private LocalTime timeProd = LocalTime.now();
    private UserDto user;
    private BigDecimal summa = BigDecimal.valueOf(0);

}
