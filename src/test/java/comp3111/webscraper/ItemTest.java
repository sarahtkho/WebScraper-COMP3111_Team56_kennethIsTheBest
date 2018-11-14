package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;


public class ItemTest {

	@Test
	public void testSetTitle() {
		Item i = new Item();
		i.setTitle("ABCDE");
		assertEquals(i.getTitle(), "ABCDE");
	}
	
	@Test
	public void testSetPrice() {
		Item i = new Item();
		i.setPrice(1.56);
		assertEquals(i.getPrice(), 1.56, 0.00000000000000005);
	}
	
	@Test
	public void testSetURL() {
		Item i = new Item();
		i.setUrl("http://yahoo.com.hk");
		assertEquals(i.getUrl(), "http://yahoo.com.hk");
	}
	
	@Test
	public void testSetDateCraig() {
		Item i = new Item();
		i.setDate("2018-12-21 11:58");
		assertEquals(i.getStringDate(), "2018-12-21 11:58");
	}
	
	@Test
	public void testSetDatePrelovedWithMinute() {
		Item i = new Item();
		i.setDate("1 minute ago");
		Calendar valid = Calendar.getInstance();
		valid.add(Calendar.MINUTE, -1);
		assertEquals(i.getDate().get(Calendar.MINUTE), valid.get(Calendar.MINUTE));
	}
	
	@Test
	public void testSetDatePrelovedWithDay() {
		Item i = new Item();
		i.setDate("1 day ago");
		Calendar valid = Calendar.getInstance();
		valid.add(Calendar.DAY_OF_MONTH, -1);
		assertEquals(i.getDate().get(Calendar.DAY_OF_MONTH), valid.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testSetDatePrelovedWithHour() {
		Item i = new Item();
		i.setDate("1 hour ago");
		Calendar valid = Calendar.getInstance();
		valid.add(Calendar.HOUR, -1);
		assertEquals(i.getDate().get(Calendar.HOUR), valid.get(Calendar.HOUR));
	}
}
