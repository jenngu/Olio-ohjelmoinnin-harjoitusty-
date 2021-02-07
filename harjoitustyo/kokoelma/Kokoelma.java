/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.kokoelma;

import harjoitustyo.apulaiset.Kokoava;
import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.OmaLista;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Kokoelma-luokka, joka hallinnoi kokoelmia ja joka toteuttaa Kokoava-rajapinnan.
 * Luokka saa komentoja Kayttoliittyma-luokalta suoritettavaksi.
 * Se kutsuu Dokumentti- ja OmaLista-luokan metodeja avuksi.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public class Kokoelma implements Kokoava<Dokumentti> {
    // dokumentti-attribuutti, joka sisältää viitteet kokoelmaan kuuluviin dokumenttiolioihin
    private OmaLista<Dokumentti> dokumentit;
    
    /**
     * Oletusrakentaja, jossa dokumentit-attribuuttiin liitetään rakentajassa luotu tyhjä listaolio 
     */
    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }
    
    /**
    * Dokumentit lukeva aksessori
    * @return dokumentit 
    */
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }
  
    /**
     * Kokoava-rajapinnan lisää()-metodi, joka tekee virhekäsittelyn 
     * uudelle dokumentille.
     * Jos dokumentti kelpaa, niin kutsutaan OmaListan metodia
     * @param uusi dokumentti
     * @throws IllegalArgumentException jos parametri virheellinen
     */
    // Toteutetaan Kokoava-rajapinnan metodit
    // Kokoava-rajapinnan lisää()-metodi
    @Override
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        //System.out.println("lopp");
        //if (uusi instanceof Uutinen) {
          //  System.out.println("jee");
        //}
        //else {
          //  System.out.println("noup");
        //}
        //if (kokoelma.lataaTiedosto() instanceof Vitsi) {
          //  System.out.println("jee2");
        //}
        // Jos uusi dokumentti on null tai ei ole vertailtava, heitetään poikkeus
        if (uusi == null || !(uusi instanceof Comparable)) {
            throw new IllegalArgumentException("");
        }
        // Jos uusi dokumentti on jo kokoelmassa, heitetään poikkeus 
        else if (uusi.equals(dokumentit)){
           throw new IllegalArgumentException("");
        }  
        // Jos parametrista voidaan luoda Uutinen vaikka kokoelma koostuu vitseistä,
        // keskeytetään lisäys
        //else if ((uusi instanceof Uutinen) && (dokumentit instanceof Vitsi)) {
          //  throw new IllegalArgumentException("");
        //}   
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
    
    /**
     * Metodi poistaa halutun alkio tunnisteen perusteella.
     * Jos tunniste on kelvollinen, kutsutaan OmaLista-metodia
     * @param tunniste dokumentin tunniste
     * @throws IllegalArgumentException poikkeus, jos parametri virheellinen
     */
    public void poista(int tunniste) throws IllegalArgumentException {
       // Poistetaan tunnisteen perusteella dokumentti kokoelmasta
        if (tunniste <= 0) {
            throw new IllegalArgumentException("");
        }
        else {
            dokumentit.poista(hae(tunniste));
        }
    }
    
    /**
     * Kokoava-rajapinnan metodi, joka hakee dokumentin kokoelmasta, jonka tunniste on sama 
     * kuin annetun parametrin.
     * @param tunniste jota verrataan
     * @return dokumentti, joka löytyy tunnisteen perusteella
     * @throws IllegalArgumentException poikkeus, jos parametri virheellinen
     */
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
    
    /**
     * Metodi kutsuu Dokumentti-luokan siivoa-metodia
     * @param sulkusanat tiedostosta saadut
     * @param välimerkit käyttäjältä saadut
     * @throws IllegalArgumentException heitetään poikkeus, jos parametrit virheellisiä
     */
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) {
        for (Dokumentti dokumentti: dokumentit) {
            dokumentti.siivoa(sulkusanat, välimerkit);
        }
    }
    
     /**
     * Ladataan tiedosto linkitettyyn listaan, joka on komentoriviparametrissa
     * @param args komentoriviparametri
     * @return boolean arvo onnistuiko lataus
     */
    // Tiedoston lukeminen
    public boolean lataaTiedosto(String[] args) {
        // Alustetaan dokumentin tyyppi: Viesti tai Dokumentti
        String tyyppi = args[0]. split("_")[0];
        try {
            String rivi = "";
            Scanner lukija = new Scanner(new File(args[0]));
            while (lukija.hasNextLine()) {
                rivi = lukija.nextLine();
                // Pilkotaas paloiksi rivit
                String[] palat = rivi.split("///");
                // Jos tyyppi on vitsi tyyppi, luodaan Vitsi-olio ja liitetään se 
                // dokumentin attribuuttiin
                if (tyyppi.equals("jokes")) {
                    Vitsi vitsi = new Vitsi(Integer.parseInt(palat[0]), palat[1], palat[2]);
                    this.lisää(vitsi);
                }
                // Muuten luodaan Uutinen-olio ja tehdään sama homma
                else if (tyyppi.equals("news")) {
                    DateTimeFormatter päivä = DateTimeFormatter.ofPattern("d.M.yyyy");
                    LocalDate date = LocalDate.parse(palat[1], päivä);
                    Uutinen uutinen = new Uutinen(Integer.parseInt(palat[0]), date, palat[2]);
                    this.lisää(uutinen);
                }
            }
            lukija.close();
        }
        catch (Exception e) {
            return false;
        } 
        return true;
    }
    
    /**
     * Ladataan sulkusanalista linkitettyyn listaan, 
     * joka saadaan tekstitiedostona komentoriviparametrina
     * @param args komentoriviparametri
     * @return sulkusanalista
     */
    public LinkedList<String> lataaSulkusanat(String[] args) {
        LinkedList<String> sulkusanat = new LinkedList<>();
        try {
            Scanner inFile = new Scanner(new File(args[1]));
            String line = "";
            while (inFile.hasNext()) {
                line = inFile.next();
                sulkusanat.add(line);
            }
            inFile.close();
        }
        catch (Exception e) {
            return null;
        }  
        return sulkusanat;
    }

    /**
     * Metodilla toteutetaan find-komento, joka etsii hakusanan tai -sanojen perusteella
     * dokumentit, jossa nämä sanat esiintyvät 
     * @param osat hakusanat 
     * @return linkitetty lista dokumenttien tunnisteita, joissa sanat on löydetty
     */
    public LinkedList<Integer> etsi(String[] osat) {
        // Luodaan lista, johon kerätään löydetyt tunnisteet
        LinkedList<Integer> löydetyt = new LinkedList<Integer>();
        try {
            // Katkaistaan hakusanat
            
           // Luodaan lista hakusanoille
            LinkedList<String> hakusanat = new LinkedList<String>();
            // Käydään läpi komennon sanat itse komennon jälkeen ja lisätään ne listalle
            int i = 1;
            while (i < osat.length) {  
                hakusanat.add(osat[i]);
                i = i + 1;
            }
            // Kutsutaan Kokoelma-luokan SanatTäsmäävät-metodia
            for (Dokumentti dokumentti: dokumentit) {
                boolean tulos = dokumentti.sanatTäsmäävät(hakusanat);
                if (tulos == true) {
                    // Jos sanat täsmäsivät, niin lisätään tunnisteet
                    löydetyt.add(dokumentti.tunniste());
                }
            } 
        }
        catch (Exception e) {
            
        }
        return löydetyt;
    }
}  
    


