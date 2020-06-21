import java.util.Comparator;

public class Comparator3 implements Comparator<Entry> {

    private int wordNumber;

    public Comparator3(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    @Override
    public int compare(Entry o1, Entry o2) {
        return o1.getNthWord(wordNumber).compareTo(o2.getNthWord(wordNumber));
    }
}
