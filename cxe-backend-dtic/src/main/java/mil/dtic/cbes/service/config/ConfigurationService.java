package mil.dtic.cbes.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ConfigurationService {

    @Value("${mail.host}")
    private String emailHost;

    @Value("${mail.from}")
    private String emailFrom;

    @Value("${mail.environment}")
    private String emailEnvironment;

    @Value("${mail.to}")
    private String emailTo;

    @Value("${dev.user:}")
    private String devUser;

    @Value("${dev.computer.name:}")
    private String devComputerName;

    @Value("${key.header:}")
    private String keyHeader;
    
    @Value("${r2.uploadFileStoragePath}")
    private String uploadStoragePath;
    
    @Value("${vscan.sandbox.path}")
    private String sandboxFolder;
    
    @Value("${vscan.app.directory}")
    private String vscanAppDirectory;

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

    public String getDevUser() {
        return devUser;
    }
    
	public void setDevUser(String devUser) {
		this.devUser = devUser;
	}

    public String getDevComputerName() {
        return devComputerName;
    }
    
	public void setDevComputerName(String devComputerName) {
		this.devComputerName = devComputerName;
	}

    public String getKeyHeader() {
        return keyHeader;
    }

	public void setKeyHeader(String keyHeader) {
		this.keyHeader = keyHeader;
	}

	public String getUploadStoragePath() {
		return uploadStoragePath;
	}

	public void setUploadStoragePath(String uploadStoragePath) {
		this.uploadStoragePath = uploadStoragePath;
	}

	public String getSandboxFolder() {
		return sandboxFolder;
	}

	public void setSandboxFolder(String sandboxFolder) {
		this.sandboxFolder = sandboxFolder;
	}

	public String getVscanAppDirectory() {
		return vscanAppDirectory;
	}

	public void setVscanAppDirectory(String vscanAppDirectory) {
		this.vscanAppDirectory = vscanAppDirectory;
	}
    
}
