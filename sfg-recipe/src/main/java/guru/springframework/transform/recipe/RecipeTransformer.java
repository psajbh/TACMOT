package guru.springframework.transform.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.backbeans.CategoryBean;
import guru.springframework.backbeans.IngredientBean;
import guru.springframework.backbeans.RecipeBean;
import guru.springframework.model.Category;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.transform.category.CategoryBeanTransformer;
import guru.springframework.transform.category.CategoryTransformer;
import guru.springframework.transform.ingredient.IngredientBeanTransformer;
import guru.springframework.transform.ingredient.IngredientTransformer;
import guru.springframework.transform.note.NotesBeanTransformer;
import guru.springframework.transform.note.NotesTransformer;
import lombok.Synchronized;

@Component
public class RecipeTransformer implements Converter<Recipe, RecipeBean> {
    
    private final CategoryTransformer categoryTransformer;
    private final IngredientTransformer ingredientTransformer;
    private final NotesTransformer notesTransformer;
    
    @Autowired
    public RecipeTransformer(CategoryTransformer categoryTransformer, 
            IngredientTransformer ingredientTransformer, 
            NotesTransformer notesTransformer) {
        this.categoryTransformer = categoryTransformer;
        this.ingredientTransformer = ingredientTransformer;
        this.notesTransformer = notesTransformer;
    }
    
    @Synchronized
    @Nullable
    @Override
    public RecipeBean convert(Recipe source) {
        if (null == source) {
            return null;
        }
        
        RecipeBean bean = new RecipeBean();
        bean.setId(source.getId());
        bean.setDescription(source.getDescription());    

        bean.setPrepTime(source.getPrepTime());
        bean.setCookTime(source.getCookTime());
        bean.setDifficulty(source.getDifficulty());
        bean.setDirections(source.getDirections());
        bean.setServings(source.getServings());
        bean.setSource(source.getSource());
        bean.setUrl(source.getUrl());
        bean.setNotes(notesTransformer.convert(source.getNotes()));
        
        if (source.getCategories() != null && source.getCategories().size() > 0){
            
            source.getCategories().forEach((Category category) -> 
            bean.getCategories().add(categoryTransformer.convert(category)));
        }
        
        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            
            source.getIngredients().forEach((Ingredient ingredient) -> 
            bean.getIngredients().add(ingredientTransformer.convert(ingredient)));
        }
        
        return bean;
        
    }

}
