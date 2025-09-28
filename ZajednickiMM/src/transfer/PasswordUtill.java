/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Marija
 */
public class PasswordUtill implements Serializable{
    
      public static String hesujLozinku(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean proveriLozinku(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
