package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pnzgu.restauran.util.mapping.DateOptions;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NakladDTO extends DtoParent implements DtoInterface{

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "Дата поставки",
            "Поставщик",
            "№ договора",
            "Сумма"
    );

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateOptions.PATTERN)
    private LocalDate dateNaklad = LocalDate.now();
    private PostavshikDTO postavshik;
    private DogovorDTO dogovor;
    private Long summa = 0L;
    private List<SostavPostavDTO> sostav;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                dateNaklad.format(DateOptions.FORMATTER),
                postavshik.getNamePost(),
                String.valueOf(dogovor.getId()),
                String.valueOf(summa)
        );
    }
}
