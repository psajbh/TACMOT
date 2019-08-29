package guru.springframework.transform.category;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.model.Category;
import lombok.Synchronized;

@Component
public class CategoryBeanTransformer implements Converter<CategoryBean, Category>{
    
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryBean bean) {
        
        if (bean == null) {
            return null;
        }

        final Category entity = new Category();
        
        entity.setId(bean.getId());
        entity.setDescription(bean.getDescription());
        return entity; 
    }


}
