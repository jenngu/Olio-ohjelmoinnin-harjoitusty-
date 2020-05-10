
import harjoitustyo.toiminnot.Kayttoliittyma;

/*
 * Oopen ajoluokka, jossa kutsutaan Käyttöliittymä-luokkaa 
 * <p>
 * @author jennifernguyen
 */
public class Oope2HT {
    public static void main(String[]args){
        // Luodaan käyttöliittymä
        Kayttoliittyma käyttöliittymä = new Kayttoliittyma();
        // Suoritetaan käyttöliittymä
        käyttöliittymä.suorita(args);
        
    }
}
