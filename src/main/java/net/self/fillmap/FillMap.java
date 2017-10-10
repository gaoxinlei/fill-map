package net.self.fillmap;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import net.self.entity.Entity;

public class FillMap {


	public static void main(String[] args) {
		Entity entity = new Entity("test", "field1:value1", "field2:value2", "field3:value3");
		Map<String, List<Object>> map1 = entity.fillMap("key_1");
		Map<String, List<Object>> map2 = entity.fillMap("key_2");
		System.out.println(map1.get("key_1").get(0));
		System.out.println(map2.get("key_2").get(1));
	}
}
