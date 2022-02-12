package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.pnzgu.restauran.util.mapping.DateOptions;
import ru.pnzgu.restauran.util.mapping.TimeOptions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZakazDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "Дата заказа",
            "Время заказа",
            "Сумма",
            "Стол",
            "Сотрудник"
    );
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateOptions.PATTERN)
    private LocalDate dateZakaz = LocalDate.now();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TimeOptions.PATTERN)
    private LocalTime timeZakaz = LocalTime.now();
    private StolDTO stol = new StolDTO();
    private UserDTO sotrud = new UserDTO();
    private BigDecimal summa = BigDecimal.valueOf(0);
    private List<SostavZakazDTO> sostav = new ArrayList<>();

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                dateZakaz.format(DateOptions.FORMATTER),
                timeZakaz.format(TimeOptions.FORMATTER),
                String.valueOf(summa),
                String.valueOf(stol.getId()),
                sotrud.getFio()
        );
    }
}
