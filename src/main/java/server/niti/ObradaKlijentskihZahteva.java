/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.niti;

import server.logika.Kontroler;
import zajednicki.domen.Entries;
import zajednicki.domen.Groups;
import zajednicki.domen.Users;
import zajednicki.konstante.Operacije;
import zajednicki.transfer.KlijentskiZahtev;
import zajednicki.transfer.ServerskiOdgovor;
import zajednicki.transfer.ZahtevZaEntrije;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class ObradaKlijentskihZahteva extends Thread {
    private final Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch (kz.getOperacija()) {
                case Operacije.SINGIN:
                    HashMap<Integer, String> mapa = (HashMap<Integer, String>) kz.getParametar();
                    String email = mapa.get(1);
                    String password = mapa.get(2);
                    Users user = Kontroler.getInstance().singIn(email, password);
                    so.setOdgovor(user);
                    break;
                case Operacije.SINGUP:
                    Users u = (Users) kz.getParametar();
                    boolean uspesnoRegistrovan = Kontroler.getInstance().singUp(u);
                    so.setOdgovor(uspesnoRegistrovan);
                    break;
                case Operacije.SINGOUT:
                    long userID = (long) kz.getParametar();
                    boolean uspesnoOdjavljen = Kontroler.getInstance().singOut(userID);
                    so.setOdgovor(uspesnoOdjavljen);
                    break;
                case Operacije.CREATE_GROUP:
                    Groups g = (Groups) kz.getParametar();
                    boolean uspesnoKreiranaGrupa = Kontroler.getInstance().createGroup(g);
                    so.setOdgovor(uspesnoKreiranaGrupa);
                    break;
                case Operacije.VRATI_GRUPE:
                    Users korisnik = (Users) kz.getParametar();
                    ArrayList<Groups> grupe = Kontroler.getInstance().vratiGrupeZaKorisnika(korisnik);
                    so.setOdgovor(grupe);
                    break;
                case Operacije.CREATE_ENTRY:
                    Entries e = (Entries) kz.getParametar();
                    boolean uspesnoKreiranEntry = Kontroler.getInstance().createEntry(e);
                    so.setOdgovor(uspesnoKreiranEntry);
                    break;
                case Operacije.VRATI_ENTRIJE_ZA_GRUPU:
                    ZahtevZaEntrije zahtev = (ZahtevZaEntrije) kz.getParametar();
                    Groups grupa = zahtev.getGrupa();
                    Users kor = zahtev.getKorisnik();
                    ArrayList<Entries> entriesG = Kontroler.getInstance().vratiEntrijeZaGrupe(grupa, kor);
                    so.setOdgovor(entriesG);
                    break;
                case Operacije.VRATI_ENTRIJE_BEZ_GRUPA:
                    Users k = (Users) kz.getParametar();
                    ArrayList<Entries> entries = Kontroler.getInstance().vratiEntrijeBezGrupa(k);
                    so.setOdgovor(entries);
                    break; 
                /*case Operacije.VRATI_ENTRIJE:
                    Users userE=(Users) kz.getParametar();
                    ArrayList<Entries> sviEntriji=Kontroler.getInstance().vratiSveEntrije(userE);
                    so.setOdgovor(sviEntriji);
                    break; */
                case Operacije.OBRISI_ENTRY:
                    Entries selektovaniEntry = (Entries) kz.getParametar();
                    boolean uspesnoObrisanEntry = Kontroler.getInstance().obrisiEntry(selektovaniEntry);
                    so.setOdgovor(uspesnoObrisanEntry);
                    break;
                case Operacije.OBRISI_GRUPU:
                    ZahtevZaEntrije zahtevZaGrupu = (ZahtevZaEntrije) kz.getParametar();
                    Groups grupaGrupa = zahtevZaGrupu.getGrupa();
                    Users korisnikGrupa = zahtevZaGrupu.getKorisnik();
                    boolean uspesnoObrisanaGrupa = Kontroler.getInstance().obrisiGrupu(grupaGrupa, korisnikGrupa);
                    so.setOdgovor(uspesnoObrisanaGrupa);
                    break;
                case Operacije.IZMENI_GRUPU:
                    Groups selektovanaGrupa = (Groups) kz.getParametar();
                    boolean uspesnoIzmenjenaGrupa = Kontroler.getInstance().izmeniGrupu(selektovanaGrupa);
                    so.setOdgovor(uspesnoIzmenjenaGrupa);
                    break;
                case Operacije.IZMENI_UNOS:
                    Entries selektovanEntry = (Entries) kz.getParametar();
                    boolean uspesnoIzmenjenEntry = Kontroler.getInstance().izmeniEntry(selektovanEntry);
                    so.setOdgovor(uspesnoIzmenjenEntry);
                    break;
                case Operacije.VRATI_PASSWORD:
                    Users userPassword = (Users) kz.getParametar();
                    boolean ispravanPassword = Kontroler.getInstance().vratiPassword(userPassword);
                    so.setOdgovor(ispravanPassword);
                    break;
                case Operacije.IZMENI_PASSWORD:
                    Users userP = (Users) kz.getParametar();
                    boolean uspesnoIzmenjenPassword = Kontroler.getInstance().izmeniPassword(userP);
                    so.setOdgovor(uspesnoIzmenjenPassword);
                    break;
                case Operacije.OBRISI_KORISNIKA:
                    Users userDelete = (Users) kz.getParametar();
                    boolean uspesnoObrisanNalog = Kontroler.getInstance().obrisiNalog(userDelete);
                    so.setOdgovor(uspesnoObrisanNalog);
                    break;
            }
            posaljiOdgovor(so);
        }
    }

    public KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
