package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Model {

    List<FutureTask<List<Integer>>> tasks = new ArrayList<>();//assigning better be done in constructor
    List<String> fileNames = new ArrayList<>();
    List<List<Integer>> results = new ArrayList<>();

    public Model() {}
    public void addTask(String path) {
        FutureTask<List<Integer>> futureTask = new FutureTask<>(new CounterHandle(path));
        tasks.add(futureTask);
        fileNames.add(path);
    }

    public Map<String, List<Integer>> getResults() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (FutureTask<List<Integer>> ft : tasks) {
            executorService.submit(ft);
        }

        for (FutureTask<List<Integer>> ft : tasks) {
            while(true) {
                if (!ft.isDone()) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }
        }

        for (FutureTask<List<Integer>> ft : tasks) {
            results.add(ft.get());
        }

        Map<String, List<Integer>> filesAndResults = new HashMap<>();
        for (int i = 0; i < fileNames.size(); i++) {
            filesAndResults.put(fileNames.get(i), results.get(i));
        }

        executorService.shutdown();

        return filesAndResults;
    }

}
