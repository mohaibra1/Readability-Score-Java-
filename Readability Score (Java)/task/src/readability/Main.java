package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Text text = new Text();

        try (Scanner scanner = new Scanner(file);){
            System.out.println("The text is: ");
            while (scanner.hasNext()){
                String data = scanner.nextLine();
                System.out.println(data);
                System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                text.setUserInput(userInput);
                text.readabilityScore(data);
            }

        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

//        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
//        Scanner sc = new Scanner(System.in);
//        String userInput = sc.nextLine();
//        text.setUserInput(userInput);

//        Scanner scanner = new Scanner(System.in);
//        String sentence = scanner.nextLine();
//        Text text = new Text();
//        text.readabilityScore(sentence);

    }
}
