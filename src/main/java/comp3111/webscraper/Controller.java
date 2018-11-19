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
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import javafx.application.*;
import java.text.DecimalFormat;

//TODO Sarah added these
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
//

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
    
    //TODO Sarah added these
    @FXML	
    private TableView<Item> table;
    
    @FXML	
    private TableColumn<TableView<Item>, String> titleCol;
    
    @FXML	
    private TableColumn<TableView<Item>, Double> priceCol;
    
    @FXML	
    private TableColumn<TableView<Item>, Hyperlink> urlCol;
    
    @FXML	
    private TableColumn<TableView<Item>, Calendar> dateCol;
    
    @FXML
    private Button refine;
    //
    
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
    	//lastSearch.setDisable(true);
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
    }
    
    public void setHostServices(HostServices hostService) {
    	this.hostService = hostService;
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
newResult.addAll(result);
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    	displayTable(result);
    	refine.setDisable(false);
    }
    
    /**
     * Called when user requests last search result
     */
    @FXML
    private void actionNew() {
//    	lastSearch.setDisable(true);
    	System.out.println("actionNew");
    	if(lastResult.size()!=0) {
//    		summarizing(lastResult);
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
     * Called when user presses refine
     */
    @FXML
    private void actionRefine() {
    	System.out.println("actionRefine");
    	refine.setDisable(true);
    	List<Item> result = new ArrayList<Item>();
    	//System.out.println(newResult.size());
    	for (Item i : newResult) {
    		//System.out.println(0);
    		if(i.getTitle().contains(textFieldKeyword.getText())) {
    			//System.out.println(i.getTitle());
    			result.add(i);
    		}
    	}
    	
    	displayTable(result);
    	//summerizing(result);
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);
    }
    
    /**
     * Display the list of Items on table
     */
    private void displayTable(List<Item> result) {
    	table.getItems().clear();
    	ObservableList<Item> l = FXCollections.observableList(result);
    	table.setItems(l);
    	titleCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, String>("title"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Double>("price"));
    	urlCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Hyperlink>("link"));
    	urlCol.setCellFactory(new HyperlinkCell());
    	dateCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Calendar>("postedDate"));
    }
    
    /**
     * Class for tablecells that contain a hyperlink
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
    				setGraphic(empty ? null : item);
    				if (!empty)
    				item.setOnAction(new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent e) {
    						System.out.println("handle"+item.getText());
    						hostService.showDocument(item.getText());
    					}
    				});
    			}
    		};
    		return cell;
    	}
    }
}

