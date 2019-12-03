package com.jhart.config;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhart.util.BuildModel;

@Configuration
public class AppConfig {
	
	//supports PomInfoController
	@Bean
	public BuildModel buildModel() throws IOException, XmlPullParserException{
    	MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        BuildModel buildModel = new BuildModel();
        buildModel.setModel(model);
        return buildModel;
	}

}
