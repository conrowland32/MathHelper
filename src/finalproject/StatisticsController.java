package finalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {
    
    private Stage stage;
    public Scene menuScene; 
    public MenuController menuController;
    
    @FXML private Label nameLabel;
    @FXML private Label fracLabel;
    @FXML private Label percentLabel;
    @FXML private Button returnButton;
    @FXML private Button resetButton;
    
    public StatisticsModel statsModel = new StatisticsModel();
    
    private double totalPercent;
    private double additionPercent;
    private double subtractionPercent;
    private double multiplicationPercent;
    private String totalPercentString;
    private String additionPercentString;
    private String subtractionPercentString;
    private String multiplicationPercentString;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage stage, StatisticsModel statsModel) {
        this.stage = stage;
        this.statsModel = statsModel;
        stage.setTitle("Statistics");
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        
        if (statsModel.getTotalProblems() != 0) {
            totalPercent = ((double)statsModel.getTotalCorrect() / (double)statsModel.getTotalProblems() * 100);
        } else {
            totalPercent = 0;
        }
        if (statsModel.getAdditionProblems() != 0) {
            additionPercent = ((double)statsModel.getAdditionCorrect() / (double)statsModel.getAdditionProblems() * 100);
        } else {
            additionPercent = 0;
        }
        if (statsModel.getSubtractionProblems() != 0) {
            subtractionPercent = ((double)statsModel.getSubtractionCorrect() / (double)statsModel.getSubtractionProblems() * 100);
        } else {
            subtractionPercent = 0;
        }
        if (statsModel.getMultiplicationProblems() != 0) {
            multiplicationPercent = ((double)statsModel.getMultiplicationCorrect() / (double)statsModel.getMultiplicationProblems() * 100);
        } else {
            multiplicationPercent = 0;
        }
        
        totalPercentString = String.format("%.1f", totalPercent);
        additionPercentString = String.format("%.1f", additionPercent);
        subtractionPercentString = String.format("%.1f", subtractionPercent);
        multiplicationPercentString = String.format("%.1f", multiplicationPercent);
        
        nameLabel.setText("Total:\nAddition:\nSubtraction:\nMultiplication:");
        fracLabel.setText(statsModel.getTotalCorrect() + " / " + statsModel.getTotalProblems() + "\n");
        fracLabel.setText(fracLabel.getText() + statsModel.getAdditionCorrect() + " / " + statsModel.getAdditionProblems() + "\n");
        fracLabel.setText(fracLabel.getText() + statsModel.getSubtractionCorrect() + " / " + statsModel.getSubtractionProblems() + "\n");
        fracLabel.setText(fracLabel.getText() + statsModel.getMultiplicationCorrect() + " / " + statsModel.getMultiplicationProblems());
        percentLabel.setText(totalPercentString + "%\n");
        percentLabel.setText(percentLabel.getText() + additionPercentString + "%\n");
        percentLabel.setText(percentLabel.getText() + subtractionPercentString + "%\n");
        percentLabel.setText(percentLabel.getText() + multiplicationPercentString + "%");
        
    }
    
    @FXML
    public void returnButton() {
        stage.hide();
        stage.setTitle("Math Helper");
        stage.setScene(menuScene);
        stage.show();
        menuController.start(stage);
    }
    
    @FXML
    public void resetButton() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reset Statistics");
        alert.setHeaderText("Warning: about to reset all statistics");
        alert.setContentText("You are about to reset all user statistics. This action cannot "
                + "be undone. Are you sure you would like to permanantly reset your statistics?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            statsModel.reset();
            try {
                handleSave();
            } catch (IOException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            start(stage, statsModel);
        } else {
            
        }        
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
        
        start(stage, statsModel);
    }
    
    public void handleSave() throws FileNotFoundException, IOException {
        
        if(statsModel == null)
            return;
        
        File file = new File("statistics.txt");
        if (file != null) {
            try {
                FileOutputStream fileOut = new FileOutputStream(file.getPath());
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                
                out.writeObject(statsModel);
                out.close();
                fileOut.close();
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while saving to " + file.getPath();
            } catch (IOException ex) {
                String message = "IOException occured while saving to " + file.getPath();
            }
        }        
    }
    
}
