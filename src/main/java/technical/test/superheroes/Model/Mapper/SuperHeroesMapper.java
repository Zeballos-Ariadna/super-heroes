package technical.test.superheroes.Model.Mapper;

import org.springframework.stereotype.Component;
import technical.test.superheroes.Model.Domain.SuperHeroeDTO;
import technical.test.superheroes.Model.Entity.SuperHeroe;

@Component("superHeroesMapper")
public class SuperHeroesMapper {

    public SuperHeroe dtoToEntity(SuperHeroeDTO dto){
        SuperHeroe entity = new SuperHeroe();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSuperPower(dto.getSuperPower());
        return entity;
    }

    public SuperHeroeDTO entityToDto(SuperHeroe entity) {
        SuperHeroeDTO dto = new SuperHeroeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSuperPower(entity.getSuperPower());
        return dto;
    }
}
