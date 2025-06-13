package readability;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        Text text1 = new Text(text);
        //text1.simpleTextDifficulty();
        text1.mediumTextDifficulty();

    }
}
