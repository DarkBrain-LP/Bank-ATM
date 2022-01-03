/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class Requete {
    Connection con;
    
    public void afficher(String requete){
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/banque", "root", "");
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet result = pst.executeQuery();
            ResultSetMetaData metaResult = result.getMetaData();
            
            System.out.println("\n***************************************************************************");
            for(int i = 1; i <= metaResult.getColumnCount(); i++){
                System.out.print("|\t" + metaResult.getColumnName(i) + "|");
            }
            
            System.out.println("\n***************************************************************************");
            while(result.next()){
                for(int i = 1; i <= metaResult.getColumnCount(); i++){
                    System.out.print("\t" + result.getObject(i).toString() + "\t |");
                }
                System.out.println("\n***************************************************************************");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Banque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkNumCarte(int num, String pwd) {
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/banque","root", "");
            PreparedStatement pst = this.con.prepareStatement("SELECT Num_compte, Password_compte FROM Compte "
                    + "WHERE Num_compte=" + num);
            ResultSet result = pst.executeQuery();
            while(result.next()){
                if(result.getString("Password_compte").equals(pwd))
                    return true;
                else
                    JOptionPane.showMessageDialog(null, "Mot de passe incorrect");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Requete.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erreur de connexion. Veuillez réessayer");
        }
        return false;
    }
    
    /*
    public boolean isClient(int num) throws SQLException{
        this.con = DriverManager.getConnection("jdbc:mysql://localhost/banque","root","");
        
    }
*/
}
