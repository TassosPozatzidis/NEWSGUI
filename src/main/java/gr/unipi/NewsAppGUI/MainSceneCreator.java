package gr.unipi.NewsAppGUI;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	
	
	 FlowPane rootFlowPane;
	    //Button scene 
	    Button search,headlines;
	    Label label;
	    GridPane rootGridPane;
	    
	    
	public MainSceneCreator(double width, double height) {
       
			super(width, height);
			rootFlowPane = new FlowPane();
			
	        search =new Button("Search All");
			rootFlowPane = new FlowPane();
	        headlines =new Button("Top Headlines");
	        rootGridPane = new GridPane();
	        //attach handle event to Welcome button
	        search.setOnMouseClicked(this);
	        headlines.setOnMouseClicked(this);
	        
	        rootFlowPane.setHgap(10);
	        rootFlowPane.setAlignment(Pos.CENTER);
	        
	        search.setMinSize(120, 30);
	        headlines.setMinSize(120, 30);
	        
	        label = new Label("Welcome to News Search App !");
	        
	        rootGridPane.setVgap(10);
			rootGridPane.setHgap(10);
			rootGridPane.setAlignment(Pos.CENTER);
			rootGridPane.add(search, 1, 1);
			rootGridPane.add(label, 0, 0);
			rootGridPane.add(headlines, 0, 1);
	        

	        
	        //add label to Root pane
//	        rootFlowPane.getChildren().add(label);
//	        
//	      //add button to Root pane
//	        rootFlowPane.getChildren().add(search);
//	        rootFlowPane.getChildren().add(headlines);
		}


	@Override
	Scene createScene() {
		// TODO Auto-generated method stub
		return new Scene(rootGridPane,width,height);
	}


	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource()==search) {
			App.mainStage.setTitle("News Window");
			App.mainStage.setScene(App.newsScene);
		}else if (event.getSource()==headlines) {
			App.mainStage.setTitle("Top Headlines Window");
			App.mainStage.setScene(App.topScene);
		}
	}
	


}
