package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import tacos.domain.Ingredient;

import static tacos.domain.Ingredient.*;

public class IngredientEntityModel extends EntityModel<IngredientEntityModel> {

    @Getter
    private String name;

    @Getter
    private Type type;

    public IngredientEntityModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
