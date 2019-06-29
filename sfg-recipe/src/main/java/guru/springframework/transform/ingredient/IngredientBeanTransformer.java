package guru.springframework.transform.ingredient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.UnitOfMeasureBean;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.transform.unitofmeasure.UnitOfMeasureBeanTransformer;
import lombok.Synchronized;

@Component
public class IngredientBeanTransformer implements Converter<IngredientBean, Ingredient> {
	
	private final UnitOfMeasureBeanTransformer unitOfMeasureBeanTransformer;
	
	public IngredientBeanTransformer(UnitOfMeasureBeanTransformer unitOfMeasureBeanTransformer) {
		this.unitOfMeasureBeanTransformer = unitOfMeasureBeanTransformer;
	}
    
    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientBean bean){
        
        if (null == bean) {
            return null;
        }
        
        Ingredient entity = new Ingredient();
        entity.setId(bean.getId());
        entity.setAmount(bean.getAmount());
        entity.setDescription(bean.getDescription());
        if (null != bean.getUom()) {
        	entity.setUom(unitOfMeasureBeanTransformer.convert(bean.getUom()));
        }
        return  entity;
        
    }

}
