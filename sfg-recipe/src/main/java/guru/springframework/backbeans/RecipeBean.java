package guru.springframework.backbeans;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import guru.springframework.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeBean {
	private Long id;
	
	@NotBlank
	@Size(min = 3, max  = 255)
	private String description;
	
	@NotNull
	@Min(1)
	@Max(999)
	private Integer prepTime;
	
	@Min(1)
	@Max(100)
	private Integer cookTime;
	
	@Min(1)
	@Max(100)
	private Integer servings;
	
	private String source;
	
	@NotBlank
	@URL
	private String url;
	
	@NotBlank
	private String directions;
	
	private Byte[] image;
	private NotesBean notes;
	private Set<IngredientBean> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private Set<CategoryBean> categories  = new HashSet<>();
}
