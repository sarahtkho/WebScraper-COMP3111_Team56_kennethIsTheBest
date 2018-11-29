package comp3111.webscraper;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

public class WebScraperTest {
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	@Test
	public void testScrape() {
		WebScraper scraper = new WebScraper();
		assertNotNull(scraper.scrape("iphone"));
	}
	
	@Test
	public void testFormatKeyword() {
		WebScraper scraper = new WebScraper();
		String formattedKeyword = scraper.formatKeyword("(iphone 8)");
		assertEquals("(iphone 8)", formattedKeyword);
	}
	
	@Test
	public void testGetNumPage() {
		WebScraper scraper = new WebScraper();
		scraper.scrape("iphone");
		assertNotEquals(scraper.getNumPage("craigslist"), 0);
		assertNotEquals(scraper.getNumPage("preloved"), 0);
	}
	
	@Test
	public void testGetNumResults() {
		WebScraper scraper = new WebScraper();
		scraper.scrape("iphone");
		assertNotEquals(scraper.getNumResults(), 0);
	}
}
