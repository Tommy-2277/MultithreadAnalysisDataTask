package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Controller {
    static Model model = new Model();
    static View view = new View();

    public void controller() throws IOException, ExecutionException, InterruptedException {
        while(true) {
            int caseNumber = view.helloUser();
            if(caseNumber == 1) {
                model.addTask(view.getPath());
            } else {
                view.printResults(model.getResults());
                break;
            }
        }

    }
}
