
package oknoglowne;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** klasa będąca okienkiem typu pop-up z wynikami po zakończonym poziomie
 *
 * @author DOM
 */

public class PopUp {
    
   
    /** metoda wyświetlająca użytkownikowi informację zwrotną o czasie gry i ilości błędów
     * 
     * @param liczba_b liczba błędnie przepisanych słów
     * @param cz czas wykonywania poziomu
     */
    
    public static void display(int liczba_b,double cz ) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Twój Wynik!");
        window.setMinWidth(250);
        
        
        String liczba_bledow = Integer.toString(liczba_b);
        Label label = new Label();
        label.setText("Liczba błędów: " + liczba_bledow);
        
        String czas = Double.toString(cz);
        Label label2 = new Label();
        label2.setText("Czas wykonania: " + czas);
        
        Button button = new Button("Powrót do menu");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,label2,button);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout,150,150);
        window.setScene(scene);
        window.showAndWait();


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String str = "Data: " + formatter.format(date) + "\nLiczba błędów:  " + liczba_bledow + "\nCzas: " + czas + "\n\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/oknoglowne/resources/scores.txt", true));
        writer.append(str);

        writer.close();
    }

    public static void display2(Boolean good) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Twój Wynik!");
        window.setMinWidth(250);


        Label label = new Label();
        if (good) label.setText("Dobrze, wygrałeś!");
        else label.setText("Źle, spróbuj jeszcze raz!");

        Button button = new Button("Powrót do menu");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,150,150);
        window.setScene(scene);
        window.showAndWait();
    }
}
