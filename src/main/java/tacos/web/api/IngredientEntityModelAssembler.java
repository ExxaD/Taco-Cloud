package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.domain.Ingredient;

public class IngredientEntityModelAssembler
        extends RepresentationModelAssemblerSupport<Ingredient, IngredientEntityModel> {

    public IngredientEntityModelAssembler() {
        super(DesignTacoController.class, IngredientEntityModel.class);
    }

    @Override
    public IngredientEntityModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }

    @Override
    protected IngredientEntityModel instantiateModel(Ingredient ingredient) {
        return new IngredientEntityModel(ingredient);
    }
}
