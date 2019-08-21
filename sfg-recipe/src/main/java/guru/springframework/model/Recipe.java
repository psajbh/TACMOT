package guru.springframework.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	
	
	@Lob
	private String directions;
	
	@Lob
	private Byte[] image;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Notes notes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable(name="recipe_category",  //join table name 
		joinColumns = @JoinColumn(name = "recipe_id"), // recipe join column name
		inverseJoinColumns = @JoinColumn(name = "category_id")) // category join column name
	private Set<Category> categories = new HashSet<>();
	
	public void setNotes(Notes notes) {
		if (null != notes) {
			this.notes = notes;
			notes.setRecipe(this);
		}
	}
	
	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
	}
	
	
	

}
