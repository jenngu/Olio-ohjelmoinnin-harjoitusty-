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
 *
 * @author jennifernguyen
 */
public class Kayttoliittyma {
    // Vakioidaan komennot
    public static final String ADD = "add";
    public static final String FIND = "find";
    public static final String PRINT = "print";
    public static final String REMOVE = "remove";
    public static final String ECHO = "echo";
    public static final String RESET = "polish";
    public static final String POLISH = "polish";
    public static final String QUIT = "quit";
    public static final String ERROR = "Error!";
    
    /**
     * 
     * @param args 
     */
    public void suorita(String args[]) {
        System.out.println("Welcome to L.O.T.");
        // Tarkistetaan komentoriviparametri
        if (args.length != 2) {
            System.out.println("Wrong number of command-line arguments!");
            System.out.println("Program terminated.");
        }
        else {
            Scanner lukija = new Scanner(System.in);
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
                    Scanner obj = new Scanner(System.in);
                    System.out.println("Please, enter a command:");
                    String komento = obj.nextLine();

                    // Toteutetaan kommennon kaiutus, jos echo = true;
                    if (echo == true) {
                        System.out.println(komento);
                    }
                    // Komennon katkaiseminen
                    String[] osat = komento.split(" ", 2);

                    // Ohjelman lopetus
                    if (komento.startsWith(QUIT) && komento.length() == 4) {
                        System.out.println("Program terminated.");
                        aja = false; 
                    }
                    // Dokumentin lisääminen kokoelmaan
                    else if (komento.startsWith(ADD) && osat.length == 2) {
                        osat = komento.split(" ", 2);
                        // Jos komento on oikeanlainen, niin kutsutaan Kokoelma-luokan lisää-metodia
                        if (kay(osat[1])) {
                            try {
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
                                }
                            }
                            catch (Exception e) {
                                System.out.println(ERROR);
                            }
                        }
                    }
                    // Kokoelmasta hakeminen
                    else if (komento.startsWith(FIND) && osat.length == 2) {
                        // Jos komento oikenalainen, kutsutaan kokoelma-luokan hae-metodia
                        osat = komento.split(" ", 2);
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
                                System.out.println(ERROR);    
                            }
                        }     
                    }
                    // Dokumenttien tulostaminen 
                    else if (komento.startsWith(PRINT) && osat.length > 1) {//(osat.length == 1 || osat.length == 2)) {
                        osat = komento.split(" ", 2);
                        if (kay(osat[0])) {
                            try {
                                int tunniste = Integer.parseInt(osat[1]);
                                if (kokoelma.hae(tunniste) != null) {
                                    System.out.println(kokoelma.hae(tunniste));
                                }
                                // Jos tunniste ei löydy kokoelmasta
                                else if (kokoelma.hae(tunniste) == null) {
                                    System.out.println(ERROR);
                                }
                                // Jos osat.length == 1, tulostetaan kaikki kokoelman dokumentit
                                else {
                                    for (int i = 0; i < kokoelma.dokumentit().size(); i = i + 1) {
                                        System.out.println(kokoelma.dokumentit().get(i));
                                    }
                                }
                            }   
                            catch (Exception e) {
                                System.out.println(ERROR);
                            }
                        }  
                    }
                    // Dokumentin poistaminen
                    else if (komento.startsWith(REMOVE) && osat.length == 2) {
                        if (kay(osat[0])) {
                            try {
                                // Muutetaan komennon toinen osa int-muotoon
                                int tunniste = Integer.parseInt(osat[1]);
                                if (kokoelma.hae(tunniste) != null){
                                    kokoelma.poista(tunniste);
                                }
                                else {
                                    System.out.println(ERROR);
                                }
                            }
                            catch (Exception e) {
                                System.out.println(ERROR);
                            }   
                        }
                    }       
                    // Kokoelman esikäsittely
                    else if (komento.startsWith(POLISH) && osat.length == 2) {
                        if (kay(osat[0])) {
                            // Kutsutaan Kokoelma-luokan siivoa-metodia
                            try {
                                String välimerkit = osat[1];
                                kokoelma.siivoa(sulkusanat ,välimerkit);
                            }
                            catch (Exception e) {
                                System.out.println(ERROR);
                            }
                        }
                    }     
                    // Kokoelman muutosten peruminen
                     else if (komento.startsWith(RESET) && komento.length() == 5) { 
                        // Ladataan alkuperäinen tiedosto uudelleen
                        kokoelma = new Kokoelma();
                        kokoelma.lataaTiedosto(args);
                    } 
                    // Tulosten kaiuttaminen        
                    else if (komento.startsWith(ECHO)) {
                        if (echo == false) {
                            echo = true;
                            System.out.println(ECHO);
                        }
                        // Jos kauitus päällä, kytketään se pois
                        else {
                            echo = false;
                        }  
                    } 
                    // Komento on jokin muu
                    else {
                        System.out.println(ERROR);
                    }  
                }
            } 
        }
    }    
    /**
     * 
     * @param osa
     * @return
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
