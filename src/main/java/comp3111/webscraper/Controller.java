/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Hyperlink;
import java.util.List;

//TODO Sarah added these
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML	private Label labelCount; 

    @FXML	private Label labelPrice; 

    @FXML	private Hyperlink labelMin; 

    @FXML	private Hyperlink labelLatest; 

    @FXML	private TextField textFieldKeyword;
    
    @FXML	private TextArea textAreaConsole;
    
    private WebScraper scraper;
    
    
    //TODO Sarah added these
    @FXML	private TableView<Item> table;
    
    @FXML	private TableColumn titleCol;
    
    @FXML	private TableColumn priceCol;
    
    @FXML	private TableColumn urlCol;
    
    @FXML	private TableColumn dateCol;
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
    	
    	//TODO (Sarah added sth here)
    	ObservableList<Item> l = FXCollections.observableList(result);
    	table.setItems(l);
    	titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    	urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));
    	//
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
    }
}

