package GUI;
// Done: Exportieren der erzeugten Liste in eine .txt --> Men�reiter Exportieren in Datei

// Statistik erstellen
// Unter Hilfe Kurzinfo zum Projekt - 10 Seiten Dokumentation als modales Fenster
// Bei Datei w�hlen pr�fen, ob die Dateiendung eine TXT-Datei ist, ansonsten Fehlermeldung -->Fertig
// DESIGN �NDERN


// Christoph: Pfad, Balloontipps, Zweiter Button - Zur�cksetzen -- ALLES FERTIG!
// zus�tzlich: nur XML Files ausw�hlbar + zweites Textfeld + Button
// Fehlend: Actionhandler fehlt noch bei "Text auf Fremdw�rter pr�fen"!!

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.lucene.store.Directory;

import DokumenteSucheforGUI.Search;
import javafx.stage.Modality;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Modality;



public class Gui extends Application {
	
	private Stage stage;
	private String filePath;

	TextField textField0 = new TextField(); // Wird in der Subklasse des FileChoosers ben�tigt an dieser Stelle

	TextArea textArea0 = new TextArea();
	
	
	// Fenster hei�t in JavaFX "stage" (Fenster)
	public void start(final Stage stage) throws Exception {

		stage.setTitle("Die Heuristiker - Anglizismenfinder");
		stage.centerOnScreen(); // Fenster wird mittig auf dem Bildschirm platziert
		stage.setHeight(500);
		stage.setWidth(600);
		stage.setResizable(false); // Nicht ver�nderbar
		stage.getIcons().add(new Image("file:Unbenannt.png"));

		final Pane pane0 = new Pane(); // Ordnungspanele auf dem Objekte gelegt werden k�nnen(auf dem der Inhalt liegt). Man kann so viele Panes zuordnen wie man lustig ist

		MenuBar menueLeiste = new MenuBar();
		menueLeiste.prefWidthProperty().bind(stage.widthProperty()); // Passt die Men�leiste auf die Breite des Fensters an

		Menu datei = new Menu("Datei");
		Menu hilfe = new Menu ("Hilfe");

		// Menu-Iems f�r DATEI
		MenuItem schliessen = new MenuItem("Beenden");
		MenuItem waehlen = new MenuItem("W�hlen");
		MenuItem exportieren = new MenuItem("Exportieren");
		MenuItem statistik = new MenuItem("Statistik anzeigen");

		// Menu-Items f�r Hilfe
		MenuItem fragezeichen = new MenuItem("�ber den Anglizismenfinder");

		fragezeichen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                   // Klasse Modaler Dialog
                   ModalerDialog mDialog0 = new ModalerDialog();

                   mDialog0.showAndWait(); // BLOCKIERT

                   System.out.println("Button schlie�en im modalen Dialog gedr�ckt.");



                   //                         String dokumentationsText = "LEERTEXT\nLEERTEXT \n Dokumentation �ber den Anglizismenfinder.\nLEERTEXT\nLEERTEXT";
                   //
                   //                         Alert alarm = new Alert(AlertType.INFORMATION);
                   //                         alarm.setTitle("");
                   //                         alarm.setHeaderText("�ber den Anglizismenfinder");
                   //                         alarm.setContentText("  Die Heuristiker\n- Gensler, Alexander\n- Kammerer, Christoph\n- Memmel, Stefan\n- Roth, Wolfgang\n\nLEERTEXT\nLEERTEXT \n Dokumentation �ber den Anglizismenfinder.\nLEERTEXT\nLEERTEXT");
                   //                         alarm.showAndWait();
                   //
                   //                         TextArea textArea2 = new TextArea();


            }

     });

		datei.getItems().add(waehlen);
		datei.getItems().add(exportieren);
		datei.getItems().add(statistik);
		datei.getItems().add(schliessen);

		hilfe.getItems().add(fragezeichen);

		menueLeiste.getMenus().addAll(datei, hilfe);

		schliessen.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});
		
		

		waehlen.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent ae) {

				System.out.println("W�hle-Men� ausgel�st - Return-Code (0)");

				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Bitte XML-Datei w�hlen");
				fileChooser.showOpenDialog(stage);
				textField0.setText(fileChooser.getInitialFileName());

			}				
		});
		
		Label label0 = new Label();

		label0.setText("Betaversion");
		label0.setLayoutX(510); // Je h�her der Wert, umso weiter rechts liegt das Label
		label0.setLayoutY(100); // 0 ist ganz oben

		final TextField textField0 = new TextField();

		final TextField textField1 = new TextField();

		final TextArea textArea0 = new TextArea();

		textArea0.setScrollTop(Double.MAX_VALUE);
		textArea0.setLayoutX(10);
		textArea0.setLayoutY(150);
	
		textArea0.setEditable(false);
		textArea0.setWrapText(true); // Automatischer Zeilenumbruch 
		textArea0.setPrefSize(574, 350);
		textArea0.setText("Zeile: 15 --> (...) innerhalb eines Meetings werden neue Ziele vereinb (...) -->  Sitzung, Besprechung         \n");

		//TextFeld in dem der Dateipfad der XML-Source-Datei eingetragen wird
		textField0.setLayoutX(10); 
		textField0.setLayoutY(50);
		textField0.setEditable(false);
		textField0.setPrefWidth(480); // Breite des Textfeldes 
		textField0.setText("Bitte XML-Datei ausw�hlen!");
		textField0.setMaxWidth(350);
		
		//zentriert Text im Textfeld
		textField0.setAlignment(Pos.CENTER);
		
		//Textfeld zur manuellen Eingabe eines zu pr�fenden Textes auf Fremdw�rter
		textField1.setLayoutX(10); 
		textField1.setLayoutY(100);
		textField1.setEditable(true);
		textField1.setPrefWidth(480); // Breite des Textfeldes 
		textField1.setText("Hier zu pr�fenden Text eingeben!");
		textField1.setMaxWidth(350);
		textField1.setAlignment(Pos.CENTER);
		
		final Button button0 = new Button("W�hlen");
		Button button1 = new Button("Zur�cksetzen");
		final Button button2 = new Button("Sfuche Starten");

		button0.setLayoutX(380);
		button0.setLayoutY(47);
		button0.setPrefSize(80,30);
		
		button1.setLayoutX(480);
		button1.setLayoutY(47);
		button1.setPrefSize(100,30);
		
		button2.setLayoutX(380);
		button2.setLayoutY(97);
		button2.setPrefSize(200,30);
		
		String tooltiptext= "Bitte w�hlen Sie die XML Datei aus!";
		Tooltip tooltip = new Tooltip();
		tooltip.setText(tooltiptext);
		button0.setTooltip(tooltip);
		
		// Actionh�ndler zum w�hlen von XML Source-Datei
		button0.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent ae) {

				System.out.println("W�hlen-Button ausgel�st - Return-Code (0)");

				DirectoryChooser dChooser = new DirectoryChooser();
				//dChooser.setTitle("Open Resource File (only XML)");
				
				//nur XML Dateien erlaubt bei der Eingabe
				//FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
			//	fileChooser.getExtensionFilters().add(extFilter);

			//	File file = fileChooser.showOpenDialog(pane0.getScene().getWindow());
			    File dir = dChooser.showDialog(pane0.getScene().getWindow());
			    
				
				//kompletten Pfadnamen der ausgew�hlten Datei in Textfeld anzeigen
			    filePath = dir.getAbsolutePath();
				if (dir != null) {
 
						textField0.setText(filePath);
						textField0.setAlignment(Pos.BASELINE_LEFT);
						}		
				System.out.println(filePath);
			}				
		});
		
        
        // Actionhandler f�r Button "Zur�cksetzen"
		button1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ae) {
				
				textArea0.setText("");
				textField0.setText("Bitte XML-Datei ausw�hlen!");
				textField0.setAlignment(Pos.CENTER);
				textField1.setText("Hier zu pr�fenden Text eingeben!");
				textField1.setMaxWidth(350);
				textField1.setAlignment(Pos.CENTER);
			
			}
		});
		
		button2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent ae) {
				System.out.println("Button 2 gedrueckt");
				String Suchwort = null;
				String Output = null;
				Suchwort = textField1.getText();
				try{
					Search suche = new Search(filePath, Suchwort);
					Output = suche.find();
					
				}catch(Exception e){
					Output= e.getMessage();
				}
				finally{
					textArea0.setText(Output);
				}
			
			}
		});
		
		
		//Actionhandler zum exportieren der Liste als txt Datei!
		exportieren.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent ae) {
				
				//�ffnet den SaveDialog um die erzielte Suche zu speichern
				FileChooser fileChooser = new FileChooser();
	            fileChooser.setTitle("Save File as .txt");
	            fileChooser.setInitialFileName("Fremdwortsuche.txt");
	            
	            //Datei kann nur als txt gespeichert werden
	            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);
	            
	            File file = fileChooser.showSaveDialog(stage);
	            
	            // schreibt den Stringh aus der TextArea in den zu speichernden File
	            if (file != null) {
	                try {
	                	
	                	String txt = textArea0.getText();
	            		file.createNewFile();
	            		FileWriter writer = new FileWriter(file);
	            		writer.write(txt);
	            		writer.close();
	                    
	                } catch (IOException ex) {
	                    System.out.println(ex.getMessage());
	                }
	            }
				
			}				
							
		});
		
		
		pane0.getChildren().addAll(button0, button1, textField1,button2, textArea0, menueLeiste, textField0);

		Scene scene = new Scene(pane0); // Fensterinhalt in dem ein Panel gelegt wird
		stage.setScene(scene); // Fensterinhalt aufs Fenster legen
		stage.show(); // Fenster sichtbar machen

	}

	public static void main (String [] args){

		launch(args); // Anwendung wird gestartet und Startmethode wird aufgerufen
		
	}
	
}

//GEH�RT IN EINE EXTRIGE KLASSE
