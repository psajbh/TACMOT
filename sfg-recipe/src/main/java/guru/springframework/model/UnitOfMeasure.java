package guru.springframework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
/*
 * @Table(uniqueConstraints = {@UniqueConstraint(columnNames = "uom", name =
 * "uniqueNameConstraint")})
 */public class UnitOfMeasure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@Column(unique=true, nullable=false, length=255)
	private String uom; //description

}
