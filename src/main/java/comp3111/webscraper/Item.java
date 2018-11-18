package comp3111.webscraper;


 /** 
  * @author Ho Wai Kin Johnny
  * 
  * Item contains the items information that are scraped from the selling portals, such as it's name, price, url and postdate
  * It implements interface Comparable for comparing the price between items
  *
  */
public class Item implements Comparable<Item>{
	private String title ; 
	private double price ;
	private String url ;
	private String postdate;
	
	/**
	 * Get item title
	 * @return item title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set item title
	 * @param title - Name of the item scraped from selling portals
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Get item price
	 * @return item price 
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set item price
	 * @param price - Price of the item scraped from selling portals
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Get item url
	 * @return item url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Set item url
	 * @param url - url of the item scraped from selling portals
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Set item postdate
	 * @param postdate - the date of the item being posted on the selling portal
	 */
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
	/**
	 * Get item post date
	 * @return postdate
	 */
	public String getPostdate() {
		return postdate;
	}
	
	/**
	 * Used to sort all items by price
	 * @param other - another item in the result vector
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
