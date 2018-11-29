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

import javafx.scene.control.Button;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    
    @FXML	
	private TableView<Item> table;

	@FXML	
	private TableColumn<TableView<Item>, String> titleCol;

	@FXML	
	private TableColumn<TableView<Item>, Double> priceCol;

	@FXML	
	private TableColumn<TableView<Item>, Hyperlink> urlCol;

	@FXML	
	private TableColumn<TableView<Item>, String> dateCol;

	@FXML
	private Button refine;
    
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
     * Initializer. Initializes the labels on the Summary tab and Refine button and clear the table.
     */
    @FXML
    public void initialize() {
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
    	
    	refine.setDisable(true);
    	table.getItems().clear();
    	titleCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, String>("title"));
		priceCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Double>("price"));
		urlCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Hyperlink>("link"));
		urlCol.setCellFactory(new HyperlinkCell());
		dateCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, String>("StringDate"));
    }
    
    public void setHostServices(HostServices hostServices) {
    	this.hostService = hostServices;
    }
    
    /**
     * Display the raw data on the Console; summarize data and display the result on Summary tab.
     * @param listItem The items that are scraped from the two selling portals
     */
    @FXML
    private void summarizing(List<Item> listItem) {
    	if (listItem.isEmpty()) {
	    	labelPrice.setText("-");
			labelMin.setText("-");
			labelMin.setOnAction(null);
			labelLatest.setText("-");
			labelLatest.setOnAction(null);
    	}
    	
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
    	output += "\nSearch on selling portal: Craigslist and Preloved."
    			+ "\nNumber of pagination on Craigslist: " + scraper.getNumPage("craigslist") + " pages."
    			+ "\nNumber of pagination on Preloved: " + scraper.getNumPage("preloved") + " pages."
    			+ "\nNumber of results: " + scraper.getNumResults() + "."
    			+ "\nSearch finsihed.\n";
    	output += "Items are sorted in ascending order of its price. If two items have the same price, item sold on Craiglist go first. If two items from the same portal has the same price, they can be sorted in any order.\n";
    	
    	labelCount.setText(Integer.toString(listItem.size()));
    	textAreaConsole.setText(output);
    	DecimalFormat df = new DecimalFormat("#.00");
    	labelPrice.setText(df.format(totalPrice/countPrice));
    	System.out.println("finish summarize");
    	labelMin.setText(Double.toString(minItem.getPrice()));
    	labelLatest.setText(lastDate.getStringDate());
    }
    
    /**
     * Called when the Go button is pressed. Keeps track of current and last search result; summarizes and displays data.
     */
    @FXML
    private void actionSearch() {
    	refine.setDisable(false);
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
	    	displayTable(result);
	    	
    	} else {
    		labelPrice.setText("-");
    		labelMin.setText("-");
    		labelMin.setOnAction(null);
    		labelLatest.setText("-");
    		labelLatest.setOnAction(null);
    	}
    }
    
    /**
     * Called when the Last Search button is pressed. Retrieves last search result and updates the tabs.
     */
    @FXML
    public void actionNew() {
    	lastSearch.setDisable(true);
    	System.out.println("actionNew");
    	if(lastResult.size()!=0) {
    		summarizing(lastResult);
    		refine.setDisable(false);
    		displayTable(lastResult);
    		newResult.clear();
			newResult.addAll(lastResult);
			lastResult.clear();
    	} else {
    		System.out.println("no previous result");
    		initialize();
    	}
    }
    
    /**
     * Called when the Quit button is pressed. Terminates the program.
     */
    @FXML
    public void actionQuit() {
    	Platform.exit();
    	System.exit(0);
    }

    /**
     * Called when the Close button is pressed. Resets the system.
     */
    @FXML
    public void actionClose() {
    	initialize();
    }
    
    /**
     * Called when the About Your Team button is pressed. Shows team information in a pop-up window.
     */
    @FXML
    public void actionAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Team Information");
    	alert.setHeaderText("Team Information: ");
    	alert.setContentText("Name: \tStudent ID: \tGithub account: \nHo Wai Kin\twkhoae\tjohnnyn2\nFung Hing Lun\thlfungad\tvictor0362\nHo Tsz Kiu\ttkhoad\tsarahtkho");
    	alert.showAndWait();
    }
    
    /**
	 * Called when the Refine button is pressed. Filters out the Items without the user-entered keyword in the title; then
	 * summarizes and displays the result on Console and Table.
	 */
    @FXML
    private void actionRefine() {
    	System.out.println("actionRefine: " + textFieldKeyword.getText());
		refine.setDisable(true);
		List<Item> result = new ArrayList<Item>();
		
		for (Item i : newResult) {
			if(i.getTitle().contains(textFieldKeyword.getText())) {
				result.add(i);
				
			}
		}
		if (result.isEmpty()) {
			labelCount.setText("0");
			labelPrice.setText("-");
			labelMin.setText("-");
			labelMin.setOnAction(null);
			labelLatest.setText("-");
			labelLatest.setOnAction(null);
			textAreaConsole.setText("There is no item with the keyword " + textFieldKeyword.getText() + ".");
    	}
		else {
			displayTable(result);
			summarizing(result);
		}
		System.out.println("Finish refine.");
    }
    
    /**
	 * Method for displaying a List of Item objects on the Table tab.
	 * @param result The list to be displayed.
	 */
    private void displayTable(List<Item> result) {
    	System.out.println("displayTable");
    	table.getItems().clear();
    	if (!result.isEmpty()) {
    		ObservableList<Item> l = FXCollections.observableList(result);
			table.setItems(l);
    	}
    }
    
    /**
	 * Class for tablecells that contain a hyperlink and open a browser when the hyperlink is clicked.
	 */
	public class HyperlinkCell implements Callback<TableColumn<TableView<Item>, Hyperlink>, TableCell<TableView<Item>, Hyperlink>> {
		public HostServices getHostServices() {
			return hostService;
		}

		@Override
		public TableCell<TableView<Item>, Hyperlink> call(TableColumn<TableView<Item>, Hyperlink> arg) {
			TableCell<TableView<Item>, Hyperlink> cell = new TableCell<TableView<Item>, Hyperlink>() {
				@Override
				protected void updateItem(Hyperlink item, boolean empty) {
					setGraphic(/*empty ? null : */item);
					if (!empty)
						item.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								System.out.println("open: "+item.getText());
								hostService.showDocument(item.getText());
							}
						});
				}
			};
			return cell;
		}
	}
}
