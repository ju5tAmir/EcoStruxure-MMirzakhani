package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    // ToDo : Fix the Region and Country handling part in DAO
//      : Validate in the front-end
//      : Autofill and auto-suggest and typo detector based expected average value from DB for some fields

    public static void main(String[] args)  {
        
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
