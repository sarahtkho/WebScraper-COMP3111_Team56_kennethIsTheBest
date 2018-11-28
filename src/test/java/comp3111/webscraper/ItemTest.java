package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;
/*
<<<<<<< HEAD
=======*/
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
		i.setPrice(5000.3);
		/*
		 *  Test the relative error in terms of ULPs since the price is stored in double (which allows floating point values).  
		 *  Select a tolerance somewhere between 1 and 10 ULPs. 
		 *  For example, here I specify that the actual result needs to be within 5 ULPs of the true value:
		 */
		assertEquals(i.getPrice(), 5000.3, 5*Math.ulp(i.getPrice()));
		
		/*i.setPrice(1.56);
		assertEquals(i.getPrice(), 1.56, 0.00000000000000005);*/
	}
	
	@Test
	public void testSetUrl() {
		Item i = new Item();
		i.setUrl("https://newyork.craigslist.org/");
		assertEquals(i.getUrl(), "https://newyork.craigslist.org/");
		
		/*Item j = new Item();
		j.setUrl("http://yahoo.com.hk");
		assertEquals(j.getUrl(), "http://yahoo.com.hk");*/
	}
	
	/*@Test
	public void testSetPostdate() {
		Item i = new Item();
		i.setPostdate("2018-11-18 00:24");
		assertEquals(i.getPostdate(), "2018-11-18 00:24");
	}*/
	
	@Test
	public void testCompareTo() {
		Item i = new Item();
		Item j = new Item();
		i.setPrice(2000.0);
		j.setPrice(3000.0);
		assertEquals(-1, i.compareTo(j));
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
