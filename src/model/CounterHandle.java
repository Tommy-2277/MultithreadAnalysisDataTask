package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CounterHandle implements Callable<List<Integer>> {
    private String path;

    public CounterHandle(String path) {
        this.path = path;
    }

    @Override
    public List<Integer> call() throws Exception {
        List<String> textFromFile = Files.readAllLines(Path.of(path));
        for (int i = 0; i < textFromFile.size(); i++) {
            if(textFromFile.get(i).equals("")) {
                textFromFile.remove(i);
                i--;
            }
        }
        List<String> textFromFileByWords = new ArrayList<>();
        for (String s: textFromFile) {
            String[] tmpArr = s.split(" ");
            for (int i = 0; i < tmpArr.length; i++) {
                textFromFileByWords.add(tmpArr[i]);
            }
        }
        for (int i = 0; i < textFromFileByWords.size(); i++) {
            String s = textFromFileByWords.get(i);
            if(s.equals("") || s.equals("-") || s.equals("—")){
                textFromFileByWords.remove(i);
                i--;
            }
        }
        List<Integer> results = new ArrayList<>();
        results.add(0, countSymbols(textFromFile));
        results.add(1, countSpaces(textFromFile));
        results.add(2, countSentences(textFromFileByWords));
        results.add(3, countWords(textFromFileByWords));
        return results;
    }

    public int countSymbols(List<String> text) {
        int counter = 0;
        for(String s: text) {
            counter+=s.length();
        }
        return counter;
    }
    public int countSpaces(List<String> text) {
        int counter = 0;
        for(String s: text) {
            int i = s.length();
            for (int j = 0; j < i; j++) {
                if(s.charAt(j) == ' ') {
                    counter++;
                }
            }
        }
        return counter;
    }
    public int countSentences(List<String> text) {
        int counter = 0;
        for (String s: text) {
            if(s.endsWith(".") || s.endsWith("!") || s.endsWith("?") || s.endsWith("...")) {
                counter++;
            }
        }
        return counter;
    }
    public int countWords(List<String> text) {
        return text.size();
    }
}