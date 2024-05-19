package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeService;
import com.se.ecostruxure_mmirzakhani.bll.HistoryService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.model.Model;

import com.se.ecostruxure_mmirzakhani.utils.ObjectService;
import com.se.ecostruxure_mmirzakhani.utils.Validate;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;
import mssql.security.provider.MD4;

import java.lang.reflect.Field;
import java.time.LocalDateTime;


public class Main extends Application {
    // ToDo : Fix the Region and Country handling part in DAO
    //  : GUI Logic class
    //  : Validate in the front-end
    //  : Autofill and auto-suggest and typo detector based expected average value from DB for some fields
    //  : Lazy loading for team employees and caching
    //  : Observer pattern for currency system. so when it's changed in model, it changes in all the classes.

    public static void main(String[] args) throws ExceptionHandler {

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
