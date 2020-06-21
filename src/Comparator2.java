import java.util.Comparator;

public class Comparator2 implements Comparator<Entry> {
    @Override
    public int compare(Entry o1, Entry o2) {
        return o1.getSymbolsCount()-o2.getSymbolsCount();
    }
}
