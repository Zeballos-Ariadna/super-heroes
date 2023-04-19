package technical.test.superheroes.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technical.test.superheroes.Annotations.Count;
import technical.test.superheroes.Exceptions.FailedVerificationException;
import technical.test.superheroes.Exceptions.NotFoundException;
import technical.test.superheroes.Model.Domain.SuperHeroeDTO;
import technical.test.superheroes.Service.SuperHeroesService;

import java.util.List;

@RestController
@RequestMapping("/super-heroes")
public class SuperHeroesController {

    private final SuperHeroesService service;

    public SuperHeroesController(SuperHeroesService service) {
        this.service = service;
    }

    @GetMapping
    @Count
    public ResponseEntity<List<SuperHeroeDTO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroeDTO> findById(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<SuperHeroeDTO>> findByName(@RequestParam String name) throws NotFoundException{
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    @Count
    public ResponseEntity<SuperHeroeDTO> save(@RequestBody SuperHeroeDTO dto) throws FailedVerificationException, NotFoundException {
        return ResponseEntity.ok().body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperHeroeDTO> edit(@RequestBody SuperHeroeDTO dto, @PathVariable int id) throws FailedVerificationException, NotFoundException{
        dto.setId(id);
        return ResponseEntity.ok().body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuperHeroeDTO> delete(@PathVariable int id) throws NotFoundException{
        return ResponseEntity.ok().body(service.delete(id));
    }
}
