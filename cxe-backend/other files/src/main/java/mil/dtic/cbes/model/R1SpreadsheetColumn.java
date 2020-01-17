package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class R1SpreadsheetColumn {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int r1spreadsheetcolumnId;
	protected String columnTitle;
	protected String columnLetter;
	protected int columnOrder;
	
	public R1SpreadsheetColumn() {
		
	}

	public int getR1spreadsheetcolumn_id() {
		return r1spreadsheetcolumnId;
	}

	public void setR1spreadsheetcolumn_id(int r1spreadsheetcolumn_id) {
		this.r1spreadsheetcolumnId = r1spreadsheetcolumn_id;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	public String getColumnLetter() {
		return columnLetter;
	}

	public void setColumnLetter(String columnLetter) {
		this.columnLetter = columnLetter;
	}

	public int getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(int columnOrder) {
		this.columnOrder = columnOrder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
