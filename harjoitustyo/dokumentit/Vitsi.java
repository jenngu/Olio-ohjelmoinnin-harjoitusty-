package harjoitustyo.dokumentit;

import java.util.LinkedList;
import harjoitustyo.omalista.OmaLista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jennifernguyen
 */
public class Vitsi extends Dokumentti {
    private String laji; 
    
    // Parametrillinen rakentaja Vitsille, joka asettaa 
    // jotain kutsumalla yliluokan yksiparametrillistä rakentajaa
    public Vitsi (int tunniste, String uusiLaji, String teksti) throws IllegalArgumentException {
        super(tunniste, teksti);
        laji(uusiLaji);
    }
    
    // Lajin asettava aksessori, joka heittää poikkeuksen, jos laji on viallinen
    public void laji(String l) throws IllegalArgumentException {
        if (l == null || l == ("")) {
            throw new IllegalArgumentException("Error");
        }
        else {
            laji = l;
        }
    }
    
    // Luetaan laji
    public String laji(){
        return laji;
    }
    
    // Object-luokan korvattu toString-metodi
    // Kutsutaan yliluokan toString-metodia
    @Override
    public String toString() {
        return super.tunniste() + "///" + laji + "///" + super.teksti(); 
        
    }
}
