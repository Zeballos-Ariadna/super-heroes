package technical.test.superheroes.Service;

import org.springframework.stereotype.Service;
import technical.test.superheroes.Exceptions.ErrorMessages;
import technical.test.superheroes.Exceptions.NotFoundException;
import technical.test.superheroes.Model.Domain.SuperHeroeDTO;
import technical.test.superheroes.Model.Entity.SuperHeroe;
import technical.test.superheroes.Model.Mapper.SuperHeroesMapper;
import technical.test.superheroes.Model.Repository.SuperHeroesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service("superHeroesService")
public class SuperHeroesService {
    private final SuperHeroesRepository superHeroesRepository;
    private final SuperHeroesMapper superHeroesMapper;

    public SuperHeroesService(SuperHeroesRepository superHeroesRepository, SuperHeroesMapper superHeroesMapper) {
        this.superHeroesRepository = superHeroesRepository;
        this.superHeroesMapper = superHeroesMapper;
    }

    public List<SuperHeroeDTO> findAll(){
        return superHeroesRepository.findAll().stream().map(superHeroesMapper::entityToDto).collect(Collectors.toList());
    }

    public SuperHeroeDTO findById(int id) throws NotFoundException{
        SuperHeroe superHeroe = superHeroesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        return superHeroesMapper.entityToDto(superHeroe);
    }
}
