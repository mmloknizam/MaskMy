/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.baza;

import zajednicki.domen.Entries;
import zajednicki.domen.Groups;
import zajednicki.domen.Users;
import zajednicki.transfer.PasswordUtill;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class DBBroker {
    public ArrayList<Object> vrati() {
        ArrayList<Object> lista = new ArrayList<>();
        String upit = "";

        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean cuvajIzmeniBrisi() throws SQLException {
        String naredba = "";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Users singIn(String email, String password) {
        String upit = "SELECT *"
                + "FROM users";

        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                Users user = new Users(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), null, null);
                if (user.getEmail().equals(email) && PasswordUtill.proveriLozinku(password, user.getPassword())) {
                    return user;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean singUp(Users u) throws SQLException {
        String naredba = "INSERT INTO users(first_name, last_name, email, password)\n"
                + "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            if (!daLiPostojiEmail(u)) {
                ps.setString(1, u.getFirstName());
                ps.setString(2, u.getLastName());
                ps.setString(3, u.getEmail());
                ps.setString(4, u.getPassword());

                ps.executeUpdate();
                Konekcija.getInstance().getConnection().commit();

                return true;
            }


        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    /*
        public ArrayList<String> vratiSveEmailove(){
        ArrayList<String>lista=new ArrayList<>();
        String upit="SELECT email\n"
                + "FROM users";
        
        try {
            Statement st=(Statement) Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            while (rs.next()) {                
                lista.add(rs.getString("email"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    } 
*/

    public boolean daLiPostojiEmail(Users u) {
        String upit = "SELECT COUNT(*)\n"
                + "FROM users\n"
                + "WHERE email=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, u.getEmail());
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean createGroup(Groups g) throws SQLException {
        String naredba = "INSERT INTO groups(user_id, name)\n"
                + "VALUES(?, ?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            if (!daLiPostojiGrupa(g)) {
                ps.setLong(1, g.getUsers().getUserID());
                ps.setString(2, g.getName());

                ps.executeUpdate();
                Konekcija.getInstance().getConnection().commit();

                return true;
            }


        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private boolean daLiPostojiGrupa(Groups g) {
        String upit = "SELECT COUNT(*)\n"
                + "FROM groups\n"
                + "WHERE name=? AND user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, g.getName().trim());
            ps.setLong(2, g.getUsers().getUserID());
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Groups> vratiGrupeZaKorisnika(Users korisnik) {
        ArrayList<Groups> lista = new ArrayList<>();
        String upit = "SELECT *\n" +
                "FROM groups\n" +
                "WHERE user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, korisnik.getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Groups g = new Groups(rs.getLong("group_id"), korisnik, rs.getString("name"), null);
                lista.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean createEntry(Entries e) throws SQLException {
        String naredba = "INSERT INTO entries( group_id, user_id,title, username, password, url, note)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);


            if (e.getGroups() != null) {
                ps.setLong(1, e.getGroups().getGroupID());
            } else {
                ps.setNull(1, Types.BIGINT);
            }

            ps.setLong(2, e.getUsers().getUserID());
            ps.setString(3, e.getTitle());
            ps.setString(4, e.getUsername());
            ps.setString(5, e.getPassword());
            ps.setString(6, e.getUrl());
            ps.setString(7, e.getNote());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<Entries> vratiEntrijeZaGrupu(Groups grupa, Users kor) {
        ArrayList<Entries> listaEnrijaG = new ArrayList<>();
        String upit = "SELECT *\n"
                + "FROM entries\n"
                + "WHERE group_id=? AND user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, grupa.getGroupID());
            ps.setLong(2, kor.getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entries e = new Entries(rs.getLong("entry_id"), grupa, kor, rs.getString("title"), rs.getString("username"), rs.getString("password"), rs.getString("url"), rs.getString("note"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
                listaEnrijaG.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEnrijaG;
    }

    public ArrayList<Entries> vratiEntrijeBezGrupa(Users k) {
        ArrayList<Entries> listaEnrijaG = new ArrayList<>();
        String upit = "SELECT *\n"
                + "FROM entries\n"
                + "WHERE user_id=? AND group_id IS null";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, k.getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entries e = new Entries(rs.getLong("entry_id"), null, k, rs.getString("title"), rs.getString("username"), rs.getString("password"), rs.getString("url"), rs.getString("note"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
                listaEnrijaG.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEnrijaG;
    }

    /*
    public ArrayList<Entries> vratiSveEntrije(Users userE) {
         ArrayList<Entries>lista=new ArrayList<>();
        String upit="SELECT entry_id, group_id, user_id, title, username, password, url, note, created_at, updated_at\n"
                  + "FROM entries\n"
                  + "WHERE user_id=?";
        
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, userE.getUserID());
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) { 
                Groups grupa=null;
                if(rs.getObject("group_id")!=null){
                    grupa=new Groups();
                    grupa.setGroupID(rs.getLong("group_id"));
                }
                Entries e=new Entries(rs.getLong("entry_id"), grupa, userE, rs.getString("title"), rs.getString("username"), rs.getString("password"), rs.getString("url"),rs.getString("note"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));
                lista.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
*/

    public boolean obrisiEntry(Entries selektovaniEntry) throws SQLException {
        String naredba;
        if (selektovaniEntry.getGroups() != null) {
            naredba = "DELETE\n"
                    + "FROM entries\n"
                    + "WHERE entry_id=? AND group_id=? AND user_id=?";
        } else {
            naredba = "DELETE\n"
                    + "FROM entries\n"
                    + "WHERE  entry_id=? AND user_id=? AND group_id IS NULL";
        }

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            if (selektovaniEntry.getGroups() != null) {
                ps.setLong(1, selektovaniEntry.getEntryID());
                ps.setLong(2, selektovaniEntry.getGroups().getGroupID());
                ps.setLong(3, selektovaniEntry.getUsers().getUserID());
            } else {
                ps.setLong(1, selektovaniEntry.getEntryID());
                ps.setLong(2, selektovaniEntry.getUsers().getUserID());
            }

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean obrisiGrupu(Groups grupaGrupa, Users korisnikGrupa) throws SQLException {
        String naredba = "DELETE\n"
                + "FROM groups\n"
                + "WHERE group_id=? and user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setLong(1, grupaGrupa.getGroupID());
            ps.setLong(2, korisnikGrupa.getUserID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean izmeniGrupu(Groups selektovanaGrupa) throws SQLException {
        if (daLiPostojiGrupa(selektovanaGrupa)) {
            return false;
        }

        String naredba = "UPDATE groups\n"
                + "SET name=?\n"
                + "WHERE user_id=? AND group_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setString(1, selektovanaGrupa.getName());
            ps.setLong(2, selektovanaGrupa.getUsers().getUserID());
            ps.setLong(3, selektovanaGrupa.getGroupID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean izmeniEntry(Entries selektovanEntry) throws SQLException {
        String naredba = "UPDATE entries\n"
                + "SET username=?, password=?, url=?, note=?\n"
                + "WHERE entry_id=? AND user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setString(1, selektovanEntry.getUsername());
            ps.setString(2, selektovanEntry.getPassword());
            ps.setString(3, selektovanEntry.getUrl());
            ps.setString(4, selektovanEntry.getNote());
            ps.setLong(5, selektovanEntry.getEntryID());
            ps.setLong(6, selektovanEntry.getUsers().getUserID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean vratiPassword(Users userPassword) throws SQLException {
        String upit = "SELECT password\n"
                + "FROM users\n"
                + "WHERE user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, userPassword.getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String lozinkaIzBaze = rs.getString("password");
                if (PasswordUtill.proveriLozinku(userPassword.getPassword(), lozinkaIzBaze)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean izmeniPassword(Users userP) throws SQLException {
        String naredba = "UPDATE users\n"
                + "SET password=?\n"
                + "WHERE user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setString(1, userP.getPassword());
            ps.setLong(2, userP.getUserID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean obrisiNalog(Users userDelete) throws SQLException {
        if (!podaciIspravni(userDelete)) {
            return false;
        }

        String naredba = "DELETE \n"
                + "FROM users\n"
                + "WHERE user_id=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.setLong(1, userDelete.getUserID());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            return true;


        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private boolean podaciIspravni(Users userDelete) {
        String upit = "SELECT password\n"
                + "FROM users\n"
                + "WHERE user_id=? AND email=?";

        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setLong(1, userDelete.getUserID());
            ps.setString(2, userDelete.getEmail());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String lozinkaIzBaze = rs.getString("password");
                if (PasswordUtill.proveriLozinku(userDelete.getPassword(), lozinkaIzBaze)) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
