package ru.pnzgu.restauran.store;

public enum Permission {
    AKT_READ("akt:read"),
    AKT_WRITE("akt:write"),

    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),

    DOGOVOR_READ("dogovor:read"),
    DOGOVOR_WRITE("dogovor:write"),

    MENU_READ("menu:read"),
    MENU_WRITE("menu:write"),

    ORDERS_READ("orders:read"),
    ORDERS_WRITE("orders:write"),

    POSTAVSHIK_READ("postavshik:read"),
    POSTAVSHIK_WRITE("postavshik:write"),

    PRODAZA_READ("prodaza:read"),
    PRODAZA_WRITE("prodaza:write"),

    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),

    RESERV_READ("reserv:read"),
    RESERV_WRITE("reserv:write"),

    SOSTAV_BLUDO_READ("sostav_bludo:read"),
    SOSTAV_BLUDO_WRITE("sostav_bludo:write"),

    SOSTAV_ORDER_READ("sostav_order:read"),
    SOSTAV_ORDER_WRITE("sostav_order:write"),

    SOSTAV_POSTAV_READ("sostav_postav:read"),
    SOSTAV_POSTAV_WRITE("sostav_postav:write"),

    SOSTAV_PROD_READ("sostav_prod:read"),
    SOSTAV_PROD_WRITE("sostav_prod:write"),

    SOSTAV_ZAKAZ_READ("sostav_zakaz:read"),
    SOSTAV_ZAKAZ_WRITE("sostav_zakaz:write"),

    SPIS_PRODUCT_READ("spis_product:read"),
    SPIS_PRODUCT_WRITE("spis_product:write"),

    STOL_READ("stol_read:read"),
    STOL_WRITE("stol_read:write"),

    TOVAR_NAKLAD_READ("tovar_naklad:read"),
    TOVAR_NAKLAD_WRITE("tovar_naklad:write"),

    USER_READ("user:read"),
    USER_WRITE("user:write"),

    ZAKAZ_READ("zakaz:read"),
    ZAKAZ_WRITE("zakaz:write"),

    REPORT_READ("report:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
