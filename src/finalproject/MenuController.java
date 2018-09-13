package finalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    
    private Stage stage; 
    private Scene menuScene;
    private Scene problemsScene;
    private Scene statsScene;
    
    private ProblemsController problemsController;
    private StatisticsController statsController;
    private StatisticsModel statsModel = new StatisticsModel();
    
    @FXML private Button addButton;
    @FXML private Button subtractButton;
    @FXML private Button multiplyButton;
    @FXML private Button aboutButton;
    @FXML private Button statsButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void start(Stage stage) {
        this.stage = stage;        
        stage.setTitle("Math Helper");
        menuScene = stage.getScene();
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        
        handleOpen();
    }
    
    @FXML
    private void addButton() {
        
        try {
            if(problemsScene == null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProblemsView.fxml"));
                Parent problemsRoot = loader.load(); 
                problemsController = loader.getController(); 
                problemsController.menuScene = menuScene; 
                problemsController.menuController = this; 
                problemsScene = new Scene(problemsRoot); 
            }
        } catch (Exception ex){
            
        }
        
        stage.hide();
        stage.setScene(problemsScene);
        stage.show();
        problemsController.operation = 1; 
        problemsController.start(stage, statsModel);
        
    }
    
    @FXML
    private void subtractButton() {
        
        try {
            if(problemsScene == null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProblemsView.fxml"));
                Parent problemsRoot = loader.load(); 
                problemsController = loader.getController(); 
                problemsController.menuScene = menuScene; 
                problemsController.menuController = this; 
                problemsScene = new Scene(problemsRoot); 
            }
        } catch (Exception ex){
            
        }
        
        stage.hide();
        stage.setScene(problemsScene);
        stage.show();
        problemsController.operation = 2; 
        problemsController.start(stage, statsModel);
        
    }
    
    @FXML
    private void multiplyButton() {
        
        try {
            if(problemsScene == null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProblemsView.fxml"));
                Parent problemsRoot = loader.load(); 
                problemsController = loader.getController(); 
                problemsController.menuScene = menuScene; 
                problemsController.menuController = this; 
                problemsScene = new Scene(problemsRoot); 
            }
        } catch (Exception ex){
            
        }
        
        stage.hide();
        stage.setScene(problemsScene);
        stage.show();
        problemsController.operation = 3; 
        problemsController.start(stage, statsModel);
        
    }
    
    @FXML
    private void aboutButton() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About Math Helper");
        alert.setContentText("Math Helper was created by Connor Rowland on April 19, 2017 "
                + "in order to fulfill the Final Project requirement for CS 3330.\n\n"
                + "Math Helper is a lightweight application which provides practice problems "
                + "for simple addition, subtraction, and multiplication. Math Helper also "
                + "provides statistics such as problems answered, problems correct, and "
                + "correct answer percentage.\n");
        
        alert.showAndWait();
    }
    
    @FXML
    private void statsButton() {
        
        try {
            if(statsScene == null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsView.fxml"));
                Parent statsRoot = loader.load(); 
                statsController = loader.getController(); 
                statsController.menuScene = menuScene; 
                statsController.menuController = this; 
                statsScene = new Scene(statsRoot);
            }
        } catch (Exception ex){
            
        }
        
        stage.hide();
        stage.setScene(statsScene);
        stage.show();
        statsController.start(stage, statsModel);
    }

    public void handleOpen() {
        
        File file = new File("statistics.txt");
        if (file != null) {
            FileInputStream fileIn;
            try {
                fileIn = new FileInputStream(file.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                                
                StatisticsModel statsAux = (StatisticsModel) in.readObject();
                statsModel.setTotalProblems(statsAux.getTotalProblems());
                statsModel.setTotalCorrect(statsAux.getTotalCorrect());
                statsModel.setAdditionProblems(statsAux.getAdditionProblems());
                statsModel.setAdditionCorrect(statsAux.getAdditionCorrect());
                statsModel.setSubtractionProblems(statsAux.getSubtractionProblems());
                statsModel.setSubtractionCorrect(statsAux.getSubtractionCorrect());
                statsModel.setMultiplicationProblems(statsAux.getMultiplicationProblems());
                statsModel.setMultiplicationCorrect(statsAux.getMultiplicationCorrect());   
                
                in.close();
                fileIn.close();
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while opening " + file.getPath();
            } catch (IOException ex) {
                String message = "IOException occured while opening " + file.getPath();
            } catch (ClassNotFoundException ex) {
                String message = "Class not found exception occured while opening " + file.getPath();
            }
        }
    }
    
}
