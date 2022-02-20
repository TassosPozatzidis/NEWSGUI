package gr.unipi.NewsAppGUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.NewsAPIException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.NewsInfo;

public class HistorySearchDisplay extends SceneCreator implements EventHandler<MouseEvent> {

	// List of News
	ArrayList<String> newsList;
	Button closeButton;
	// Flow pane
	FlowPane buttonFlowPane, buttonFlowPane2, buttonFlowPane3;
	// Grid pane
	GridPane rootGridPane;
	// buttons
	Button showBtn, backBtn;

	ObservableList<String> row;

	ResultSet rs;

	ObservableList<ObservableList> data;
	// label
	Label countryLbl, categoryLbl;

	// text fields
	TextField countryField;

	// Table view

	TableView newsTableView;

	public HistorySearchDisplay(double width, double height) {
		super(width, height);
		newsList = new ArrayList<String>();
		rootGridPane = new GridPane();
		buttonFlowPane = new FlowPane();
		buttonFlowPane3 = new FlowPane();

		countryLbl = new Label("Country: ");
		categoryLbl = new Label("Category: ");

		countryField = new TextField();

		data = FXCollections.observableArrayList();
		;
		backBtn = new Button("Home");
		showBtn = new Button("Refresh");
		closeButton = new Button("Exit");
		newsTableView = new TableView<>();

		showBtn.setMinSize(120, 30);
		backBtn.setMinSize(120, 30);
		closeButton.setMinSize(120, 30);

		buttonFlowPane3.setHgap(10);
		buttonFlowPane3.setAlignment(Pos.BOTTOM_CENTER);
		buttonFlowPane3.getChildren().add(backBtn);
		buttonFlowPane3.getChildren().add(showBtn);
		buttonFlowPane3.getChildren().add(closeButton);

		rootGridPane.setVgap(10);
		rootGridPane.setHgap(10);
		rootGridPane.setAlignment(Pos.CENTER);
		rootGridPane.getColumnConstraints().add(new ColumnConstraints(620));
		rootGridPane.add(newsTableView, 0, 0);
		rootGridPane.add(buttonFlowPane3, 0, 1);

		backBtn.setOnMouseClicked(this);
		showBtn.setOnMouseClicked(this);
		closeButton.setOnMouseClicked(this);
	}

	public void buildData() {

		try {
			rs = DatabaseConnection.readNews();
		} catch (SQLException e1) {
			PopUpError.DisplayError(e1, "Connection with DB problem");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		newsTableView.getColumns().clear();// clear all columns
		newsTableView.getItems().clear();// clear all data
		// data = FXCollections.observableArrayList();
		try {

			int y = 0;
			for (int i = 1; i < rs.getMetaData().getColumnCount() - 2; i++) {// [0] column is table id ,and 2 last
																				// columns are sql db dates
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() { // Callback
																											// is a
																											// function
																											// which
																											// takes the
																											// data from
																											// a
																											// specific
																											// row (in
																											// the form
																											// of an
																											// ObservableList
																											// in this
																											// example)
																											// and
																											// returns
																											// the value
																											// from that
																											// row in
																											// the
																											// column
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});

				newsTableView.getColumns().addAll(col);
			}

			while (rs.next()) {
				row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));

				}
				data.add(row);
			//	getLastResult();

			}
			newsTableView.setItems(data); // add at newsTableView of javaFx tha data retrieved form ssql db
		} catch (Exception e) {
			PopUpError.DisplayError(e, "Problem with SQL Query");
			e.printStackTrace();
		}

	}

//	public static void /**ArrayList<String>**/ getLastResult() {
//		ArrayList<ObservableList> top5List = new ArrayList<ObservableList>();
//		ArrayList<String> ar = new ArrayList<String>();
//		top5List.addAll(data2);
//		if (top5List.get(0).get(1) != null || top5List.get(0).get(1).equals("")) {
//			ar.add((String) top5List.get(0).get(1));// 1st column from table is country
//			ar.add((String) top5List.get(0).get(2));// 2nd column from table is category
//		} else {
//			ar.add((String) top5List.get(0).get(3));//3rd column  from table is keyword
//			ar.add((String) top5List.get(0).get(4));//4th column from table is language
//			ar.add((String) top5List.get(0).get(5));//5th column from table is domains
//			ar.add((String) top5List.get(0).get(6));//6th column from table is date from
//			ar.add((String) top5List.get(0).get(7));//7th column from table is 
//		}
//			System.out.println("list list" + top5List);
//		System.out.println("listt" + top5List.get(0).get(1));
//		//return ar;
//
//	}

	public void handleCloseButtonAction(MouseEvent event) {
		closeButton.getScene().getWindow();
		App.mainStage.close();

	}

	@Override
	Scene createScene() {
		// TODO Auto-generated method stub
		return new Scene(rootGridPane, width, height);
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == backBtn) {
			App.mainStage.setTitle("MainFX Window");
			App.mainStage.setScene(App.mainScene);
		} else if (event.getSource() == showBtn) {
			buildData();

		} else if (event.getSource() == closeButton) {
			handleCloseButtonAction(event);
		}
	}

}
