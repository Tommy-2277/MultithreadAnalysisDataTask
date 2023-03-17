package view;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class View {
    Scanner scanner = new Scanner(System.in);

    public int helloUser() {
        while (true) {
            System.out.println(enterCommandNumber);
            String stringCommand = scanner.nextLine();
            if (stringCommand.equals("1") || stringCommand.equals("2")) {
                return Integer.parseInt(stringCommand);
            } else {
                System.out.println(wrongNumber);
            }

        }
    }

    public String getPath() {
        while (true) {
            System.out.println(enterPathToFile);
            String pathString = scanner.nextLine();
            if (Files.exists(Path.of(pathString)) && Files.isRegularFile(Path.of(pathString))) {
                return pathString;
            }
        }
    }

    public void printResults(Map<String, List<Integer>> map) {
        Set<Map.Entry<String, List<Integer>>> newSet = map.entrySet();
        for (Map.Entry<String, List<Integer>> pair : newSet) {
            String key = pair.getKey();
            List<Integer> value = pair.getValue();
            System.out.println("\nВ файле " + key + ":" +
                    "\nколичество символов - " + value.get(0) +
                    "\nколичество пробелов - " + value.get(1) +
                    "\nколичество предложений - " + value.get(2) +
                    "\nколичество слов - " + value.get(3));
        }
    }

    public final String enterPathToFile = "Укажите полный путь к файлу, в котором нужно произвести подсчёт слов, предложений и т.д.";
    public final String enterCommandNumber = "\nВведите \"1\" для добавления файла;\nВведите \"2\" для получения результатов.";
    public final String wrongNumber = "Неправильно введено число.";
}
