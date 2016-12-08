package es.upv.com.aenaApp.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * @param contiene los métodos para hacer arrastrable y cargar nueva ventana
 */

public class Dialogs {
	
	private double desplazX=0;
	private double desplazY=0;
	
	//Carga tipo de ventana
	public void loadScene(String Scene, ActionEvent event) throws IOException {

	Parent home_page_parent = FXMLLoader
			.load(getClass().getResource(Scene));
	Scene home_page_scene = new Scene(home_page_parent);
	Stage app_stage = (Stage) ((Node) event.getSource()).getScene()
			.getWindow();		
	app_stage.hide(); // para esconder ventana principal
	app_stage.setScene(home_page_scene);
	app_stage.centerOnScreen();
	home_page_scene.setFill(null);
	app_stage.show();
	
	setArrastrable(home_page_parent,app_stage);
}

	
    public void setArrastrable(Parent page, Stage PopUpStage){
		/*Para hacerla arrastrable*/

		//Tomamos la posición actual al pulsar con el ratón
		page.setOnMousePressed((evento)	->	{	
			desplazX = evento.getSceneX();
			desplazY = evento.getSceneY();
		});

		//Actualizamos la posición al desplazar
		//mientras está pulsado (arrastramos) (drag)

		page.setOnMouseDragged((evento)	->	{	
			PopUpStage.setX(evento.getScreenX() - desplazX);
			PopUpStage.setY(evento.getScreenY() - desplazY);
		});
		/*Fin código para hacerla arrastrable*/
    }

}
