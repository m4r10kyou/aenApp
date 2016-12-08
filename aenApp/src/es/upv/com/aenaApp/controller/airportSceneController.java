package es.upv.com.aenaApp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import application.data.Data;
import application.model.Airport;
import es.upv.com.aenaApp.MainApp;

public class airportSceneController {

	@FXML
	private ComboBox<Airport> airports;
	@FXML
	private Button btnLlegada;
	@FXML
	private Button btnSalida;
	@FXML
	private DatePicker rangoInicio;
	@FXML
	private DatePicker rangoFin;

	@FXML
	private Label categAeropuerto;

	private String main = "mainScene.fxml";
	private String flights = "infoFlightsScene.fxml";
	
	private Airport airport;

	private int fechaInicio = 9;
	private int fechaFin = 20;

	Dialogs framework = new Dialogs();
	
	
	MainApp mainApp=new MainApp();

	Data valores=mainApp.getInstance();


	@FXML
	private void initialize() {
		// Creamos la lista de Aeropuestos

		List<Airport> listAirport = new ArrayList<Airport>();
		for (int indexAirport = 0; indexAirport < valores.getAirportList().size(); indexAirport++) {
			listAirport.add(valores.getAirportList().get(indexAirport));
		}

		// Ordenamos la lista creada antes
		if (listAirport.size() >= 0) {
			Collections.sort(listAirport, new Comparator<Airport>() {
				@Override
				public int compare(final Airport object1, final Airport object2) {
					return object1.getName().compareTo(object2.getName());
				}
			});
		}

		// Introducimos la lista al combobox
		for (int indexAirport = 0; indexAirport < listAirport.size(); indexAirport++) {
			airports.getItems().add(listAirport.get(indexAirport));
		}

		// Para que salga en Castellano los datos del Calendario
		Locale.setDefault(new Locale("ES"));

		// Configuramos los Calendarios para establecer Rango
		rangoInicio.setValue(LocalDate.of(2015, 3, 9));
		rangoFin.setValue(LocalDate.of(2015, 3, 20));

		// Creamos un limite de fechas inicial
		final Callback<DatePicker, DateCell> limitesInicio = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(LocalDate.of(2015, 3, 9))

						) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}

						if (item.isAfter(LocalDate.of(2015, 3, 20))

						) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};

		rangoInicio.setDayCellFactory(limitesInicio);

		// Modificamos los limites anteriores para que el nuevo inicio sea el
		// parámetro anterior
		final Callback<DatePicker, DateCell> limitesFin = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(rangoInicio.getValue())

						) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}

						if (item.isAfter(LocalDate.of(2015, 3, 20))

						) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
						//Restamos un día para tomar el propio día inicio como hábil
						long p = ChronoUnit.DAYS.between(
								rangoInicio.getValue().minusDays(1), item);
						setTooltip(new Tooltip("Seleccionaste " + p + " días"));
					}
				};
			}
		};
		rangoFin.setDayCellFactory(limitesFin);

		/*
		 * Hecho esto tomamos los dates Dado que lo tenemos limitado por 2
		 * semanas y mismo mes, tomamos sólo el día y no parametrizamos a
		 * LocalData
		 */
		rangoInicio.setOnAction((event) -> {
			fechaInicio = rangoInicio.getValue().getDayOfMonth();
		});

		rangoFin.setOnAction((event) -> {
			fechaFin = rangoFin.getValue().getDayOfMonth();
		});

		// forzamos la carga de acción por defecto a Salidas
		salidas(null);

		// referenciamos airport el nombre de la selección dentro del combobox

		airports.setOnAction((event) -> {
			//airports.setValue((Airport)AENA_Airport.getAirportByCode("VLC"));
			airport = airports.getSelectionModel().getSelectedItem();
			
		});

	}

	@FXML
	void salidas(ActionEvent event) {
		btnLlegada.setTextFill(Color.WHITE);
		btnSalida.setTextFill(Color.BLACK);
		categAeropuerto.setText("Origen");
		btnSalida.setMouseTransparent(true);
		btnLlegada.setMouseTransparent(false);
	}

	@FXML
	void llegadas(ActionEvent event) {
		btnLlegada.setTextFill(Color.BLACK);
		btnSalida.setTextFill(Color.WHITE);
		categAeropuerto.setText("Destino");
		btnSalida.setMouseTransparent(false);
		btnLlegada.setMouseTransparent(true);
	}

	@FXML
	void btnVolver(ActionEvent event) throws IOException {

		framework.loadScene(main, event);

	}

	@FXML
	void btnBuscar(ActionEvent event) throws IOException {

		if (!airports.getSelectionModel().isEmpty() && (fechaInicio <= fechaFin)) {
			dialog(event);


		} else if (fechaInicio > fechaFin) {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Atención");
			alert.setHeaderText("El rango de fechas está mal introducido");
			alert.initStyle(StageStyle.UTILITY);
			alert.setContentText("Por favor, comprueba que la Fecha Fin es mayor que Fecha Inicio");

			alert.showAndWait();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Atención");
			alert.setHeaderText("No seleccionaste aeropuerto");
			alert.initStyle(StageStyle.UTILITY);
			alert.setContentText("Por favor, selecciona un aeropuerto de la lista");

			alert.showAndWait();
		}
		

	}

	// / DIALOGOS

	private boolean dialog(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(mainSceneController.class.getResource(flights));
			AnchorPane page = (AnchorPane) loader.load();
			infoFlightsSceneController controller = loader.getController();
			controller.SettingText(btnSalida.isMouseTransparent(), airport, fechaInicio, fechaFin);
			Stage PopUpStage = new Stage();
			PopUpStage.initStyle(StageStyle.TRANSPARENT);
			PopUpStage.initModality(Modality.WINDOW_MODAL);
			PopUpStage.centerOnScreen();
			PopUpStage.setResizable(false);
			Scene scene = new Scene(page);
			PopUpStage.setScene(scene);
			scene.setFill(null);
			PopUpStage.initOwner((Stage) ((Node) event.getSource()).getScene()
					.getWindow());
			controller.setInfoStage(PopUpStage);

			framework.setArrastrable(page, PopUpStage);

			PopUpStage.showAndWait();

			return controller.getOkValue();

		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error en el Aeropuerto");
			alert.initStyle(StageStyle.UTILITY);
			alert.setContentText("Existe un error al pasar los datos del Aeropuerto "+ airport+
					" con el rango de fechas del "+fechaInicio+"-03-2015 al "+fechaFin+
					"-03-2015, y el tipo de vuelo");
			alert.showAndWait();
			System.out.println("Internal error: " + ex.getMessage());
		}
		return false;
	}

}
