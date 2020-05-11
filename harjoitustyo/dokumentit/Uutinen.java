/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Uutinen-luokka, joka periytyy Dokumentti-luokasta
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public class Uutinen extends Dokumentti {
    private LocalDate päivämäärä;
    
    /**
    * Kolmiparametrinen rakentaja Uutiselle, joka asettaa tunnisteen ja tekstin kutsumalla
    * yliluokan rakentajaa.
    * Päivämäärä asetetaan kutsumalla päivämäärän asettavan aksessorin
    * @param tunniste uutiselle
    * @param pm uutiselle 
    * @param teksti uutiselle 
    *
    * @throws IllegalArgumentException jos parametrit virheellisiä
    */
    // Kolmiparametrinen rakentaja Uutiselle, joka asettaa tunnisteen ja tekstin 
    // yliluokan yksiparametrillista rakentaa 
    public Uutinen (int tunniste, LocalDate pm, String teksti) throws IllegalArgumentException {
        super(tunniste, teksti);
        päivämäärä(pm);
    }

    /**
    * Päivämäärän lukeva aksessori
    * @return päivämäärä uutiselle
    */
    public LocalDate päivämäärä() {
        return päivämäärä;
    }
    
    /**
    * Päivämäärän asettava aksessori.
    * @param pm 
    * @throws IllegalArgumentException jos parametri on virheellinen
    */
    // Päivämäärän asetteva aksessori
    public void päivämäärä(LocalDate pm) throws IllegalArgumentException {
        if (pm == null) {
            throw new IllegalArgumentException("Error");
        }
        else {
            päivämäärä = pm; 
        }
    }
    
    /** 
     * Muutetaan päivämäärä haluttuun muotoon
     * @param pm 
     */
    public static String of(LocalDate pm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.Y");
        return formatter.format(pm);
    }            
    

    /**
    * Object-luokan korvattu toString-metodi, jossa kutsutaan yliluokan toString()-metodia
    * @return String-tyyppinen sisältö
    */
    @Override
    public String toString() {
        return super.tunniste() + "///" + of(päivämäärä) + "///" + super.teksti(); 
    }
}
