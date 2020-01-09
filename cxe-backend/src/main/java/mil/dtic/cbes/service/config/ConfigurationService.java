package mil.dtic.cbes.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.Config;
import mil.dtic.cbes.repositories.ConfigRepository;

@Service
public class ConfigurationService {

	  // See DB for description of the constants
	  private static final String DISABLE_RMI = "disable.rmi";
	  private static final String EMAIL_FROM = "email.From";
	  private static final String EMAIL_HOST = "email.Host";
	  private static final String EMAIL_TO = "email.to";
	  private static final String EMAIL_ON_STARTUP_APPS = "email.onStartupApps";
	  private static final String ENV_NAME = "env.name";
	  private static final String UNV_RULE_AGENCIES = "unverified.rule.agencies";
	  private static final String BUSINESS_TOUCHFILE_NAME = "businessrules.touchfile.name";
	  private static final String TOUCHFILE_POLL_FREQ = "touchfile.polling.frequency.secs";
	  private static final String JM_EMAIL_ENABLED = "jm.emailEnabled";
	  private static final String JM_EMAIL_FROM = "jm.emailFrom";
	  private static final String JM_EMAIL_TO = "jm.emailTo";
	  private static final String JM_ENABLE_AUTO_DEPLOY = "jm.enable.autodeploy";
	  private static final String JM_QUEUE_NAME = "jm.queueName";
	  private static final String JM_RESTART_RUNNING_JOBS = "jm.restartRunningJobs";
	  private static final String JM_RUN_WIZARD_JOBS_IMMEDIATELY = "jm.runWizardJobsImmediately";
	  private static final String R2_ADMIN_URL = "r2.adminUrl";
	  public static final String R2_ANNOUNCMENTS = "r2.announcements";
	  public static final String R2_HELP = "r2.help";
	  private static final String R2_BUDGET_CYCLE = "r2.budgetCycle";
	  
	  private static final String R2_FRIENDLY_EXCEPTIONS_ENABLED = "r2.friendlyExceptionsEnabled";
	  private static final String R2_HELP_FILE_PATH = "r2.helpFilePath";
	  public static final String R2_JB_PDF_PASSWORD = "r2.jbPdfPassword";
	  public static final String R2_JB_PDF_PASSWORD_ENABLE = "r2.jbPdfPasswordEnable";
	  private static final String R2_LOGIN_URL = "r2.loginUrl";
	  private static final String R2_LOGO_IMAGES_FOLDER = "r2.logoImagesFolder";
	  public static final String R2_MAINTENANCE_MODE_MSG = "r2.maintenanceModeMsg";
	  public static final String R2_MAINTENANCE_MODE_VALUE = "r2.maintenanceModeValue";
	  private static final String R2_NOTREGISTERED_URL = "r2.notregisteredurl";
	  private static final String R2_REGISTRATION_EMAIL_TO_ADMINS = "r2.registrationEmailToAdmins";
	  private static final String R2_REMOVE_FILE_ARTIFACTS = "r2.removeFileArtifacts";
	  private static final String R2_TRACKING_URL = "r2.trackingUrl";
	  private static final String R2_UPLOAD_FILE_STORAGE_PATH = "r2.uploadFileStoragePath";
	  private static final String R2_WHATS_NEW_FILE_PATH = "r2.whatsNewFilePath";
	  private static final String R2_WORKING_FOLDER = "r2.workingFolder";
	  private static final String R2_USER_WRITE = "r2.disableUserProfileUpdateOnLogin";
	  private static final String R2_REGISTRATION_EMAIL_TO_USER = "r2.registrationEmailToUser";
	//  @Deprecated
	//  private static final String P40_LIMITATIONS_HTML = "p40.limitationsHtml";
	  public static final String P40_GUIDE = "p40.guide";
	  public static final String P40_ANNOUNCEMENTS = "p40.announcements";
	  private static final String P40_CONCURRENT_WARNING_HTML = "p40.concurrentWarningHTML";
	  public static final String P40_MAINTENANCE_MODE_MSG = "r2.maintenanceModeMsg";
	  public static final String P40_MAINTENANCE_MODE_VALUE = "r2.maintenanceModeValue";
	  
	  private static final String P40_ENABLE_DELETE = "p40.enabledelete";
	  private static final String P40_ENABLE_EXTRA_VICON = "p40.enableextravalidicon";
	  private static final String P40_ONLY_VERIFIED_BRULES = "p40.enableonlyverifiedbizrules";
	  private static final String P40_HEARTBEAT_FILE = "p40.heartbeat.file";
	  private static final String P40_HEARTBEAT_FREQ = "p40.heartbeat.freq.secs";
	  private static final String P40_PERF_HACKS = "p40.perfhacks";
	  private static final String PRCP_IGNORE_P1 = "prcp.ignoreP1";
	  private static final String PRCP_IGNORE_R1 = "prcp.ignoreR1";
	  private static final String USER_EMAIL_ADDRESS = "user.emailAddress";
	  private static final String USER_FIRST_NAME = "user.firstName";
	  private static final String USER_FROM_LDAP_FOR_LOCAL_USER = "user.fromLdapForLocalUser";
	  private static final String USER_FULL_NAME = "user.fullName";
	  private static final String USER_LAST_NAME = "user.lastName";
	  private static final String USER_LOCAL_USER_ID = "user.localUserId";
	  private static final String USER_MIDDLE_INITIAL = "user.middleInitial";
	  private static final String USER_PHONE_NUMBER = "user.phoneNumber";
	  private static final String USER_R2_ROLE = "user.r2Role";
	  private static final String USER_R2_GROUP= "user.r2group";
	  
	   private static final String VSCAN_COMMAND_PATH = "vscan.commandPath";
	  private static final String VSCAN_MOVE_COMMAND_PATH = "vscan.moveCommandPath";
	  private static final String VSCAN_SANDBOX_FOLDER = "vscan.sandboxFolder";
	  private static final String LABEL_FOR_BES_JB = "xslt.jb.besLabel";	// long name for the BES cycle
	  private static final String LABEL_FOR_PB_JB = "xslt.jb.pbLabel";		// long name for the PB cycle
	  private static final String LABEL_FOR_BES = "xslt.besLabel";			// short name for the BES cycle
	  private static final String LABEL_FOR_PB = "xslt.pbLabel";			// short name for the PB cycle
	  private static final String LABEL_FOR_POM = "xslt.pomLabel";			// short name for the POM cycle
	  private static final String SCM_BUILD_URL = "scm.build.url";
	  private static final String JB_WARNING_STAMP = "jb.warningStamp";
	  private static final String JB_WARNING_STAMP_HORIZONTAL_PAGE_OFFSET = "jb.warningStamp.horizontalPageOffset";
	  private static final String JB_WARNING_STAMP_VERTICAL_PAGE_OFFSET = "jb.warningStamp.verticalPageOffset";
	  private static final String JB_DRAFT_STAMP = "jb.draftStamp";
	  private static final String JB_PREPRCP_STAMP = "jb.prePrcpStamp";
	  private static final String JB_REVIEW_STAMP = "jb.reviewStamp";
	  private static final String JB_REVIEWERS_EMAIL = "jb.reviewers";
	  private static final String JB_REST_RFR_JSON = "jb.rest.rfr.json";
	  
	  private static final String XSLT_USE_JAVA_FOR_FOOTNOTE_NUMBERING = "xslt.useJavaForFootnoteNumbering";
	  private static final String DISABLE_COMPILED_XSLT_CACHING = "xslt.DisableCompiledXsltCaching";
	  private static final String BROWSEABLE_LOG_DIRS = "debug.browseableLogDirs";
	  private static final String XML_RESOURCES_LOAD_FROM = "xml.resources.loadFrom";
	  private static final String XML_RESOURCES_BASE_DIR = "xml.resources.baseDir";
	  private static final String XML_RESOURCES_POPULATE_BASE_DIR_AT_STARTUP = "xml.resources.populateBaseDirAtStartup";
	  private static final String JBWIZ_GENERATE_P1R1_REQUIRED = "jbwiz.generateP1R1Required";
	  private static final String JBWIZ_ATTACH_P1R1_REQUIRED = "jbwiz.attachP1R1Required";
	  private static final String JBWIZ_HELP = "jbwiz.help";
	  private static final String JBWIZ_P40_BASIC_INFO_HELP = "jbwiz.p40BasicInfoHelp";
	  private static final String JBWIZ_R2_BASIC_INFO_HELP = "jbwiz.r2BasicInfoHelp";
	  private static final String JBWIZ_P40_EXHIBIT_SELECT_HELP = "jbwiz.p40ExhibitSelectHelp";
	  private static final String JBWIZ_R2_EXHIBIT_SELECT_HELP = "jbwiz.r2ExhibitSelectHelp";
	  private static final String JBWIZ_SINGLE_VOLUMES_HELP = "jbwiz.singleVolumesHelp";
	  private static final String JBWIZ_MULTI_VOLUMES_HELP = "jbwiz.multiVolumesHelp";
	  private static final String JBWIZ_P40_TABLE_OF_CONTENTS_HELP = "jbwiz.p40TableOfContentsHelp";
	  private static final String JBWIZ_R2_TABLE_OF_CONTENTS_HELP = "jbwiz.r2TableOfContentsHelp";
	  private static final String JBWIZ_SINGLE_VOLUME_BREAKDOWN_HELP = "jbwiz.singleVolumeBreakdownHelp";
	  private static final String JBWIZ_P40_MULTI_VOLUME_BREAKDOWN_HELP = "jbwiz.p40MultiVolumeBreakdownHelp";
	  private static final String JBWIZ_R2_MULTI_VOLUME_BREAKDOWN_HELP = "jbwiz.r2MultiVolumeBreakdownHelp";
	  private static final String JBWIZ_P40_ATTACHMENTS_HELP = "jbwiz.p40AttachmentsHelp";
	  private static final String JBWIZ_R2_ATTACHMENTS_HELP = "jbwiz.r2AttachmentsHelp";
	  private static final String JBWIZ_SUMMARY_AND_BUILD_HELP = "jbwiz.summaryAndBuildHelp";
	  private static final String JBWIZ_CONFIRMATION_HELP = "jbwiz.confirmationHelp";
	  private static final String BUILDMJB_R2_OVERVIEW_HELP = "buildmjb.r2overviewHelp";
	  private static final String BUILDMJB_P40_OVERVIEW_HELP = "buildmjb.p40overviewHelp";
	  private static final String OCO_SUPPRESS = "oco.suppressed";
	  private static final String OCO_COLLAPSE = "oco.collapse";
	  private static final String MAINTENANCE_MESSAGE = "maintenance.message";
	  private static final String DEFAULT_CYCLE_TXT = "xxxxx";
	  private static final String CLASSIFICATION_LABEL = "classification.label"; // Such as "UNCLASSIFIED" or "SECRET".
	  private static final String CLASSIFICATION_SYMBOL = "classification.symbol"; // Such as "U" or "S".
	  private static final String UNCLASSIFIED = "UNCLASSIFIED";
	  
	  private static final String U = "U";
	  public static final String CLASSIFICATION_BANNER = "classification.banner";
	  public static final String CLASSIFICATION_CSS = "classification.css";
	  public static final String CLASSIFICATION_STYLE = "classification.style";
	  private static final String BES = "BES";
	  private static final String PB = "PB";
	  private static final String POM = "POM";
	  private static final int DEFAULT_VALUE = 25;
	  private static final int DEFAULT_TOUCHFILE_POLL_FREQ = 60;
	  private static final int DEFAULT_P40_HEARTBEAT_FILE_FREQ = 60;
	  private static final String DRAFT = "DRAFT";
	  private static final String REVIEW = "REVIEW";
	  private static final String PREPRCP = "PREPRCP";
	  private static final String UPLOAD_WHITELIST = "upload.whitelist";
	  private static final String FLUID_ROLE = "user.fluid.role";
	  
	  private static final String JB_WORKFLOW_STATUS_DRAFT_DISABLED = "jb.workFlowStatusDraftDisabled";
	  private static final String JB_WORKFLOW_STATUS_PRE_PRCP_DISABLED = "jb.workFlowStatusPrePRCPDisabled";
	  private static final String JB_WORKFLOW_STATUS_RFR_DISABLED = "jb.workFlowStatusRFRDisabled";
	  private static final String JB_WORKFLOW_STATUS_FINAL_DISABLED = "jb.workFlowStatusFinalDisabled";
	  
	  @Autowired
	  private ConfigRepository configRepo;
	
	@Value("${mail.host}")
	private String emailHost;
	
	@Value("${mail.from}")
	private String emailFrom;
	
	@Value("${mail.environment}")
	private String emailEnvironment;
	
	@Value("${mail.to}")
	private String emailTo;
		
	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailEnvironment() {
		return emailEnvironment;
	}

	public void setEmailEnvironment(String emailEnvironment) {
		this.emailEnvironment = emailEnvironment;
	}
	
	public String getConfigValueByName(String name) {
		return configRepo.findByName(name).getValue();
	}
	
	public String getR2UploadFileStoragePath() {
		return configRepo.findByName(R2_UPLOAD_FILE_STORAGE_PATH).getValue();
	}
	
	public String getSandboxPath() {
		return configRepo.findByName(VSCAN_SANDBOX_FOLDER).getValue();
	}
}
