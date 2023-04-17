package technical.test.superheroes.Service;

import org.springframework.stereotype.Service;
import technical.test.superheroes.Exceptions.ErrorMessages;
import technical.test.superheroes.Exceptions.FailedVerificationException;
import technical.test.superheroes.Exceptions.NotFoundException;
import technical.test.superheroes.Model.Domain.SuperHeroeDTO;
import technical.test.superheroes.Model.Entity.SuperHeroe;
import technical.test.superheroes.Model.Mapper.SuperHeroesMapper;
import technical.test.superheroes.Model.Repository.SuperHeroesRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("superHeroesService")
public class SuperHeroesService {
    private final SuperHeroesRepository repository;
    private final SuperHeroesMapper mapper;

    public SuperHeroesService(SuperHeroesRepository repository, SuperHeroesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<SuperHeroeDTO> findAll(){
        return repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    public SuperHeroeDTO findById(int id) throws NotFoundException{
        SuperHeroe superHeroe = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        return mapper.entityToDto(superHeroe);
    }

    public List<SuperHeroeDTO> findByName(String name) throws NotFoundException{
        List<SuperHeroe> superHeroeList = repository.findByName(name/*.toUpperCase()*/);
        if(superHeroeList.isEmpty())
            throw new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND);

        return superHeroeList.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    public SuperHeroeDTO save(SuperHeroeDTO dto) throws FailedVerificationException, NotFoundException{
        if(Objects.isNull(dto)
                || Objects.isNull(dto.getName())
                || Objects.isNull(dto.getSuperPower()))
            throw new FailedVerificationException(ErrorMessages.REGISTER_NOT_FOUND);

        SuperHeroe superHeroe;

        if(Objects.isNull(dto.getId()))
            superHeroe = new SuperHeroe();
        else superHeroe = repository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        superHeroe.setName(dto.getName());
        superHeroe.setSuperPower(dto.getSuperPower());

        repository.save(superHeroe);

        return mapper.entityToDto(superHeroe);
    }

    public SuperHeroeDTO delete(int id) throws NotFoundException{
        SuperHeroe entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.REGISTER_NOT_FOUND));

        repository.delete(entity);
        return mapper.entityToDto(entity);
    }

}
