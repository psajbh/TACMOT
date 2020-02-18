package mil.dtic.cbes.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "submissiondates")
public class SubmissionDates {
	
	List<SubmissionDate> submissionDates;
	
	public List<SubmissionDate> getSubmissionDates() {
		return submissionDates;
	}
	
	@XmlElement(name = "submissionDate")
	public void setSubmissionDates(List<SubmissionDate> submissionDates) {
		this.submissionDates = submissionDates;
	}
	
	public void add(SubmissionDate submissionDate) {
		if(this.submissionDates == null) {
			this.submissionDates = new ArrayList<SubmissionDate>();
		}
		this.submissionDates.add(submissionDate);
	}
	
	
	

}
