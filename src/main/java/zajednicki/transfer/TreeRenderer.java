/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zajednicki.transfer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 *
 * @author Marija
 */
public class TreeRenderer extends DefaultTreeCellRenderer {
    private final Icon folderIcon;
    private final Icon fileIcon;

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

        if (node.getAllowsChildren()) {
            setIcon(folderIcon); // root i cvorovi kao folderi
        } else {
            setIcon(fileIcon);   // leaf čvorovi (eventualni fajlovi)
        }
        return this;
    }

}
