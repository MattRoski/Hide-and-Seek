import java.util.Random;

public class GridOriginal {
    private static final int ROWS = 15; //13 empty
    private static final int COLUMNS = 30; //28 empty
    private static SpaceOriginal[][] grid;
    private static SpaceOriginal[][] supportGrid;

    public GridOriginal() {

        grid = new SpaceOriginal[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = new SpaceOriginal();

            }
        }
        supportGrid = new SpaceOriginal[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                supportGrid[i][j] = new SpaceOriginal();
            }
        }
        for (int i = 1; i < ROWS; i++) {
            for (int j = 1; j < COLUMNS; j++) {
                //grid[i][j] = new Space();
                grid[i][j].setColumnNumber(j);
                grid[i][j].setRowNumber(i);
            }
        }
    }

    public static void fillGrid() {

        for (int j = 0; j < COLUMNS; j++) {//top bound
            int i = 0;
            grid[i][j].setValueToBound();
        }
        for (int i = 0; i < ROWS; i++) {//left bound
            int j = 0;
            grid[i][j].setValueToBound();
        }
        for (int i = 1; i < 14; i++) {//right bound
            int j = 29;
            grid[i][j].setValueToBound();
        }
        for (int j = 0; j < 30; j++) {//bottom bound
            int i = 14;
            grid[i][j].setValueToBound();
        }
        for (int i = 1; i < 14; i++) {//fill in the rest
            for (int j = 1; j < 29; j++) {
                grid[i][j].setValueToEmpty();
            }
        }

    }

    public static void fillSupportGrid() {

        for (int j = 0; j < COLUMNS; j++) {//top bound
            int i = 0;
            supportGrid[i][j].setValueToBound();
        }
        for (int i = 0; i < ROWS; i++) {//left bound
            int j = 0;
            supportGrid[i][j].setValueToBound();
        }
        for (int i = 1; i < 14; i++) {//right bound
            int j = 29;
            supportGrid[i][j].setValueToBound();
        }
        for (int j = 0; j < 30; j++) {//bottom bound
            int i = 14;
            supportGrid[i][j].setValueToBound();
        }
        for (int i = 1; i < 14; i++) {//fill in the rest
            for (int j = 1; j < 29; j++) {
                supportGrid[i][j].setValueToEmpty();
            }
        }

    }

    public void printGrid() {

        int x = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(grid[i][j].getValue());
                x++;

                if (x == 30) {
                    System.out.println("");
                    x = x - 30;

                }
            }
        }
        System.out.println("");

    }

    public void addStuff() { //Adds all characters to grid randomly
        Random randNum = new Random();
        int n = randNum.nextInt(ROWS);
        int m = randNum.nextInt(COLUMNS);
        for (int i = 0; i < 10; i++) {
            if (grid[n][m].getValue().equals(".")) {
                grid[n][m].setValueToObstacle();
                n = randNum.nextInt(ROWS);
                m = randNum.nextInt(COLUMNS);
            } else {
                i--;
                n = randNum.nextInt(ROWS);
                m = randNum.nextInt(COLUMNS);
            }
        }
        n = randNum.nextInt(ROWS);
        m = randNum.nextInt(COLUMNS);
        for (int i = 0; i < 10; i++) {
            if (grid[n][m].getValue().equals(".")) {
                grid[n][m].setValueToHider();
                n = randNum.nextInt(ROWS);
                m = randNum.nextInt(COLUMNS);
            } else {
                i--;
                n = randNum.nextInt(ROWS);
                m = randNum.nextInt(COLUMNS);
            }
        }
        n = randNum.nextInt(ROWS);
        m = randNum.nextInt(COLUMNS);
        for (int i = 0; i < 10; i++) {
            if (grid[n][m].getValue().equals(".")) {
                grid[n][m].setValueToSeeker();
                n = randNum.nextInt(ROWS);
                m = randNum.nextInt(COLUMNS);
            } else {
                i--;
                n = randNum.nextInt(ROWS);
                m = randNum.nextInt(COLUMNS);
            }
        }
    }

    public int remainingHiders() {
        int num = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (grid[i][j].getValue().equals("H") || (grid[i][j].getValue().contains("T"))) {
                    num++;
                }
            }
        }
        return num;
    }

    public void seekerMovement() { //Controls the movement of all Seekers and changes all hiders "H,T" to X when found
        Random randNum = new Random();
        int n = randNum.nextInt(8) + 1;
        for (int i = 1; i < 14; i++) {

            for (int j = 1; j < 29; j++) {
                if ((grid[i][j].getValue().contains("S")) && (!supportGrid[i][j].getValue().contains("S"))) {

                    switch (n) {
                        case 1: { //right

                            if (grid[i][j + 1].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i][j + 1].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;
                                j++;
                                break;
                            } else if ((grid[i][j + 1].getValue().contains("H")) || (grid[i][j + 1].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i][j + 1].setValueToFound();
                                System.out.println("Caught one at " + grid[i][j].getRowNumber() + ", " + (grid[i][j].getColumnNumber() + 1));
                                n = randNum.nextInt(8) + 1;
                                j++;
                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                        case 2: { //left
                            if (grid[i][j - 1].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i][j - 1].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;
                                break;

                            } else if ((grid[i][j - 1].getValue().contains("H")) || (grid[i][j - 1].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i][j - 1].setValueToFound();
                                System.out.println("Caught one at " + grid[i][j].getRowNumber() + ", " + (grid[i][j].getColumnNumber() - 1));
                                n = randNum.nextInt(8) + 1;
                                break;

                            } else {
                                j--;
                                n = randNum.nextInt(8) + 1;
                                break;

                            }
                        }
                        case 3: { //down
                            if (grid[i + 1][j].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i + 1][j].setValueToSeeker();
                                supportGrid[i + 1][j].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else if ((grid[i + 1][j].getValue().contains("H")) || (grid[i + 1][j].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i + 1][j].setValueToFound();

                                System.out.println("Caught one at " + (grid[i][j].getRowNumber() + 1) + ", " + grid[i][j].getColumnNumber());
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                        case 4: { //up
                            if (grid[i - 1][j].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i - 1][j].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else if ((grid[i - 1][j].getValue().contains("H")) || (grid[i - 1][j].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i - 1][j].setValueToFound();
                                System.out.println("Caught one at " + (grid[i][j].getRowNumber() - 1) + ", " + grid[i][j].getColumnNumber());
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                        case 5: { //up left
                            if (grid[i - 1][j - 1].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i - 1][j - 1].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else if ((grid[i - 1][j - 1].getValue().contains("H")) || (grid[i - 1][j - 1].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i - 1][j - 1].setValueToFound();
                                System.out.println("Caught one at " + (grid[i][j].getRowNumber() - 1) + ", " + (grid[i][j].getColumnNumber() - 1));
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                        case 6: { //up right
                            if (grid[i - 1][j + 1].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i - 1][j + 1].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else if ((grid[i - 1][j + 1].getValue().contains("H")) || (grid[i - 1][j + 1].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i - 1][j + 1].setValueToFound();
                                System.out.println("Caught one at " + (grid[i][j].getRowNumber() - 1) + ", " + (grid[i][j].getColumnNumber() + 1));
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                        case 7: { //down left
                            if (grid[i + 1][j - 1].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i + 1][j - 1].setValueToSeeker();
                                supportGrid[i + 1][j - 1].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else if ((grid[i + 1][j - 1].getValue().contains("H")) || (grid[i + 1][j - 1].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i + 1][j - 1].setValueToFound();
                                System.out.println("Caught one at " + (grid[i][j].getRowNumber() + 1) + ", " + (grid[i][j].getColumnNumber() - 1));
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                        case 8: { //down right
                            if (grid[i + 1][j + 1].getValue().contains(".")) {

                                grid[i][j].setValueToEmpty();
                                grid[i + 1][j + 1].setValueToSeeker();
                                supportGrid[i + 1][j + 1].setValueToSeeker();
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else if ((grid[i + 1][j + 1].getValue().contains("H")) || (grid[i + 1][j + 1].getValue().contains("T"))) {

                                grid[i][j].setValueToEmpty();
                                grid[i + 1][j + 1].setValueToFound();
                                System.out.println("Caught one at " + (grid[i][j].getRowNumber() + 1) + ", " + (grid[i][j].getColumnNumber() + 1));
                                n = randNum.nextInt(8) + 1;

                                break;

                            } else {
                                n = randNum.nextInt(8) + 1;
                                j--;
                                break;
                            }
                        }
                    }

                }
            }
        }
    }

    public void FoundToSeeker() {
        for (int i = 1; i < 14; i++) {
            for (int j = 1; j < 29; j++) {
                if (grid[i][j].getValue().contains("X")) {
                    grid[i][j].setValueToSeeker();
                }
            }
        }
    }


}
