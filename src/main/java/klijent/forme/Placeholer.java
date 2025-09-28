/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klijent.forme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author Marija
 */
public class Placeholer {

    public static void dodajPlaceholder(JTextField polje, String text) {
        polje.setText(text);
        polje.setForeground(Color.GRAY);

        polje.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (polje.getText().equals(text)) {
                    polje.setText("");
                    polje.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (polje.getText().isEmpty()) {
                    polje.setText(text);
                    polje.setForeground(Color.GRAY);
                }
            }


        });
    }

    public static void dodajPlaceholder(JPasswordField polje, String text) {
        polje.setEchoChar((char) 0);
        polje.setText(text);
        polje.setForeground(Color.GRAY);

        polje.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(polje.getPassword()).equals(text)) {
                    polje.setText("");
                    polje.setForeground(Color.BLACK);
                    polje.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (polje.getPassword().length == 0) {
                    polje.setForeground(Color.GRAY);
                    polje.setText(text);
                    polje.setEchoChar((char) 0);
                }
            }


        });
    }

}
