package mil.dtic.cbes.utils.aspect;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.service.config.ConfigurationService;

@Component
public abstract class FakeSiteminderSupport {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String OS = "os.name";
    private static final String COMPUTER_NAME = "COMPUTERNAME";
    private static final String HOST_NAME = "HOSTNAME";
    private static final String WIN = "win";
    private static final String MAC = "mac";

    @Autowired
    protected ConfigurationService configurationService;
    
    private String secondaryHost = null;
    private String host = null;

    protected String processFakeSiteminder() {
        log.info("processFakeSiteminder- start");
        
        if (isNotaserver(System.getProperty(FakeSiteminderSupport.OS).toLowerCase())) {
            
            try {
                host = InetAddress.getLocalHost().getHostName();
            }
            catch(UnknownHostException uhe) {
                log.error("processFakeSiteminder - "+uhe.getMessage(),uhe);
                setSecondarySupportToDetermineHostName();
            }
            
            return (null != host) ? getUserNameFromHost(host) : secondaryHost; 
        }
        
        return null;
    }
    
    private String getUserNameFromHost(String host) {
        String configComputerName = configurationService.getDevComputerName();
        
        if (host.equals(configComputerName)) {
            return configurationService.getDevUser();
        }
        
        return null;
    }
    
    private void setSecondarySupportToDetermineHostName() {
        Map<String, String> env = System.getenv();
        validateEnvironment(env.get(FakeSiteminderSupport.COMPUTER_NAME), FakeSiteminderSupport.HOST_NAME);
    }

    private boolean isNotaserver(String os) {
        
        if (null == os) {
            return false;
        }

        if (os.indexOf(FakeSiteminderSupport.WIN) >= 0 || os.indexOf(FakeSiteminderSupport.MAC) >= 0) {
            return true;
        }

        return false;
    }

    private void validateEnvironment(String win, String mac) {
        if ((null == win && null == mac) || (null != win && null != mac)){
            return;
        }

        secondaryHost = (null != win) ? win : mac;
    }

}
