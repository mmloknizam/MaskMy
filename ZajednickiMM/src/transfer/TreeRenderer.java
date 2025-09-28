/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Marija
 */
public class TreeRenderer extends DefaultTreeCellRenderer{
        private Icon folderIcon;
        private Icon fileIcon;

    public TreeRenderer() {
        folderIcon = UIManager.getIcon("FileView.directoryIcon");
        fileIcon = UIManager.getIcon("FileView.fileIcon");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);    
              //tree - JTree koji renderuje cvor
              //value - cvor koji se crte
              //selected - da li je cvor selektovan
              //expanded - da li je cvor otvoren(folder otvoren)
              //leaf - da li je cvor leaf(nema dece)
              //row - redni broj cvora
              //hashFocus - da li cvor ima fokus
              
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        if (node.getAllowsChildren()==true) {
            setIcon(folderIcon); // root i cvorovi kao folderi
        } else {
            setIcon(fileIcon);   // leaf čvorovi (eventualni fajlovi)
        }
        return this;
    }      
        
}
