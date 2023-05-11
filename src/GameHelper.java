import java.util.Scanner;

public class GameHelper {

    public int getUserInput(String enterANumber) {
        String userInput = "enter a number";
        System.out.print(userInput + ": ");
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }
    }
