package guru.springframework.backbeans;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.model.Category;
import guru.springframework.model.Difficulty;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeBean {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	//private Byte[] image;
	private NotesBean notes;
	private Set<IngredientBean> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private Set<CategoryBean> categories  = new HashSet<>();
}
