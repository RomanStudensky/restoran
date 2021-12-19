package ru.pnzgu.restauran.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.pnzgu.restauran.store.entity.Menu;
import ru.pnzgu.restauran.store.entity.Product;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SostavBludoDTO extends DtoParent{
    private Integer count;
    private ProductDTO product;
    private MenuDTO menu;
}

