package guru.springframework.transform.ingredient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.model.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientBeanTransformer implements Converter<IngredientBean, Ingredient> {
    
    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientBean bean){
        
        if (null == bean) {
            return null;
        }
        
        Ingredient entity = new Ingredient();
        entity.setId(bean.getId());
        entity.setDescription(bean.getDescription());
        return  entity;
        
    }

}
