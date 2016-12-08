package es.upv.com.aenaApp;


import java.io.IOException;

import application.data.Data;
import es.upv.com.aenaApp.controller.Dialogs;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * @author Mario Cordero Sánchez
 */
public class MainApp extends Application {
	
    public static final String APPLICATION_ICON =
            "images/aenApp.ico";
    public static final String SPLASH_IMAGE =
            "images/aenaSS.png";
    public static final String SPLASH_ICON =
            "images/aena.ico";
    
	String mainScene = "controller/mainScene.fxml";
	String css="view/application.css";


    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 270;
    private static final int SPLASH_HEIGHT = 270;

    Dialogs framework = new Dialogs();
    

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
		loadProgress.setStyle("-fx-accent: #90ce00;");
		progressText = new Label("Dirigiéndose a la Terminal de Salida . . .");
	    progressText.setTextFill(Color.WHITESMOKE);

		splashLayout = new VBox();
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		progressText.setAlignment(Pos.CENTER);
		splashLayout
				.setStyle("-fx-padding: 5; -fx-background-color: #1a2732; -fx-border-width:5; -fx-border-color: linear-gradient(to bottom, #90ce00, derive(#90ce00, 50%));");
		splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
    	//Creamos una lista a modo de carga para asignar carga
   
        final Task<ObservableList<String>> airportTasks = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundTasks =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableTasks =
                        FXCollections.observableArrayList(
                                "Aeropuertos Nacionales", "Aeropuertos Internacionales",
                                "Compañías Aéreas Nacionales", 
                                "Compañías Aéreas Internacionales", 
                                "Tipos de Aviones", "Retrasos", "Menú Principal"
                        );
                getInstance();
                updateMessage("Cargando  . . .");
                for (int i = 0; i < availableTasks.size(); i++) {
                    Thread.sleep(200);
                    updateProgress(i + 1, availableTasks.size());
                    String nextTask = availableTasks.get(i);
                    foundTasks.add(nextTask);
                    updateMessage("Cargando . . . " + nextTask);
                }
                Thread.sleep(400);
                updateMessage("Venga, ¡qué despegamos!");
                

                return foundTasks;
            }
        };

        showSplash(
                initStage,
                airportTasks,
                () -> showMainStage(airportTasks.valueProperty())
        );
        new Thread(airportTasks).start();
    }

	private void showMainStage(
			ReadOnlyObjectProperty<ObservableList<String>> airportTasks) {
		
			try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource(mainScene));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource(css)
							.toExternalForm());
			mainStage = new Stage(StageStyle.TRANSPARENT);
			mainStage.setTitle("AenAPP");
			mainStage.getIcons().add(new Image(APPLICATION_ICON));
			mainStage.setScene(scene);
			
			framework.setArrastrable(root,mainStage);
			mainStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.getIcons().add(new Image(SPLASH_ICON));
        initStage.show();
    }

    public interface InitCompletionHandler {
        public void complete();
    }
    
    
    public Data getInstance(){
    	
    	return 	application.data.Data.getInstance();

    }
   
    
}