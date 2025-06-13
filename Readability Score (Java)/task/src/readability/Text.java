package readability;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private final String text;

    public Text(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void simpleTextDifficulty(){
        System.out.println(text.length() > 100 ? "HARD" : "EASY");
    }

    public void mediumTextDifficulty(){
        String[] splt = text.split("[.!?]");
        List<String> arr = new ArrayList<>();
        for (String str: splt) {
            arr.addAll(List.of(str.split("\\s+")));
        }

        int average = arr.size() / splt.length;
        System.out.println(average > 10 ? "HARD" : "EASY");
    }

}
