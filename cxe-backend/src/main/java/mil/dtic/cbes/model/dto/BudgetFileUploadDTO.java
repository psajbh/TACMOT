package mil.dtic.cbes.model.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class BudgetFileUploadDTO extends Dto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String createdBy;
	private Date dateCreated;
	private String description;
	private Long size;
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDateString() {
		if (dateCreated != null) {
			DateFormat format = DateFormat.getDateTimeInstance();
			return format.format(dateCreated);
		} else {
			return null;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormattedSize() {
		if (size != null) {
			return FileUtils.byteCountToDisplaySize(size);
		} else {
			return null;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
