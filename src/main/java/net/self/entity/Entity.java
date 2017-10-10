package net.self.entity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Entity {

	private static final String SEPERATOR = ",";
	private static final String RESOURCE_LOCATION = "entity.properties";
	
	private String name;
	private String field1;
	private String field2;
	private String field3;
	
	
	public Entity(String name, String field1, String field2, String field3) {
		super();
		this.name = name;
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}


	
	private Properties properties;
	{
		properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream(RESOURCE_LOCATION));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Map<String, List<Object>> fillMap(String key){
		if(null==key||key.length()==0) {
			return null;
		}
		String property = properties.getProperty(key);
		if(property==null||property.length()==0) {
			return null;
		}
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		String[] fieldNames = property.split(SEPERATOR);
		try {
			List<Object> fieldList = new ArrayList<Object>();
			map.put(key, fieldList);
			for(String filedName:fieldNames) {
				Field field = this.getClass().getDeclaredField(filedName);
				field.setAccessible(true);
				fieldList.add(field.get(this));
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
