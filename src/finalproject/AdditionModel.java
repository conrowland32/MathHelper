package finalproject;

public class AdditionModel extends AbstractModel {

    @Override
    public String getProblem(char operator) {
        firstQuestion = true;
        alreadyChecked = false;
        
        num1 = (int)(Math.random() * 51);
        num2 = (int)(Math.random() * 51);
        String problem = num1 + " " + operator + " " + num2 + " = ";
        
        return problem;
    }
    
    @Override
    public Boolean checkAnswer(int answer) {
        alreadyChecked = true;
        
        if(num1 + num2 == answer) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public int getCorrectAnswer() {
        return num1 + num2;
    }
    
}
