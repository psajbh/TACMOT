package mil.dtic.cbes.model;

import java.io.Serializable;
import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;

import mil.dtic.cbes.model.entities.IEntity;

@Entity
@Table(name="budget_file_upload")
public class UploadedBudgetFile implements IEntity, Serializable{
	
	  private static final long serialVersionUID = 1L;
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="bfu_ID")
	  protected Integer id;
	
	  protected transient boolean delete;
	
	  @Column(name="bfu_file_name")
	  private String name;
	
	  @Column(name="bfu_URL")
	  private String url;
	  
	  @Column(name="bfu_type")
	  private String type;
	
	  @Column(name="created_by_user")
	  private String createdBy;
	
	  @Column(name="date_created")
	  private Date dateCreated;
	
	  @Column(name="bfu_desc")
	  private String description;
	
	  private transient Long size;
	
	  private transient boolean availableOnFilesystem;
	  
	  public Object clone() throws CloneNotSupportedException{
	      return super.clone();
	  }
	  
	  public Integer getId() {
		return id;
	  }
	
	  public void setId(Integer id) {
		this.id = id;
	  }
	
	  public boolean isDelete() {
	    return delete;
	  }
	 
	  public void setDelete(boolean delete) {
	    this.delete = delete;
	  }
	
	  public String getName() {
	    return name;
	  }
	
	  public void setName(String name) {
	    this.name = name;
	  }
	
	  public String getUrl() {
	    return url;
	  }
	
	  public void setUrl(String url) {
	    this.url = url;
	  }
	
	  public String getCreatedBy() {
	    return createdBy;
	  }
	
	  public void setCreatedBy(String createdBy) {
	    this.createdBy = createdBy;
	  }
	
	  public Date getDateCreated() {
	    return dateCreated;
	  }
	
	  public void setDateCreated(Date dateCreated) {
	    this.dateCreated = dateCreated;
	  }
	
	  public boolean isAvailableOnFilesystem() {
	    return availableOnFilesystem;
	  }
	
	  public void setAvailableOnFilesystem(boolean availableOnFilesystem) {
	    this.availableOnFilesystem = availableOnFilesystem;
	  }
	
	  public String getDescription() {
	    return description;
	  }
	
	  public void setDescription(String description) {
	    this.description = description;
	  }
	
	  public Long getSize() {
	    return size;
	  }
	
	  public void setSize(Long size) {
	    this.size = size;
	  }
	
	  public String getFormattedSize() {
	    if (size != null) {
	      return FileUtils.byteCountToDisplaySize(size);
	    } else {
	      return null;
	    }
	  }
	  
	  public URI getFileURI() {
		if (url != null) {
			return URI.create(url);
		} else {
			return null;
		}
	  }
	
	  public void setFileURI(URI uri) {
	    this.url = uri.toString();
	  }
	
//	  public String getType() {
//	    return name.substring(name.lastIndexOf('.') + 1);
//	  }
	  	
	  public String getDateString() {
		if (dateCreated != null) {
			DateFormat format = DateFormat.getDateTimeInstance();
			return format.format(dateCreated);
		} else {
			return null;
		}
	  }
	
	  public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		  return "UploadedBudgetFile [name=" + name + ", url=" + url + ", dateCreated=" + dateCreated + ", size=" + size + ", id=" + id + "]";
	  }
}
