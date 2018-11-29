package comp3111.webscraper;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class ControllerTest {
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
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
	public void testInitialize() {
		Controller c = new Controller();
		Method method = null;
		try {
			method = Controller.class.getDeclaredMethod("initualize");
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// fields need to be checked
		Field[] fields = new Field[9];
		fields[0] = findField("lastSearch");
		fields[1] = findField("textAreaConsole");
		fields[2] = findField("labelCount");
		fields[3] = findField("labelPrice");
		fields[4] = findField("labelMin");
		fields[5] = findField("labelLatest");
		fields[6] = findField("lastResult");
		fields[7] = findField("newResult");
		fields[8] = findField("textFieldKeyword");
		
		method.setAccessible(true);
		try {
			method.invoke(c);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("invokation error");
		}
		
		for(int i=0; i<9;i++) {
			if(fields[i]==null)
				fail("incorrect field name for ["+i+"]");
			else
				fields[i].setAccessible(true);
		}
		
		// MenuItem lastSearch
		try {
			MenuItem mi = (MenuItem)fields[0].get(c);
			assertEquals(mi.isDisable(),true);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("fail in field[0].get(c)");
		}
		
		// TextArea textAreaConsole
		try {
			TextArea ta = (TextArea)fields[1].get(c);
			assertEquals(ta.getText(),"");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("fail in field[1].get(c)");
		}
		
		// Label labelCount
		try {
			Label l = (Label)fields[2].get(c);
			assertEquals(l.getText(),"<total>");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("fail in field[2].get(c)");
		}
	}
}
