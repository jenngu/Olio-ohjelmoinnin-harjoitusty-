
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;
import harjoitustyo.apulaiset.Tietoinen;
import java.util.LinkedList;


/**
 * Abstrakti luokka, joka toteuttaa Tietoinen- ja Comparable-rajapinnat
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public  abstract class Dokumentti implements Tietoinen<Dokumentti>, Comparable<Dokumentti> {
    // tunniste-attribuutti on dokumentin tunnisteluku
    private int tunniste;
    // teksti-attribuutti säilöö Dokumentin tekstin
    private String teksti;
    
    /**
     * Parametrillinen rakentaja, joka käyttää asettavia aksessoreita
     * @param id tunniste
     * @param uusiTeksti teksti dokumentille
     * @throws IllegalArgumentException jos parametrit ovat virheellisiä
     */
    public Dokumentti(int id, String uusiTeksti) throws IllegalArgumentException {
        tunniste(id);
        teksti(uusiTeksti);
    }
    
    /**
     * Dokumentin tunnisteen lukeva aksessori
     * @return int-tyyppinen tunniste
     */ 
    public int tunniste() {
        return tunniste; 
    }
    /**
     * Dokumentin tekstin lukeva aksessori
     * @return String-tyyppinen teksti
     */ 
    public String teksti() {
        return teksti;
    }
    
    /**
     * Tunnisteen asettava aksessori
     * @param id tunniste
     * @throws IllegalArgumentException parametrina on virheellinen arvo
     */
    public void tunniste(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Error"); 
        }
        else {
            tunniste = id;
        }      
    }
    /**
     * Uuden teksti asettava aksessori
     * @param uusiTeksti asetetaan uusi teksti dokumentille
     * @throws IllegalArgumentException parametrina on virheellinen arvo
     */
    public void teksti(String uusiTeksti) throws IllegalArgumentException {
        if (uusiTeksti == null || uusiTeksti.length() <= 0) {
            throw new IllegalArgumentException("Error");
        }
        else {
            teksti = uusiTeksti;
        }
    }
    
    /**
     * Object-luokan korvattu toString-metodi
     * @return String-tyyppinen sisältö
     */
    @Override
    public String toString() {
        // Palautetaan tunniste ja teksti ///-erotettuna
        return tunniste +  "///" + teksti ;
    }
    
    /**
     * Object-luokan equals metodi, joka katsoo dokumentit 
     * samoiksi tunnisteiden perusteella
     * @param o parametrina saatu olio, jota halutaan verrata.
     * @return true, jos nimet ovat samat, muuten false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (o instanceof Dokumentti) {
            return this.tunniste() == ((Dokumentti) o).tunniste();
        }
        return false;
    }
    
    /**
     * Comparable-rajapinnan compareTo-metodi,
     * joka vertaa olioiden tunnisteita keskenään.
     * @param t Dokumentti tyyppinen parametri
     * @return 1, jos olio on suurempi parametrina saatu olio, 
     * 0, jos olio on yhtä suuri parametrina saatu olio ja 
     * -1, jos tämä olio on pienempi kuin parametrina saatu olio.
     */
    @Override
    public int compareTo(Dokumentti t) {
        // Kun vertailtavat ovat samat
        if (this.tunniste == t.tunniste) {
            return 0;
        } 
        else if (this.tunniste > t.tunniste) {
            return 1;
        }
        else {
            return -1;
        }
    }
    
    /**
     * Tietoinen-rajapinnan metodi, joka käy läpi halutut hakusanat dokumentin sisältöön
     * ja palauttaa boolen arvon, jos kaikki hakusanat löytyvät dokumentin tekstistä
     * @param hakusanat, jotka käyttäjä on määrittänyt
     * @return true, jos sanat täsmäävät ja false, jos ei
     * @throws IllegalArgumentException jos parametri on virheellinen
     */
    @Override
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {
        if (hakusanat == null || hakusanat.isEmpty()) {
            throw new IllegalArgumentException();
        }
        // Luodaan taulukko, johon asetetaan tekstin sanat 
        String[] sanat = teksti.split(" ");
        // Alustetaan laskuri
        int laskuri = 0;
        for (int i = 0; i < hakusanat.size(); i = i + 1) { 
            // Alustetaan samoja sanoja laskeva apumuuttuja
            int samat = 0;
            // Silmukka, joka käy läpi kaikki sanat-taulukon sanat
            for (int j = 0; j < sanat.length; j = j + 1) {
                if (hakusanat.get(i).equals(sanat[j])) {
                    samat = samat + 1;
                }
            }
            if (samat > 0) {
                laskuri = laskuri + 1;
            }
        }   
        // Kun on käyty verailu läpi, katsotaan löytyikö jokainen listan sana tekstistä
        if (laskuri == hakusanat.size()) {
            return true;
        }
        else {
            return false;
        }
    } 
    
    /**
     * Tietoinen-rajapinnan metodi, joka siivoa dokumentin tekstin välimerkeistä
     * sulkumerkeistä ja poistaa isot alkukirjaimet
     * @param sulkusanat tulevat tekstitiedoston mukana
     * @param välimerkit tulevat käyttäjältä 
     * @throws IllegalArgumentException jos parametrit ovat virheelliset
     */
    @Override
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
        if (sulkusanat == null || sulkusanat.isEmpty() || välimerkit == null || välimerkit.length() < 1) {
            throw new IllegalArgumentException();
        }
         else {
            // Poistetaan välimerkit
            char[] ca = välimerkit.toCharArray();
            for (char c : ca) {
                teksti = teksti.replace(""+c, "");
            }
            // Poistetaan sitten tekstistä isot alkukirjaimet
            teksti = teksti.replace("[^a-zA-Z ]", "").toLowerCase();
            
            // Hajotetaan teksti paloiksi
            String[] sanat = teksti().split(" "); 
             
            // Luodaan laskuri
            int laskuri = 0;
            // Alustetaan uusi teksti
            String uusiTeksti = "";
            
            while (laskuri < sanat.length) {
                // Luodaan muuttuja current sanalle
                String currSana = sanat[laskuri];
                
                // Alustetaan lippumuuttuja 
                boolean onko = true;
                
                // Käydään tekstin sanat läpi
                for (int i = 0; i < sulkusanat.size() && onko; i = i + 1) {
                    if (currSana.equals(sulkusanat.get(i))) {
                        currSana = "";
                        onko = false;
                    }
                }
                laskuri = laskuri + 1;
                if (!currSana.equals("")) {
                    if (uusiTeksti.equals("")) {
                        uusiTeksti = currSana;
                    }
                    else {
                        uusiTeksti = uusiTeksti + " " + currSana;
                    }
                }
            } 
            teksti(uusiTeksti);
        }
    }
}
