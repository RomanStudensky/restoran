package ru.pnzgu.restauran.util.mapping;

import ru.pnzgu.restauran.dto.DtoParent;
import ru.pnzgu.restauran.store.entity.EntityParent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleMapper<Dto extends DtoParent, Entity extends EntityParent> {

    Dto dto;
    Entity entity;
    ModelMapper modelMapper = new ModelMapper();

    public SimpleMapper(Dto dto, Entity entity) {
        this.dto = dto;
        this.entity = entity;
    }

    public Dto mapEntityToDto(Entity e) {
        return (Dto) modelMapper.map(e, dto.getClass());
    }

    public Entity mapDtoToEntity(Dto d) {
        return (Entity) modelMapper.map(d, entity.getClass());
    }
}
