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
public class ModelTableClient extends AbstractTableModel{
    ArrayList<Object>list;
    String[] column={};
    
    public ModelTableClient(){
        list=new ArrayList<>();
    }
    
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
        Object listItem=list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return "";
            default:
                return "return!";
        }
    }
    
}
