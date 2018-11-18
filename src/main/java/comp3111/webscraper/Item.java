package comp3111.webscraper;

import java.util.Calendar;

import javafx.scene.control.Hyperlink;

public class Item {
	private String title ; 
	private double price ;
	private String url ;
	private Calendar postedDate;
	private Hyperlink link;
	
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
	public void setLink() {
		link = new Hyperlink(url);
	}
	public Hyperlink getLink() {
		return link;
	}
	public Calendar getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Calendar postedDate) {
		this.postedDate = postedDate;
	}

}
