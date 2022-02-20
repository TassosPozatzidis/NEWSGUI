package gr.unipi.NewsAppGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application{
	
	 //Stage
    static Stage mainStage;
    //Scene
    static Scene mainScene, newsScene,topScene,errorScene,hsScene, logoutScene;
    //Flow pane root

    @Override
    public void start(Stage mainStage) {
    	
        this.mainStage=mainStage;
        //create scene for every class 
		SceneCreator mainSceneCreator = new MainSceneCreator(640, 480);//main scene 
		mainScene = mainSceneCreator.createScene();
		SceneCreator newsSceneCreator = new NewsSceneCreator(1157, 525);
		newsScene = newsSceneCreator.createScene();
		SceneCreator topSceneCreator = new HeadlinesSceneCreator(1157, 525);
		topScene = topSceneCreator.createScene();
		SceneCreator historySearchDisplay = new HistorySearchDisplay(854, 493);
		hsScene = historySearchDisplay.createScene();
		//SceneCreator  popUpError = new PopUpError(640,480);
		//errorScene = popUpError.createScene();
        

        
        mainStage.setTitle("Main Window");
        mainStage.setScene(mainScene);
        mainStage.show();
    }



    
    public static void main(String[] args) {
        launch();
    }


    	

}