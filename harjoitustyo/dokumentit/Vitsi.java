package harjoitustyo.dokumentit;

import java.util.LinkedList;
import harjoitustyo.omalista.OmaLista;

/**
 * Vitsi-luokka, joka periytyy Dokumentti-luokasta
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public class Vitsi extends Dokumentti {
    private String laji; 
    
    /**
    * Kolmiparametrinen rakentaja Vitsille, joka asettaa tunnisteen ja tekstin kutsumalla
    * yliluokan rakentajaa.
    * Laji asetetaan kutsumalla lajin asettavan aksessorin
    * @param tunniste vitsille
    * @param laji vitsille
    * @param teksti vitsille
    *
    * @throws IllegalArgumentException jos parametrit virheellisiä
    */
    public Vitsi (int tunniste, String uusiLaji, String teksti) throws IllegalArgumentException {
        super(tunniste, teksti);
        laji(uusiLaji);
    }
    
    /**
    * Lajin asettava aksessori
    * @param laji vitsille
    * @throws IllegalArgumentException, jos laji on viallinen
    */
    public void laji(String l) throws IllegalArgumentException {
        if (l == null || l == ("")) {
            throw new IllegalArgumentException("Error");
        }
        else {
            laji = l;
        }
    }
    
    /**
    * Lajin lukeva aksessori
    * @return laji vitsille
    */
    public String laji(){
        return laji;
    }
    
    /**
    * Object-luokan korvattu toString-metodi, jossa kutsutaan yliluokan toString()-metodia
    * @return String-tyyppinen sisältö
    */
    @Override
    public String toString() {
        return super.tunniste() + "///" + laji + "///" + super.teksti(); 
        
    }
}
