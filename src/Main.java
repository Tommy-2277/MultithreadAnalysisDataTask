import controller.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/*
Задача 2:
Система анализа текстовых данных.
1. Сделать консольную программу, которая примет на вход список путей к файлам для чтения (любое количество).
2. После ввода всех путей пользователем - пользователь вводит команду "запуск"
3. Программа запускает в отдельном потоке для каждого файла операцию подсчета количества символов в файле, пробелов, количество предложений и количество слов.
4. Ограничить количество потоков пулом из 3-х потоков
5. После заверешения всех потоков - программа выводит на экран полученную статистическую информацию по каждому файлу.
6. Использовать подход MVC
 */
public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Controller controller = new Controller();
        controller.controller();//method name is not accurate
    }
}