/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oknoglowne;
import java.io.*;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;


/** klasa zawierająca wszystkie layouty do kazdej sceny (kazdego poziomu)
oraz wyrazenie lambda na "Enter" sprawdzające wpisywane przez użytkownika słowo */
public class OknoGlowne extends Application {

    Stage window;
    /** scena menu */
    public Scene scene; /** scena z pierwszym poziomem */
    public Scene scene2; /** scena z wyborem poziomów */
    public Scene scene3; /** scena z opisem zasad gry */
    public Scene scene4; /** scena z drugim poziomem */
    public Scene scene5;/** scena z trzecim poziomem */
    public Scene scene6;
    public Scene scene7;/** scena wyboru trybu gry */
    public Scene scene8;/** scena wyboru poziomu z przeciaganie */
    public Scene scene9;/** scena z poziomem1 z przeciaganiem */
    public Scene scene10;/** scena z poziomem2 z przeciaganiem */
    public Scene scene11;


    /** warunek dla rozpoczęcia odliczania czasu */
    public int r; /** określenie rozmiaru listy ze słowami */
    public int rozmiar; /** określa pozycję slowa w liście */
    public int licznik=0; /** ilość błędnych słów */
    public int bledy=0; /** początek odliczania czasu */
    public double czas_p; /** koniec odliczania czasu */
    public double czas_k;

    private double startX;
    private double startY;


    public static void main(String[] args) {
        launch(args);
    }
    
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        
       window = primaryStage;

       //Layout glowny
       VBox layout = new VBox(20);
       layout.getStyleClass().add("pane");
       
       Button button4 = new Button(); // przyciski scena pierwsza (menu)
       button4.getStyleClass().add("buttonWithImage1");
       
       Button button5 = new Button(); // przyciski scena pierwsza (menu)
       button5.getStyleClass().add("buttonWithImage2");
       
       Button button6 = new Button(); // przyciski scena pierwsza (menu)
       button6.getStyleClass().add("buttonWithImage3");
       
       Button button7 = new Button(); // przyciski scena pierwsza (menu)
       button7.getStyleClass().add("buttonWithImage4");

        Button button18 = new Button(); // przyciski scena pierwsza (menu)
        button18.getStyleClass().add("buttonWithImage5");

        Button button19 = new Button(); // przyciski scena pierwsza (menu)
        button19.getStyleClass().add("buttonWithImage6");

        Button button20 = new Button(); // przyciski scena pierwsza (menu)
        button20.getStyleClass().add("buttonWithImage7");

        Button button21 = new Button(); // przyciski scena pierwsza (menu)
        button21.getStyleClass().add("buttonWithImage8");

       
       //layout pierwszej sceny (menu)
       layout.getChildren().addAll(button4,button5,button6,button7);
       layout.setAlignment(Pos.CENTER);
       scene = new Scene(layout,999,667); 
       scene.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());
       
       //layout "jak grać?"
       VBox layout4 = new VBox(20);
       layout4.getStyleClass().add("pane");
       layout4.setAlignment(Pos.CENTER);
       Label zasada1 = new Label("1. Wybierz poziom trudności." + " Poziom 1 - najłatwiejszy, Poziom 3 - najtrudniejszy");
       Label zasada2 = new Label("2. Wciśnieciu klawisza enter po wybraniu poziomu skutkuje odliczaniem czasu.");
       Label zasada3 = new Label("3. Nie ma możliwości powrotu do menu w trakcie trwania poziomu.");
       Label zasada4 = new Label("4. Po skończenu poziomu pojawi się okienko z wynikami i możliwością powrotu do menu.");
       Button button12 = new Button(); // przyciski scena pierwsza (menu)
       button12.getStyleClass().add("buttonWithImage8");
       zasada1.setTextFill(Color.web("#DC143C"));
       zasada2.setTextFill(Color.web("#DC143C"));
       zasada3.setTextFill(Color.web("#DC143C"));
       zasada4.setTextFill(Color.web("#DC143C"));
       zasada1.setFont(new Font("Arial", 25));
       zasada2.setFont(new Font("Arial", 25));
       zasada3.setFont(new Font("Arial", 25));
       zasada4.setFont(new Font("Arial", 25));
       layout4.getChildren().addAll(zasada1,zasada2,zasada3,zasada4,button12);
       scene4 = new Scene(layout4,999,667);
       scene4.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());
        
       //slowa wyswietlane do przepisania dla uzytkownika (1 poziom)
       Label label = new Label();
       label.setTextFill(Color.web("#0076a3"));
       label.setFont(new Font("Arial", 30));
       
       //slowa wyswietlane do przepisania dla uzytkownika (2 poziom)
       Label label4 = new Label();
       label4.setTextFill(Color.web("#0076a3"));
       label4.setFont(new Font("Arial", 30));
       
       //slowa wyswietlane do przepisania dla uzytkownika (2 poziom)
       Label label7 = new Label();
       label7.setTextFill(Color.web("#0076a3"));
       label7.setFont(new Font("Arial", 30));


        
        
        button4.setOnAction(e -> { //przejscie do drugiej sceny z wyborem poziomow
           window.setScene(scene7);
        });
        
        button5.setOnAction(e -> { //przejscie do sceny z opisem "jak grac?"
            window.setScene(scene4);
        });

        button6.setOnAction(e -> { //pokazanie wyników

            StringBuilder content = new StringBuilder();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("src/oknoglowne/resources/scores.txt"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            String aLineFromFile;
            while (true){
                try {
                    assert br != null;
                    if ((aLineFromFile = br.readLine()) == null) break;
                    content.append(aLineFromFile).append("\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyniki");

            TextArea area = new TextArea(content.toString());
            area.setWrapText(true);
            area.setEditable(false);

            alert.getDialogPane().setContent(area);
            alert.setResizable(true);
            alert.showAndWait();
        });
          
        button7.setOnAction(e -> { //wyjscie z gry
           window.close();
        });
       
       Button button8 = new Button(); //scena 2 - wybor poziomu
       button8.getStyleClass().add("buttonWithImage5");
       
       Button button9 = new Button(); //scena 2 - wybor poziomu
       button9.getStyleClass().add("buttonWithImage6");
       
       Button button10 = new Button(); //scena 2 - wybor poziomu
       button10.getStyleClass().add("buttonWithImage7");
       
       Button button11 = new Button(); //scena 2 - wroc do menu
       button11.getStyleClass().add("buttonWithImage8");

       
       //pierwszy poziom

       button8.setOnAction(e -> {
           window.setScene(scene2);

            try {
                sprawdzanie.odczytPlikuTekstowego("src/oknoglowne/resources/slowa.txt");
            } catch (IOException ex) {
                Logger.getLogger(OknoGlowne.class.getName()).log(Level.SEVERE, null, ex);
            }
            Collections.shuffle(sprawdzanie.slowa);
            licznik=0;
            bledy=0;
            rozmiar = sprawdzanie.slowa.size();
            r = rozmiar;
            label.setText("Wcisnij enter, aby rozpocząć...");
        });

       button9.setOnAction(e -> { //drugi poziom
           window.setScene(scene5);

           try {
                sprawdzanie.odczytPlikuTekstowego("src/oknoglowne/resources/slowa2.txt");
            } catch (IOException ex) {
                Logger.getLogger(OknoGlowne.class.getName()).log(Level.SEVERE, null, ex);
            }
            Collections.shuffle(sprawdzanie.slowa);
            licznik=0;
            bledy=0;
            rozmiar = sprawdzanie.slowa.size();
            r = rozmiar;
            Warning.display(10);
            label4.setText("Wcisnij enter, aby rozpocząć...");
        });

       button10.setOnAction(e -> { //trzeci poziom
           window.setScene(scene6);

           try {
                sprawdzanie.odczytPlikuTekstowego("src/oknoglowne/resources/slowa3.txt");
            } catch (IOException ex) {
                Logger.getLogger(OknoGlowne.class.getName()).log(Level.SEVERE, null, ex);
            }
            Collections.shuffle(sprawdzanie.slowa);
            licznik=0;
            bledy=0;
            rozmiar = sprawdzanie.slowa.size();
            r = rozmiar;
            Warning.display(5);
            label7.setText("Wcisnij enter, aby rozpocząć...");
        });
       
       button11.setOnAction(e -> { //powrot do menu glownego z wyboru poziomu trybu pierwszego
           window.setScene(scene7);
        });
       
        button12.setOnAction(e -> { // powrot do menu glownego z instrukcji gry
           window.setScene(scene);
        });

        button21.setOnAction(e -> { // powrot do menu glownego z  wyboru poziomu trybu drugiego
            window.setScene(scene7);
        });

        Button button15 = new Button(); //wybor trybu gry przeciaganie
        button15.getStyleClass().add("buttonWithImage15");

        Button button16 = new Button(); //wybor trybu gry wpisywanie
        button16.getStyleClass().add("buttonWithImage16");

        Button button17 = new Button(); // wroc do menu
        button17.getStyleClass().add("buttonWithImage8");

        button15.setOnAction(e -> { //powrot do menu glownego
            window.setScene(scene3);
        });

        button16.setOnAction(e -> window.setScene(scene8));

        button17.setOnAction(e -> { //powrot do menu glownego
            window.setScene(scene);
        });

        VBox layout7 = new VBox(35);
        layout7.getStyleClass().add("pane");
        layout7.getChildren().addAll(button15,button16,button17);
        layout7.setAlignment(Pos.CENTER);
        scene7 = new Scene(layout7,999,667);
        scene7.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());

        //Layout do drugiej sceny z wyborem poziomow
        VBox layout8 = new VBox(35);
        layout8.getStyleClass().add("pane");
        layout8.getChildren().addAll(button18,button19,button20,button21);
        layout8.setAlignment(Pos.CENTER);
        scene8 = new Scene(layout8,999,667);
        scene8.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());


        button18.setOnAction(e -> { // poziom 1 trybu drugiego
            window.setScene(scene9);
        });

        button19.setOnAction(e -> { // poziom 2 trybu drugiego
            window.setScene(scene10);
        });

        button20.setOnAction(e -> { // poziom 3 trybu drugiego
            window.setScene(scene11);
        });

        //label'e do sceny z pierwszym poziomem trybu przeciaganie
        Image klawiaturaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/klawiatura.png")));
        Label klawiaturaLabel = new Label();
        klawiaturaLabel.setGraphic(new ImageView(klawiaturaImage));

        //Button do wyjscia z pierwszego poziomu do pulpitu  trybu przeciaganie
        Button button2 = new Button();
        button2.getStyleClass().add("buttonWithImage4");
        button2.setOnAction(e -> window.close());

        //Button do wyjscia z drugiego poziomu do pulpitu trybu przeciaganie
        Button button13 = new Button();
        button13.getStyleClass().add("buttonWithImage4");
        button13.setOnAction(e -> window.close());





        //Layout do drugiej sceny z wyborem poziomow
        VBox layout3 = new VBox(35);
        layout3.getStyleClass().add("pane");
        layout3.getChildren().addAll(button8,button9,button10,button11);
        layout3.setAlignment(Pos.CENTER);
        scene3 = new Scene(layout3,999,667);
        scene3.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());

        //label'e do sceny z pierwszym poziomem
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/twoje.png")));
        Label label2 = new Label(); 
        label2.setGraphic(new ImageView(image2));
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/dobrze.png")));
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/zle.png")));
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/transp.png")));
        Label label3 = new Label();




        
        //label'e do sceny z drugim poziomem
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/twoje.png")));
        Label label5 = new Label(); 
        label5.setGraphic(new ImageView(image6));
        Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/dobrze.png")));
        Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/zle.png")));
        Image image9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/transp.png")));
        Label label6 = new Label();
        
        //label'e do sceny z trzecim poziomem
        Image image10 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/twoje.png")));
        Label label8 = new Label(); 
        label8.setGraphic(new ImageView(image10));
        Image image11 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/dobrze.png")));
        Image image12 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/zle.png")));
        Image image13 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/transp.png")));
        Label label9 = new Label();




        

        //TextBox do wpisywania slow w 1 poziomie
        TextField space = new TextField();
        space.setPrefWidth(80);
        space.setMaxWidth(80);
        
        //TextBox do wpisywania slow w 2 poziomie
        TextField space2 = new TextField();
        space2.setPrefWidth(80);
        space2.setMaxWidth(80);
        
        //TextBox do wpisywania slow w 3 poziomie
        TextField space3 = new TextField();
        space3.setPrefWidth(80);
        space3.setMaxWidth(80);
      

        
        //wpisywanie slow dla pierwszego poziomu
        space.setOnKeyPressed((KeyEvent event) -> { 
            if(event.getCode().equals(KeyCode.ENTER)){
                
                if(r == rozmiar) czas_p = Stoper.start(); //odliczanie czasu
                
                if(sprawdzanie.zmien(sprawdzanie.slowa, space.getText(), licznik)) label3.setGraphic(new ImageView(image3));
                else{
                if(r != rozmiar)
                {
                    label3.setGraphic(new ImageView(image4));
                    bledy++; //licznik bledow
                }
                }
                                    
                space.setText("");
                sprawdzanie.slowa.remove(licznik);

                label.setText(sprawdzanie.slowa.get(licznik));
                rozmiar--;
                if (rozmiar == 1) 
                {
                    czas_k =(Stoper.stop()-czas_p)/1000;
                    try {
                        PopUp.display(bledy, czas_k);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bledy=0;
                    licznik=0;
                    rozmiar=0;
                    r=0;
                    label3.setGraphic(new ImageView(image5)); //wyzerowanie labela z dobrze/zle
                    window.setScene(scene);
                }
            }
        });
  
        //wpisywanie slow dla drugiego poziomu
        space2.setOnKeyPressed((KeyEvent event) -> { 
            if(event.getCode().equals(KeyCode.ENTER)){

                if(r == rozmiar) czas_p = Stoper.start(); //odliczanie czasu
                
                if(sprawdzanie.zmien(sprawdzanie.slowa, space2.getText(), licznik)) label6.setGraphic(new ImageView(image7));
                else{
                if(r != rozmiar)
                {
                    label6.setGraphic(new ImageView(image8));
                    bledy++; //licznik bledow
                }
                }
                                    
                space2.setText("");
                sprawdzanie.slowa.remove(licznik);

                label4.setText(sprawdzanie.slowa.get(licznik));
                rozmiar--;
                if (rozmiar == 1 || bledy == 10) 
                {
                    czas_k =(Stoper.stop()-czas_p)/1000;
                    try {
                        PopUp.display(bledy, czas_k);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bledy=0;
                    licznik=0;
                    rozmiar=0;
                    r=0;
                    label6.setGraphic(new ImageView(image9)); //wyzerowanie labela z dobrze/zle
                    window.setScene(scene);
                }
            }
        });
        
        //wpisywanie slow dla trzeciego poziomu
        space3.setOnKeyPressed((KeyEvent event) -> { 
            if(event.getCode().equals(KeyCode.ENTER)){

                if(r == rozmiar) czas_p = Stoper.start(); //odliczanie czasu
                
                if(sprawdzanie.zmien(sprawdzanie.slowa, space3.getText(), licznik)) label9.setGraphic(new ImageView(image11));
                else{
                if(r != rozmiar)
                {
                    label9.setGraphic(new ImageView(image12));
                    bledy++; //licznik bledow
                }
                }
                                    
                space3.setText("");
                sprawdzanie.slowa.remove(licznik);

                label7.setText(sprawdzanie.slowa.get(licznik));
                rozmiar--;
                if (rozmiar == 3 || bledy == 5) 
                {
                    czas_k =(Stoper.stop()-czas_p)/1000;
                    try {
                        PopUp.display(bledy, czas_k);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bledy=0;
                    licznik=0;
                    rozmiar=0;
                    r=0;
                    label9.setGraphic(new ImageView(image13)); //wyzerowanie labela z dobrze/zle
                    window.setScene(scene);
                }
            }
        });

        //Button do wyjscia z trzeciego poziomu do pulpitu trybu przeciaganie
        Button button14 = new Button();
        button14.getStyleClass().add("buttonWithImage4");
        button14.setOnAction(e -> window.close());

        Button button24 = new Button();
        button24.getStyleClass().add("buttonWithImage4");
        button24.setOnAction(e -> window.close());

        Button button34 = new Button();
        button34.getStyleClass().add("buttonWithImage4");
        button34.setOnAction(e -> window.close());

        //Button do wyjscia z trzeciego poziomu do pulpitu trybu przeciaganie
        Button button45 = new Button();
        button45.getStyleClass().add("buttonWithImage4");
        button45.setOnAction(e -> window.close());
        
        //Layout do sceny z pierwszym poziomem
        VBox layout2 = new VBox(20);
        layout2.getStyleClass().add("pane");
        layout2.getChildren().addAll(label2,label,space,label3,button2);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2,999,667);
        scene2.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());
        
         //Layout do sceny z drugim poziomem
        VBox layout5 = new VBox(20);
        layout5.getStyleClass().add("pane");
        layout5.getChildren().addAll(label5,label4,space2,label6,button13);
        layout5.setAlignment(Pos.CENTER);
        scene5 = new Scene(layout5,999,667);
        scene5.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());

        //Layout do sceny z trzecim poziomem
        VBox layout6 = new VBox(30);
        layout6.getStyleClass().add("pane");
        layout6.getChildren().addAll(label8,label7,space3,label9,button45);
        layout6.setAlignment(Pos.CENTER);
        scene6 = new Scene(layout6,999,667);
        scene6.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());

        Image imageKlawiatura = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/klawiatura.png")));
        Label labelKlawiatura1 = new Label();
        labelKlawiatura1.setGraphic(new ImageView(imageKlawiatura));
        labelKlawiatura1.autosize();

        Label labelKlawiatura2 = new Label();
        labelKlawiatura2.setGraphic(new ImageView(imageKlawiatura));
        labelKlawiatura2.autosize();

        Label labelKlawiatura3 = new Label();
        labelKlawiatura3.setGraphic(new ImageView(imageKlawiatura));
        labelKlawiatura3.autosize();


        Label literaA = new Label();
        Image imageA = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/a.png")));
        literaA.setGraphic(new ImageView(imageA));
        literaA.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaA.getTranslateX();
            startY = e.getSceneY() - literaA.getTranslateY();
        });
        literaA.setOnMouseDragged(e -> {
            literaA.setTranslateX(e.getSceneX() - startX);
            literaA.setTranslateY(e.getSceneY() - startY);
        });

        Label literaB = new Label();
        Image imageB = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/b.png")));
        literaB.setGraphic(new ImageView(imageB));
        literaB.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaB.getTranslateX();
            startY = e.getSceneY() - literaB.getTranslateY();
        });
        literaB.setOnMouseDragged(e -> {
            literaB.setTranslateX(e.getSceneX() - startX);
            literaB.setTranslateY(e.getSceneY() - startY);
        });

        Label literaC = new Label();
        Image imageC = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/c.png")));
        literaC.setGraphic(new ImageView(imageC));
        literaC.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaC.getTranslateX();
            startY = e.getSceneY() - literaC.getTranslateY();
        });
        literaC.setOnMouseDragged(e -> {
            literaC.setTranslateX(e.getSceneX() - startX);
            literaC.setTranslateY(e.getSceneY() - startY);
        });

        Label literaD = new Label();
        Image imageD = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/d.png")));
        literaD.setGraphic(new ImageView(imageD));
        literaD.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaD.getTranslateX();
            startY = e.getSceneY() - literaD.getTranslateY();
        });
        literaD.setOnMouseDragged(e -> {
            literaD.setTranslateX(e.getSceneX() - startX);
            literaD.setTranslateY(e.getSceneY() - startY);
        });

        Label literaE = new Label();
        Image imageE = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/e.png")));
        literaE.setGraphic(new ImageView(imageE));
        literaE.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaE.getTranslateX();
            startY = e.getSceneY() - literaE.getTranslateY();
        });
        literaE.setOnMouseDragged(e -> {
            literaE.setTranslateX(e.getSceneX() - startX);
            literaE.setTranslateY(e.getSceneY() - startY);
        });

        Label literaF = new Label();
        Image imageF = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/f.png")));
        literaF.setGraphic(new ImageView(imageF));
        literaF.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaF.getTranslateX();
            startY = e.getSceneY() - literaF.getTranslateY();
        });
        literaF.setOnMouseDragged(e -> {
            literaF.setTranslateX(e.getSceneX() - startX);
            literaF.setTranslateY(e.getSceneY() - startY);
        });

        Label literaH = new Label();
        Image imageH = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/h.png")));
        literaH.setGraphic(new ImageView(imageH));
        literaH.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaH.getTranslateX();
            startY = e.getSceneY() - literaH.getTranslateY();
        });
        literaH.setOnMouseDragged(e -> {
            literaH.setTranslateX(e.getSceneX() - startX);
            literaH.setTranslateY(e.getSceneY() - startY);
        });

        Label literaK = new Label();
        Image imageK = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/k.png")));
        literaK.setGraphic(new ImageView(imageK));
        literaK.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaK.getTranslateX();
            startY = e.getSceneY() - literaK.getTranslateY();
        });
        literaK.setOnMouseDragged(e -> {
            literaK.setTranslateX(e.getSceneX() - startX);
            literaK.setTranslateY(e.getSceneY() - startY);
        });

        Label literaM = new Label();
        Image imageM = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/m.png")));
        literaM.setGraphic(new ImageView(imageM));
        literaM.setOnMousePressed(e -> {
            startX = e.getSceneX() - literaM.getTranslateX();
            startY = e.getSceneY() - literaM.getTranslateY();
        });
        literaM.setOnMouseDragged(e -> {
            literaM.setTranslateX(e.getSceneX() - startX);
            literaM.setTranslateY(e.getSceneY() - startY);
        });

        //Button do sprawdzania ulozenia liter poziom 1
        Button sprawdzButton1 = new Button();
        sprawdzButton1.getStyleClass().add("sprawdzImage");
        sprawdzButton1.getStyleClass().add("buttonWithImage4");
        sprawdzButton1.setOnAction(e -> {
            try {
                // obsluga gdzie itp
                PopUp.display2(literaA.getTranslateX() >= -281 && literaA.getTranslateX() <= -234 && literaA.getTranslateY() >= -189 && literaA.getTranslateY() <= -153
                        && literaB.getTranslateX() >= -64 && literaB.getTranslateX() <= -25 && literaB.getTranslateY() >= -213 && literaB.getTranslateY() <= -172
                        && literaC.getTranslateX() >= -165 && literaC.getTranslateX() <= -126 && literaC.getTranslateY() >= -282 && literaC.getTranslateY() <= -246);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            window.setScene(scene);
        });

        //Button do sprawdzania ulozenia liter poziom 2
        Button sprawdzButton2 = new Button();
        sprawdzButton2.getStyleClass().add("sprawdzImage");
        sprawdzButton2.getStyleClass().add("buttonWithImage4");
        sprawdzButton2.setOnAction(e -> {
            try {
                // obsluga gdzie itp
                PopUp.display2(literaD.getTranslateX() >= -157 - 17 && literaD.getTranslateX() <= -157 + 17 && literaD.getTranslateY() >= -171 - 17 && literaD.getTranslateY() <= -171 + 17
                        && literaE.getTranslateX() >= -171 - 17 && literaE.getTranslateX() <= -171 + 17 && literaE.getTranslateY() >= -290 - 17 && literaE.getTranslateY() <= -290 + 17
                        && literaF.getTranslateX() >= -108 - 17 && literaF.getTranslateX() <= -108 + 17 && literaF.getTranslateY() >= -311 - 17 && literaF.getTranslateY() <= -311 + 17);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            window.setScene(scene);
        });

        //Button do sprawdzania ulozenia liter poziom 3
        Button sprawdzButton3 = new Button();
        sprawdzButton3.getStyleClass().add("sprawdzImage");
        sprawdzButton3.getStyleClass().add("buttonWithImage4");
        sprawdzButton3.setOnAction(e -> {
            try {
                // obsluga gdzie itp
                PopUp.display2(literaH.getTranslateX() >= -9 - 17 && literaH.getTranslateX() <= -9 + 17 && literaH.getTranslateY() >= -171 - 17 && literaH.getTranslateY() <= -171 + 17
                        && literaK.getTranslateX() >= 90 - 17 && literaK.getTranslateX() <= 90 + 17 && literaK.getTranslateY() >= -240 - 17 && literaK.getTranslateY() <= -240 + 17
                        && literaM.getTranslateX() >= 53 - 17 && literaM.getTranslateX() <= 53 + 17 && literaM.getTranslateY() >= -262 - 17 && literaM.getTranslateY() <= -262 + 17);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            window.setScene(scene);
        });















        //Layout do sceny z pierwszym poziomem trybu przeciaganie
        VBox layout9 = new VBox(30);
        layout9.getStyleClass().add("pane");
        layout9.getChildren().addAll(labelKlawiatura1, literaA, literaB, literaC, sprawdzButton1, button14);
        layout9.setAlignment(Pos.CENTER);
        scene9 = new Scene(layout9,999,667);
        scene9.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());


        //Layout do sceny z drugim poziomem trybu przeciaganie
        VBox layout10 = new VBox(30);
        layout10.getStyleClass().add("pane");
        layout10.getChildren().addAll(labelKlawiatura2, literaD, literaE, literaF, sprawdzButton2, button24);
        layout10.setAlignment(Pos.CENTER);
        scene10 = new Scene(layout10,999,667);
        scene10.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());

        //Layout do sceny z trzecim poziomem trybu przeciaganie
        VBox layout11 = new VBox(30);
        layout11.getStyleClass().add("pane");
        layout11.getChildren().addAll(labelKlawiatura3, literaH, literaK, literaM, sprawdzButton3, button34);
        layout11.setAlignment(Pos.CENTER);
        scene11 = new Scene(layout11,999,667);
        scene11.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("styles.css")).toExternalForm());





        window.setScene(scene);
        window.setTitle("Keyboard Master");
        window.show();
    }
}
