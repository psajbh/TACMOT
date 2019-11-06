package com.jhart.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jhart.dto.BuildItemDTO;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.jhart.dto.BuildItemDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class BuildInfoController {
	
    private final static String GIT_BRANCH = "git.branch";
    private final static String GIT_BUILD_HOST= "git.build.host";
    private final static String GIT_BUILD_TIME = "git.build.time";
    private final static String GIT_BUILD_VER = "git.build.version";
    private final static String GIT_COMMIT_ID = "git.commit.id";
    private final static String GIT_COMMIT_ID_ABRV = "git.commit.id.abbrev";
    private final static String GIT_COMMIT_MSG_FULL = "git.commit.message.full";
    
    private final static String GIT_FILE = "git.properties";
    private final static String ERROR_MSG = "Build information could not be retrieved";
    private final static String ERROR_TYPE = "ERROR: ";
    private final static String EOL = "\n";
    //private final static String INIT_DATATABLE = "setupDatatable();";
    
    private final static String BRANCH = "BRANCH: ";
    private final static String HOST = "HOST: ";
    private final static String VERSION = "VERSION: ";
    private final static String TIME = "TIME: ";
    private final static String COMMIT_ID = "COMMIT_ID: ";
    private final static String COMMIT_ID_SHORT = "COMMIT_ID (Short): ";
    private final static String COMMIT_ID_MSG = "COMMIT MSG:";
	
	@GetMapping("/buildInfo")
	public String buildInfo() {
		return "buildInfo";
	}
	
	public String  getBuildInfo() {
		
		List<BuildItemDTO> buildItems = new ArrayList<>();
		String json = readGitProperties();
		
        if (json.equals(BuildInfoController.ERROR_MSG)) {
            buildItems.add(createBuildItem(BuildInfoController.ERROR_TYPE, BuildInfoController.ERROR_MSG));
        }
        else {
            Gson gson = new Gson();
            Type StringMap = new TypeToken<Map<String, String>>(){}.getType();
            Map<String,String> map = gson.fromJson(json, StringMap);
            buildItems.add(createBuildItem(BuildInfoController.BRANCH, map.get(BuildInfoController.GIT_BRANCH)));
            buildItems.add(createBuildItem(BuildInfoController.HOST, map.get(BuildInfoController.GIT_BUILD_HOST)));
            buildItems.add(createBuildItem(BuildInfoController.VERSION, map.get(BuildInfoController.GIT_BUILD_VER)));
            buildItems.add(createBuildItem(BuildInfoController.TIME, map.get(BuildInfoController.GIT_BUILD_TIME)));
            buildItems.add(createBuildItem(BuildInfoController.COMMIT_ID, map.get(BuildInfoController.GIT_COMMIT_ID)));
            buildItems.add(createBuildItem(BuildInfoController.COMMIT_ID_SHORT, map.get(BuildInfoController.GIT_COMMIT_ID_ABRV)));
            buildItems.add(createBuildItem(BuildInfoController.COMMIT_ID_MSG, map.get(BuildInfoController.GIT_COMMIT_MSG_FULL)));
        }
        
        log.debug(buildItems.toString());
		return buildItems.toString();
		
	}
	
    private BuildItemDTO createBuildItem(String type, String value) {
        BuildItemDTO buildItem = new BuildItemDTO(type, value);
        return buildItem;
    }
	
	private String readGitProperties() {

	    ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("git.properties");
	    try {
	        return readFromInputStream(inputStream);
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	        return "Version information could not be retrieved";
	    }
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
	    StringBuilder resultStringBuilder = new StringBuilder();

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    }

	    return resultStringBuilder.toString();

	}	

}
