/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.omalista;

import java.util.LinkedList;
import harjoitustyo.apulaiset.*;

/**
 * OmaLista-luokka, joka perityy LinkedList-luokasta ja toteuttaa Ooperoiva-rajapinnan.
 * Luokassa tapahtuu alkioiden lisäys ja poisto kokelmasta.
 * <p>
 * @param <E> tyyppi, joka perityy LinkedLististä
 * <p>   
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    /** 
    * Metodi lisää alkion listalle siten, että alkio sijoittuu kaikkien itseään pienempien tai 
    * yhtä suurien alkioiden jälkeen ja ennen kaikkia itseään suurempia alkioita.
    * @param uusi alkio, joka halutaan lisätä kokoelmaan
    */
    @SuppressWarnings({"unchecked"})
    @Override
    public void lisää(E uusi) {
        // Tarkistetaan parametri
        if (uusi == null || !(uusi instanceof Comparable)) {
            throw new IllegalArgumentException("");
        }
        // Vertailussa käytetään apuna Comparable-tyyppisiä viitteitä
        Comparable uusiAlkio = (Comparable) uusi;
        // Tarkistetaan listan sisältö
        // Jos se on tyhjä, niin voidaan lisätä uusi listaan ensimmäiseksi alkioksi
        if (isEmpty()) {
            addFirst(uusi);
        }
        // Muuten käydään lista läpi ja kutsutaan Comparable<T>-rajapinnan compareTo-metodia
        // Jos verrattava alkio < parametrinä saatu olio, niin lisätään alkio 
        // laskurin kohdalle, muuten lisätään alkio listan loppuun
        else {
            // Alustetaan laskuri
            int i = 0;
            // Alustetaan lippumuuttuja 
            boolean jatketaan = true;
            while (jatketaan && i < size()) {
                if (uusiAlkio.compareTo(get(i)) < 0) {
                    add(i, uusi);
                    jatketaan = false;
                }  
                i = i + 1;
            }   
            // Jos jatketaan edelleen true, silloin ei ole lisätty kokoelmaan mitään
            if (jatketaan == true) {
                addLast(uusi);
            }
        } 
    }
    
    /**
     * Metodissa poistetaan parametrina saatu alkio kokoelmasta
     * @param poistettava 
     */ 
    public void poista(E poistettava) {
        // Käydään kokoelma läpi, ja poistetaan
        for (int j = 0; j < size(); j = j + 1) {
            if (get(j) == poistettava) {
                remove(j);
                j = j - 1;
            }
        }
    }
}

