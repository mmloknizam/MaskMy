/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klijent.modeli;

import klijent.kontroler.Komunikacija;
import zajednicki.domen.Entries;
import zajednicki.domen.Groups;
import zajednicki.domen.Users;
import zajednicki.konstante.Operacije;
import zajednicki.transfer.KlijentskiZahtev;
import zajednicki.transfer.ServerskiOdgovor;
import zajednicki.transfer.ZahtevZaEntrije;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


/**
 *
 * @author Marija
 */
public class ModelTableEntries extends AbstractTableModel {
    ArrayList<zajednicki.domen.Entries> list;
    String[] column = {"Title", "Username", "Password", "Url", "Note"};
    zajednicki.domen.Users ulogovani;

    public ModelTableEntries(Users ulogovani, ArrayList<Entries> list) {
        this.ulogovani = ulogovani;
        this.list = list;
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
        Entries listItem = list.get(rowIndex);

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

    public void prikaziSelektovanEntry(Entries entry) {
        list.clear();
        list.add(entry);

        fireTableDataChanged();
    }

    public void ocistiTabelu() {
        list.clear();
        fireTableDataChanged();
    }

    public void prikaziEntrijeZaGrupu(Groups group) {
        list.clear();

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_ENTRIJE_ZA_GRUPU);
        kz.setParametar(new ZahtevZaEntrije(group, ulogovani));

        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

        list = (ArrayList<Entries>) so.getOdgovor();

        fireTableDataChanged();
    }
}
