package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);

        try (Scanner scanner = new Scanner(file);){
            System.out.println("The text is: ");
            while (scanner.hasNext()){
                String data = scanner.nextLine();
                System.out.println(data);
                Text text = new Text();
                text.AutomatedReadabilityIndex(data);
            }

        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

//        Scanner scanner = new Scanner(System.in);
//        String sentence = scanner.nextLine();
//        Text text = new Text();
//        text.AutomatedReadabilityIndex(sentence);

    }
}
