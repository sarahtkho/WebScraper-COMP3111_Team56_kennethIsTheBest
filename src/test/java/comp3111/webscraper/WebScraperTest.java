package comp3111.webscraper;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class WebScraperTest {
	
	private List<Item> result;
	WebScraper wsObject;
	
	public WebScraperTest() {
		WebScraper ws = new WebScraper();
		wsObject = ws; 
		result = ws.scrape("iphone");
	}
	
	@Test
	public void testScrape() {
		assertNotNull(result);
	}
	
	@Test
	public void testFormatKeyword() {
		WebScraper scraper = new WebScraper();
		String formattedKeyword = scraper.formatKeyword("(iphone 8)");
		assertEquals("(iphone 8)", formattedKeyword);
	}
	
	@Test
	public void testGetNumPage() {
		assertNotEquals(wsObject.getNumPage("craigslist"), 0);
		assertNotEquals(wsObject.getNumPage("preloved"), 0);
	}
	
	@Test
	public void testGetNumResults() {
		assertNotEquals(wsObject.getNumResults(), 0);
	}
}
