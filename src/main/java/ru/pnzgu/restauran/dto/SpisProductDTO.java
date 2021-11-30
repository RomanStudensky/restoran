package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpisProductDTO extends DtoParent {
    private ProductDTO product;
    private String reason;
    private Integer quantity;
    @JsonProperty(value = "akt")
    private AktDTO aktSpis;
    private BigDecimal price;
    private BigDecimal summa;
}
