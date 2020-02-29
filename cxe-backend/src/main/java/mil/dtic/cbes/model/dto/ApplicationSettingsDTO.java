package mil.dtic.cbes.model.dto;

public class ApplicationSettingsDTO implements IDto {

	private String settingName;
	
	private String setting_value;
	
	private String setting_description;
	
	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSetting_value() {
		return setting_value;
	}

	public void setSetting_value(String setting_value) {
		this.setting_value = setting_value;
	}

	public String getSetting_description() {
		return setting_description;
	}

	public void setSetting_description(String setting_description) {
		this.setting_description = setting_description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((settingName == null) ? 0 : settingName.hashCode());
		result = prime * result + ((setting_description == null) ? 0 : setting_description.hashCode());
		result = prime * result + ((setting_value == null) ? 0 : setting_value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ApplicationSettingsDTO other = (ApplicationSettingsDTO) obj;
		if (settingName == null) {
			if (other.settingName != null) {
				return false;
			}
		} else if (!settingName.equals(other.settingName)) {
			return false;
		}
		if (setting_description == null) {
			if (other.setting_description != null) {
				return false;
			}
		} else if (!setting_description.equals(other.setting_description)) {
			return false;
		}
		if (setting_value == null) {
			if (other.setting_value != null) {
				return false;
			}
		} else if (!setting_value.equals(other.setting_value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("ApplicationSettingsDTO [settingName=%s, setting_value=%s, setting_description=%s]",
				settingName, setting_value, setting_description);
	}

}
