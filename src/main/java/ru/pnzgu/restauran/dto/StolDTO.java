package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StolDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№ стола",
            "Кол-во мест"
    );
    private Long countPlace = 0L;
    private List<ReservDTO> reservs = new ArrayList<>();

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                String.valueOf(countPlace)
        );
    }
}
