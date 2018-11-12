package comp3111.webscraper;


import org.junit.Test;
import static org.junit.Assert.*;


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
}
