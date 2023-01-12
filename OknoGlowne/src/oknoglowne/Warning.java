/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oknoglowne;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** klasa wywołująca okienko pop-up z ostrzeżeniem dotyczącym maksymalnej ilości błędów
 *
 * @author DOM
 */

public class Warning {
    
    
    /** metoda wyświetlająca komunikat
     * 
     * @param x maksymalna ilość błędnie przepisanych słów dla danego poziomu
     */
     public static void display(int x){
         
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ostrzeżenie");
        window.setMinWidth(350);
        
        
        
        Label label = new Label();
        label.setText("Maksymalna liczba błędów na tym poziomie wynosi " + x + " !!!");

        Button button = new Button("Przejdź dalej");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout,150,200);
        window.setScene(scene);
        window.showAndWait();
}
}
