package guru.springframework.transformers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.model.Category;

@Component
public class CategoryTransformer implements Converter<CategoryBean, Category>{  //source,target
	
	@Override
	public Category convert(CategoryBean source) {
		return null;
	}

}
