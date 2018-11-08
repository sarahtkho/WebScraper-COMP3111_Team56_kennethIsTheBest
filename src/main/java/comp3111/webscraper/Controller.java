/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;

import java.util.Calendar;
import java.util.List;

import javafx.application.*;

/**
 * 
 * @author kevinw
 *
 *
 * Controller class that manage GUI interaction. Please see document about JavaFX for details.
 * 
 */
public class Controller {

    @FXML 
    private Label labelCount; 

    @FXML 
    private Label labelPrice; 

    @FXML 
    private Hyperlink labelMin; 

    @FXML 
    private Hyperlink labelLatest; 

    @FXML
    private TextField textFieldKeyword;
    
    @FXML
    private TextArea textAreaConsole;
    
    private WebScraper scraper;
    
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {
    	
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    	labelCount.setText(Integer.toString(result.size()));
    	
    	// calculaate the avg price
    	int countPrice = 0;
    	double totalPrice = 0.0;
    	Item minItem = null, lastDate = null;
    	
    	// check if result have item inside (result.size() > 0 )
    	if (result.size() !=0) {
	    	for (Item item:result) {
	    		if(item.getPrice()>0.0) {
	    			countPrice++;
	    			totalPrice+= item.getPrice();
	    			// assign the first valid item to MIN or compare the item of MIN and in the result list 
	    			if(minItem == null || minItem.getPrice()>item.getPrice())
	    				minItem = item;
	    			// assign the first valid item to DATE or compare the item of DATE and in the result list
	    			if(lastDate == null || lastDate.getDate().before(item.getDate()))
	    				lastDate = item;
	    		}
	    	}
	    	
	    	labelPrice.setText(Double.toString(totalPrice/countPrice));
	    	labelMin.setText(Double.toString(minItem.getPrice()));
	    	labelLatest.setText(lastDate.getStringDate());
	    	
	    	
    	}
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
    }
    @FXML
    private void actionQuit() {
    	Platform.exit();
    	System.exit(0);
    }

    @FXML
    private void actionClose() {
    	
    }
    
    @FXML
    private void actionAbout() {
    	
    }
}

