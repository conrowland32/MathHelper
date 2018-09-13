package finalproject;

public class StatisticsModel implements java.io.Serializable {
    
    private int totalProblems = 0;
    private int totalCorrect = 0;
    private int additionProblems = 0;
    private int additionCorrect = 0;
    private int subtractionProblems = 0;
    private int subtractionCorrect = 0;
    private int multiplicationProblems = 0;
    private int multiplicationCorrect = 0;
    
    public void setStats(Boolean answer, int operation) {
        
        switch (operation) {
            
            case 1:
                this.totalProblems++;
                additionProblems++;
                if(answer == true) {
                    totalCorrect++;
                    additionCorrect++;
                }
                break;
                
            case 2:
                totalProblems++;
                subtractionProblems++;
                if(answer == true) {
                    totalCorrect++;
                    subtractionCorrect++;
                }
                break;
                
            case 3:
                totalProblems++;
                multiplicationProblems++;
                if(answer == true) {
                    totalCorrect++;
                    multiplicationCorrect++;
                }
                break;
                
            default:
                break;
        }
    }
    
    public void reset() {
        totalProblems = 0;
        totalCorrect = 0;
        additionProblems = 0;
        additionCorrect = 0;
        subtractionProblems = 0;
        subtractionCorrect = 0;
        multiplicationProblems = 0;
        multiplicationCorrect = 0;
    }
    
    public int getTotalProblems() {
        return totalProblems;
    }
    
    public int getTotalCorrect() {
        return totalCorrect;
    }
    
    public int getAdditionProblems() {
        return additionProblems;
    }
    
    public int getAdditionCorrect() {
        return additionCorrect;
    }
    
    public int getSubtractionProblems() {
        return subtractionProblems;
    }
    
    public int getSubtractionCorrect() {
        return subtractionCorrect;
    }
    
    public int getMultiplicationProblems() {
        return multiplicationProblems;
    }
    
    public int getMultiplicationCorrect() {
        return multiplicationCorrect;
    }
    
    public void setTotalProblems(int totalProblems) {
        this.totalProblems = totalProblems;
    }
    
    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }
    
    public void setAdditionProblems(int additionProblems) {
        this.additionProblems = additionProblems;
    }
    
    public void setAdditionCorrect(int additionCorrect) {
        this.additionCorrect = additionCorrect;
    }
    
    public void setSubtractionProblems(int subtractionProblems) {
        this.subtractionProblems = subtractionProblems;
    }
    
    public void setSubtractionCorrect(int subtractionCorrect) {
        this.subtractionCorrect = subtractionCorrect;
    }
    
    public void setMultiplicationProblems(int multiplicationProblems) {
        this.multiplicationProblems = multiplicationProblems;
    }
    
    public void setMultiplicationCorrect(int multiplicationCorrect) {
        this.multiplicationCorrect = multiplicationCorrect;
    }

}
