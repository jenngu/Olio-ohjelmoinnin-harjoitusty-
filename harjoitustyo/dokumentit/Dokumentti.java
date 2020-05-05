/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.dokumentit;
import harjoitustyo.apulaiset.Tietoinen;
import java.util.LinkedList;


/**
 *
 * @author jennifernguyen
 */
public  abstract class Dokumentti implements Tietoinen<Dokumentti>, Comparable<Dokumentti> {
    // tunniste-attribuutti on dokumentin tunnisteluku
    private int tunniste;
    // teksti-attribuutti säilöö Dokumentin tekstin
    private String teksti;
   
    // Parametrillinen rakentaja, joka käyttää asettavia aksessoreita
    public Dokumentti(int id, String uusiTeksti) throws IllegalArgumentException {
        tunniste(id);
        teksti(uusiTeksti);
    }
    
    // Luetaan tunniste
    public int tunniste() {
        return tunniste; 
    }
    // Luetaan teksti
    public String teksti() {
        return teksti;
    }
    
    // Aksessorit heittävät 
    // poikkeuksen, jos parametrina on virheellinen arvo
    public void tunniste(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Error"); 
        }
        else {
            tunniste = id;
        }      
    }
    public void teksti(String uusiTeksti) throws IllegalArgumentException {
        if (uusiTeksti == null || uusiTeksti.length() <= 0) {
            throw new IllegalArgumentException("Error");
        }
        else {
            teksti = uusiTeksti;
        }
    }
    
    // Object-luokan toString-metodi
    @Override
    public String toString() {
        // Palautetaan tunniste ja teksti ///-erotettuna
        return tunniste +  "///" + teksti ;
    }
    
    // Object-luokan equals-metodi, joka katsaa dokumentit 
    // samoiksi tunnisteiden perusteella
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
    
    // Comparable<T>-rajapinnan compareTo-metodi, joka vertaa tunnisteita
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
     *
     * @param hakusanat
     * 
     * @throws IllegalArgumentException
     */
    //SanatTäsmäävät-metodi
    @Override
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {
        if (hakusanat == null || hakusanat.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
            int laskuri = 0;
            for (int i = 0; i < hakusanat.size(); i = i + 1) {
                // Jos sana löytyy tekstistä, niin lisätään laskuriin + 1
                if (teksti.trim().contains(hakusanat.get(i))) {
                    //Tarkistetaan, onko annettu alkio kunnollinen sana vai esim. vain tavu
                    if (hakusanat.get(i).length() == 2) {
                        return false;
                    }
                    else if (hakusanat.get(i).charAt(2) == '.') {
                        return false;
                    }
                    else {
                        laskuri = laskuri + 1;
                    }
                }
            }
            // Kun on käyty hakusanalista läpi, katsotaan löytyikö jokainen listan sana tekstistä
            if (laskuri == hakusanat.size()) {
                return true;
            }
            else {
                return false;
            }
        }    
    } 
    
    /**
     *
     * @param sulkusanat
     * @param välimerkit
     * 
     * @throws IllegalArgumentException
     */
    // Siivoa-metodi
    @Override
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
        if (sulkusanat == null || sulkusanat.isEmpty() || välimerkit == null || välimerkit.length() < 1) {
            throw new IllegalArgumentException();
        }
         else {
            // Poistetaan tekstistä isot alkukirjaimet
            teksti = teksti.replace("[^a-zA-Z ]", "").toLowerCase();
            
            // Poistetaan välimerkit
            char[] ca = välimerkit.toCharArray();
            for (char c : ca) {
                teksti = teksti.replace(""+c, "");
            }
            
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
