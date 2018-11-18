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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	public String getPostdate() {
		return postdate;
	}
	
	//Used to sort the Vector<Item> result
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
