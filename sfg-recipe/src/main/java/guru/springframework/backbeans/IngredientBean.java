package guru.springframework.backbeans;

import java.math.BigDecimal;

import guru.springframework.model.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientBean {
	
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasure uom;
}
