/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import baza.DBBroker;
import domen.Entries;
import domen.Groups;
import domen.Users;
import forme.ServerskaForma;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class Kontroler {
   private static Kontroler instance;
   private DBBroker dbb;
   private  ServerskaForma sf;
   private ArrayList<Users> ulogovani=new ArrayList<>();
   
   private Kontroler(){
       dbb=new DBBroker();
   }

    public static Kontroler getInstance() {
        if(instance==null){
            instance=new Kontroler();
        }
        return instance;
    }

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public Users singIn(String email, String password) {
        Users user=dbb.singIn(email, password);
        
        if(user!=null){
            if(ulogovani.contains(user)){
                user.setUserID(-1);
                return user;
            }
            ulogovani.add(user);
        }
        return user;
    }

    public boolean singUp(Users u) {
       try {
           boolean uspesnoRegistrovan=dbb.singUp(u);
           
           return uspesnoRegistrovan;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean singOut(long userID) {
        for(int i=0; i<ulogovani.size(); i++){
            if(ulogovani.get(i).getUserID()==userID){
                ulogovani.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean createGroup(Groups g) {
       try {
           boolean uspesnoKreiranaGrupa=dbb.createGroup(g);
           
           return uspesnoKreiranaGrupa;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public ArrayList<Groups> vratiGrupeZaKorisnika(Users korisnik) {
        ArrayList<Groups> grupe=dbb.vratiGrupeZaKorisnika(korisnik);
        return grupe;
    }

    public boolean createEntry(Entries e) {
       try {
           boolean uspesnoKreiranEntry=dbb.createEntry(e);
           
           return uspesnoKreiranEntry;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public ArrayList<Entries> vratiEntrijeZaGrupe(Groups grupa, Users kor) {
        ArrayList<Entries> entriesG=dbb.vratiEntrijeZaGrupu(grupa, kor);
        
        return entriesG;
    }

    public ArrayList<Entries> vratiEntrijeBezGrupa(Users k) {
        ArrayList<Entries> entries=dbb.vratiEntrijeBezGrupa(k);
        
        return entries;
    }

    /*
    public ArrayList<Entries> vratiSveEntrije(Users userE) {
        ArrayList<Entries> sviEntriji=dbb.vratiSveEntrije(userE);
        
        return sviEntriji;
    }
*/

    public boolean obrisiEntry(Entries selektovaniEntry) {
       try {
           boolean uspesnoObrisanEntry=dbb.obrisiEntry(selektovaniEntry);
           
           return uspesnoObrisanEntry;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean obrisiGrupu(Groups grupaGrupa, Users korisnikGrupa) {
       try {
           boolean uspesnoObrisanaGrupa=dbb.obrisiGrupu(grupaGrupa, korisnikGrupa);
           
           return uspesnoObrisanaGrupa;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean izmeniGrupu(Groups selektovanaGrupa) {
       try {
           boolean uspesnoIzmenjenaGrupa=dbb.izmeniGrupu(selektovanaGrupa);
           
           return uspesnoIzmenjenaGrupa;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean izmeniEntry(Entries selektovanEntry) {
       try {
           boolean uspesnoIzmenjenEntry=dbb.izmeniEntry(selektovanEntry);
           
           return uspesnoIzmenjenEntry;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean vratiPassword(Users userPassword) {
       try {
           boolean ispravanPassword=dbb.vratiPassword(userPassword);
           
           return ispravanPassword;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean izmeniPassword(Users userP) {
       try {
           boolean uspesnoIzmenjenPassword=dbb.izmeniPassword(userP);
           
           return uspesnoIzmenjenPassword;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }

    public boolean obrisiNalog(Users userDelete) {
       try {
           boolean uspesnoObrisanNalog=dbb.obrisiNalog(userDelete);
           
           return uspesnoObrisanNalog;
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }


}
