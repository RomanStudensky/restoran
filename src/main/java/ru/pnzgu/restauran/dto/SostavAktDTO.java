package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavAktDTO extends DtoParent {
    private ProductDTO product;
    private String reason;
    private Integer quantity = 0;
    @JsonProperty(value = "akt")
    private AktDTO aktSpis;
    private Double price = 0.0D;
    private Double summa = 0.0D;
}
