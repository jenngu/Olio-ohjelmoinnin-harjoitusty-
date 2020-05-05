/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.kokoelma;

import harjoitustyo.apulaiset.Kokoava;
import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.OmaLista;

/**
 *
 * @author jennifernguyen
 */
// Kokoelmien hallinnointi
public class Kokoelma implements Kokoava<Dokumentti> {
    // dokumentti-attribuutti, joka sisältää viitteet kokoelmaan kuuluviin dokumenttiolioihin
    private OmaLista<Dokumentti> dokumentit;
    
    // Oletusrakentaja, jossa dokumentit-attribuuttiin liitetään rakentajassa luotu tyhjä listaolio  
    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }
    
    // Lukeva aksessori
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }
    
    // Toteutetaan Kokoava-rajapinnan metodit
    // Kokoava-rajapinnan lisää()-metodi
    @Override
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        // Jos uusi dokumentti on null tai ei ole vertailtava, heitetään poikkeus
        if (uusi == null || !(uusi instanceof Comparable)) {
            throw new IllegalArgumentException("");
        }
        // Jos uusi dokumentti on jo kokoelmassa, heitetään poikkeus 
        else if (uusi.equals(dokumentit)){
           throw new IllegalArgumentException("");
        }    
        // Jos uusi tunniste on sama kuin jo kokoelmassa oleva tunniste, heitetään poikkeus  
        else {
            for (int i = 0; i < dokumentit.size(); i = i + 1) {
                if (uusi.tunniste() == dokumentit.get(i).tunniste()) {
                    throw new IllegalArgumentException("");
                }
            }
        }
        // Muuten lisätään, uusi dokumentti kokoelmaan
        dokumentit.lisää(uusi);
        
    }
    
    // Kokoava-rajapinnan hae()-metodi
    @Override
    public Dokumentti hae(int tunniste) throws IllegalArgumentException {
        if (tunniste <= 0) {
            return null;
        }
        else {
            int i = 0;
            boolean lippu = true;
            while (i < dokumentit.size() && lippu) {
                if (tunniste == dokumentit.get(i).tunniste()) {
                    lippu = false; 
                    return dokumentit.get(i);
                    
                }
            i = i + 1;
            } 
            // Jos viitettä ei löytynyt, palautetaan null
            if (lippu == true) {
                return null;
            }
        }
        return null;
    }
}  
    

