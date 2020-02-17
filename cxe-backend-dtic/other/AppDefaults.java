package mil.dtic.cbes.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.cayenne.query.SelectQuery;

import mil.dtic.cbes.p40.vo.BudgetCycleConfig;
import mil.dtic.cbes.submissions.ValueObjects.BudgetCycle;
import mil.dtic.cbes.submissions.ValueObjects.R2Release;
import mil.dtic.cbes.submissions.ValueObjects.SubmissionDate;
import mil.dtic.utility.CayenneUtils;
import mil.dtic.utility.VersionProperties;

public class AppDefaults
{
  protected R2Release currentRelease;
  protected List<BudgetCycle> budgetCycles;
  protected List<SubmissionDate> submissionDates;
  protected String transformerFactory;
  protected List<R2Release> releaseList;
  protected List<XmlSchemaBean> schemaList;
  protected VersionProperties frontendVersion;

  public R2Release getCurrentRelease()
  {
    return currentRelease;
  }

  public void setCurrentRelease(R2Release currentRelease)
  {
    this.currentRelease = currentRelease;
  }

  /**
   * Gets Budget cycles both from spring config ONLY.
   * @return
   */
  public List<BudgetCycle> getDefaultBudgetCycles()
  {
    List<BudgetCycle> combinedCycles = new ArrayList<BudgetCycle>();
    combinedCycles.addAll(budgetCycles);
    Collections.sort(combinedCycles);
    return combinedCycles;
  }

  /**
   * Gets Budget cycles both from database ONLY. Note this will return cycles which would be over-ridden by the defaults
   * @return
   */
  public List<BudgetCycle> getDatabaseBudgetCycles()
  {
    //Create combined list of spring configured and database configured budget cycles
    List<BudgetCycle> combinedCycles = new ArrayList<BudgetCycle>();

    //Add DB configured budget cycles
    SelectQuery query = new SelectQuery(BudgetCycleConfig.class);

    @SuppressWarnings("unchecked")
    List<BudgetCycleConfig> budgetCycleConfigs = CayenneUtils.createDataContext().performQuery(query);

    for (BudgetCycleConfig budgetCycleConfig : budgetCycleConfigs)
    {
      BudgetCycle cycle = new BudgetCycle();
      cycle.setBudgetYear(budgetCycleConfig.getBudgetYear());
      cycle.setCycle(budgetCycleConfig.getCycle());
      cycle.setLabel(budgetCycleConfig.getLabel());
      cycle.setAmended(budgetCycleConfig.getAmended());

      List<mil.dtic.cbes.p40.vo.SubmissionDate> submissionDatesFromDatabase = budgetCycleConfig.getSubmissionDates();

      List<SubmissionDate> disconnectedSubmissionDates = new ArrayList<SubmissionDate>();

      for (mil.dtic.cbes.p40.vo.SubmissionDate budgetCycleSubmissionDate : submissionDatesFromDatabase)
      {
        SubmissionDate sd = new SubmissionDate();
        sd.setValue(budgetCycleSubmissionDate.getVal());
        sd.setLabel(budgetCycleSubmissionDate.getLabel());
        disconnectedSubmissionDates.add(sd);
      }

      cycle.setSubmissionDates(disconnectedSubmissionDates);
      combinedCycles.add(cycle);
    }

    Collections.sort(combinedCycles);

    return combinedCycles;
  }

  /**
   * Gets Budget cycles both from spring config and from database.
   * @return
   */
  public List<BudgetCycle> getBudgetCycles()
  {
    //Create combined list of spring configured and database configured budget cycles
    List<BudgetCycle> combinedCycles = new ArrayList<BudgetCycle>();

    //Add spring configured budget cycles
    combinedCycles.addAll(budgetCycles);

    //Add DB configured budget cycles
    SelectQuery query = new SelectQuery(BudgetCycleConfig.class);

    @SuppressWarnings("unchecked")
    List<BudgetCycleConfig> budgetCycleConfigs = CayenneUtils.createDataContext().performQuery(query);

    for (BudgetCycleConfig budgetCycleConfig : budgetCycleConfigs)
    {
      BudgetCycle cycle = new BudgetCycle();
      cycle.setBudgetYear(budgetCycleConfig.getBudgetYear());
      cycle.setCycle(budgetCycleConfig.getCycle());
      cycle.setLabel(budgetCycleConfig.getLabel());
      cycle.setAmended(budgetCycleConfig.getAmended());

      List<mil.dtic.cbes.p40.vo.SubmissionDate> submissionDates = budgetCycleConfig.getSubmissionDates();

      List<SubmissionDate> disconnectedSubmissionDates = new ArrayList<SubmissionDate>();

      for (mil.dtic.cbes.p40.vo.SubmissionDate budgetCycleSubmissionDate : submissionDates)
      {
        SubmissionDate sd = new SubmissionDate();
        sd.setValue(budgetCycleSubmissionDate.getVal());
        sd.setLabel(budgetCycleSubmissionDate.getLabel());
        disconnectedSubmissionDates.add(sd);
      }

      cycle.setSubmissionDates(disconnectedSubmissionDates);

      //If Budget cycle already exists, over-ride with database cycle
      if (hasBudgetCycle(combinedCycles, budgetCycleConfig))
      {
        for(int i = 0 ; i < budgetCycles.size() ; i++)
        {
          BudgetCycle defaultCycle = budgetCycles.get(i);

          if (defaultCycle.getBudgetYear().equals(cycle.getBudgetYear()) && defaultCycle.getCycle().equals(cycle.getCycle())
              && defaultCycle.getAmended().equals(cycle.getAmended())){
            budgetCycles.set(i, cycle);
          }

        }

      }else{
        combinedCycles.add(cycle);
      }

    }
    Collections.sort(combinedCycles);
    return combinedCycles;
  }

  /**
   * Helper method to determine if a BudgetCycle in the appDefaults is the same as one in the DB.
   * @param budgetCycles
   * @param budgetCycleConfig
   * @return
   */
  private boolean hasBudgetCycle(List<BudgetCycle> budgetCycles,BudgetCycleConfig budgetCycleConfig){

    for(BudgetCycle cycle : budgetCycles)
    {
      if(cycle.getBudgetYear().equals(budgetCycleConfig.getBudgetYear()) &&
        cycle.getCycle().equals(budgetCycleConfig.getCycle()) &&
        cycle.getAmended().equals(budgetCycleConfig.getAmended()))
      {
        return true;
      }
    }

    return false;

  }

  public void setBudgetCycles(List<BudgetCycle> budgetCycles)
  {
    Set<BudgetCycle> bset = new HashSet<BudgetCycle>();
    bset.addAll(budgetCycles);
    this.budgetCycles = new ArrayList<BudgetCycle>();
    this.budgetCycles.addAll(bset);
    Collections.sort(this.budgetCycles);

    Set<SubmissionDate> sset = new HashSet<SubmissionDate>();
    submissionDates = new ArrayList<SubmissionDate>();
    for (BudgetCycle budgetCycle : this.budgetCycles) {
      sset.addAll(budgetCycle.getSubmissionDates());
    }
    submissionDates.addAll(sset);
    Collections.sort(submissionDates);
  }

  public List<SubmissionDate> getSubmissionDates()
  {
    List<SubmissionDate> slist = new ArrayList<SubmissionDate>();
    slist.addAll(submissionDates);
    for(BudgetCycle budgetCycle : getDatabaseBudgetCycles()){
        slist.addAll(budgetCycle.getSubmissionDates());
    }
    Collections.sort(slist);
    return slist;
  }


  public String getTransformerFactory()
  {
    return transformerFactory;
  }

  public void setTransformerFactory(String transformerFactory)
  {
    this.transformerFactory = transformerFactory;
    //System.setProperty("javax.xml.transform.TransformerFactory", transformerFactory);
  }

  public List<R2Release> getReleaseList()
  {
    return releaseList;
  }

  public void setReleaseList(List<R2Release> releaseList)
  {
    this.releaseList = releaseList;
  }

  public List<XmlSchemaBean> getSchemaList()
  {
    return schemaList;
  }

  public void setSchemaList(List<XmlSchemaBean> schemaList)
  {
    this.schemaList = schemaList;
  }

  public VersionProperties getFrontendVersion()
  {
    return frontendVersion;
  }

  public void setFrontendVersion(VersionProperties frontendVersion)
  {
    this.frontendVersion = frontendVersion;
  }

  /**
   * Constructs a <code>String</code> with all attributes
   * in name = value format.
   *
   * @return a <code>String</code> representation
   * of this object.
   */
  @Override
  public String toString()
  {
      final String TAB = "    ";

      String retValue = "";

      retValue = "AppDefaults ( "
          + super.toString() + TAB
          + "currentRelease = " + this.currentRelease + TAB
          + "budgetCycles = " + this.budgetCycles + TAB
          + "submissionDates = " + this.submissionDates + TAB
          + "transformerFactory = " + this.transformerFactory + TAB
          + "releaseList = " + this.releaseList + TAB
          + " )";

      return retValue;
  }
}