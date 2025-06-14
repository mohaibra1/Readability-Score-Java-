package readability;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text {
    private String userInput;

    public void setUserInput(String userInput){
        this.userInput = userInput;
    }

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

    public void readabilityScore(String text){
        int words = getWords(text);
        int sentences = getSentences(text);
        int chars = getCharacters(text);

        String[] wordss = text.split("\\s+");
        int totalSyllables = 0;
        int polysyllables = 0;

        for (String word : wordss) {
            String cleanWord = word.toLowerCase().replaceAll("[^a-z]", "");
            int syllables = countSyllables(cleanWord);

            totalSyllables += syllables;
            if (syllables >= 3) {
                polysyllables++;
            }
        }


        double score = automatedReadabilityIndex(words,sentences,chars);
        double flesch = fleschKincaidReadabilityIndex(words, sentences, totalSyllables);
        double smog = smogIndex(polysyllables, sentences);
        double coleman = colemanLiauIndex(((double) chars / words) * 100, ((double) sentences /words) * 100);

        System.out.printf("Words: %d\nSentences: %d\nCharacters: %d\nSyllables: %d\n" +
                        "Polysyllables: %d\n",
                words,sentences,chars,totalSyllables, polysyllables);

        int scoreUpper = upperBond(gradeLevel(score));
        int fleschUpper = upperBond(gradeLevel(flesch));
        int smogUpper = upperBond(gradeLevel(smog));
        int colemanUpper = upperBond(gradeLevel(coleman));

        System.out.println(userInput);
        if (Objects.equals(userInput, "all")){
            System.out.printf("Automated Readability Index: %.2f (about %d-year-olds)\n", score, scoreUpper);
            System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds)\n", flesch, fleschUpper);
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds)\n",  smog, smogUpper);
            System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds)\n" , coleman, colemanUpper);
        }else if(userInput.equals("ARI")){
            System.out.printf("Automated Readability Index: %.2f (about %d-year-olds)\n", score, scoreUpper);
        }else if (userInput.equals("FK")){
            System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds)\n", flesch, fleschUpper);
        }else if (userInput.equals("SMOG")){
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds)\n",  smog, smogUpper);
        }else if (userInput.equals("CL")){
            System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds)\n" , coleman, colemanUpper);
        }
    }

    public double automatedReadabilityIndex(int words, int sentences, int chars){
        return 4.71 * ((double) chars /words) + 0.5 * ((double) words /sentences) - 21.43;
    }

    public double fleschKincaidReadabilityIndex(int words, int sentences, int syllables){
        return 0.39 * ((double) words /sentences) + 11.8 * ((double) syllables /words) - 15.59;
    }

    public double smogIndex(int polysyllables, int sentences){
        return 1.043 * Math.sqrt(polysyllables * ((double) 30 /sentences)) +  3.1291;
    }

    public double colemanLiauIndex(double l, double s){
        return 0.0588 * l  - 0.296 *  s - 15.8;
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

    private int countSyllables(String word){
        if (word.isEmpty()) return 1;

        int count = 0;
        boolean lastWasVowel = false;
        String vowels = "aeiouy";

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            boolean isVowel = vowels.indexOf(ch) != -1;

            if (isVowel && !lastWasVowel) {
                count++;
                lastWasVowel = true;
            } else {
                lastWasVowel = false;
            }
        }

        // Remove silent 'e'
        if (word.endsWith("e") && count > 1) {
            count--;
        }

        return Math.max(count, 1);
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

    private int upperBond(String str){
        if (str.equals("Invalid number!")){
            return 0;
        }
        String[] splt = str.split("-");
        int x = Integer.parseInt(splt[0]);
        int y = Integer.parseInt(splt[1]);

        return Math.max(x,y);
    }

}
