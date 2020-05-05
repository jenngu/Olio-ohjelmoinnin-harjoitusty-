/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jennifernguyen
 */
public class Uutinen extends Dokumentti {
    private LocalDate päivämäärä;
    
    // Kolmiparametrinen rakentaja Uutiselle, joka asettaa tunnisteen ja tekstin 
    // yliluokan yksiparametrillista rakentaa 
    public Uutinen (int tunniste, LocalDate pm, String teksti) throws IllegalArgumentException {
        super(tunniste, teksti);
        päivämäärä(pm);
    }

    // Luetaan päivämäärä
    public LocalDate päivämäärä() {
        return päivämäärä;
    }
    
    // Päivämäärän asetteva aksessori
    public void päivämäärä(LocalDate pm) throws IllegalArgumentException {
        if (pm == null) {
            throw new IllegalArgumentException("Error");
        }
        else {
            päivämäärä = pm; 
        }
    }
    
    public static String of(LocalDate pm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.Y");
        return formatter.format(pm);
    }            
    
    // Object-luokan korvattu toString-metodi
    // Kutsutaan yliluokan toString-metodia
    @Override
    public String toString() {
        return super.tunniste() + "///" + of(päivämäärä) + "///" + super.teksti(); 
    }
}
