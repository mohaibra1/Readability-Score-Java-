package readability;

public class Text {
    private final String text;

    public Text(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void textDifficulty(){
        System.out.println(text.length() > 100 ? "HARD" : "EASY");
    }
}
