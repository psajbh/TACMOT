package guru.springframework.transform.ingredient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.model.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientTransformer implements Converter<Ingredient, IngredientBean>{
    
    @Synchronized
    @Nullable
    @Override
    public IngredientBean convert(Ingredient entity) {
        
        if (null == entity) {
            return null;
        }
        
        IngredientBean bean = new IngredientBean();
        bean.setId(bean.getId());
        bean.setDescription(bean.getDescription());
        return  bean;
        
    }

}
