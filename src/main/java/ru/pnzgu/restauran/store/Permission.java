package ru.pnzgu.restauran.store;

public enum Permission {
    AKT_READ("akt:read"),
    AKT_WRITE("akt:write"),

    CATEGORY_READ("akt:read"),
    CATEGORY_WRITE("akt:write"),

    DOGOVOR_READ("akt:read"),
    DOGOVOR_WRITE("akt:write"),

    MENU_READ("akt:read"),
    MENU_WRITE("akt:write"),

    ORDERS_READ("akt:read"),
    ORDERS_WRITE("akt:write"),

    POSTAVSHIK_READ("akt:read"),
    POSTAVSHIK_WRITE("akt:write"),

    PRODAZA_READ("akt:read"),
    PRODAZA_WRITE("akt:write"),

    PRODUCT_READ("akt:read"),
    PRODUCT_WRITE("akt:write"),

    RESERV_READ("akt:read"),
    RESERV_WRITE("akt:write"),

    SOSTAV_BLUDO_READ("akt:read"),
    SOSTAV_BLUDO_WRITE("akt:write"),

    SOSTAV_ORDER_READ("akt:read"),
    SOSTAV_ORDER_WRITE("akt:write"),

    SOSTAV_POSTAV_READ("akt:read"),
    SOSTAV_POSTAV_WRITE("akt:write"),

    SOSTAV_PROD_READ("akt:read"),
    SOSTAV_PROD_WRITE("akt:write"),

    SOSTAV_ZAKAZ_READ("akt:read"),
    SOSTAV_ZAKAZ_WRITE("akt:write"),

    SOSTAV_PRODUCT_READ("akt:read"),
    SOSTAV_PRODUCT_WRITE("akt:write"),

    STOL_READ("akt:read"),
    STOL_WRITE("akt:write"),

    TOVAR_NAKLAD_READ("akt:read"),
    TOVAR_NAKLAD_WRITE("akt:write"),

    USER_READ("akt:read"),
    USER_WRITE("akt:write"),

    ZAKAZ_READ("akt:read"),
    ZAKAZ_WRITE("akt:write"),

    DOCUMENT_READ("akt:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
