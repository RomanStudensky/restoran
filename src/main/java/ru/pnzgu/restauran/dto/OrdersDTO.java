package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pnzgu.restauran.util.mapping.DateOptions;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "Дата заявки",
            "Поставщик",
            "№ договора"
    );

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateOptions.PATTERN)
    private LocalDate dateOrder = LocalDate.now();
    private PostavshikDTO postavshik;
    private DogovorDTO dogovor;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                dateOrder.format(DateOptions.FORMATTER),
                postavshik.getNamePost(),
                String.valueOf(dogovor.getId())
        );
    }
}
