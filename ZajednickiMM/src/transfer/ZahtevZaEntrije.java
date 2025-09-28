/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import domen.Groups;
import domen.Users;
import java.io.Serializable;

/**
 *
 * @author Marija
 */
public class ZahtevZaEntrije implements Serializable{
    private Groups grupa;
    private Users korisnik;

    public ZahtevZaEntrije() {
    }

    public ZahtevZaEntrije(Groups grupa, Users korisnik) {
        this.grupa = grupa;
        this.korisnik = korisnik;
    }

    public Groups getGrupa() {
        return grupa;
    }

    public void setGrupa(Groups grupa) {
        this.grupa = grupa;
    }

    public Users getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Users korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
