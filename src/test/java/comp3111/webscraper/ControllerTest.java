package comp3111.webscraper;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ControllerTest {
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();	
	
	private List<Item> result;
	
	public ControllerTest() {
		WebScraper ws = new WebScraper();
		result = ws.scrape("iphone");
	}
	
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
	public void testSummarazing() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, InstantiationException  {
		Controller c = new Controller();
		Method m = c.getClass().getDeclaredMethod("summarizing", List.class);
		m.setAccessible(true);
		m.invoke(c,result);
		
		Field[] fields = new Field[4];
		fields[0] = findField("labelCountString");
		fields[1] = findField("labelPriceString");
		fields[2] = findField("labelMinString");
		fields[3] = findField("labelLatestString");
		
		for(int i=0; i<4;i++) {
			if(fields[i]==null)
				fail("incorrect field name for ["+i+"]");
			else
				fields[i].setAccessible(true);
		}
		
		int countPrice = 0;
    	double totalPrice = 0.0;
    	Item minItem = null, lastDate = null;
    	for (Item item : result) {
    		if(item.getPrice()>0.0) {
    			countPrice++;
    			totalPrice+= item.getPrice();
    			if(minItem == null || minItem.getPrice()>item.getPrice()) {
    				minItem = item;
    			}
    			
    			if(lastDate == null || lastDate.getDate().before(item.getDate())){
    				lastDate = item;
    			}
    		}
    	}
		
		if(!fields[0].get(c).equals(Integer.toString(result.size()))) {
			fail(fields[0].get(c) +" "+ Integer.toString(result.size()));
		}
    	
    	DecimalFormat df = new DecimalFormat("#.00");
		if(!fields[1].get(c).equals( df.format(totalPrice/countPrice)))
			fail(fields[1].get(c) +" "+ df.format(totalPrice/countPrice));
		
		if(!fields[2].get(c).equals(Double.toString(minItem.getPrice())))
			fail(fields[2].get(c)+" "+Double.toString(minItem.getPrice()));
		
		if(!fields[3].get(c).equals(lastDate.getStringDate()))
			fail(fields[3].get(c)+" "+lastDate.getStringDate());
	}
}
