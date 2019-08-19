package guru.springframework.transform.category;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.model.Category;
import lombok.Synchronized;

@Component
public class CategoryTransformer implements Converter<Category, CategoryBean>{  //source,target
	
    @Synchronized
    @Nullable
	@Override
	public CategoryBean convert(Category source) {
        
		if (source == null) {
		    return null;
		}
		
		final CategoryBean bean = new CategoryBean();
		bean.setId(source.getId());
		bean.setDescription(source.getDescription());
		return bean;
	}

}
