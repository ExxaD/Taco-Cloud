package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;
import tacos.domain.Taco;

import java.time.LocalDateTime;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoEntityModel extends EntityModel<TacoEntityModel> {

    private static final IngredientEntityModelAssembler
        ingredientAssembler = new IngredientEntityModelAssembler();

    @Getter
    private final String name;

    @Getter
    private final LocalDateTime createdAt;

    @Getter
    private final CollectionModel<IngredientEntityModel> ingredients;

    public TacoEntityModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}
