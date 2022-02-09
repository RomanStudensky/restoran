package ru.pnzgu.restauran.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SostavBludoDTO extends DtoParent{
    private Integer count;
    private ProductDTO product;
    private MenuDTO menu;
}

