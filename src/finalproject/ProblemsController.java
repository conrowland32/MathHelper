package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProblemsController implements Initializable, ButtonInterface {
    
    private Stage stage; 
    public Scene menuScene; 
    public MenuController menuController;
    
    private DropShadow redBorder = new DropShadow();
    private DropShadow greenBorder = new DropShadow();
    
    private AbstractModel model;
    private StatisticsModel statsModel = new StatisticsModel();
    private StatisticsController statsController = new StatisticsController();
    
    public int operation;
    private char operator;
    
    @FXML private Button menuButton;
    @FXML private Button problemButton;
    @FXML private Button submitButton;
    @FXML private Label problemLabel;
    @FXML private Label titleLabel;
    @FXML private Label answerResponseLabel;
    @FXML private TextField answerField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void start(Stage stage, StatisticsModel statsModel) {
        this.stage = stage;
        this.statsModel = statsModel;
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        
        problemLabel.setText("");
        answerField.setText("");
        answerField.setEffect(null);
        initBorders();
        answerResponseLabel.setText("");
        answerField.setEditable(false);
        answerField.setDisable(true);
        answerField.setVisible(false);
        
        switch (operation) {
            case 1:
                stage.setTitle("Math Helper - Addition");
                titleLabel.setText("Addition");
                operator = '+';
                model = new AdditionModel();
                break;
            case 2:
                stage.setTitle("Math Helper - Subtraction");
                titleLabel.setText("Subtraction");
                operator = '-';
                model = new SubtractionModel();
                break;
            case 3:
                stage.setTitle("Math Helper - Multiplication");
                titleLabel.setText("Multiplication");
                operator = 'x';
                model = new MultiplicationModel();
                break;
            default:
                break;
        }
    }
    
    @FXML
    private void mainMenuButton() {
        stage.hide();
        stage.setTitle("Math Helper");
        stage.setScene(menuScene);
        stage.show();
        menuController.start(stage);
    }
    
    @FXML
    private void newProblem() {
        problemLabel.setText(model.getProblem(operator));
        answerField.setText("");
        answerField.setEffect(null);
        answerResponseLabel.setText("");
        answerField.setDisable(false);
        answerField.setVisible(true);
        answerField.setEditable(true);      
    }
    
    @FXML
    private void checkAnswer() {
                
        if (model.getFirstQuestionState() == true  &&  model.getAlreadyCheckedState() == false) {
            String answerString = answerField.getText();
            int answer = Integer.parseInt(answerString);
            
            setAnswerResponse(model.checkAnswer(answer));
            setAnswerBorder(model.checkAnswer(answer));
            
            statsModel.setStats(model.checkAnswer(answer), operation);
        }
        
        answerField.setEditable(false);
        answerField.setDisable(true);
        answerField.setTranslateZ(50);
        
        try {
            handleSave();
        } catch (IOException ex) {
            Logger.getLogger(ProblemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initBorders() {
        redBorder.setColor(Color.RED);
        redBorder.setOffsetX(0);
        redBorder.setOffsetY(0);
        redBorder.setHeight(30);
        redBorder.setWidth(30);
        
        greenBorder.setColor(Color.GREEN);
        greenBorder.setOffsetX(0);
        greenBorder.setOffsetY(0);
        greenBorder.setHeight(30);
        greenBorder.setWidth(30);
    }
    
    private void setAnswerResponse(Boolean answerCorrect) {
        if(answerCorrect == true) {
            answerResponseLabel.setText("Your answer is correct");
            answerResponseLabel.setTextFill(Color.GREEN);
        } else {
            answerResponseLabel.setText("The correct answer is " + model.getCorrectAnswer());
            answerResponseLabel.setTextFill(Color.RED);
        }        
    }
    
    private void setAnswerBorder(Boolean answerCorrect) {
        if(answerCorrect == true) {
            answerField.setEffect(null);
            answerField.setEffect(greenBorder);
        } else {
            answerField.setEffect(null);
            answerField.setEffect(redBorder);
        }
    }
    
    @Override @FXML
    public void button0Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "0");
        }
    }
    
    @Override @FXML
    public void button1Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "1");
        }
    }
    
    @Override @FXML
    public void button2Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "2");
        }
    }
    
    @Override @FXML
    public void button3Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "3");
        }
    }
    
    @Override @FXML
    public void button4Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "4");
        }
    }
    
    @Override @FXML
    public void button5Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "5");
        }
    }
    
    @Override @FXML
    public void button6Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "6");
        }
    }
    
    @Override @FXML
    public void button7Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "7");
        }
    }
    
    @Override @FXML
    public void button8Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "8");
        }
    }
    
    @Override @FXML
    public void button9Action() {
        if (answerField.isDisabled() == false) {
            answerField.setText(answerField.getText() + "9");
        }
    }
    
    @Override @FXML
    public void clearButtonAction() {
        if (answerField.isDisabled() == false) {
            answerField.setText("");
        }
    }
    
    @Override @FXML
    public void backButtonAction() {
        if (answerField.isDisabled() == false) {
            String current = answerField.getText();
            
            if (current != null && current.length() > 0) {
                current = current.substring(0, current.length() - 1);
            }
            
            answerField.setText(current);
        }
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
