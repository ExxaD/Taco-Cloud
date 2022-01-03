package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.domain.Taco;

import java.util.List;

public class TacoEntityModelAssembler
       extends RepresentationModelAssemblerSupport<Taco, TacoEntityModel> {

    public TacoEntityModelAssembler() {
        super(DesignTacoController.class, TacoEntityModel.class);
    }

    @Override
    protected TacoEntityModel instantiateModel(Taco taco) {
        return new TacoEntityModel(taco);
    }

    @Override
    public TacoEntityModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
