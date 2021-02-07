
import harjoitustyo.toiminnot.Kayttoliittyma;

/*
 * Harjoitustyön ajoluokka, jossa kutsutaan Käyttöliittymä-luokkaa 
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 */
public class Oope2HT {
    public static void main(String[]args){
        // Luodaan käyttöliittymä
        Kayttoliittyma käyttöliittymä = new Kayttoliittyma();
        // Suoritetaan käyttöliittymä
        käyttöliittymä.suorita(args);
        
    }
}
