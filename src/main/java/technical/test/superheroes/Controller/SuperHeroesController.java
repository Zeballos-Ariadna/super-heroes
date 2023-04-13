package technical.test.superheroes.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technical.test.superheroes.Model.Domain.SuperHeroeDTO;
import technical.test.superheroes.Service.SuperHeroesService;

import java.util.List;

@RestController
@RequestMapping("/super-heroes")
public class SuperHeroesController {

    private final SuperHeroesService superHeroesService;

    public SuperHeroesController(SuperHeroesService superHeroesService) {
        this.superHeroesService = superHeroesService;
    }

    @GetMapping
    public ResponseEntity<List<SuperHeroeDTO>> findAll(){
        return new ResponseEntity<>(superHeroesService.findAll(), HttpStatus.OK);
    }

    
}
