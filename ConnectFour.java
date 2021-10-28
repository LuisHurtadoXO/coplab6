import java.util.Scanner;

public class ConnectFour {

    public static void printBoard(char[][] array) {     //method 1 - used to print the board
        for (int x = array.length - 1; x >= 0; x--) {
            for (int y = 0; y < array[0].length; y++) {
                System.out.print(array[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array) {        //method 2 - used to fill in the empty spaces of the board array with "-"
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                array[x][y] = '-';
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType) {       //method 3 - ensures that a chip is inserted based on the user column selection. if the spot is already taken, then a chip will be placed in the next available slot in the column
        for (int x = 0; x < array.length; x++) {
            if (array[x][col] == '-') {
                array[x][col] = chipType;
                return x;
            }
        }
        return -1;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {      //method 4 - after every chip insertion, it is checked whether there are four of the same chips in a row. if four in a row is detected, value returned is true, where if there is not, then value returned is false
        int count = 0;

        for(int x = 0; x < array.length; x++) {     //checks for four in a row in any column
            if(array[x][col] == chipType) {
                count++;
                if(count == 4) {
                    return true;
                }
            }
            else{
                count = 0;
            }
        }
        count = 0;

        for(int y = 0; y < array[0].length; y++) {      //checks for four in a row in any row
            if(array[row][y] == chipType) {
                count++;
                if(count == 4) {
                    return true;
                }
            }
            else{
                count = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        int height;        //integer values for height and length
        int length;

        while(true) {
            System.out.print("What would you like the height of the board to be? ");        //takes in previously assigned variables in order to check that the selection of height and length of the board are at least 4
            height = scnr.nextInt();
            if(height >= 4) {
                break;
            }
        }
        while(true) {
            System.out.print("What would you like the length of the board to be? ");
            length = scnr.nextInt();
            if(length >= 4) {
                break;
            }
        }

        char board[][] = new char[height][length];      //sets number of characters of the array based on height and length chosen
        char playerTeam;        //character value for different player teams

        int columnSel;      //integer values for user column selection, chip placement, and to determine whether the board is full as to detect a tie
        int chipPlacement;
        int boardFull = 0;

        boolean playerWin = true;       //boolean values for whether a player has won and whether the game is live
        boolean gameStatus;

        initializeBoard(board);     //desired board is printed
        printBoard(board);

        System.out.println("Player 1: x");      //player team designation prompt is printed
        System.out.println("Player 2: o");

        while(true) {
            if(playerWin) {     //designates characters x and o to players 1 and 2 respectively
                System.out.print("Player 1: ");
                playerTeam = 'x';
            }
            else{
                System.out.print("Player 2: ");
                playerTeam = 'o';
            }
            System.out.print("Which column would you like to choose ? ");       //prompt for column selection is printed
            columnSel = scnr.nextInt();
            if(columnSel < 0 || columnSel >= length) {
                break;      //stops game if column selection is negative or if it exceeds the board length
            }
            else{
                chipPlacement = insertChip(board, columnSel, playerTeam);       //places chip in desired column
            }
            if(chipPlacement == -1) {
                break;      //stops game if there is no space available  in a column
            }
            else{
                printBoard(board);
                gameStatus = checkIfWinner(board, columnSel , chipPlacement, playerTeam);       //when gameStatus value is true due to checkIfWinner method detecting four in a row, it is evaluated where player 1 or player 2 win message is displayed based on whom won
                if(gameStatus) {
                    if(playerWin) {
                        System.out.print("Player 1 won the game!");
                    }
                    else{
                        System.out.print("Player 2 won the game!");
                    }
                    break;
                    }
                playerWin =! playerWin;
                boardFull++;
            }
            if(boardFull == height * length) {      //if the board's spaces are completely taken up without four in a row from either players' chips, then a draw message is printed
            System.out.println("Draw. Nobody wins.");
            break;
            }
        }
    }
}

