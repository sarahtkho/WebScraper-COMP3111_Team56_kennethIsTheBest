package comp3111.webscraper;


 /** 
  * @author Ho Wai Kin Johnny
  * 
  * Item contains the items information that are scraped from the selling portals, such as it's name, price, url and postdate
  * It implements Comparable<Item> for comparing the price between items
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
	 * @param title
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
	 * @param price
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
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Set item postdate
	 * @param postdate
	 */
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
	/**
	 * Get item post date
	 * @return
	 */
	public String getPostdate() {
		return postdate;
	}
	
	/**
	 * Used to sort the Vector<Item> result
	 * @param other
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
