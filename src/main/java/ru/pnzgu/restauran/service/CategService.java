package ru.pnzgu.restauran.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pnzgu.restauran.store.repository.CategoryRepository;
import ru.pnzgu.restauran.store.repository.MenuRepository;

@Service
@RequiredArgsConstructor
public class CategService {
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
}
