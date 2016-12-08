package es.upv.com.aenaApp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import application.data.Data;
import application.model.AENA_Airport;
import application.model.Airport;
import application.model.AirportFlights;
import es.upv.com.aenaApp.MainApp;

public class StaticsSceneController {

	private String mainScene = "mainScene.fxml";

	Dialogs framework = new Dialogs();

	MainApp main = new MainApp();

	// Botones
	@FXML
	private Button btnMultiply;
	@FXML
	private Button btnDelay;
	@FXML
	private Button btSingle;
	@FXML
	private Button btnOk;

	// Tabla
	@FXML
	private TableColumn<AirportFlights, String> airportColumn;

	@FXML
	private TableView<AirportFlights> airportTable;
	@FXML
	private TableColumn<AirportFlights, String> interFligths;

	@FXML
	private TableColumn<AirportFlights, String> totalFlights;

	@FXML
	private TableColumn<AirportFlights, String> localFlights;

	@FXML
	private TableColumn<AirportFlights, String> averageColumn;

	@FXML
	private TableColumn<AirportFlights, String> delayColumn;
	// Otras cosas
	@FXML
	private HBox editAirportsBox;
	@FXML
	private PieChart pieChartFlights;
	@FXML
	private TextField numAirportsField;
	@FXML
	private BarChart<String, Double> barChartDelay;

	private double range = 12.0;

	private Tooltip tooltip;
	private PieChart.Data selectedData;

	private AirportFlights airport;

	protected Data datosVuelos = main.getInstance();

	ObservableList<AirportFlights> listAirport = FXCollections
			.observableArrayList();
	List<Airport> valores;

	@FXML
	private LineChart<String, Integer> numFlightsUnique;

	public StaticsSceneController() {

		Data datosVuelos = main.getInstance();

		// Creamos una lista de aeropuertos
		valores = datosVuelos.getAirportList();
		// Dado que no existe los datos bien wrapeados del Aeropuerto de la
		// Gomera, lo eliminamos de la lista
		// Fallo del creador del AENA_FlightsData
		// PD: Gracias majo.
		valores.remove(33);

		for (int i = 0; i < valores.size(); i++) {
			// A partir d la lista aeropuertos añaimos los vuelos
			listAirport.add(datosVuelos.getAirportFlights(valores.get(i)));
		}

		airport = listAirport.get(5);

	}

	@FXML
	void btnVolver(ActionEvent event) throws IOException {

		framework.loadScene(mainScene, event);

	}

	/*
	 * public void setStaticsStage(Stage Stage) { this.Statics = Stage; }
	 */

	// TODO Initialize
	@FXML
	public void initialize() {
		
		//Inicializamso el boton seleccionado y trasnparente
		btSingle.setId("botonesST_selected");
		btSingle.setMouseTransparent(true);
		// Ordenamos aeropuertos por código de Aeropuerto
		if (listAirport.size() >= 0) {
			Collections.sort(listAirport, new Comparator<AirportFlights>() {

				@Override
				public int compare(final AirportFlights object1,
						final AirportFlights object2) {
					return object1.getAirport().getCode()
							.compareTo(object2.getAirport().getCode());
				}
			});
		}
		// Cargamos un valor inicial
		numFlightsUnique.setTitle("Número de Vuelos para "
				+ airport.getAirport().toString());
		drawnXY(airport);

		setItemsTable(listAirport, airportTable, airportColumn, totalFlights,
				localFlights, interFligths, averageColumn, delayColumn);

		airportTable.setOnMouseClicked((event) -> {
			numFlightsUnique.getData().clear();// Así eliminamos el contenido
			// previo y conseguimos que sólo
			// muestre un aeropuerto
			airport = airportTable.getSelectionModel().getSelectedItem();
			numFlightsUnique.setTitle("Número de Vuelos para "
					+ airport.getAirport().toString());
			drawnXY(airport);
		});

		editAirportsBox.setVisible(false);

		// Bloqueamos la introducción de letras en los TextFields numéricos
		numAirportsField.addEventFilter(KeyEvent.KEY_TYPED,
				new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent inputevent) {
				if (!inputevent.getCharacter().matches("\\d")) {
					inputevent.consume();
				}
			}
		});
		// Simplemente está puesto porque si se hace desde el botón únicamente
		// salía una vez el codigo Aeropuerto,por muy grande que fuese
		// el numero, así que metemos un número para ocupar menos memoria
		drawnBar(1);
		drawnpieChart(1);
	}

	private double round(double numero, int decimales) {
		return Math.round(numero * Math.pow(10, decimales))
				/ Math.pow(10, decimales);
	}

	// Métodos replicados de otras clases

	// Nota: no se pudo instanciar del Controller infoflights,
	// ya que los parametros de las tablas son distintos (cambiando visibilidad
	// a protected)

	// Método para centrar el contenido de las celdas de una columna

	private void centerContent(TableColumn<AirportFlights, String> column) {
		column.setCellFactory(new Callback<TableColumn<AirportFlights, String>, TableCell<AirportFlights, String>>() {
			@Override
			public TableCell<AirportFlights, String> call(
					TableColumn<AirportFlights, String> p) {
				TableCell<AirportFlights, String> tc = new TableCell<AirportFlights, String>() {
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

	// remarcar si el rango es positivo o negativo
	private void renderDelay(TableColumn<AirportFlights, String> delay) {

		delay.setCellFactory(column -> {
			return new TableCell<AirportFlights, String>() {
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
						if (item.startsWith("-")) {
							setTextFill(Color.RED);
						} else if (!item.equals("0.0")) {
							setTextFill(Color.GREEN);
						}
					}
				}
			};
		});

	}

	// TODO Series XYChart
	@SuppressWarnings("unchecked")
	private void drawnXY(AirportFlights airportSingle) {

		XYChart.Series<String, Integer> totalFlights = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> localFlights = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> interFlights = new XYChart.Series<String, Integer>();

		Airport ae = recuperaAirport((AENA_Airport) airportSingle.getAirport());

		int fechaFin = airport.getTo().getDayOfMonth();
		int numVuelos, local, inter;

		for (int fechaInicio = airport.getFrom().getDayOfMonth(); fechaInicio <= fechaFin; fechaInicio++) {
			numVuelos = datosVuelos.getAirportFlights(ae,
					LocalDate.of(2015, 3, fechaInicio)).getNumFlights();
			local = datosVuelos.getAirportFlights(ae,
					LocalDate.of(2015, 3, fechaInicio)).getNumNationalFlights();
			inter = datosVuelos.getAirportFlights(ae,
					LocalDate.of(2015, 3, fechaInicio))
					.getNumInternationalFlights();

			totalFlights.getData().add(
					new XYChart.Data<String, Integer>(fechaInicio + "-03-2015",
							numVuelos));
			localFlights.getData().add(
					new XYChart.Data<String, Integer>(fechaInicio + "-03-2015",
							local));
			interFlights.getData().add(
					new XYChart.Data<String, Integer>(fechaInicio + "-03-2015",
							inter));

		}

		interFlights.setName("Internacionales");
		localFlights.setName("Nacionales");
		totalFlights.setName("Totales");
		numFlightsUnique.getData().addAll(interFlights, localFlights,
				totalFlights);
		
		//TODO tooltip
		for (final XYChart.Series<String, Integer> puntos : this.numFlightsUnique.getData()) {
			for (final XYChart.Data<String, Integer> data : puntos.getData()) {
				Node node = data.getNode();
		
				Tooltip.install(node, new Tooltip(
												"Para el día " + (String)data.getXValue() + 
												"\n" + "Vuelos " + puntos.getName() + " :" + data.getYValue()
						));

				node.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						node.setEffect(new Glow());
						String styleString = "-fx-border-color: white; -fx-border-width: 3; -fx-border-style: dashed;";
						node.getStyleClass().add("onHover");
						node.setStyle(styleString);
					}
				});



				node.setOnMouseExited(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						node.setStyle(null);
						node.setEffect(null);

						node.getStyleClass().remove("onHover");
					}
				});
			}
		}
		
		
	}

	// TODO opcion seleccion tablas
	@FXML
	void setLineChart(ActionEvent event) {
		btSingle.setId("botonesST_selected");
	    btnDelay.setId("botonesST");
	    btnMultiply.setId("botonesST");
		btSingle.setMouseTransparent(true);
		numFlightsUnique.setVisible(true);
		barChartDelay.setVisible(false);
		btnDelay.setMouseTransparent(false);
		pieChartFlights.setVisible(false);
		btnMultiply.setMouseTransparent(false);

		editAirportsBox.setVisible(false);

		// Ordenamos aeropuertos por código de Aeropuerto
		if (listAirport.size() >= 0) {
			Collections.sort(listAirport, new Comparator<AirportFlights>() {
				@Override
				public int compare(final AirportFlights object1,
						final AirportFlights object2) {
					return object1.getAirport().getCode()
							.compareTo(object2.getAirport().getCode());
				}
			});
		}

	}

	// TODO Series BarChart
	private void drawnBar(int numAirports) {

		// Elimnamos valores anteriores
		barChartDelay.getData().clear();

		for (int total = numAirports - 1; total >= 0; total--) {
			Airport ae = airportTable.getItems().get(total).getAirport();
			AirportFlights valoresAF = airportTable.getItems().get(total);
			XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
			series.setName(airportTable.getItems().get(total).getAirport()
					.getName().toString());

			series.getData().add(
					new XYChart.Data<String, Double>(ae.getCode().toString(),
							valoresAF.getDelay()));
			barChartDelay.getData().add(series);
			barChartDelay.setBarGap(0);
			barChartDelay.setCategoryGap(0);
			barChartDelay.setTitle(setArticle(numAirports)
					+ "con mayor retraso medio");
			//TODO tooltip
			for (final XYChart.Series<String, Double> barras : this.barChartDelay.getData()) {
				for (final XYChart.Data<String, Double> data : barras.getData()) {
					Node node = data.getNode();
					final Airport airport = this.recuperaAirport((AENA_Airport)AENA_Airport.getAirportByCode((String)data.getXValue()));
					final AirportFlights airportflight = this.datosVuelos.getAirportFlights(airport);
					Tooltip.install(node, new Tooltip(airportflight.getAirport() + "\n" + 
							"Retraso Medio: " + this.round((double)data.getYValue(), 4)
							));

					node.setOnMouseEntered(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent arg0) {
							node.setEffect(new Glow());
							String styleString = "-fx-border-color: white; -fx-border-width: 3; -fx-border-style: dashed;";
							node.getStyleClass().add("onHover");
							node.setStyle(styleString);
						}
					});



					node.setOnMouseExited(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							node.setStyle(null);
							node.setEffect(null);

							node.getStyleClass().remove("onHover");
						}
					});
				}
			}


			// System.out.println(airportTable.getItems().get(total).getAirport());
		}

	}

	@FXML
	void setBarChart(ActionEvent event) {
		btSingle.setId("botonesST");
	    btnDelay.setId("botonesST_selected");
	    btnMultiply.setId("botonesST");
		btnDelay.setMouseTransparent(true);
		barChartDelay.setVisible(true);
		numFlightsUnique.setVisible(false);
		btSingle.setMouseTransparent(false);
		pieChartFlights.setVisible(false);
		btnMultiply.setMouseTransparent(false);

		if (!editAirportsBox.isVisible())
			editAirportsBox.setVisible(true);
		// Así ordenamos Vuelos por retraso
		if (listAirport.size() >= 0) {
			Collections.sort(listAirport, new Comparator<AirportFlights>() {
				@Override
				public int compare(final AirportFlights object1,
						final AirportFlights object2) {
					// return
					// String.valueOf(object1.getDelay()).compareTo(String.valueOf(object2.getDelay()));
					// No ya que el orden era por String y podías pasar de 10 a
					// 100
					return Double.compare(object1.getDelay(),
							object2.getDelay());
				}
			});
		}// Poner aquí ya que interesa que sea después de ordenar el código
		drawnBar(6);
	}

	@FXML
	void setPieChart(ActionEvent event) {
		btSingle.setId("botonesST");
	    btnDelay.setId("botonesST");
	    btnMultiply.setId("botonesST_selected");
		pieChartFlights.setVisible(true);
		btnMultiply.setMouseTransparent(true);
		numFlightsUnique.setVisible(false);
		btSingle.setMouseTransparent(false);
		barChartDelay.setVisible(false);
		btnDelay.setMouseTransparent(false);

		if (!editAirportsBox.isVisible())
			editAirportsBox.setVisible(true);
		// Así ordenamos vuelos por numero total de vuelos
		if (listAirport.size() >= 0) {
			Collections.sort(listAirport, new Comparator<AirportFlights>() {
				@Override
				public int compare(final AirportFlights object1,
						final AirportFlights object2) {
					// return Integer.compare(object1.getNumFlights(),
					// object2.getNumFlights()); no ya que el orden es creciente
					return Integer.compare(object2.getNumFlights(),
							object1.getNumFlights());
				}
			});
		}
		drawnpieChart(6);

	}

	// TODO Series pieChartChart
	private void drawnpieChart(int numAirports) {

		// Elimnamos valores anteriores
		pieChartFlights.getData().clear();

		ObservableList<PieChart.Data> datosTarta = FXCollections
				.observableArrayList();
		tooltip = new Tooltip("");
		airportTable.getItems().subList(0, numAirports - 1);
		for (int total = numAirports - 1; total >= 0; total--) {

			Airport ae = airportTable.getItems().get(total).getAirport();
			AirportFlights valoresAF = airportTable.getItems().get(total);
			// (new PieChart.Data(ae.getCode()
			// .toString(), valoresAF.getNumFlights()));
			datosTarta.add(new PieChart.Data(ae.getCode().toString(), valoresAF
					.getNumFlights()));
			for (final PieChart.Data data : pieChartFlights.getData()) {
				Tooltip.install(data.getNode(),tooltip);
				applyMouseEvents(data,valoresAF);
			}
			pieChartFlights.setData(datosTarta);
		}


		pieChartFlights.setTitle(setArticle(numAirports) + "con más Vuelos");

	}

	// TODO setValue
	@FXML
	void setValue(ActionEvent event) {

		boolean piechart = pieChartFlights.isVisible();

		int numAirports = Integer.parseInt(numAirportsField.getText());

		if ((numAirports <= 0 || numAirports > listAirport.size())) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Atención");
			alert.setHeaderText("El número de Aeropuertos no es correcto");
			alert.initStyle(StageStyle.UTILITY);
			alert.setContentText("Por favor, el rango válido de Aeropuertos es de 1 a "
					+ listAirport.size());

			alert.showAndWait();

		} else {
			if (!piechart) {
				System.out.println("Dibuja barras");
				drawnBar(numAirports);
			} else {
				System.out.println("Dibuja tarta");
				drawnpieChart(numAirports);
			}
		}
		numAirportsField.clear();
	}

	// /Realmente no es necesaria hacerla en un método aparte, pero por
	// comodidad de código
	protected void setItemsTable(ObservableList<AirportFlights> listAirport,
			TableView<AirportFlights> airportTable,
			TableColumn<AirportFlights, String> airportColumn,
			TableColumn<AirportFlights, String> totalFlights,
			TableColumn<AirportFlights, String> localFlights,
			TableColumn<AirportFlights, String> interFligths,
			TableColumn<AirportFlights, String> averageColumn,
			TableColumn<AirportFlights, String> delayColumn) {
		airportTable.setItems(listAirport);
		airportColumn.setCellValueFactory(e -> new SimpleStringProperty(e
				.getValue().getAirport().toString()));
		totalFlights.setCellValueFactory(e -> new SimpleStringProperty(String
				.valueOf(e.getValue().getNumFlights())));
		centerContent(totalFlights);

		localFlights.setCellValueFactory(e -> new SimpleStringProperty(String
				.valueOf(e.getValue().getNumNationalFlights())));
		centerContent(localFlights);

		interFligths.setCellValueFactory(e -> new SimpleStringProperty(String
				.valueOf(e.getValue().getNumInternationalFlights())));
		centerContent(interFligths);
		averageColumn.setCellValueFactory(e -> new SimpleStringProperty(String
				.valueOf(round(e.getValue().getNumFlights() / range, 2))));
		centerContent(averageColumn);

		delayColumn.setCellValueFactory(e -> new SimpleStringProperty(String
				.valueOf(round(e.getValue().getDelay(), 2))));
		renderDelay(delayColumn);
	}

	private Airport recuperaAirport(AENA_Airport ae) {

		for (Airport a : datosVuelos.getAirportList()) {

			if (ae.getCode().compareTo(a.getCode()) == 0)
				return a;
		}
		return null;
	}

	// TODO para is pulsamos intro
	//Si está vacio ponemos 0 para que salga el alert
	@FXML
	void enterPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			try {
				setValue(null);
			} catch (Exception e) {
				numAirportsField.setText(String.valueOf(0));
				setValue(null);

			}
		}
	}

	@FXML
	void enterPressed2(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			try {
				setValue(null);
			} catch (Exception e) {
				numAirportsField.setText(String.valueOf(0));
				setValue(null);
			}
		}
	}

	private String setArticle(int numAirports) {
		if (numAirports == 1)
			return "El Aeropuerto ";
		else {
			String res = "Los " + numAirports + " Aeropuertos ";
			return res;
		}

	}
	//MouseEvents for PieCharTooltip
	private void applyMouseEvents(final PieChart.Data data, AirportFlights airportData) {

		final Node node = data.getNode();

		node.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				node.setEffect(new Glow());
				String styleString = "-fx-border-color: white; -fx-border-width: 3; -fx-border-style: dashed;";
				node.setStyle(styleString);
				Airport ae=recuperaAirport((AENA_Airport)AENA_Airport.getAirportByCode(data.getName()));
				AirportFlights af=datosVuelos.getAirportFlights(ae);
				tooltip.setText(String.valueOf(ae.getData().getName()+ "\nTotal Vuelos: " + af.getNumFlights() + 
						"\nVuelos Nacionales: " + af.getNumNationalFlights() + 
						"\nVuelos Intenacionales: " + af.getNumInternationalFlights()) );
			}

		});

		node.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				node.setEffect(null);
				node.setStyle("");
			}
		});

		node.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				selectedData = data;
				System.out.println("Selected data " + airportData.getAirport().toString());
			}
		});
	}
}
