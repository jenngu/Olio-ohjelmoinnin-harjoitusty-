/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.toiminnot;

import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.kokoelma.Kokoelma;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Käyttöliittymä-luokka, jossa luetaan käyttäjältä komentoja koskien dokumentteja. 
 * Komentojen perusteella kutsutaan Kokoelma-luokkaa ja toteutetaan komentojen käskyt
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public class Kayttoliittyma { 
    /**
     * Metodi, joka suorittaa komentojen kyselyn ja niiden suorittamisen
     * @param args komentoriviparametrit
     */
    public void suorita(String args[]) {
        System.out.println("Welcome to L.O.T.");
        // Tarkistetaan komentoriviparametri
        if (args.length != 2) {
            System.out.println("Wrong number of command-line arguments!");
            System.out.println("Program terminated.");
        }
        else {
            Scanner obj = new Scanner(System.in);
            // Alustetaan echo
            boolean echo = false;
            // Alustetaan lippumuuttuja
            boolean aja = true;
            Kokoelma kokoelma = new Kokoelma();
            // Kutsutaan tiedostonlataus- ja sulkusana- metodeja
            boolean lataa = kokoelma.lataaTiedosto(args);
            LinkedList<String> sulkusanat = kokoelma.lataaSulkusanat(args);
            
            // Tarkitetaan, että kokoelman ja sulkusanalistan lataus on onnistunut
            if (lataa == false || sulkusanat == null) {
                System.out.println("Missing file!");
                System.out.println("Program terminated.");
                aja = false;
            }
            else {
                while (aja) {
                    System.out.println("Please, enter a command:");
                    String komento = obj.nextLine();
                    // Komennon katkaiseminen
                    String[] komentosa = komento.split(" ");
                    // Toteutetaan kommennon kaiutus, jos echo = true;
                    if (echo == true) {
                        System.out.println(komento);
                    }

                    // Ohjelman lopetus
                    if (komento.startsWith("quit") && komento.length() == 4) {
                        System.out.println("Program terminated.");
                        aja = false; 
                    }
                    // Tulosten kaiuttaminen        
                    else if (komentosa[0].equals("echo")) {
                        if (echo == false) {
                            echo = true;
                            System.out.println("echo");
                        }
                        // Jos kauitus päällä, kytketään se pois
                        else {
                            echo = false;
                        }
                    }
                    // Dokumentin lisääminen kokoelmaan
                    else if (komentosa[0].equals("add")) {
                        try {
                            // Komennon katkaiseminen
                            String[] osat = komento.split(" ", 2);
                            // Pilkotaan dokumentin palat välimerkkien kohdalta
                            String[] dokupalat = osat[1].split("///");
                            // Muutetaan päivämäärä oikeean muotoon
                            DateTimeFormatter pvm = DateTimeFormatter.ofPattern("d.M.yyyy");
                            // Kutustaan Kokoelma-luokan lisää-metodia
                            if (args[0].split("_")[0].equals("jokes")) {
                                Vitsi uusivitsi = new Vitsi(Integer.parseInt(dokupalat[0]), 
                                dokupalat[1], dokupalat[2]);
                                kokoelma.lisää(uusivitsi); 
                            }                    
                            else if (args[0].split("_")[0].equals("news")) {
                                Uutinen uusiuutinen = new Uutinen(Integer.parseInt(dokupalat[0]), 
                                    LocalDate.parse(dokupalat[1], pvm), dokupalat[2]);
                                kokoelma.lisää(uusiuutinen);
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Error!");
                            }
                    }
                    // Kokoelmasta hakeminen
                    else if (komentosa[0].equals("find") && komentosa.length > 1) {
                        String[] osat = komento.split(" ");
                        // Jos komento oikenalainen, kutsutaan kokoelma-luokan hae-metodia
                        if (kay(osat[0])) {
                            try {
                                // Luodaan lista, johon talletetaan kaikki löydetyt samat tunnisteet
                                LinkedList<Integer> löydetyt = new LinkedList<Integer>();
                                löydetyt = kokoelma.etsi(osat);  
                                if (löydetyt.size() > 0) {
                                    for (int i = 0; i< löydetyt.size(); i = i + 1) {
                                        System.out.println(löydetyt.get(i));
                                    }
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Error!");    
                            }
                        }     
                    }
                    // Dokumenttien tulostaminen 
                    else if (komentosa[0].equals("print") && komentosa.length >= 1) {
                        String[] osat = komento.split(" ", 2);
                        if (kay(osat[0])) {
                            try {
                                // Jos komento on pelkkä print, tulostetaan koko dokumentti
                                if (komento.startsWith("print") && osat.length == 1) {
                                    for (int i = 0; i < kokoelma.dokumentit().size(); i = i + 1) {
                                        System.out.println(kokoelma.dokumentit().get(i));
                                    }
                                }
                                else if (komento.startsWith("print") && osat.length == 2) {
                                    int tunniste = Integer.parseInt(osat[1]);
                                    if (kokoelma.hae(tunniste) == null) {
                                        System.out.println("Error!");
                                    }
                                    else {
                                        System.out.println(kokoelma.hae(tunniste));
                                    }    
                                }
                            }   
                           catch (Exception e) {
                                System.out.println("Error!");
                            }
                        } 
                    }
                    // Dokumentin poistaminen
                    else if (komentosa[0].equals("remove") && komentosa.length == 2) {
                        String[] osat = komento.split(" ", 2);
                        if (kay(osat[0])) {
                            try {
                                // Muutetaan komennon toinen osa int-muotoon
                                int tunniste = Integer.parseInt(osat[1]);
                                if (kokoelma.hae(tunniste) != null){
                                    kokoelma.poista(tunniste);
                                }
                                else {
                                    System.out.println("Error!");
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Error!");
                            }   
                        }
                    }       
                    // Kokoelman esikäsittely
                    else if (komentosa[0].equals("polish") && komentosa.length == 2) {
                        String[] osat = komento.split(" ", 2);
                        if (kay(osat[0])) {
                            // Kutsutaan Kokoelma-luokan siivoa-metodia
                            try {
                                String välimerkit = osat[1];
                                kokoelma.siivoa(sulkusanat ,välimerkit);
                            }
                            catch (Exception e) {
                                System.out.println("Error!");
                            }
                        }
                    }     
                    // Kokoelman muutosten peruminen
                    else if (komentosa[0].equals("reset") && komentosa.length == 1) { 
                        // Ladataan alkuperäinen tiedosto uudelleen
                        kokoelma = new Kokoelma();
                        kokoelma.lataaTiedosto(args);
                    }  
                    // Komento on jokin muu
                    else {
                        System.out.println("Error!");
                    }  
                }
            } 
        }
    }    
    /**
     * Metodi tarkistaa, että käyttäjän antama komento on oikeanlainen
     * @param osa komento
     * @return true, jos komento käy, muuten false
     */
    // Tarkistetaan syötteen oikeellisuus
    public boolean kay(String osa) {
        if (osa.length() == 0) {
            return false;
        }
        else if (osa.charAt(0) == ' ') {
            return false;
        }
        else {
            return true;
            }      
    }
}

