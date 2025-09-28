/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class ModelTableServer extends AbstractTableModel{
    ArrayList<Object>lista;
    String[] kolone={};
    
    public ModelTableServer(){
        lista=new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }
   

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object stavkaListe=lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return "";
            default:
                return "return!";
        }
    }
    
}
