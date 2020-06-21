import java.util.Comparator;

public class Comparator1 implements Comparator<Entry> {
    @Override
    public int compare(Entry o1, Entry o2) {
        return o1.getText().compareTo(o2.getText());
    }
}
