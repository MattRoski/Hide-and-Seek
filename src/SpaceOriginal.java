public class SpaceOriginal {
    private final String seeker = "S";
    private final String hider = "H";
    private final String empty = ".";
    private final String obstacle = "O";
    private final String found = "X";
    private final String bound = "+";
    private int rowNumber ;
    private int columnNumber;
    private String value;



    public String getValue() {
        return value;
    }


    public void setValueToFound() {
        this.value = found;
    }
    public void setValueToBound(){
        this.value = bound;
    }
    public void setValueToObstacle() {
        this.value = obstacle;
    }
    public void setValueToEmpty() {
        this.value = empty;
    }
    public void setValueToHider() {
        this.value = hider;
    }
    public void setValueToSeeker() {
        this.value = seeker;
    }

    public int getRowNumber(){
        return this.rowNumber;
    }

    public int getColumnNumber(){
        return this.columnNumber;
    }
    public void setColumnNumber(int num){
        this.columnNumber = num;
    }
    public void setRowNumber(int num){
        this.rowNumber = num;
    }

}