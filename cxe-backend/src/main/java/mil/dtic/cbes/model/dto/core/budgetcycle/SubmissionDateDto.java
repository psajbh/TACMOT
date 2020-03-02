package mil.dtic.cbes.model.dto.core.budgetcycle;

import java.util.Date;

public class SubmissionDateDto {
	
	String submissionDateId; // Sep2019
	String code; // Sep2019
	String label; // September 2019
	Integer rank;  // Jan = 1, Dec = 12
	Date submissionDate;
	
	public SubmissionDateDto() {}

	public String getSubmissionDateId() {
		return submissionDateId;
	}

	public void setSubmissionDateId(String submissionDateId) {
		this.submissionDateId = submissionDateId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubmissionDateDto other = (SubmissionDateDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubmissionDateDto [submissionDateId=" + submissionDateId + ", code=" + code + ", label=" + label + "]";
	}
	
	


}
