package technical.test.superheroes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import technical.test.superheroes.Exceptions.NotFoundException;
import technical.test.superheroes.Model.Domain.SuperHeroeDTO;
import technical.test.superheroes.Model.Entity.SuperHeroe;
import technical.test.superheroes.Model.Mapper.SuperHeroesMapper;
import technical.test.superheroes.Model.Repository.SuperHeroesRepository;
import technical.test.superheroes.Service.SuperHeroesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SuperHeroeServiceTest {

    @Mock
    private SuperHeroesRepository repository;

    @Mock
    private SuperHeroesService service;

    @Spy
    private SuperHeroesMapper mapper;

    @BeforeEach
    public void setup() {
        service = new SuperHeroesService(repository, mapper);
    }
    @Test
    void findAll_shouldReturnSuperHeroeList(){
        List<SuperHeroe> superHeroeList = new ArrayList<>();
        superHeroeList.add(new SuperHeroe(1,"Wolverine", "Regenerative capacity"));
        superHeroeList.add(new SuperHeroe(2,"Black Widow","Tactical expert, close combatant"));

        when(repository.findAll()).thenReturn(superHeroeList);
        List<SuperHeroe> list = repository.findAll();
        assertThat(list).hasAtLeastOneElementOfType(SuperHeroe.class);
    }

    @Test
    void findAll_shouldReturnEmptyList() {
        List<SuperHeroe> superHeroeList = new ArrayList<>();
        when(repository.findAll()).thenReturn(superHeroeList);
        List<SuperHeroe> list = repository.findAll();
        assertThat(list).isEmpty();
    }

    @Test
    void findById_shouldReturnSuperHeroe() throws NotFoundException {
        SuperHeroe superHeroe = new SuperHeroe(1, "Iron Man", "Super strength, durability and resistance due to armor");
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(superHeroe));
        SuperHeroeDTO superHeroeDTO = service.findById(1);
        assertThat(superHeroeDTO).isNotNull();
    }

    @Test
    void findById_shouldReturnNotFoundException(){
        when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> service.findById(1))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void findByName_shouldReturnSuperHeroeList(){
        List<SuperHeroe> superHeroeList = new ArrayList<>();
        superHeroeList.add(new SuperHeroe(1,"Spider Man", "Superhuman strength"));

        when(repository.findByName(any(String.class))).thenReturn(superHeroeList);

        List<SuperHeroe> list = repository.findByName("man");
        assertThat(list).hasAtLeastOneElementOfType(SuperHeroe.class);
    }
}
