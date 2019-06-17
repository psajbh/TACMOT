package guru.springframework.transform.ingredient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.model.Ingredient;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureTransformer;
import lombok.Synchronized;

@Component
public class IngredientTransformer implements Converter<Ingredient, IngredientBean>{
	
	private final UnitOfMeasureTransformer unitOfMeasureTranformer;
	
	public IngredientTransformer(UnitOfMeasureTransformer unitOfMeasureTranformer) {
		this.unitOfMeasureTranformer = unitOfMeasureTranformer;
	}
    
    @Synchronized
    @Nullable
    @Override
    public IngredientBean convert(Ingredient entity) {
        
        if (null == entity) {
            return null;
        }
        
        IngredientBean bean = new IngredientBean();
        bean.setId(entity.getId());
        bean.setDescription(entity.getDescription());
        bean.setAmount(entity.getAmount());
        bean.setUom(unitOfMeasureTranformer.convert(entity.getUom()));
        return  bean;
        
    }

}
