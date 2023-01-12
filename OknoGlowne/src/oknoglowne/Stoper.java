/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oknoglowne;

import static java.lang.System.currentTimeMillis;

/** licznik czasu od początku poziomu do jego zakończenia
 *
 * @author DOM
 */


public class Stoper {
    
   
    /** pole do zapisu czasu systemowego */
    public static double poczatek; 
    /** pole do zapisu czasu systemowego */
    public static double koniec; 
    
    /** zapisanie do pola początek informacji o czasie rozpoczęcia pomiaru czasu
     * 
     * @return systemowy czas w milisekundach
     */
    public static double start()
    {
           poczatek =  currentTimeMillis();
           return poczatek;
    } 
                                    
    /** zapisanie do pola koniec informacji o czasie zakończenia pomiaru czasu
     * 
     * @return systemowy czas w milisekundach
     */
    public static double stop()
    {
       koniec = currentTimeMillis();
       return koniec;
    }
    
}
