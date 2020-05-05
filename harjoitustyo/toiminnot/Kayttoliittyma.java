/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harjoitustyo.toiminnot;

import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import java.util.Scanner;
import harjoitustyo.kokoelma.Kokoelma;
import harjoitustyo.omalista.OmaLista;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author jennifernguyen
 */
public class Kayttoliittyma {
    private Kokoelma kokoelma;
    // Vakioidaan komennot
    public static final String ADD = "add";
    public static final String FIND = "find";
    public static final String PRINT = "print";
    public static final String REMOVE = "remove";
    public static final String ECHO = "echo";
    public static final String RESET = "polish";
    public static final String POLISH = "polish";
    public static final String QUIT = "quit";
    
    // Ladataan tiedosto
    //public void tiedosto() {
        
    //}
    public void suorita() {
        System.out.println("Welcome to L.O.T.");
        boolean aja = true; 
        
        /*while (aja) {
            Scanner obj = new Scanner(System.in);
            System.out.println("Please, enter a command:");
            String komento = obj.nextLine();
            // Komennon katkaiseminen
            String[] osat;
            if (komento.startsWith(ECHO)) {
                System.out.println(ECHO);
                boolean lippu = true;
                while (lippu) {
                    // Tehdään erillinen kysely
                    Scanner myobj = new Scanner(System.in);
                    System.out.println("");
                    String toistokomento = obj.nextLine();
                    if (toistokomento.startsWith(ECHO)) {
                        lippu = false;
                    }
                    else {
                        System.out.println(toistokomento);
                        // ja pitäisi suorittaa kaikki komennot luupin sisällä
                    } 
               }
            }
            // Dokumentin lisääminen kokoelmaan
            if (komento.startsWith(ADD)) {
                osat = komento.split(" ", 2);
                if (kay(osat[1])) {
                    Kokoelma uusi = new Kokoelma();
                    uusi.lisää(osat[2]);
                }
                else {
                    System.out.println("Error!");
                }
            }
            // Kokoelmasta hakeminen
            else if (komento.startsWith(FIND)) {
                // Kutsutaan kokoelma-luokan hae-metodia
                osat = komento.split(" ", 2);
                if (kay(osat[1])) {
                    int i = 0;
                    // Käydään kaikki dokumentit läpi
                    // Jos löytyy osuva, niin tulostetaan se
                    while (i < dokumentit.size()) {
                        if (osat[2].tunniste() == dokumentit.get(i).tunniste()) {
                            System.out.print(dokumentit.get(i).tunniste());
                        }
                        i = i + 1;
                    }
                }    
            }
            // Dokumenttien tulostaminen 
            else if (komento.startsWith(PRINT)) {
                osat = komento.split(" ", 2);
                // Tulostetaan tunnisteen perusteella dokumentin sisältö
                int i = 0;
                boolean eilöydy = true;
                while (eilöydy && i < dokumentit.lenght()) {
                    if (osat[2] == dokumentit.get(i).tunniste()) {
                        System.out.print(dokumentti(i));
                        eilöydy = false;
                    }
                    i = i + 1;
                }
                if (eilöydy = true) {
                    System.out.println("Error!");
                }
            }
            // Dokumentin poistaminen
            else if (komento.startsWith(REMOVE)) {
                osat = komento.split(" ", 2);
                int i = 0;
                boolean eilöydy = true;
                while (eilöydy && i < dokumentit.lenght()) {
                    if (osat[2] == dokumentit.get(i).tunniste()) {
                        // Poisto!
                        eilöydy = false;
                    }
                }
                if (eilöydy = true) {
                    System.out.println("Error!");
                }
            }
            // Ohjelman lopetus
            else if (komento.startsWith(QUIT) && komento.length() == 4) {
                aja = false; 
                System.out.println("Program terminated.");
            }
            // Komento on jokin muu
            else {
                System.out.println("Error!");
            }   
        }*/
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
