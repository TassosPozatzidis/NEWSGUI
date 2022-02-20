package gr.unipi.NewsAppGUI;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exception.NewsAPIException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.IpAPI;
import model.NewsAPI;
import model.NewsInfo;
import services.IpAPIService;
import services.NewsAPIService;

public class NewsSceneCreator extends SceneCreator implements EventHandler<MouseEvent> {

	// List of News
	ArrayList<NewsInfo> newsList;

	Object historyList;
	// Flow pane
	FlowPane buttonFlowPane, buttonFlowPane2, buttonFlowPane3;
	// Grid pane
	GridPane rootGridPane, inputFieldsPane;
	// buttons
	Button searchNewsBtn, saveNewsBtn, clearBtn, backBtn, closeButton, previousBtn;
	// combo box
	ComboBox comboBoxcat, comboBoxlan;
	// label
	Label keywordLbl, categoryLbl, sourceLbl, languageLbl, dateFromLbl, dateToLbl;

	DatePicker startDatePicker;
	DatePicker endDatePicker;
	// text fields
	TextField keywordField, categoryField, sourceField, languageField, dateFromField, dateToField;

	VBox vbox;
	// Table view
	TableView<NewsInfo> newsTableView;

	public NewsSceneCreator(double width, double height) {

		super(width, height);
		newsList = new ArrayList<NewsInfo>();
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();
		buttonFlowPane2 = new FlowPane();
		buttonFlowPane3 = new FlowPane();

		keywordLbl = new Label("Keyword: ");
		categoryLbl = new Label("Category: ");
		sourceLbl = new Label("Source: ");
		languageLbl = new Label("Language: ");
		dateFromLbl = new Label("From: ");
		dateToLbl = new Label("To: ");

		keywordField = new TextField();
		comboBoxcat = new ComboBox();
		comboBoxlan = new ComboBox();

		historyList = new Object();

		comboBoxcat.getItems().add("");
		comboBoxcat.getItems().add("business");
		comboBoxcat.getItems().add("entertainment");
		comboBoxcat.getItems().add("general");
		comboBoxcat.getItems().add("health");
		comboBoxcat.getItems().add("science");
		comboBoxcat.getItems().add("sports");
		comboBoxcat.getItems().add("technology");

		comboBoxcat.setOnAction((event) -> {
			int selectedIndex = comboBoxcat.getSelectionModel().getSelectedIndex();
			Object selectedItem = comboBoxcat.getSelectionModel().getSelectedItem();

		});
		comboBoxlan.getItems().add("");
		comboBoxlan.getItems().add("en");
		comboBoxlan.getItems().add("es");
		comboBoxlan.getItems().add("fr");
		comboBoxlan.getItems().add("he");
		comboBoxlan.getItems().add("it");
		comboBoxlan.getItems().add("gr");
		comboBoxlan.getItems().add("pt");

		comboBoxlan.setOnAction((event) -> {
			int selectedIndex = comboBoxlan.getSelectionModel().getSelectedIndex();
			Object selectedItem = comboBoxlan.getSelectionModel().getSelectedItem();

		});

		sourceField = new TextField();
		dateFromField = new TextField();
		dateToField = new TextField();

		startDatePicker = new DatePicker();
		endDatePicker = new DatePicker();

		searchNewsBtn = new Button("Search");
		saveNewsBtn = new Button("Search History");
		backBtn = new Button("Back");
		clearBtn = new Button("Clear");
		closeButton = new Button("Exit");
		previousBtn = new Button ("Previous Search");

		saveNewsBtn.setMinSize(120, 30);
		backBtn.setMinSize(120, 30);
		closeButton.setMinSize(120, 30);
		clearBtn.setMinSize(120, 30);


		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(startDatePicker.getValue().plusDays(1));

		inputFieldsPane = new GridPane();
		newsTableView = new TableView<NewsInfo>();

		//customize Flow Pane
		buttonFlowPane.setHgap(10);
		buttonFlowPane.setAlignment(Pos.BOTTOM_CENTER);
		buttonFlowPane.getChildren().add(backBtn);
		buttonFlowPane.getChildren().add(saveNewsBtn);
		
		buttonFlowPane3.setHgap(10);
		buttonFlowPane3.setAlignment(Pos.BOTTOM_CENTER);
		buttonFlowPane3.getChildren().add(clearBtn);		
		buttonFlowPane3.getChildren().add(closeButton);
		
		buttonFlowPane2.setHgap(10);
		buttonFlowPane2.setAlignment(Pos.BOTTOM_CENTER);
		buttonFlowPane2.setPrefWrapLength(210);		
		buttonFlowPane2.getChildren().add(previousBtn);
		buttonFlowPane2.getChildren().add(searchNewsBtn);

		// customize input field Grid Pane
		inputFieldsPane.setAlignment(Pos.TOP_RIGHT);
		inputFieldsPane.setVgap(10);
		inputFieldsPane.setHgap(10);
		inputFieldsPane.add(keywordLbl, 0, 1);
		inputFieldsPane.add(keywordField, 1, 1);
		inputFieldsPane.add(categoryLbl, 0, 2);
		inputFieldsPane.add(comboBoxcat, 1, 2);
		inputFieldsPane.add(sourceLbl, 0, 3);
		inputFieldsPane.add(sourceField, 1, 3);
		inputFieldsPane.add(languageLbl, 0, 4);
		inputFieldsPane.add(comboBoxlan, 1, 4);
		inputFieldsPane.add(dateFromLbl, 0, 5);
		inputFieldsPane.add(startDatePicker, 1, 5);
		inputFieldsPane.add(dateToLbl, 0, 6);
		inputFieldsPane.add(endDatePicker, 1, 6);
		inputFieldsPane.add(buttonFlowPane2, 1, 7);
		// Customize Root Grid Pane
		rootGridPane.setVgap(10);
		rootGridPane.setHgap(10);
		rootGridPane.getColumnConstraints().add(new ColumnConstraints(780));
		rootGridPane.getColumnConstraints().add(new ColumnConstraints(300));
		rootGridPane.add(inputFieldsPane, 1, 0);
		rootGridPane.add(newsTableView, 0, 0);
		rootGridPane.add(buttonFlowPane, 0, 1);
		// rootGridPane.add(backBtn, 1, 1);
		rootGridPane.add(buttonFlowPane3, 1, 1);

		// add table display columns and their names
		TableColumn<NewsInfo, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		titleColumn.setMinWidth(180);
		newsTableView.getColumns().add(titleColumn);

		TableColumn<NewsInfo, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		descriptionColumn.setMinWidth(250);
		newsTableView.getColumns().add(descriptionColumn);

		TableColumn<NewsInfo, String> urlColumn = new TableColumn<>("Url");
		urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
		urlColumn.setMinWidth(220);
		newsTableView.getColumns().add(urlColumn);

		TableColumn<NewsInfo, String> releaseDateColumn = new TableColumn<>("Release Date");
		releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("upload_date"));
		releaseDateColumn.setMinWidth(150);
		newsTableView.getColumns().add(releaseDateColumn);

		// Attach events
		backBtn.setOnMouseClicked(this);
		searchNewsBtn.setOnMouseClicked(this);
		saveNewsBtn.setOnMouseClicked(this);
		backBtn.setOnMouseClicked(this);
		clearBtn.setOnMouseClicked(this);
		closeButton.setOnMouseClicked(this);
		previousBtn.setOnMouseClicked(this);

	}

	@Override
	Scene createScene() {
		// TODO Auto-generated method stub
		return new Scene(rootGridPane, width, height);
	}

	@Override
	public void handle(MouseEvent event) {
		String key = "";
		String cat = "";
		String source = "";
		String language = "";

		if (event.getSource() == backBtn) {
			App.mainStage.setTitle("MainFX Window");
			App.mainStage.setScene(App.mainScene);
		} else if (event.getSource() == saveNewsBtn) {
			App.mainStage.setTitle("Search History Window");
			App.mainStage.setScene(App.hsScene);
		} else if (event.getSource() == clearBtn) {
			clearTextFields();
		}else if (event.getSource() == previousBtn) {
			
			try {
				ArrayList<String> readLastSearch = DatabaseConnection.readLastSearch();
				System.out.println(readLastSearch);
				newsTableView.getItems().clear();
				String cnt=readLastSearch.get(1);
				String ctg=readLastSearch.get(2);
				String q=readLastSearch.get(3);
				String lan=readLastSearch.get(4);
				String sources=readLastSearch.get(5);
				String from=readLastSearch.get(6);
				String to=readLastSearch.get(7);
				System.out.println(cnt+" 1,"+ctg+" 2,"+q+" 3,"+lan+" 4,"+sources+" 5,"+from+" 6,"+to+" 7,");
				getNewsBySearch(null,ctg, q, lan, sources, from,to);
				tableSync();
				clearTextFields();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				PopUpError.DisplayError(e,"Connection with DB problem");
				
			} catch (NewsAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				PopUpError.DisplayError(e,"Problem with search criteria, please try again");
			}
				

		}
		else if (event.getSource() == searchNewsBtn) { // after searchbtn get values of the components
			key = keywordField.getText();
			cat = (String) comboBoxcat.getValue();
			source = sourceField.getText();
			language = (String) comboBoxlan.getValue();
			String from = startDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String to = endDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			try {
				if (cat == null && language != null) {												// problem when inserting null values (cat-lan) at db
					DatabaseConnection.addNews(" ", " ", key, language, source, from, to); 			// insert values of the
																									// components into the db
				} else if (cat == null && language == null) {
					DatabaseConnection.addNews(" ", " ", key, " ", source, from, to);
				} else if (cat != null && language == null) {
					DatabaseConnection.addNews(" ", cat, key, " ", source, from, to);
				} else {
					DatabaseConnection.addNews(" ", cat, key, language, source, from, to);
				}

			} catch (SQLException e1) { // sql exception
				// TODO Auto-generated catch block
				PopUpError.DisplayError(e1,"Connection with DB problem");
				e1.printStackTrace();
				
			}
			try {
				getNewsBySearch(null, cat, key, language, source, from, to); // call function getNewsBySearch ,to search
																				// in everything endpoint
				tableSync();
				clearTextFields();

			} catch (NewsAPIException e) {
				// TODO Auto-generated catch block
				PopUpError.DisplayError(e,"Problem with search criteria, please try again");
				newsTableView.getItems().clear();
				e.printStackTrace();

			}
		} else if (event.getSource() == closeButton) {
			handleCloseButtonAction(event);
		}

	}

	public void getNewsBySearch(String country, String category, String q, String language, String sources, String from,
			String to) throws NewsAPIException {
		newsList.clear();
		final IpAPIService IpSearchService = IpAPI.getIpAPIService();			//call getIpApiservice for automatic geolocation
		final NewsAPIService newsSearchService = NewsAPI.getNewsAPIService();
		final String Country = IpSearchService.findCountry(null);				//get the country
		if (country == null) {
			final List<NewsInfo> results = newsSearchService.searchAllCriteriaCategory(Country, category, q, language,
					sources, from, to); // get the results of searchAllCriteria and place them to a NewsInfo list  
										// /parameters : q,language,source,from,to
			newsList.addAll(results);
		} else {
			final List<NewsInfo> results = newsSearchService.searchAllCriteriaCategory(country, category, q, language,
					sources, from, to);
			newsList.addAll(results);
		}

		// get all list and pass it to results
	}

	public void handleCloseButtonAction(MouseEvent event) {//function for close button 
		closeButton.getScene().getWindow();					//returns a stage
		App.mainStage.close();								//close main stage

	}

	public void tableSync() {//sync table to display the results
		List<NewsInfo> items = newsTableView.getItems();
		items.clear();

		for (NewsInfo news : newsList) {
			if (news instanceof NewsInfo) {
				items.add(news);
			}
		}

	}

	public void clearTextFields() {// clear all fields
		keywordField.setText("");
		sourceField.setText("");
		comboBoxlan.setValue("");
		comboBoxcat.setValue("");
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
	}

}
