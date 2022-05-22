public class Program {
    public static void main(String[] args) {
        int x = 0;
        GridOriginal myGrid = new GridOriginal();
        myGrid.fillGrid();
        myGrid.fillSupportGrid();
        System.out.println("Turn: " + 0);
        System.out.println("Remaining Hiders: " + 10);
        myGrid.addStuff();
        myGrid.printGrid();


        while (x <= 250) {
            myGrid.seekerMovement();
            myGrid.fillSupportGrid();


            System.out.println("Turn: " + x);
            System.out.println("Remaining Hiders: " + myGrid.remainingHiders());
            myGrid.printGrid();

            myGrid.FoundToSeeker();

            if (myGrid.remainingHiders() == 0) {
                System.out.println("Found all Hiders");
                System.out.println("Game Over!");
                break;
            }
            if (x == 250) {
                System.out.println("Game Over!");
                System.out.println(myGrid.remainingHiders() + " got away!");
            }
            x++;
        }
    }
}
