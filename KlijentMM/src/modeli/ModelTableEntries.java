/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Entries;
import domen.Groups;
import domen.Users;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import konstante.Operacije;
import kontroler.Komunikacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.ZahtevZaEntrije;


/**
 *
 * @author Marija
 */
public class ModelTableEntries extends AbstractTableModel{
    ArrayList<Entries>list;
    String[] column={"Title", "Username", "Password", "Url", "Note"};
    Users ulogovani;
    
    public ModelTableEntries(Users ulogovani, ArrayList<Entries> list){
        this.ulogovani=ulogovani;
        this.list=list;
    }
    
    /*
    public ModelTableEntries(Users ulogovani){
        this.ulogovani=ulogovani;
        KlijentskiZahtev kz=new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_ENTRIJE);
        kz.setParametar(ulogovani);
        
        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so=Komunikacija.getInstance().primiOdgovor();
        
        list=(ArrayList<Entries>) so.getOdgovor();
    }
    */
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }
   

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entries listItem=list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return listItem.getTitle();
            case 1: 
                return listItem.getUsername();
            case 2:
                return listItem.getPassword();
            case 3:
                return listItem.getUrl();
            case 4:
                return listItem.getNote();
            default:
                return "return!";
        }
    }

    public void prikaziSelektovanEntry(Entries entry){
        list.clear();
        list.add(entry);
        
        fireTableDataChanged();
    }
    
    public void  ocistiTabelu(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void prikaziEntrijeZaGrupu(Groups group){
        list.clear();
        
        KlijentskiZahtev kz=new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_ENTRIJE_ZA_GRUPU);
        kz.setParametar(new ZahtevZaEntrije(group, ulogovani));
        
        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so=Komunikacija.getInstance().primiOdgovor();
        
        list=(ArrayList<Entries>) so.getOdgovor();
        
        fireTableDataChanged();
    }
}
