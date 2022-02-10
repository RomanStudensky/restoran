package ru.pnzgu.restauran.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {
    DIRECTOR("Директор/Бухгалтер", Set.of(
            Permission.REPORT_READ,
            Permission.POSTAVSHIK_WRITE,    Permission.POSTAVSHIK_READ,
            Permission.DOGOVOR_WRITE,       Permission.DOGOVOR_READ,
            Permission.USER_WRITE,          Permission.USER_READ
    )),
    COOK("Повар", Set.of(
            Permission.TOVAR_NAKLAD_WRITE,  Permission.TOVAR_NAKLAD_READ,
            Permission.SOSTAV_POSTAV_WRITE, Permission.SOSTAV_POSTAV_READ,
            Permission.ORDERS_WRITE,        Permission.ORDERS_READ,
            Permission.SOSTAV_ORDER_WRITE,  Permission.SOSTAV_ORDER_READ,
            Permission.AKT_WRITE,           Permission.AKT_READ,
            Permission.SPIS_PRODUCT_WRITE,  Permission.SPIS_PRODUCT_READ,
            Permission.PRODUCT_WRITE,       Permission.PRODUCT_READ,
            Permission.CATEGORY_WRITE,      Permission.CATEGORY_READ,
            Permission.MENU_WRITE,          Permission.MENU_READ,
            Permission.SOSTAV_BLUDO_WRITE,  Permission.SOSTAV_BLUDO_READ
    )),
    ADMIN("Администратор", Set.of(
            Permission.ZAKAZ_WRITE,         Permission.ZAKAZ_READ,
            Permission.SOSTAV_ZAKAZ_WRITE,  Permission.SOSTAV_ZAKAZ_READ
    )),
    OFFICIANT("Официант", Set.of(
            Permission.RESERV_WRITE,        Permission.RESERV_READ,
            Permission.STOL_WRITE,          Permission.STOL_READ
    )),
    BARMAN("Бармен", Set.of(
            Permission.PRODAZA_WRITE,       Permission.PRODAZA_READ,
            Permission.SOSTAV_PROD_WRITE,   Permission.SOSTAV_PROD_READ
    ));

    private final String value;
    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
