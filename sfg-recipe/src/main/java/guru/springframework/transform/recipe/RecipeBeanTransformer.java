package guru.springframework.transform.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Recipe;
import guru.springframework.transform.category.CategoryBeanTransformer;
import guru.springframework.transform.ingredient.IngredientBeanTransformer;
import guru.springframework.transform.note.NotesBeanTransformer;

@Component
public class RecipeBeanTransformer implements Converter<RecipeBean, Recipe>{
    private final CategoryBeanTransformer categoryBeanTransformer;
    private final IngredientBeanTransformer ingredientBeanTransformer;
    private final NotesBeanTransformer notesBeanTransformer;
    
    @Autowired
    public RecipeBeanTransformer(CategoryBeanTransformer categoryBeanTransformer, 
            IngredientBeanTransformer ingredientBeanTransformer, 
            NotesBeanTransformer notesBeanTransformer) {
        this.categoryBeanTransformer = categoryBeanTransformer;
        this.ingredientBeanTransformer = ingredientBeanTransformer;
        this.notesBeanTransformer = notesBeanTransformer;
    }

    public Recipe convert(RecipeBean bean) {
        
        if (null == bean) {
            return null;
        }
        
        Recipe entity = new Recipe();
        entity.setId(bean.getId());
        entity.setDescription(bean.getDescription());
        entity.setPrepTime(bean.getPrepTime());
        entity.setCookTime(bean.getCookTime());
        entity.setDifficulty(bean.getDifficulty());
        entity.setDirections(bean.getDirections());
        entity.setServings(bean.getServings());
        entity.setSource(bean.getSource());
        entity.setUrl(bean.getUrl());
        entity.setNotes(notesBeanTransformer.convert(bean.getNotes()));
        
        if (bean.getCategories() != null && bean.getCategories().size() > 0){
            //Java7            
            //  for (CategoryBean categoryBean : source.getCategories()) {
            //      entity.getCategories().add(categoryBeanTransformer.convert(categoryBean));
            //}
            
            bean.getCategories().forEach((CategoryBean categoryBean) -> 
            entity.getCategories().add(categoryBeanTransformer.convert(categoryBean)));
        }
        
        if (bean.getIngredients() != null && bean.getIngredients().size() > 0){
            bean.getIngredients().forEach((IngredientBean ingredientBean) -> 
            entity.getIngredients().add(ingredientBeanTransformer.convert(ingredientBean)));
        }
        
        return entity;
        
    }
}
