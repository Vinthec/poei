package fr.vinthec.personnage.resources.hpapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "personnage-api")
public class ConfigProperties {
     
    private String url;

	public String getUrl() {
		return url;
	}
 
    void setUrl(String url) {
		this.url = url;
	}
    
}