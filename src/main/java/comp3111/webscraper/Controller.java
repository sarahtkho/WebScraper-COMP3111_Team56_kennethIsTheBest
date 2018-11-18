/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;

import java.util.Calendar;
import java.util.List;

//TODO Sarah added these
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.Hyperlink;
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

    public static HostServices hostServices;
    
    private List<Item> lastResult;
    
    private List<Item> newResult;

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
    	table.getItems().clear();
    }
    
    public void setHostServices(HostServices hostServices) {
    	this.hostServices = hostServices;
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
    	table.getItems().clear();
    	displayTable(result);
    }
    
    /**
     * Display the list of Items on table
     */
    private void displayTable(List<Item> result) {
    	ObservableList<Item> l = FXCollections.observableList(result);
    	table.setItems(l);
    	titleCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, String>("title"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Double>("price"));
    	urlCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Hyperlink>("link"));
    	urlCol.setCellFactory(new HyperlinkCell());
    	dateCol.setCellValueFactory(new PropertyValueFactory<TableView<Item>, Calendar>("postedDate"));
    }
    
    /**
     * Called when user requests last search result
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
    	if(lastResult.size()!=0)
    		displayTable(lastResult);
    }
    
    /**
     * Called when user presses refine
     */
    @FXML
    private void actionRefine() {
    	System.out.println("actionRefine");

    }
    /**
     * Class for tablecells that contain a hyperlink
     */
    public class HyperlinkCell implements Callback<TableColumn<TableView<Item>, Hyperlink>, TableCell<TableView<Item>, Hyperlink>> {
    	public HostServices getHostServices() {
    		return hostServices;
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
    						hostServices.showDocument(item.getText());
    					}
    				});
    			}
    		};
    		return cell;
    	}
    }
}

