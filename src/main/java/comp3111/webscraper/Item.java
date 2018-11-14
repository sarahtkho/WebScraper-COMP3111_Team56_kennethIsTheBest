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
		return postedDate.get(Calendar.YEAR) + "-" + (postedDate.get(Calendar.MONTH)+1) + "-" + postedDate.get(Calendar.DAY_OF_MONTH)+" "+postedDate.get(Calendar.HOUR)+":"+postedDate.get(Calendar.MINUTE);
		// Month get a +1 for MONTH start at 0; 0 == JAN
	}
	public void setDate(String searchDate) {
		postedDate.clear();
		if(!searchDate.contains("ago")) {
			this.postedDate.set(Integer.parseInt(searchDate.substring(0,searchDate.indexOf("-"))),
				Integer.parseInt(searchDate.substring(searchDate.indexOf("-")+1, searchDate.lastIndexOf("-")))-1,	// -1 for month is start at 0 -> 0 == JANUARY
				Integer.parseInt(searchDate.substring(searchDate.lastIndexOf("-")+1,searchDate.lastIndexOf("-")+3 )),
				Integer.parseInt(searchDate.substring(searchDate.indexOf(":")-2,searchDate.indexOf(":"))),
				Integer.parseInt(searchDate.substring(searchDate.indexOf(":")+1, searchDate.indexOf(":")+3)));
		} else {
			this.postedDate = Calendar.getInstance();
			if(searchDate.contains("hour"))
				postedDate.add(Calendar.HOUR, Integer.parseInt(searchDate.substring(0, searchDate.indexOf(" ")))*-1);
			else if(searchDate.contains("day"))
				postedDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(searchDate.substring(0, searchDate.indexOf(" ")))*-1);
			else //if (searchDate.contains("minute"))
				postedDate.add(Calendar.MINUTE, Integer.parseInt(searchDate.substring(0, searchDate.indexOf(" ")))*-1);
		}
	}

}
