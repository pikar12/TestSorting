import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class Sort {
    private static int choice;
    private static int wordNumber;
    private static final String SOURCE_PATH="c:/test/test.txt";
    private static final String DESTINATION_PATH="c:/test/test_result.txt";

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("Выбор способа сортировки (1-3): ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String s = reader.readLine();
                if (s.isEmpty())
                    return;
                choice = Integer.parseInt(s);
                if (choice==3) {
                    System.out.print("По какому слову сортировать? Введите номер слова: ");
                    wordNumber=Integer.parseInt(reader.readLine());
                }
                List<String> sourceList = getSourceList();
                List<Entry> entryList = getSourceEntriesUnsorted(sourceList);
                Collections.sort(entryList,getComparator());
                System.out.println(entryList);
                saveToFile(entryList);
                break;
            } catch (NumberFormatException e) {
                continue;
            } catch (IOException e) {
                return;
            }
        }
    }

    private static void saveToFile(List<Entry> entryList) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(DESTINATION_PATH)) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream,"Windows-1251"));
            for (Entry entry : entryList) {
                bufferedWriter.write(entry.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private static List<String> getSourceList() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(SOURCE_PATH)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream,"Windows-1251"));
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line=reader.readLine())!=null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            throw e;
        }

    }

    private static List<Entry> getSourceEntriesUnsorted(List<String> sourceList) {
        Map<String,Integer> map = getSourceMap(sourceList);
        List<Entry> list = new ArrayList<>();
        for (String s : sourceList) {
            Entry entry = new Entry(s);
            Integer count = map.get(s);
            entry.setCount(count);
            list.add(entry);
        }
        return list;
    }

    private static Map<String,Integer> getSourceMap(List<String> sourceList) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        for(String s : sourceList) {
            if (map.containsKey(s)) {
                Integer integer = map.get(s);
                integer++;
                map.put(s,integer);
            }
            else
                map.put(s,1);
        }
        return map;
    }

    private static Comparator<Entry> getComparator() {
        switch (choice) {
            case 1:
                return new Comparator1();
            case 2:
                return new Comparator2();
            case 3:
                return new Comparator3(wordNumber);
            default:
                return null;
        }
    }

}
