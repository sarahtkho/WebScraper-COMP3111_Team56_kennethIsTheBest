/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import javafx.application.*;
import java.text.DecimalFormat;

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
    private MenuItem lastSearch;
	
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
    
    private HostServices hostService;
    
    private List<Item> lastResult;
    
    private List<Item> newResult;
    
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
    	lastSearch.setDisable(true);
    	textAreaConsole.setText("");
    	labelCount.setText("<total>");
    	labelPrice.setText("<AvgPrice>");
    	labelMin.setText("<Lowest>");
    	labelMin.setOnAction(null);
    	labelLatest.setText("<Latest>");
    	labelLatest.setOnAction(null);
    	lastResult = new ArrayList<Item>();
    	newResult = new ArrayList<Item>();
    	textFieldKeyword.setText("");
    }
    
    public void setHostServices(HostServices hostServices) {
    	this.hostService = hostServices;
    }
    
    /**
     * Called when the search button is pressed.
     */
    
    private void summarizing(List<Item> listItem) {
    	String output = "";
    	// calculate the avg price
    	int countPrice = 0;
    	double totalPrice = 0.0;
    	Item minItem = null, lastDate = null;
    	for (Item item : listItem) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() +"\t"+item.getStringDate()+"\n";
    		if(item.getPrice()>0.0) {
    			countPrice++;
    			totalPrice+= item.getPrice();
    			// assign the first valid item to MIN or compare the item of MIN and in the result list 
    			if(minItem == null || minItem.getPrice()>item.getPrice()) {
    				minItem = item;
    				labelMin.setOnAction(new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent e) {
    						hostService.showDocument(item.getUrl());
    					}
    				});
    			}
    			
    			// assign the first valid item to DATE or compare the item of DATE and in the result list
    			if(lastDate == null || lastDate.getDate().before(item.getDate())){
    				lastDate = item;
    				labelLatest.setOnAction(new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent e) {
    						hostService.showDocument(item.getUrl());
    					}
    				});
    			}
    				
    		}
    	}
    	labelCount.setText(Integer.toString(listItem.size()));
    	textAreaConsole.setText(output);
    	DecimalFormat df = new DecimalFormat("#.00");
    	labelPrice.setText(df.format(totalPrice/countPrice));
    	System.out.println("finish summarize");
    	labelMin.setText(Double.toString(minItem.getPrice()));
    	labelLatest.setText(lastDate.getStringDate());
    }
    
    @FXML
    private void actionSearch() {
    	lastSearch.setDisable(false);
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	
    	
    	if(!newResult.isEmpty()) {
    		lastResult.clear();
    		lastResult.addAll(newResult);
    		newResult.clear();
    	}
    	
    	// check if result have item inside (result.size() > 0 )
    	if (result.size() !=0) {
    		summarizing(result);
	    	newResult.addAll(result);
	    	
    	} else {
    		labelPrice.setText("-");
    		labelMin.setText("-");
    		labelMin.setOnAction(null);
    		labelLatest.setText("-");
    		labelLatest.setOnAction(null);
    	}
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	lastSearch.setDisable(true);
    	System.out.println("actionNew");
    	if(lastResult.size()!=0) {
    		summarizing(lastResult);
    	} else {
    		System.out.println("no previous result");
    		initialize();
    	}
    }
    @FXML
    private void actionQuit() {
    	Platform.exit();
    	System.exit(0);
    }

    @FXML
    private void actionClose() {
    	initialize();
    }
    
    @FXML
    private void actionAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Team Information");
    	alert.setHeaderText("Team Information: ");
    	alert.setContentText("Name: \tStudent ID: \tGithub account: \nHo Wai Kin\twkhoae\tjohnnyn2\nFung Hing Lun\thlfungad\tvictor0362\nHo Tsz Kiu\ttkhoad\tsarahtkho");
    	alert.showAndWait();
    }
}

