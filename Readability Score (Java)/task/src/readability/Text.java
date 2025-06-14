package readability;

import java.util.ArrayList;
import java.util.List;

public class Text {


    public void simpleTextDifficulty(String text){

        System.out.println(text.length() > 100 ? "HARD" : "EASY");
    }

    public void mediumTextDifficulty(String text){
        String[] splt = text.split("[.!?]");
        List<String> arr = new ArrayList<>();
        for (String str: splt) {
            arr.addAll(List.of(str.split("\\s+")));
        }

        int average = arr.size() / splt.length;
        System.out.println(average > 10 ? "HARD" : "EASY");
    }

    public void AutomatedReadabilityIndex(String text){
        int words = getWords(text);
        int sentences = getSentences(text);
        int chars = getCharacters(text);

        double score = 4.71 * ((double) chars /words) + 0.5 * ((double) words /sentences) - 21.43;
        String gradeL = gradeLevel(score);
        System.out.printf("words: %d\nsentences: %d\ncharacters: %d\nThe score is: %.2f\nThis text should be understood by %s years olds.",
                words,sentences,chars,score, gradeL);
    }

    private String gradeLevel(double score){
        int roundOff = (int) Math.ceil(score);
        return switch (roundOff) {
            case 1 -> "5-6";
            case 2 -> "6-7";
            case 3 -> "7-8";
            case 4 -> "8-9";
            case 5 -> "9-10";
            case 6 -> "10-11";
            case 7 -> "12-13";
            case 8 -> "12-14";
            case 9 -> "13-14";
            case 10 -> "14-15";
            case 11 -> "15-16";
            case 12 -> "16-17";
            case 13 -> "17-18";
            case 14 -> "18-22";
            default -> "Invalid number!";
        };
    }

    private int getWords(String text){
        String rpl = text.replaceAll("[.!?]", "");
        String[] words = text.split(" ");
        return words.length;
    }

    private int getSentences(String text){
        String[] splt = text.split("[.!?]");
        return splt.length;
    }

    private int getCharacters(String text){
        String rpl = text.replaceAll("\\s+", "");
        return rpl.length();
    }

}
