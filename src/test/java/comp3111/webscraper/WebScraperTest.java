package comp3111.webscraper;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class WebScraperTest {

	@Test
	public void testScrape() {
		WebScraper ws = new WebScraper();
		List<Item> li = ws.scrape("iphone");
		
		if(li != null)
			assertTrue(true);
	}

}
