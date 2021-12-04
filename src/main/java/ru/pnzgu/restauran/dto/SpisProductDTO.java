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
    private Integer quantity = 0;
    @JsonProperty(value = "akt")
    private AktDTO aktSpis;
    private BigDecimal price = BigDecimal.valueOf(0);
    private BigDecimal summa = BigDecimal.valueOf(0);
}
