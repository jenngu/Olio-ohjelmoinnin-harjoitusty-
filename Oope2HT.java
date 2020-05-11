
import harjoitustyo.toiminnot.Kayttoliittyma;

/*
 * Harjoitustyön ajoluokka, jossa kutsutaan Käyttöliittymä-luokkaa 
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, 2020
 * <p>  
 * @author jennifernguyen, (jennifer.nguyen@tuni.fi)
 * Informaatioteknologian ja viestinnän tiedekunta,
 * Tampereen yliopisto
 */
public class Oope2HT {
    public static void main(String[]args){
        // Luodaan käyttöliittymä
        Kayttoliittyma käyttöliittymä = new Kayttoliittyma();
        // Suoritetaan käyttöliittymä
        käyttöliittymä.suorita(args);
        
    }
}
