package ru.pnzgu.restauran.util.mapping;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import ru.pnzgu.restauran.dto.DtoParent;
import ru.pnzgu.restauran.store.entity.EntityParent;

@RequiredArgsConstructor
public class SimpleMapper<Dto extends DtoParent, Entity extends EntityParent> {

    private final Dto dto;
    private final Entity entity;

    private final ModelMapper modelMapper = new ModelMapper();

    public Dto mapEntityToDto(Entity e) {
        return (Dto) modelMapper.map(e, dto.getClass());
    }

    public Entity mapDtoToEntity(Dto d) {
        return (Entity) modelMapper.map(d, entity.getClass());
    }
}
