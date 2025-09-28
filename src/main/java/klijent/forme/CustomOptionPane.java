package klijent.forme;


import javax.swing.*;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marija
 */
public class CustomOptionPane {

    Color svetloRoze = new Color(255, 198, 224);
    Font font = new Font("Arial Black", Font.BOLD, 18);

    public CustomOptionPane() {
        //UIManager.put("control", svetloRoze);
        //UIManager.put("info", svetloRoze);
        UIManager.put("nimbusBase", svetloRoze);
        //UIManager.put("nimbusBlueGrey", svetloRoze);
        //UIManager.put("nimbusLightBackground", svetloRoze);

        UIManager.put("OptionPane.background", svetloRoze);
        UIManager.put("Panel.background", svetloRoze);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
    }


}
