package com.api.test.interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseConfig {

	
	public static final String propertyFileLocation = "src/assignment/url";
	public static final String PROPERTY_FILE = "PROPERTY_FILE";
	private static final String resourceFile = System.getProperty(PROPERTY_FILE);
	
	public static final String marvel_baseURL = "https://gateway.marvel.com/";
	public static final int comics_id = 27649 ;
	public static final int character_id = 1011010;
	public static final int wrong_character_id = 1011019666;
	public static final String marvel_character_name = "Spider-Man (Ultimate)";
	public static final String thumbnail_url = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/531771b4e8c60";
	
	public static final String timeStamp ="thesoer" ;
	public static final String apiKey = "001ac6c73378bbfff488a36141458af2";
	public static final String hashKey = "72e5ed53d1398abb831c3ceec263f18b";
	public static final String invalid_hashKey = "72e5ed53d1398abb831c3ceec26";
	public static final String unknown_apiKey = "78199acbe2838477359ba62a0b17fb8f";
	public static final String wrong_hash = "0374959fa9f47f2b694c0673c6aa19c1";
			
	public static String getMarvelURL() {
		
		try {
			return getConfigFor(marvel_baseURL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resourceFile;
	}
	
	private static Properties properties;
	private static Properties getProperties() throws Exception {
		
		if(properties == null) {
			
			properties = loadResourceFile(resourceFile);
		}
		return properties;
		
	}
	private static Properties loadResourceFile(String fileName) throws Exception {
		
		File resourceFile = new File(fileName);
		if(resourceFile.exists()) {
			
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(resourceFile);
			properties.load(fileInputStream);
			return properties;
		}
		return null;
	}
	
	public static String getConfigFor(String configItem) throws Exception {
		
		String fileName = System.getProperty(PROPERTY_FILE);
		System.out.println("test" +System.getProperty(PROPERTY_FILE));
		
		Properties properties = loadResourceFile(fileName);
		return System.getProperty(configItem) != null ?
				System.getProperty(configItem) : properties.getProperty(configItem);
	}
	
	
}
