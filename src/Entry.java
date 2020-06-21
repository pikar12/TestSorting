import java.util.Scanner;

public class Entry {
    private String text;
    private int count;

    public Entry(String text) {
        this.text = text;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public int getCount() {
        return count;
    }

    public int getSymbolsCount() {
        return text.length();
    }

    public String getNthWord(int i) {
        Scanner scanner = new Scanner(getText());
        int currentIndex = 0;
        while (scanner.hasNext()) {
            String s = scanner.next();
            currentIndex++;
            if (i==currentIndex)
                return s;
        }
        return "";
    }

    @Override
    public String toString() {
        return text + " " + count;
    }
}
