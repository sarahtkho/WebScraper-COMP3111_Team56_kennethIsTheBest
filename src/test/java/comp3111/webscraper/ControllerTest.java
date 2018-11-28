package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.util.List;

public class ControllerTest {
	
	@Test
	public void testSetHostService() {
		WebScraperApplication wa = new WebScraperApplication();
		Controller con = new Controller();
		con.setHostServices(wa.getHostServices());
		Field field = null;
		try {
			field = Controller.class.getDeclaredField("hostService");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("incorrect implement of creation of field variable");
		}
		field.setAccessible(true);
		try {
			assertEquals(field.get(con),wa.getHostServices());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("incorrect from field.get(con)");
		}
		
	}
	
	private Field findField(String fieldName) {
		Field field = null;
		try {
			field = Controller.class.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("no such field name");
		}
		return field;
	}
	
	@Test
	public void testSummarizing() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		WebScraper ws = new WebScraper();
		Controller c = new Controller();
		List<Item> li = ws.scrape("abc");
		Class<?> listClass = List.class;
		Method method = Controller.class.getDeclaredMethod("summarizing", listClass);
		method.setAccessible(true);
		method.invoke(c, li);
	}
	
	@Test
	public void testActionAbout() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Controller c = new Controller();
		Method method = c.getClass().getDeclaredMethod("actionAbout");
		method.setAccessible(true);
		method.invoke(c);
	}
	
	@Test
	public void testActionNew() {
		
	}
	
	@Test 
	public void testActionSearch() {
		
	}
}
