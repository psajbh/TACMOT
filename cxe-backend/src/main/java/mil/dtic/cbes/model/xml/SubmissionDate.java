package mil.dtic.cbes.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "submissiondate")
public class SubmissionDate {
	
	String submissionDateId; // Sep2019
	String code; // Sep2019
	String label; // September 2019
	
	public SubmissionDate() {}

	public SubmissionDate(String id, String code, String label) {
		this.submissionDateId = id;
		this.code = code;
		this.label = label;
	}
	
	public String getSubmissionDateId() {
		return submissionDateId;
	}

	@XmlAttribute(name = "id")
	public void setSubmissionDateId(String submissionDateId) {
		this.submissionDateId = submissionDateId;
	}

	public String getCode() {
		return code;
	}

	@XmlElement(name = "code")
	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "SubmissionDate [submissionDateId=" + submissionDateId + ", code=" + code + ", label=" + label + "]";
	}
	

}
