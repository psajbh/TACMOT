package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mapping {

	public static final String MAPPING_ID = "mappingId";
	public static final String RAW_COLUMN = "rawColumn";
	public static final String VALID_COLUMN = "validColumn";
	public static final String TYPE_ID = "typeId";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int mappingId;
	public String rawColumn;
	public String validColumn;
	public int typeId;

	public Mapping()
	{
	}
	
	public int getMapping_id() {
		return mappingId;
	}

	public void setMapping_id(int mapping_id) {
		this.mappingId = mapping_id;
	}

	public String getRaw_column() {
		return rawColumn;
	}

	public void setRaw_column(String raw_column) {
		this.rawColumn = raw_column;
	}

	public String getValid_column() {
		return validColumn;
	}

	public void setValid_column(String valid_column) {
		this.validColumn = valid_column;
	}

	public int getType_id() {
		return typeId;
	}

	public void setType_id(int type_id) {
		this.typeId = type_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
