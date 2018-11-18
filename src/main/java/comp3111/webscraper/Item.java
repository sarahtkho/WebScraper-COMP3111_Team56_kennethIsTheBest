package comp3111.webscraper;


 /** 
  * Contains the items information that are scraped from the selling portals, such as it's name, price, url and postdate
  * It implements interface Comparable for comparing the price between items
  * 
  * @author Ho Wai Kin Johnny
  */
public class Item implements Comparable<Item>{
	private String title ; 
	private double price ;
	private String url ;
	private String postdate;
	
	/**
	 * Default constrcutor
	 */
	public Item(){
		
	}
	
	/**
	 * Get item title
	 * @return Item title (e.g. iPhone 8 plus)
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set item title
	 * @param title The name of the item scraped from selling portals (e.g. iPhone 8 plus)
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Get item price
	 * @return Item price (e.g. 530.5)
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set item price
	 * @param price The price of the item scraped from selling portals (e.g. 530.5)
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Get item url
	 * @return Item url (e.g. https://newyork.craigslist.org/https://newyork.craigslist.org/wch/mob/d/iphone-8-plus/6751819289.html)
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Set item url
	 * @param url The url of the item scraped from selling portals (e.g. https://newyork.craigslist.org/https://newyork.craigslist.org/wch/mob/d/iphone-8-plus/6751819289.html)
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Set item postdate
	 * @param postdate The date of the item being posted on the selling portal (e.g. 2018-11-18 00:24)
	 */
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
	/**
	 * Get item post date
	 * @return The date of the item being posted on selling portal (e.g. 2018-11-18 00:24)
	 */
	public String getPostdate() {
		return postdate;
	}
	
	/**
	 * Used to sort all items by price
	 * @param other Another item in the result vector
	 * @return 1, -1 or 0 to indicate the compare result
	 */
	@Override
	public int compareTo(Item other) {
		if(this.price > other.price)
			return 1;
		else if(this.price < other.price)
			return -1;
		else
			return 0;
	}
}
