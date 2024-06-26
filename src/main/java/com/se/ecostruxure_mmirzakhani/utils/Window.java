package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Abstract CRUD operations for windows
 */
public class Window implements IUtils {

    /**
     * Create a new stage based on the provided settings.
     *
     * @param windowType The type of window to create.
     * @param model The model associated with the controller of new window (can be null if controller doesn't need model)
     * @param modality The modality of the window.
     * @param resizable Whether the window should be resizable.
     * @param <T> Generic type representing type of the provided model.
     * @throws ExceptionHandler If an error occurs during stage creation.
     */
    public static <T> void  createStage(WindowType windowType,
                                        T model,
                                        Modality modality,
                                        boolean resizable)
            throws ExceptionHandler

    {
        // Load an FXML File based on requested WindowType.
        FXMLLoader fxmlLoader = loadFXML(getResourcePath(windowType));

        // Load a scene using the loaded FXML file
        Scene scene = loadScene(fxmlLoader);

        // Pass the model to the controller of new page
        IController<T> controller = fxmlLoader.getController();
        controller.setModel(model);

        // Create a new stage and set its properties.
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(resizable);

        // Set modality for the stage if requested (which means it's not null)
        if (modality != null) stage.initModality(modality);

        // Display the stage
        stage.show();

    }


    /**
     * Create a new stage based on the specified window type with default settings.
     * @param windowType The type of window to create.
     * @throws ExceptionHandler If an error occurs during stage creation.
     */
    public static void createStage(WindowType windowType) throws ExceptionHandler{
        createStage(windowType, null, null, false);
    }


    /**
     * Closes the provided stage.
     * @param stage The stage to be closed.
     */
    public static void closeStage(Stage stage){
        stage.close();
    }

    public static void closeStage(Scene scene){
        ((Stage) scene.getWindow()).close();
    }


    /**
     * Retrieves the Stage object associated with the scene containing the specified Node.
     * @param node The JavaFX Node whose Scene's Stage is to be retrieved.
     * @return The stage object associated with the provided Node.
     */
    public static Stage getStage(Node node){
        return (Stage) node.getScene().getWindow();
    }

    /**
     * Loads an FXML scene using the provided FXMLLoader.
     *
     * @param fxmlLoader The FXMLLoader configured with the FXML file to load.
     * @return The Scene object created from the loaded FXML file.
     * @throws ExceptionHandler If there is an issue loading the FXML file or creating the Scene.
     */
    private static Scene loadScene(FXMLLoader fxmlLoader) throws ExceptionHandler{
        try {
            // Load the FXML file using the provided FXMLLoader
            Parent root = fxmlLoader.load();

            // Create and return a new scene based on created root node
            return new Scene(root);
        } catch (IOException e) {
            // Throw an exception if runtime error occurred
            throw new ExceptionHandler(e.getMessage());
        }
    }

    /**
     * Load an FXML file specified by the provided resource path.
     * @param resourcePath The path to the FXML file.
     * @return An instance of FXMLLoader configured to load the specified FXML file.
     * @throws ExceptionHandler If there is an error loading FXML file or if the resource path is invalid.
     */
    private static FXMLLoader loadFXML(String resourcePath) throws ExceptionHandler {
        return new FXMLLoader(Window.class.getResource(resourcePath));
    }


    /**
     * Retrieve the resource path for the specified window type, easily extendable by in the future
     * if new FXML files are created for various uses.
     *
     * @param windowType The type of window for which the resource path is needed.
     * @return The resource path corresponding to the specified window type.
     * @throws ExceptionHandler If there is an issue fetching the resource path.
     */
    private static String getResourcePath(WindowType windowType) throws ExceptionHandler{
        return switch (windowType){
            case MAIN               -> WindowType.MAIN.getPath();
            case DASHBOARD          -> WindowType.DASHBOARD.getPath();
            case EMPLOYEE_MAIN      -> WindowType.EMPLOYEE_MAIN.getPath();
            case TEAM_MAIN          -> WindowType.TEAM_MAIN.getPath();
            case PROJECT_MANAGER    -> WindowType.PROJECT_MANAGER.getPath();
            case CREATE_EMPLOYEE    -> WindowType.CREATE_EMPLOYEE.getPath();
            case ASSIGNMENT         -> WindowType.ASSIGNMENT.getPath();
            case PROJECT_MAIN       -> WindowType.PROJECT_MAIN.getPath();
            case CREATE_PROJECT     -> WindowType.CREATE_PROJECT.getPath();
            case UPDATE_EMPLOYEE    -> WindowType.UPDATE_EMPLOYEE.getPath();
            case UPDATE_PROJECT     -> WindowType.UPDATE_PROJECT.getPath();
            case CREATE_TEAM        -> WindowType.CREATE_TEAM.getPath();
            case UPDATE_TEAM        -> WindowType.UPDATE_TEAM.getPath();


            // ToDo: Add additional cases for new window types and their corresponding resource paths

            // If there is any issue in fetching FXML file
            default -> throw new ExceptionHandler(ExceptionMessage.ILLEGAL_FILE_OPERATION.getValue());
        };
    }

    public static <T> void loadPane(Pane pane, WindowType window, T model) throws ExceptionHandler {
        try {
            FXMLLoader loader = loadFXML(getResourcePath(window));
            Parent root = loader.load();

            IController<T> controller = loader.getController();
            controller.setModel(model);

            pane.getChildren().setAll(root);


        } catch (IOException e){
            throw new ExceptionHandler(e.getMessage());
        }
    }

    public static void loadPane(Pane pane, WindowType window) throws ExceptionHandler {
        loadPane(pane, window, null);
    }
}
