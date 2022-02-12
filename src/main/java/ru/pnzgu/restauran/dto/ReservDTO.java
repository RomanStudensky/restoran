package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pnzgu.restauran.util.mapping.DateOptions;
import ru.pnzgu.restauran.util.mapping.TimeOptions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "ФИО клиента",
            "Дата",
            "Время",
            "Кол-во гостей"
    );

    private String fullName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateOptions.PATTERN)
    private LocalDate dateReserv;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TimeOptions.PATTERN)
    private LocalTime timeReserv;
    private StolDTO stol;
    private Long countPeople;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                String.valueOf(fullName),
                dateReserv.format(DateOptions.FORMATTER),
                timeReserv.format(TimeOptions.FORMATTER),
                String.valueOf(countPeople)
        );
    }
}
