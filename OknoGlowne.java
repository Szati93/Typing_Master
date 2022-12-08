/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oknoglowne;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;  
import javafx.scene.control.Button;  
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    /** warunek dla rozpoczęcia odliczania czasu */
    public int r; /** określenie rozmiaru listy ze słowami */
    public int rozmiar; /** określa pozycję slowa w liście */
    public int licznik=0; /** ilość błędnych słów */
    public int bledy=0; /** początek odliczania czasu */
    public double czas_p; /** koniec odliczania czasu */
    public double czas_k;
    
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

       
       //layout pierwszej sceny (menu)
       layout.getChildren().addAll(button4,button5,button6,button7);
       layout.setAlignment(Pos.CENTER);
       scene = new Scene(layout,999,667); 
       scene.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());
       
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
       scene4.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());
        
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
           window.setScene(scene3);
        });
        
        button5.setOnAction(e -> { //przejscie do sceny z opisem "jak grac?"
            window.setScene(scene4);
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
       
       button8.setOnAction(e -> { /** cos */
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
       
       button11.setOnAction(e -> { //powrot do menu glownego
           window.setScene(scene);
        });
       
        button12.setOnAction(e -> { //powrot do menu glownego
           window.setScene(scene);
        });
       
       //Layout do drugiej sceny z wyborem poziomow
        VBox layout3 = new VBox(35);
        layout3.getStyleClass().add("pane");
        layout3.getChildren().addAll(button8,button9,button10,button11);
        layout3.setAlignment(Pos.CENTER);
        scene3 = new Scene(layout3,999,667);
        scene3.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());

        //label'e do sceny z pierwszym poziomem
        Image image2 = new Image(getClass().getResourceAsStream("resources/twoje.png"));
        Label label2 = new Label(); 
        label2.setGraphic(new ImageView(image2));
        Image image3 = new Image(getClass().getResourceAsStream("resources/dobrze.png"));
        Image image4 = new Image(getClass().getResourceAsStream("resources/zle.png"));
        Image image5 = new Image(getClass().getResourceAsStream("resources/transp.png"));
        Label label3 = new Label();
        
        //label'e do sceny z drugim poziomem
        Image image6 = new Image(getClass().getResourceAsStream("resources/twoje.png"));
        Label label5 = new Label(); 
        label5.setGraphic(new ImageView(image6));
        Image image7 = new Image(getClass().getResourceAsStream("resources/dobrze.png"));
        Image image8 = new Image(getClass().getResourceAsStream("resources/zle.png"));
        Image image9 = new Image(getClass().getResourceAsStream("resources/transp.png"));
        Label label6 = new Label();
        
        //label'e do sceny z trzecim poziomem
        Image image10 = new Image(getClass().getResourceAsStream("resources/twoje.png"));
        Label label8 = new Label(); 
        label8.setGraphic(new ImageView(image10));
        Image image11 = new Image(getClass().getResourceAsStream("resources/dobrze.png"));
        Image image12 = new Image(getClass().getResourceAsStream("resources/zle.png"));
        Image image13 = new Image(getClass().getResourceAsStream("resources/transp.png"));
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
      
        
        //Button do wyjscia z pierwszego poziomu do pulpitu
        Button button2 = new Button();
        button2.getStyleClass().add("buttonWithImage4");
        button2.setOnAction(e -> { 
                licznik=0;
                bledy=0;
                rozmiar=0;
                r=0;
                space.setText("");
                label3.setGraphic(new ImageView(image5));
                window.close();
        });
        
        //Button do wyjscia z drugiego poziomu do pulpitu
        Button button13 = new Button();
        button13.getStyleClass().add("buttonWithImage4");
        button13.setOnAction(e -> { 
                licznik=0;
                bledy=0;
                rozmiar=0;
                r=0;
                space.setText("");
                label6.setGraphic(new ImageView(image9));
                window.close();
        });
        
        //Button do wyjscia z trzeciego poziomu do pulpitu
        Button button14 = new Button();
        button14.getStyleClass().add("buttonWithImage4");
        button14.setOnAction(e -> { 
                licznik=0;
                bledy=0;
                rozmiar=0;
                r=0;
                space.setText("");
                label9.setGraphic(new ImageView(image13));
                window.close();
        });
        
        //wpisywanie slow dla pierwszego poziomu
        space.setOnKeyPressed((KeyEvent event) -> { 
            if(event.getCode().equals(KeyCode.ENTER)){
                
                if(r == rozmiar) czas_p = Stoper.start(); //odliczanie czasu
                
                if(sprawdzanie.zmien(sprawdzanie.slowa, space.getText(),licznik) == true) label3.setGraphic(new ImageView(image3));
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
                    PopUp.display(bledy, czas_k);
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
                
                if(sprawdzanie.zmien(sprawdzanie.slowa, space2.getText(),licznik) == true) label6.setGraphic(new ImageView(image7));
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
                    PopUp.display(bledy, czas_k);
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
                
                if(sprawdzanie.zmien(sprawdzanie.slowa, space3.getText(),licznik) == true) label9.setGraphic(new ImageView(image11));
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
                    PopUp.display(bledy, czas_k);
                    bledy=0;
                    licznik=0;
                    rozmiar=0;
                    r=0;
                    label9.setGraphic(new ImageView(image13)); //wyzerowanie labela z dobrze/zle
                    window.setScene(scene);
                }
            }
        });
        
        //Layout do sceny z pierwszym poziomem
        VBox layout2 = new VBox(20);
        layout2.getStyleClass().add("pane");
        layout2.getChildren().addAll(label2,label,space,label3,button2);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2,999,667);
        scene2.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());
        
         //Layout do sceny z drugim poziomem
        VBox layout5 = new VBox(20);
        layout5.getStyleClass().add("pane");
        layout5.getChildren().addAll(label5,label4,space2,label6,button13);
        layout5.setAlignment(Pos.CENTER);
        scene5 = new Scene(layout5,999,667);
        scene5.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());
        
         //Layout do sceny z trzecim poziomem
        VBox layout6 = new VBox(30);
        layout6.getStyleClass().add("pane");
        layout6.getChildren().addAll(label8,label7,space3,label9,button14);
        layout6.setAlignment(Pos.CENTER);
        scene6 = new Scene(layout6,999,667);
        scene6.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());
        
      
        
        
        window.setScene(scene);
        window.setTitle("Keyboard Master");
        window.show();
    }
 
}
