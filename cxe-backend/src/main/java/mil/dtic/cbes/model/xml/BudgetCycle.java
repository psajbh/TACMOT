package mil.dtic.cbes.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement(name = "budgetcycle")
	public class BudgetCycle {

		String budgetCycleId; //BES2010
		String label; // 'PB 2020'
		String cycle; // 'PB', 'BES', 'POM' etc.
		String budgetYear; // '2020';
		List<SubmissionDate> submissionDates;

		public BudgetCycle() {}
		
		public BudgetCycle(String budgetCycleId, String label, String cycle, String budgetYear) {
			this.budgetCycleId = budgetCycleId;
			this.label = label;
			this.cycle = cycle;
			this.budgetYear = budgetYear;
		}

		public BudgetCycle(String budgetCycleId, String label, String cycle, String budgetYear, List<SubmissionDate> submissionDates) {
			this.budgetCycleId = budgetCycleId;
			this.label = label;
			this.cycle = cycle;
			this.budgetYear = budgetYear;
			this.submissionDates = submissionDates;
		}

		public String getBudgetCycleId() {
			return budgetCycleId;
		}

		@XmlAttribute(name = "id")
		public void setBudgetCycleId(String budgetCycleId) {
			this.budgetCycleId = budgetCycleId;
		}

		public String getBudgetYear() {
			return budgetYear;
		}

		@XmlElement(name = "budgetyear")
		public void setBudgetYear(String budgetYear) {
			this.budgetYear = budgetYear;
		}

		public String getLabel() {
			return label;
		}

		@XmlElement(name = "label")
		public void setLabel(String label) {
			this.label = label;
		}

		public String getCycle() {
			return cycle;
		}

		@XmlElement(name = "cycle")
		public void setCycle(String cycle) {
			this.cycle = cycle;
		}

		public List<SubmissionDate> getSubmissionDates() {
			if (null == this.submissionDates) {
				this.submissionDates = new ArrayList<SubmissionDate>();
			}
			return submissionDates;
		}

		@XmlElement(name = "submissiondates")
		public void setSubmissionDates(List<SubmissionDate> submissionDates) {
			this.submissionDates = submissionDates;
		}

		@Override
		public String toString() {
			return "BudgetCycle [budgetCycleId=" + budgetCycleId + ", label=" + label + ", cycle=" + cycle
					+ ", budgetYear=" + budgetYear + ", submissionDates=" + submissionDates + "]";
		}



}
