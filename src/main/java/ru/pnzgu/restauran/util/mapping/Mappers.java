package ru.pnzgu.restauran.util.mapping;

import lombok.experimental.UtilityClass;
import ru.pnzgu.restauran.dto.*;
import ru.pnzgu.restauran.store.entity.*;

@UtilityClass
public class Mappers {
    public static final SimpleMapper<AktDTO, AktSpis> AKT_MAPPER = new SimpleMapper<>(new AktDTO(), new AktSpis());
    public static final SimpleMapper<CategoryDTO, Category> CATEGORY_MAPPER = new SimpleMapper<>(new CategoryDTO(), new Category());
    public static final SimpleMapper<DogovorDTO, Dogovor> DOGOVOR_MAPPER = new SimpleMapper<>(new DogovorDTO(), new Dogovor());
    public static final SimpleMapper<MenuDTO, Menu> MENU_MAPPER = new SimpleMapper<>(new MenuDTO(), new Menu());
    public static final SimpleMapper<NakladDTO, Naklad> NAKLAD_MAPPER = new SimpleMapper<>(new NakladDTO(), new Naklad());
    public static final SimpleMapper<OrdersDTO, Order> ORDER_MAPPER = new SimpleMapper<>(new OrdersDTO(), new Order());
    public static final SimpleMapper<PostavshikDTO, Postavshik> POSTAVSHIK_MAPPER = new SimpleMapper<>(new PostavshikDTO(), new Postavshik());
    public static final SimpleMapper<ProdazaDTO, Prodaza> PRODAZA_MAPPER = new SimpleMapper<>(new ProdazaDTO(), new Prodaza());
    public static final SimpleMapper<ProductDTO, Product> PRODUCT_MAPPER = new SimpleMapper<>(new ProductDTO(), new Product());
    public static final SimpleMapper<ReservDTO, Reserv> RESERV_MAPPER = new SimpleMapper<>(new ReservDTO(), new Reserv());
    public static final SimpleMapper<SostavAktDTO, SostavAkt> SOSTAV_AKT_MAPPER = new SimpleMapper<>(new SostavAktDTO(), new SostavAkt());
    public static final SimpleMapper<SostavBludoDTO, SostavBludo> SOSTAV_BLUDO_MAPPER = new SimpleMapper<>(new SostavBludoDTO(), new SostavBludo());
    public static final SimpleMapper<SostavPostavDTO, SostavPostav> SOSTAV_POSTAV_MAPPER = new SimpleMapper<>(new SostavPostavDTO(), new SostavPostav());
    public static final SimpleMapper<SostavProdDTO, SostavProd> SOSTAV_PROD_MAPPER = new SimpleMapper<>(new SostavProdDTO(), new SostavProd());
    public static final SimpleMapper<SostavZakazDTO, SostavZakaz> SOSTAV_ZAKAZ_MAPPER = new SimpleMapper<>(new SostavZakazDTO(), new SostavZakaz());
    public static final SimpleMapper<SotrudnikDTO, Sotrudnik> SOTRUDNIK_MAPPER = new SimpleMapper<>(new SotrudnikDTO(), new Sotrudnik());
    public static final SimpleMapper<StolDTO, Stol> STOL_MAPPER = new SimpleMapper<>(new StolDTO(), new Stol());
    public static final SimpleMapper<ZakazDTO, Zakaz> ZAKAZ_MAPPER = new SimpleMapper<>(new ZakazDTO(), new Zakaz());
}
