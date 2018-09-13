package finalproject;

public abstract class AbstractModel {
    
    int num1;
    int num2;
    Boolean firstQuestion = false;
    Boolean alreadyChecked = false;

    public abstract String getProblem(char operator);
    
    public abstract Boolean checkAnswer(int answer);
    
    public abstract int getCorrectAnswer();
    
    public Boolean getFirstQuestionState() {
        return this.firstQuestion;
    }
    
    public Boolean getAlreadyCheckedState() {
        return this.alreadyChecked;
    }
    
}
