package ru.pnzgu.restauran.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {
    BUHGALTER("Бухгалтер", Set.of(Permission.DOCUMENT_READ,
                    Permission.POSTAVSHIK_WRITE, Permission.POSTAVSHIK_READ,
                    Permission.DOGOVOR_WRITE, Permission.DOGOVOR_READ,
                    Permission.USER_WRITE, Permission.USER_READ)),

    POVAR("Повар", Set.of(Permission.AKT_WRITE, Permission.AKT_READ)),
    ADMIN("Администратор"),
    OFICIANT("Официант"),
    BARMAN("Бармен");

    private final String value;
    private final Set<Permission> permissions;



    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
