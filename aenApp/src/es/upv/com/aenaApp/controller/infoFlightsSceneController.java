package es.upv.com.aenaApp.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import application.data.Data;
import application.model.Airport;
import application.model.Flight;
import es.upv.com.aenaApp.MainApp;

public class infoFlightsSceneController {

	String source = "/images/header_";
	String img = "";

	@FXML
	private ImageView icon;
	@FXML
	private Label airportType;
	@FXML
	private Label airportName;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private VBox fData1;
	@FXML
	private VBox fData2;
	@FXML
	private VBox fData3;
	@FXML
	private VBox fData4;
	@FXML
	private VBox fData5;
	@FXML
	private VBox fData6;
	@FXML
	private VBox fData7;
	@FXML
	private VBox fData8;
	@FXML
	private VBox fData9;
	@FXML
	private VBox fData10;
	@FXML
	private VBox fData11;
	@FXML
	private VBox fData;

	@FXML
	private Label date;
	@FXML
	private TableView<Flight> listTable;
	@FXML
	private TableColumn<Flight, String> horaVuelo;
	@FXML
	private TableColumn<Flight, String> numVuelo;
	@FXML
	private TableColumn<Flight, String> airportColumn;
	@FXML
	private TableColumn<Flight, String> modelPlane;
	@FXML
	private TableColumn<Flight, String> statusVuelo;
	@FXML
	private TableColumn<Flight, String> company;
	@FXML
	private TableColumn<Flight, String> delay;

	@FXML
	private Label date1;
	@FXML
	private TableView<Flight> listTable1;
	@FXML
	private TableColumn<Flight, String> horaVuelo1;
	@FXML
	private TableColumn<Flight, String> numVuelo1;
	@FXML
	private TableColumn<Flight, String> airportColumn1;
	@FXML
	private TableColumn<Flight, String> modelPlane1;
	@FXML
	private TableColumn<Flight, String> statusVuelo1;
	@FXML
	private TableColumn<Flight, String> company1;
	@FXML
	private TableColumn<Flight, String> delay1;

	@FXML
	private Label date2;
	@FXML
	private TableView<Flight> listTable2;
	@FXML
	private TableColumn<Flight, String> horaVuelo2;
	@FXML
	private TableColumn<Flight, String> numVuelo2;
	@FXML
	private TableColumn<Flight, String> airportColumn2;
	@FXML
	private TableColumn<Flight, String> modelPlane2;
	@FXML
	private TableColumn<Flight, String> statusVuelo2;
	@FXML
	private TableColumn<Flight, String> company2;
	@FXML
	private TableColumn<Flight, String> delay2;

	@FXML
	private Label date3;
	@FXML
	private TableView<Flight> listTable3;
	@FXML
	private TableColumn<Flight, String> horaVuelo3;
	@FXML
	private TableColumn<Flight, String> numVuelo3;
	@FXML
	private TableColumn<Flight, String> airportColumn3;
	@FXML
	private TableColumn<Flight, String> modelPlane3;
	@FXML
	private TableColumn<Flight, String> statusVuelo3;
	@FXML
	private TableColumn<Flight, String> company3;
	@FXML
	private TableColumn<Flight, String> delay3;

	@FXML
	private Label date4;
	@FXML
	private TableView<Flight> listTable4;
	@FXML
	private TableColumn<Flight, String> horaVuelo4;
	@FXML
	private TableColumn<Flight, String> numVuelo4;
	@FXML
	private TableColumn<Flight, String> airportColumn4;
	@FXML
	private TableColumn<Flight, String> statusVuelo4;
	@FXML
	private TableColumn<Flight, String> modelPlane4;
	@FXML
	private TableColumn<Flight, String> company4;
	@FXML
	private TableColumn<Flight, String> delay4;
	@FXML
	private Label date5;
	@FXML
	private TableView<Flight> listTable5;
	@FXML
	private TableColumn<Flight, String> horaVuelo5;
	@FXML
	private TableColumn<Flight, String> numVuelo5;
	@FXML
	private TableColumn<Flight, String> airportColumn5;
	@FXML
	private TableColumn<Flight, String> statusVuelo5;
	@FXML
	private TableColumn<Flight, String> modelPlane5;
	@FXML
	private TableColumn<Flight, String> company5;
	@FXML
	private TableColumn<Flight, String> delay5;
	@FXML
	private Label date6;
	@FXML
	private TableView<Flight> listTable6;
	@FXML
	private TableColumn<Flight, String> horaVuelo6;
	@FXML
	private TableColumn<Flight, String> numVuelo6;
	@FXML
	private TableColumn<Flight, String> airportColumn6;
	@FXML
	private TableColumn<Flight, String> statusVuelo6;
	@FXML
	private TableColumn<Flight, String> modelPlane6;
	@FXML
	private TableColumn<Flight, String> company6;
	@FXML
	private TableColumn<Flight, String> delay6;
	@FXML
	private Label date7;
	@FXML
	private TableView<Flight> listTable7;
	@FXML
	private TableColumn<Flight, String> horaVuelo7;
	@FXML
	private TableColumn<Flight, String> numVuelo7;
	@FXML
	private TableColumn<Flight, String> airportColumn7;
	@FXML
	private TableColumn<Flight, String> statusVuelo7;
	@FXML
	private TableColumn<Flight, String> modelPlane7;
	@FXML
	private TableColumn<Flight, String> company7;
	@FXML
	private TableColumn<Flight, String> delay7;
	@FXML
	private Label date8;
	@FXML
	private TableView<Flight> listTable8;
	@FXML
	private TableColumn<Flight, String> horaVuelo8;
	@FXML
	private TableColumn<Flight, String> numVuelo8;
	@FXML
	private TableColumn<Flight, String> airportColumn8;
	@FXML
	private TableColumn<Flight, String> statusVuelo8;
	@FXML
	private TableColumn<Flight, String> modelPlane8;
	@FXML
	private TableColumn<Flight, String> company8;
	@FXML
	private TableColumn<Flight, String> delay8;
	@FXML
	private Label date9;
	@FXML
	private TableView<Flight> listTable9;
	@FXML
	private TableColumn<Flight, String> horaVuelo9;
	@FXML
	private TableColumn<Flight, String> numVuelo9;
	@FXML
	private TableColumn<Flight, String> airportColumn9;
	@FXML
	private TableColumn<Flight, String> statusVuelo9;
	@FXML
	private TableColumn<Flight, String> modelPlane9;
	@FXML
	private TableColumn<Flight, String> company9;
	@FXML
	private TableColumn<Flight, String> delay9;
	@FXML
	private Label date10;
	@FXML
	private TableView<Flight> listTable10;
	@FXML
	private TableColumn<Flight, String> horaVuelo10;
	@FXML
	private TableColumn<Flight, String> numVuelo10;
	@FXML
	private TableColumn<Flight, String> airportColumn10;
	@FXML
	private TableColumn<Flight, String> statusVuelo10;
	@FXML
	private TableColumn<Flight, String> modelPlane10;
	@FXML
	private TableColumn<Flight, String> company10;
	@FXML
	private TableColumn<Flight, String> delay10;
	@FXML
	private Label date11;
	@FXML
	private TableView<Flight> listTable11;
	@FXML
	private TableColumn<Flight, String> horaVuelo11;
	@FXML
	private TableColumn<Flight, String> numVuelo11;
	@FXML
	private TableColumn<Flight, String> airportColumn11;
	@FXML
	private TableColumn<Flight, String> statusVuelo11;
	@FXML
	private TableColumn<Flight, String> modelPlane11;
	@FXML
	private TableColumn<Flight, String> company11;
	@FXML
	private TableColumn<Flight, String> delay11;

	// TODO resto de cosas utilizables
	private boolean okValue = false;
	private Stage InfoStage;

	MainApp main = new MainApp();

	// Los mapa observables segun la fecha

	public void setInfoStage(Stage Stage) {
		this.InfoStage = Stage;
	}

	public boolean getOkValue() {
		return okValue;
	}

	public void SettingText(boolean value, Airport airport, int fechaInicio,
			int fechaFin) {

		if (value) {
			img = "salidas";
			airportType.setText("Salidas de ");
			airportColumn.setText("Destino");
		} else {
			img = "llegadas";
			airportType.setText("Llegadas a ");
			airportColumn.setText("Origen");

		}
		airportName.setText(airport.toString().toUpperCase());
		Image i = new Image(source + img + ".png");
		icon.setImage(i);

		Data valores = main.getInstance();

		int rangoFecha = fechaFin - fechaInicio;
		/*
		 * Elegimos fijar las tablas con un switch ya que si la creasemos fuera
		 * habría que reajustar todo el código por si los valores se encuentran
		 * al final del rango, así únicamente creamos la tabla cuando existe
		 * dicho rango
		 * 
		 * y para ajustar la altura del ScrollPane creamos cada fecha wrapeada
		 * con la superior en un VBox, así con setcontent ajustamos
		 * dinámicamente el scroll
		 */

		System.out.println(rangoFecha);
		// TODO Inicio del Switch
		switch (rangoFecha) {

		default:

			ObservableMap<String, Flight> datosVuelos = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos = FXCollections
					.observableArrayList(datosVuelos.values());

			setOtherAirportColumn(listaVuelos, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			scrollPane.setContent(fData);
			pasarGarbageCollector();
			break;

		case 1:
			// T0
			ObservableMap<String, Flight> datosVuelos10 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos10 = FXCollections
					.observableArrayList(datosVuelos10.values());

			setOtherAirportColumn(listaVuelos10, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);

			// T1
			ObservableMap<String, Flight> datosVuelos11 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos11 = FXCollections
					.observableArrayList(datosVuelos11.values());

			listTable1.setItems(listaVuelos11);

			setOtherAirportColumn(listaVuelos11, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			scrollPane.setContent(fData1);

			pasarGarbageCollector();
			break;
		case 2:

			// T0
			ObservableMap<String, Flight> datosVuelos20 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos20 = FXCollections
					.observableArrayList(datosVuelos20.values());

			setOtherAirportColumn(listaVuelos20, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);

			// T1
			ObservableMap<String, Flight> datosVuelos21 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos21 = FXCollections
					.observableArrayList(datosVuelos21.values());

			setOtherAirportColumn(listaVuelos21, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos22 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos22 = FXCollections
					.observableArrayList(datosVuelos22.values());

			setOtherAirportColumn(listaVuelos22, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			scrollPane.setContent(fData2);

			pasarGarbageCollector();
			break;
		case 3:
			// T0
			ObservableMap<String, Flight> datosVuelos30 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos30 = FXCollections
					.observableArrayList(datosVuelos30.values());

			setOtherAirportColumn(listaVuelos30, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos31 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos31 = FXCollections
					.observableArrayList(datosVuelos31.values());

			setOtherAirportColumn(listaVuelos31, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos32 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos32 = FXCollections
					.observableArrayList(datosVuelos32.values());

			setOtherAirportColumn(listaVuelos32, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos33 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos33 = FXCollections
					.observableArrayList(datosVuelos33.values());

			setOtherAirportColumn(listaVuelos33, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			scrollPane.setContent(fData3);
			pasarGarbageCollector();
			break;
		case 4:
			// T0
			ObservableMap<String, Flight> datosVuelos40 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos40 = FXCollections
					.observableArrayList(datosVuelos40.values());

			setOtherAirportColumn(listaVuelos40, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos41 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos41 = FXCollections
					.observableArrayList(datosVuelos41.values());

			setOtherAirportColumn(listaVuelos41, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos42 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos42 = FXCollections
					.observableArrayList(datosVuelos42.values());

			setOtherAirportColumn(listaVuelos42, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos43 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos43 = FXCollections
					.observableArrayList(datosVuelos43.values());

			setOtherAirportColumn(listaVuelos43, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos44 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos44 = FXCollections
					.observableArrayList(datosVuelos44.values());

			setOtherAirportColumn(listaVuelos44, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			scrollPane.setContent(fData4);
			pasarGarbageCollector();
			break;

		case 5:
			// T0
			ObservableMap<String, Flight> datosVuelos50 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos50 = FXCollections
					.observableArrayList(datosVuelos50.values());

			setOtherAirportColumn(listaVuelos50, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos51 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos51 = FXCollections
					.observableArrayList(datosVuelos51.values());

			setOtherAirportColumn(listaVuelos51, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos52 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos52 = FXCollections
					.observableArrayList(datosVuelos52.values());

			setOtherAirportColumn(listaVuelos52, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos53 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos53 = FXCollections
					.observableArrayList(datosVuelos53.values());

			setOtherAirportColumn(listaVuelos53, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos54 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos54 = FXCollections
					.observableArrayList(datosVuelos54.values());

			setOtherAirportColumn(listaVuelos54, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos55 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos55 = FXCollections
					.observableArrayList(datosVuelos55.values());

			setOtherAirportColumn(listaVuelos55, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			scrollPane.setContent(fData5);
			pasarGarbageCollector();
			break;
		case 6:
			// T0
			ObservableMap<String, Flight> datosVuelos60 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos60 = FXCollections
					.observableArrayList(datosVuelos60.values());

			setOtherAirportColumn(listaVuelos60, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos61 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos61 = FXCollections
					.observableArrayList(datosVuelos61.values());

			setOtherAirportColumn(listaVuelos61, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos62 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos62 = FXCollections
					.observableArrayList(datosVuelos62.values());

			setOtherAirportColumn(listaVuelos62, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos63 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos63 = FXCollections
					.observableArrayList(datosVuelos63.values());

			setOtherAirportColumn(listaVuelos63, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos64 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos64 = FXCollections
					.observableArrayList(datosVuelos64.values());

			setOtherAirportColumn(listaVuelos64, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos65 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos65 = FXCollections
					.observableArrayList(datosVuelos65.values());

			setOtherAirportColumn(listaVuelos65, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			// T6
			ObservableMap<String, Flight> datosVuelos66 = listaVuelos(valores,
					fechaInicio + 6, value, airport);

			setDateFlight(date6, fechaInicio + 6);

			ObservableList<Flight> listaVuelos66 = FXCollections
					.observableArrayList(datosVuelos66.values());

			setOtherAirportColumn(listaVuelos66, numVuelo6, horaVuelo6,
					statusVuelo6, delay6, company6, modelPlane6, listTable6);
			setAirportColumn(value, airportColumn6);

			scrollPane.setContent(fData6);
			pasarGarbageCollector();
			break;
		case 7:
			// T0
			ObservableMap<String, Flight> datosVuelos70 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos70 = FXCollections
					.observableArrayList(datosVuelos70.values());

			setOtherAirportColumn(listaVuelos70, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos71 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos71 = FXCollections
					.observableArrayList(datosVuelos71.values());

			setOtherAirportColumn(listaVuelos71, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos72 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos72 = FXCollections
					.observableArrayList(datosVuelos72.values());

			setOtherAirportColumn(listaVuelos72, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos73 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos73 = FXCollections
					.observableArrayList(datosVuelos73.values());

			setOtherAirportColumn(listaVuelos73, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos74 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos74 = FXCollections
					.observableArrayList(datosVuelos74.values());

			setOtherAirportColumn(listaVuelos74, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos75 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos75 = FXCollections
					.observableArrayList(datosVuelos75.values());

			setOtherAirportColumn(listaVuelos75, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			// T6
			ObservableMap<String, Flight> datosVuelos76 = listaVuelos(valores,
					fechaInicio + 6, value, airport);

			setDateFlight(date6, fechaInicio + 6);

			ObservableList<Flight> listaVuelos76 = FXCollections
					.observableArrayList(datosVuelos76.values());

			setOtherAirportColumn(listaVuelos76, numVuelo6, horaVuelo6,
					statusVuelo6, delay6, company6, modelPlane6, listTable6);
			setAirportColumn(value, airportColumn6);

			// T7
			ObservableMap<String, Flight> datosVuelos77 = listaVuelos(valores,
					fechaInicio + 7, value, airport);

			setDateFlight(date7, fechaInicio + 7);

			ObservableList<Flight> listaVuelos77 = FXCollections
					.observableArrayList(datosVuelos77.values());

			setOtherAirportColumn(listaVuelos77, numVuelo7, horaVuelo7,
					statusVuelo7, delay7, company7, modelPlane7, listTable7);
			setAirportColumn(value, airportColumn7);

			scrollPane.setContent(fData7);
			pasarGarbageCollector();
			break;
		case 8:
			// T0
			ObservableMap<String, Flight> datosVuelos80 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos80 = FXCollections
					.observableArrayList(datosVuelos80.values());

			setOtherAirportColumn(listaVuelos80, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos81 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos81 = FXCollections
					.observableArrayList(datosVuelos81.values());

			setOtherAirportColumn(listaVuelos81, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos82 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos82 = FXCollections
					.observableArrayList(datosVuelos82.values());

			setOtherAirportColumn(listaVuelos82, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos83 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos83 = FXCollections
					.observableArrayList(datosVuelos83.values());

			setOtherAirportColumn(listaVuelos83, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos84 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos84 = FXCollections
					.observableArrayList(datosVuelos84.values());

			setOtherAirportColumn(listaVuelos84, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos85 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos85 = FXCollections
					.observableArrayList(datosVuelos85.values());

			setOtherAirportColumn(listaVuelos85, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			// T6
			ObservableMap<String, Flight> datosVuelos86 = listaVuelos(valores,
					fechaInicio + 6, value, airport);

			setDateFlight(date6, fechaInicio + 6);

			ObservableList<Flight> listaVuelos86 = FXCollections
					.observableArrayList(datosVuelos86.values());

			setOtherAirportColumn(listaVuelos86, numVuelo6, horaVuelo6,
					statusVuelo6, delay6, company6, modelPlane6, listTable6);
			setAirportColumn(value, airportColumn6);

			// T7
			ObservableMap<String, Flight> datosVuelos87 = listaVuelos(valores,
					fechaInicio + 7, value, airport);

			setDateFlight(date7, fechaInicio + 7);

			ObservableList<Flight> listaVuelos87 = FXCollections
					.observableArrayList(datosVuelos87.values());

			setOtherAirportColumn(listaVuelos87, numVuelo7, horaVuelo7,
					statusVuelo7, delay7, company7, modelPlane7, listTable7);
			setAirportColumn(value, airportColumn7);

			// T8
			ObservableMap<String, Flight> datosVuelos88 = listaVuelos(valores,
					fechaInicio + 8, value, airport);

			setDateFlight(date8, fechaInicio + 8);

			ObservableList<Flight> listaVuelos88 = FXCollections
					.observableArrayList(datosVuelos88.values());

			setOtherAirportColumn(listaVuelos88, numVuelo8, horaVuelo8,
					statusVuelo8, delay8, company8, modelPlane8, listTable8);
			setAirportColumn(value, airportColumn8);

			scrollPane.setContent(fData8);
			pasarGarbageCollector();
			break;
		case 9:
			// T0
			ObservableMap<String, Flight> datosVuelos90 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos90 = FXCollections
					.observableArrayList(datosVuelos90.values());

			setOtherAirportColumn(listaVuelos90, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos91 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos91 = FXCollections
					.observableArrayList(datosVuelos91.values());

			setOtherAirportColumn(listaVuelos91, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos92 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos92 = FXCollections
					.observableArrayList(datosVuelos92.values());

			setOtherAirportColumn(listaVuelos92, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos93 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos93 = FXCollections
					.observableArrayList(datosVuelos93.values());

			setOtherAirportColumn(listaVuelos93, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos94 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos94 = FXCollections
					.observableArrayList(datosVuelos94.values());

			setOtherAirportColumn(listaVuelos94, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos95 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos95 = FXCollections
					.observableArrayList(datosVuelos95.values());

			setOtherAirportColumn(listaVuelos95, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			// T6
			ObservableMap<String, Flight> datosVuelos96 = listaVuelos(valores,
					fechaInicio + 6, value, airport);

			setDateFlight(date6, fechaInicio + 6);

			ObservableList<Flight> listaVuelos96 = FXCollections
					.observableArrayList(datosVuelos96.values());

			setOtherAirportColumn(listaVuelos96, numVuelo6, horaVuelo6,
					statusVuelo6, delay6, company6, modelPlane6, listTable6);
			setAirportColumn(value, airportColumn6);

			// T7
			ObservableMap<String, Flight> datosVuelos97 = listaVuelos(valores,
					fechaInicio + 7, value, airport);

			setDateFlight(date7, fechaInicio + 7);

			ObservableList<Flight> listaVuelos97 = FXCollections
					.observableArrayList(datosVuelos97.values());

			setOtherAirportColumn(listaVuelos97, numVuelo7, horaVuelo7,
					statusVuelo7, delay7, company7, modelPlane7, listTable7);
			setAirportColumn(value, airportColumn7);

			// T8
			ObservableMap<String, Flight> datosVuelos98 = listaVuelos(valores,
					fechaInicio + 8, value, airport);

			setDateFlight(date8, fechaInicio + 8);

			ObservableList<Flight> listaVuelos98 = FXCollections
					.observableArrayList(datosVuelos98.values());

			setOtherAirportColumn(listaVuelos98, numVuelo8, horaVuelo8,
					statusVuelo8, delay8, company8, modelPlane8, listTable8);
			setAirportColumn(value, airportColumn8);

			// T9
			ObservableMap<String, Flight> datosVuelos99 = listaVuelos(valores,
					fechaInicio + 9, value, airport);

			setDateFlight(date9, fechaInicio + 9);

			ObservableList<Flight> listaVuelos99 = FXCollections
					.observableArrayList(datosVuelos99.values());

			setOtherAirportColumn(listaVuelos99, numVuelo9, horaVuelo9,
					statusVuelo9, delay9, company9, modelPlane9, listTable9);
			setAirportColumn(value, airportColumn9);

			scrollPane.setContent(fData9);
			pasarGarbageCollector();
			break;
		case 10:
			// T0
			ObservableMap<String, Flight> datosVuelos100 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos100 = FXCollections
					.observableArrayList(datosVuelos100.values());

			setOtherAirportColumn(listaVuelos100, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);
			// T1
			ObservableMap<String, Flight> datosVuelos101 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos101 = FXCollections
					.observableArrayList(datosVuelos101.values());

			setOtherAirportColumn(listaVuelos101, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos102 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos102 = FXCollections
					.observableArrayList(datosVuelos102.values());

			setOtherAirportColumn(listaVuelos102, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos103 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos103 = FXCollections
					.observableArrayList(datosVuelos103.values());

			setOtherAirportColumn(listaVuelos103, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos104 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos104 = FXCollections
					.observableArrayList(datosVuelos104.values());

			setOtherAirportColumn(listaVuelos104, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos105 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos105 = FXCollections
					.observableArrayList(datosVuelos105.values());

			setOtherAirportColumn(listaVuelos105, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			// T6
			ObservableMap<String, Flight> datosVuelos106 = listaVuelos(valores,
					fechaInicio + 6, value, airport);

			setDateFlight(date6, fechaInicio + 6);

			ObservableList<Flight> listaVuelos106 = FXCollections
					.observableArrayList(datosVuelos106.values());

			setOtherAirportColumn(listaVuelos106, numVuelo6, horaVuelo6,
					statusVuelo6, delay6, company6, modelPlane6, listTable6);
			setAirportColumn(value, airportColumn6);

			// T7
			ObservableMap<String, Flight> datosVuelos107 = listaVuelos(valores,
					fechaInicio + 7, value, airport);

			setDateFlight(date7, fechaInicio + 7);

			ObservableList<Flight> listaVuelos107 = FXCollections
					.observableArrayList(datosVuelos107.values());

			setOtherAirportColumn(listaVuelos107, numVuelo7, horaVuelo7,
					statusVuelo7, delay7, company7, modelPlane7, listTable7);
			setAirportColumn(value, airportColumn7);

			// T8
			ObservableMap<String, Flight> datosVuelos108 = listaVuelos(valores,
					fechaInicio + 8, value, airport);

			setDateFlight(date8, fechaInicio + 8);

			ObservableList<Flight> listaVuelos108 = FXCollections
					.observableArrayList(datosVuelos108.values());

			setOtherAirportColumn(listaVuelos108, numVuelo8, horaVuelo8,
					statusVuelo8, delay8, company8, modelPlane8, listTable8);
			setAirportColumn(value, airportColumn8);

			// T9
			ObservableMap<String, Flight> datosVuelos109 = listaVuelos(valores,
					fechaInicio + 9, value, airport);

			setDateFlight(date9, fechaInicio + 9);

			ObservableList<Flight> listaVuelos109 = FXCollections
					.observableArrayList(datosVuelos109.values());

			setOtherAirportColumn(listaVuelos109, numVuelo9, horaVuelo9,
					statusVuelo9, delay9, company9, modelPlane9, listTable9);
			setAirportColumn(value, airportColumn9);

			// T10
			ObservableMap<String, Flight> datosVuelos1010 = listaVuelos(
					valores, fechaInicio + 10, value, airport);

			setDateFlight(date10, fechaInicio + 10);

			ObservableList<Flight> listaVuelos1010 = FXCollections
					.observableArrayList(datosVuelos1010.values());

			setOtherAirportColumn(listaVuelos1010, numVuelo10, horaVuelo10,
					statusVuelo10, delay10, company10, modelPlane10,
					listTable10);
			setAirportColumn(value, airportColumn10);

			scrollPane.setContent(fData10);
			pasarGarbageCollector();
			break;

		case 11:
			// T0
			ObservableMap<String, Flight> datosVuelos110 = listaVuelos(valores,
					fechaInicio, value, airport);

			setDateFlight(date, fechaInicio);

			ObservableList<Flight> listaVuelos110 = FXCollections
					.observableArrayList(datosVuelos110.values());

			setOtherAirportColumn(listaVuelos110, numVuelo, horaVuelo,
					statusVuelo, delay, company, modelPlane, listTable);
			setAirportColumn(value, airportColumn);

			// T1
			ObservableMap<String, Flight> datosVuelos111 = listaVuelos(valores,
					fechaInicio + 1, value, airport);
			setDateFlight(date1, fechaInicio + 1);

			ObservableList<Flight> listaVuelos111 = FXCollections
					.observableArrayList(datosVuelos111.values());

			setOtherAirportColumn(listaVuelos111, numVuelo1, horaVuelo1,
					statusVuelo1, delay1, company1, modelPlane1, listTable1);
			setAirportColumn(value, airportColumn1);

			// T2
			ObservableMap<String, Flight> datosVuelos112 = listaVuelos(valores,
					fechaInicio + 2, value, airport);

			setDateFlight(date2, fechaInicio + 2);

			ObservableList<Flight> listaVuelos112 = FXCollections
					.observableArrayList(datosVuelos112.values());

			setOtherAirportColumn(listaVuelos112, numVuelo2, horaVuelo2,
					statusVuelo2, delay2, company2, modelPlane2, listTable2);
			setAirportColumn(value, airportColumn2);

			// T3
			ObservableMap<String, Flight> datosVuelos113 = listaVuelos(valores,
					fechaInicio + 3, value, airport);

			setDateFlight(date3, fechaInicio + 3);

			ObservableList<Flight> listaVuelos113 = FXCollections
					.observableArrayList(datosVuelos113.values());

			setOtherAirportColumn(listaVuelos113, numVuelo3, horaVuelo3,
					statusVuelo3, delay3, company3, modelPlane3, listTable3);
			setAirportColumn(value, airportColumn3);

			// T4
			ObservableMap<String, Flight> datosVuelos114 = listaVuelos(valores,
					fechaInicio + 4, value, airport);

			setDateFlight(date4, fechaInicio + 4);

			ObservableList<Flight> listaVuelos114 = FXCollections
					.observableArrayList(datosVuelos114.values());

			setOtherAirportColumn(listaVuelos114, numVuelo4, horaVuelo4,
					statusVuelo4, delay4, company4, modelPlane4, listTable4);
			setAirportColumn(value, airportColumn4);

			// T5
			ObservableMap<String, Flight> datosVuelos115 = listaVuelos(valores,
					fechaInicio + 5, value, airport);

			setDateFlight(date5, fechaInicio + 5);

			ObservableList<Flight> listaVuelos115 = FXCollections
					.observableArrayList(datosVuelos115.values());

			setOtherAirportColumn(listaVuelos115, numVuelo5, horaVuelo5,
					statusVuelo5, delay5, company5, modelPlane5, listTable5);
			setAirportColumn(value, airportColumn5);

			// T6
			ObservableMap<String, Flight> datosVuelos116 = listaVuelos(valores,
					fechaInicio + 6, value, airport);

			setDateFlight(date6, fechaInicio + 6);

			ObservableList<Flight> listaVuelos116 = FXCollections
					.observableArrayList(datosVuelos116.values());

			setOtherAirportColumn(listaVuelos116, numVuelo6, horaVuelo6,
					statusVuelo6, delay6, company6, modelPlane6, listTable6);
			setAirportColumn(value, airportColumn6);

			// T7
			ObservableMap<String, Flight> datosVuelos117 = listaVuelos(valores,
					fechaFin - 4, value, airport);

			setDateFlight(date7, fechaFin - 4);

			ObservableList<Flight> listaVuelos117 = FXCollections
					.observableArrayList(datosVuelos117.values());

			setOtherAirportColumn(listaVuelos117, numVuelo7, horaVuelo7,
					statusVuelo7, delay7, company7, modelPlane7, listTable7);
			setAirportColumn(value, airportColumn7);

			// T8
			ObservableMap<String, Flight> datosVuelos118 = listaVuelos(valores,
					fechaFin - 3, value, airport);

			setDateFlight(date8, fechaFin - 3);

			ObservableList<Flight> listaVuelos118 = FXCollections
					.observableArrayList(datosVuelos118.values());

			setOtherAirportColumn(listaVuelos118, numVuelo8, horaVuelo8,
					statusVuelo8, delay8, company8, modelPlane8, listTable8);
			setAirportColumn(value, airportColumn8);

			// T9
			ObservableMap<String, Flight> datosVuelos119 = listaVuelos(valores,
					fechaFin - 2, value, airport);

			setDateFlight(date9, fechaFin - 2);

			ObservableList<Flight> listaVuelos119 = FXCollections
					.observableArrayList(datosVuelos119.values());

			setOtherAirportColumn(listaVuelos119, numVuelo9, horaVuelo9,
					statusVuelo9, delay9, company9, modelPlane9, listTable9);
			setAirportColumn(value, airportColumn9);

			// T10
			ObservableMap<String, Flight> datosVuelos1110 = listaVuelos(
					valores, fechaFin - 1, value, airport);

			setDateFlight(date10, fechaFin - 1);

			ObservableList<Flight> listaVuelos1110 = FXCollections
					.observableArrayList(datosVuelos1110.values());

			setOtherAirportColumn(listaVuelos1110, numVuelo10, horaVuelo10,
					statusVuelo10, delay10, company10, modelPlane10,
					listTable10);
			setAirportColumn(value, airportColumn10);

			// T11
			ObservableMap<String, Flight> datosVuelos1111 = listaVuelos(
					valores, fechaFin, value, airport);

			setDateFlight(date11, fechaFin);

			ObservableList<Flight> listaVuelos1111 = FXCollections
					.observableArrayList(datosVuelos1111.values());

			setOtherAirportColumn(listaVuelos1111, numVuelo11, horaVuelo11,
					statusVuelo11, delay11, company11, modelPlane11,
					listTable11);
			setAirportColumn(value, airportColumn11);

			scrollPane.setContent(fData11);

			pasarGarbageCollector();
			break;

		}

	}

	// TODO resto de métodos
	@FXML
	public void initialize() {

		scrollPane.setContent(null);
		pasarGarbageCollector();

	}

	@FXML
	private void okButton() {
		okValue = true;
		boolean conec = isConnected("www.ascodevida.com");
		System.out.println(conec);
		pasarGarbageCollector();
		InfoStage.close();
	}

	private void setDateFlight(Label date, int rangoFecha) {
		Locale.setDefault(new Locale("es", "ES"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("EEEE");
		// Si cogemos dia por ".getDayofWeek() nos devuelve el nombre en
		// Guirish

		LocalDate dateFlight = LocalDate.of(2015, 3, rangoFecha);
		// Ponemos el día siendo la primera letra mayúscula, y luego la
		// fecha en formato (dia/mes/año)
		date.setText(dateFlight.format(formatterDay).substring(0, 1)
				.toUpperCase()
				+ dateFlight.format(formatterDay).substring(1)
				+ ", "
				+ dateFlight.format(formatter));
	}

	// Metodo para dependiendo si es true or false cambiar el origen o destino
	// en la columna airport
	private void setAirportColumn(boolean valor,
			TableColumn<Flight, String> columnaAeropuerto) {
		if (valor == false) {
			columnaAeropuerto
					.setCellValueFactory(e -> new SimpleStringProperty(e
							.getValue().getOrigin()));
			columnaAeropuerto.setText("Origen");

		} else {
			columnaAeropuerto
					.setCellValueFactory(e -> new SimpleStringProperty(e
							.getValue().getDestiny()));
			columnaAeropuerto.setText("Destino");

		}
		centerContent(columnaAeropuerto);
	}

	private void setOtherAirportColumn(ObservableList<Flight> listaVuelos,
			TableColumn<Flight, String> numVuelo,
			TableColumn<Flight, String> horaVuelo,
			TableColumn<Flight, String> statusVuelo,
			TableColumn<Flight, String> delay,
			TableColumn<Flight, String> company,
			TableColumn<Flight, String> modelPlane, TableView<Flight> listTable) {

		pasarGarbageCollector();

		// Fijamos los valores
		listTable.setItems(listaVuelos);

		// ajustamos el tamaño de la lista al contenido
		listTable.setFixedCellSize(25);
		listTable.prefHeightProperty().bind(
				listTable.fixedCellSizeProperty().multiply(
						Bindings.size(listTable.getItems()).add(1.01)));
		listTable.minHeightProperty().bind(listTable.prefHeightProperty());
		listTable.maxHeightProperty().bind(listTable.prefHeightProperty());

		// Numero de vuelo
		numVuelo.setCellValueFactory(e -> new SimpleStringProperty(e.getValue()
				.getFlightNumber()));
		centerContent(numVuelo);

		// Hora de Vuelo
		horaVuelo.setCellValueFactory(e -> new SimpleStringProperty(e
				.getValue().getDateTime().toLocalTime().toString()));
		centerContent(horaVuelo);

		// Status de Vuelo
		statusVuelo.setCellValueFactory(e -> new SimpleStringProperty(e
				.getValue().getFlightStatus().statusProperty().getValue()
				.toString()));
		centerContent(statusVuelo);

		// modelo del vuelo
		modelPlane.setCellValueFactory(e -> new SimpleStringProperty(e
				.getValue().getPlane()));
		centerContent(modelPlane);

		// retraso
		delay.setCellValueFactory(e -> new SimpleStringProperty(String
				.valueOf(e.getValue().getDelay())));
		centerContent(delay);

		// dibujamos el valor en función de rango Rojo (negativo) Verde (>0)
		renderDelay(delay);

		// compañía
		company.setCellValueFactory(e -> new SimpleStringProperty(e.getValue()
				.getCompany()));
		centerContent(company);
		// Si está vacía mostramos mensaje
		tableNull(listTable);
		pasarGarbageCollector();

	}

	private void tableNull(TableView<Flight> listTable) {
		if (listTable.getItems().isEmpty()) {
			listTable.setPlaceholder(new Label(
					"No existen vuelos registrados este día"));
			// Modificamos tamaño para que no se monte sobre la cabecera
			listTable.setFixedCellSize(50);
			listTable.prefHeightProperty().bind(
					listTable.fixedCellSizeProperty().multiply(
							Bindings.size(listTable.getItems()).add(1.01)));
			listTable.minHeightProperty().bind(listTable.prefHeightProperty());
			listTable.maxHeightProperty().bind(listTable.prefHeightProperty());

		}

	}

	private ObservableMap<String, Flight> listaVuelos(Data valores,
			int rangoFecha, boolean value, Airport airport) {

		ObservableMap<String, Flight> datosVuelos = FXCollections
				.emptyObservableMap();
		// miramos la transparencia para saber si es Salida o Llegada
		if (value) {// Si es transparente está
					// activo Salidas
			datosVuelos = valores
					.getAirportFlights(airport,
							LocalDate.of(2015, 3, rangoFecha)).getDepartures()
					.getFlights();

		} else {// Sino es Llegadas

			datosVuelos = valores
					.getAirportFlights(airport,
							LocalDate.of(2015, 3, rangoFecha)).getArrivals()
					.getFlights();
		}
		return datosVuelos;

	}

	private boolean isConnected(String dirUrl) {
		Socket socket = null;
		boolean connected = false;

		try {
			socket = new Socket(dirUrl, 80);
			connected = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
				}
		}
		return connected;
	}

	private void renderDelay(TableColumn<Flight, String> delay) {

		delay.setCellFactory(column -> {
			return new TableCell<Flight, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						setAlignment(Pos.CENTER);
						// Si el elemento es Menor que 0
						//Ya que es bueno que sea menor
						if (item.startsWith("-")) {
							setText(item.replace("-",""));
							setTextFill(Color.GREEN);
						} else if (!item.equals("0.0")) {
							setTextFill(Color.RED);
						}
					}
				}
			};
		});

	}

	// Método para centrar el contenido de las celdas de una columna
	private void centerContent(TableColumn<Flight, String> column) {
		column.setCellFactory(new Callback<TableColumn<Flight, String>, TableCell<Flight, String>>() {
			@Override
			public TableCell<Flight, String> call(TableColumn<Flight, String> p) {
				TableCell<Flight, String> tc = new TableCell<Flight, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {
							setText(item);
						}
					}
				};
				tc.setAlignment(Pos.CENTER);
				return tc;
			}
		});
	}

	public void pasarGarbageCollector() {

		Runtime garbage = Runtime.getRuntime();
		// System.out.println("Memoria libre antes de limpieza:"+garbage.freeMemory());

		garbage.gc();

		// System.out.println("Memoria libre después de limpieza:"+garbage.freeMemory());
	}
}
