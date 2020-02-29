package mil.dtic.cbes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ApplicationSettings")
public class ApplicationSettings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_setting_id")
	private Integer applicationSettingID;
	
	@Column(name = "setting_name")
	private String settingName;
	
	@Column(name = "setting_value")
	private String settingValue;
	
	@Column(name = "setting_description")
	private String settingDescription;

	public Integer getApplicationSettingID() {
		return applicationSettingID;
	}

	public void setApplicationSettingID(Integer applicationSettingID) {
		this.applicationSettingID = applicationSettingID;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	public String getSettingDescription() {
		return settingDescription;
	}

	public void setSettingDescription(String settingDescription) {
		this.settingDescription = settingDescription;
	}

	@Override
	public String toString() {
		return String.format(
				"ApplicationSettings [applicationSettingID=%s, settingName=%s, settingValue=%s, settingDescription=%s]",
				applicationSettingID, settingName, settingValue, settingDescription);
	}
}
