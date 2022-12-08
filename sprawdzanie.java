/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oknoglowne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/** klasa zawierająca metody logiczne potrzebne do działania gry
 * 
 * @author DOM
 */
public class sprawdzanie  {

public static List<String> slowa = new ArrayList<>();
    
    /** dodawanie slow z pliku tekstowego do listy
     * 
     * @param slowo jedno slowo z listy 
     */
     public static void dodajSlowo(String slowo)
     {
         slowa.add(slowo);
     }

     /** wczytywanie z pliku słów do strumienia
      * 
      * @param nazwa nazwa pliku tekstowego ze słowami do poszczególnych poziomów
      * @throws IOException 
      */
    public static void odczytPlikuTekstowego(String nazwa) throws IOException {
        

        // odczyt wiersz po wierszu
        BufferedReader plik2 = null;
        try {            
            plik2 = new BufferedReader(new FileReader(nazwa));
            System.out.println("\n\nSTART!!!!!:\n");
            String l = plik2.readLine();
             
            while (l != null) {
                dodajSlowo(l); 
                l = plik2.readLine();       
            }
        } finally {
            if (plik2 != null) {
                plik2.close();
            }
        }
}
  
    /** metoda sprawdzająca poprawność wpisywanego słowa przez użytkownika z wylosowanym słowem z listy wyświetlonym na ekranie
     * 
     * @param slowa słowo z listy
     * @param s słowo wpisanie przez użytkownika
     * @param a numer indeksu słowa, które ma zostać usunięte z listy
     * @return prawda lub fałsz pozwalające określić poprawność przepisania słowa przez użytkownika
     */
    public static boolean zmien(List slowa, String s, int a)
    {
        boolean check;

        String sprawdzane = slowa.get(a).toString();

        if(s.equals(sprawdzane))    check=true;
        else  check=false;

        return check;
    }
    
}
