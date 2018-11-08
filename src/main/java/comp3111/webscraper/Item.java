package comp3111.webscraper;

import java.util.Calendar;

public class Item {
	private String title ; 
	private double price ;
	private String url ;
	private Calendar postedDate;
	
	public Item() {
		this.postedDate= Calendar.getInstance();
	}
	
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
	public Calendar getDate() {
		return postedDate;
	}
	public String getStringDate() {
		return postedDate.get(Calendar.YEAR) + "-" + postedDate.get(Calendar.MONTH) + "-" + postedDate.get(Calendar.DAY_OF_MONTH);
	}
	public void setDate(String searchDate) {
		this.postedDate.set(Integer.parseInt(searchDate.substring(0,searchDate.indexOf("-"))),
				Integer.parseInt(searchDate.substring(searchDate.indexOf("-")+1, searchDate.lastIndexOf("-"))),
				Integer.parseInt(searchDate.substring(searchDate.lastIndexOf("-")+1,searchDate.lastIndexOf("-")+3 )),
				Integer.parseInt(searchDate.substring(searchDate.indexOf(":")-2,searchDate.indexOf(":"))),
				Integer.parseInt(searchDate.substring(searchDate.indexOf(":")+1, searchDate.indexOf(":")+3)));
		
	}

}
