package tacos.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Taco;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(path = "/design",
        produces = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class DesignTacoController {

    private final TacoRepository tacoRepo;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public CollectionModel<TacoEntityModel> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        TacoEntityModelAssembler modelAssembler = new TacoEntityModelAssembler();
        List<TacoEntityModel> tacoResources =
                new ArrayList<>();
        tacos.forEach(taco -> tacoResources.add(modelAssembler.toModel(taco)));
        CollectionModel<TacoEntityModel> recentResources =
                CollectionModel.of(tacoResources);
        recentResources.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                .withRel("recents"));
        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return ResponseEntity.ok(optTaco.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody @Valid Taco taco) {
        Taco savedTaco = tacoRepo.save(taco);
        log.info("Taco [id = {}] was saved", savedTaco.getId());
        return savedTaco;
    }

}
