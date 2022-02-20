package gr.unipi.NewsAppGUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import java.io.PrintWriter;
import java.io.StringWriter;

//Java Program to create a popup and add
//it to the stage and make the popup hide
//automatically when it loses focus using
//the setAutoHide() function
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Popup;

public class PopUpError {
	


	public static void DisplayError(Exception e,String s) {
		// TODO Auto-generated method stub
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(s);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
		alert.showAndWait();
	}


}


