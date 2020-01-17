package mil.dtic.cbes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class P1SpreadsheetColumn {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int p1spreadsheetcolumnId;
	protected String columnTitle;
	protected String columnLetter;
	protected int columnOrder;
	
	public P1SpreadsheetColumn() {
		
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getP1spreadsheetcolumn_id() {
		return p1spreadsheetcolumnId;
	}

	public void setP1spreadsheetcolumn_id(int p1spreadsheetcolumn_id) {
		this.p1spreadsheetcolumnId = p1spreadsheetcolumn_id;
	}

	public int getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(int columnOrder) {
		this.columnOrder = columnOrder;
	}
}
