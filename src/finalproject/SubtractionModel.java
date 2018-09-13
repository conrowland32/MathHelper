package finalproject;

public class SubtractionModel extends AbstractModel {

    @Override
    public String getProblem(char operator) {
        firstQuestion = true;
        alreadyChecked = false;
        
        num1 = (int)(Math.random() * 101);
        num2 = (int)(Math.random() * 101);
        String problem;
        
        if(num1 > num2) {
            problem = num1 + " " + operator + " " + num2 + " = ";
        } else {
            problem = num2 + " " + operator + " " + num1 + " = ";
        }
        
        return problem;
    }
    
    @Override
    public Boolean checkAnswer(int answer) {
        alreadyChecked = true;
        
        if(num1 > num2) {
            if(num1 - num2 == answer) {
                return true;
            } else {
                return false;
            }
        } else {
            if(num2 - num1 == answer) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    @Override
    public int getCorrectAnswer() {
        if(num1 > num2) {
            return num1 - num2;
        } else {
            return num2 - num1;
        }
    }

}
