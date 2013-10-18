package beans.properties;

import java.util.ResourceBundle;

public class Properties {
	
	/**
	 * 
	 */
	public Properties()
	{
		
	}
	/**
	 * This very simple method retrieves a value based on the key or name of the property file.
	 */
	public String getResourceValue(String key)
	{
		String resourceValue = "";
		resourceValue = ResourceBundle.getBundle("quizlet.bundle.quizlet_en").getString(key);
		return resourceValue;
	}
}
