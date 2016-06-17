package org.hbrs.se2.process.control;

import com.vaadin.ui.UI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.exceptions.DatabaseException;
import org.hbrs.se2.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.services.db.JDBCConnection;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class LoginControl {
    
    public static void checkAuthentication(String login, String password) throws NoSuchUserOrPassword, DatabaseException {
        ResultSet set = null;
        
        try {
            // DB-Zugriff
            Statement st = JDBCConnection.getInstance().getStatement();
            set = st.executeQuery("SELECT * "
                    + "FROM realm.user "
                    + "WHERE login =\'" + login + "\' "
                    + "AND password =\'" + password + "\'");
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Sklaven benachrichtigen!");
        }
        
        User user = null;
        
        try {
            if (set.next()) {
                user = new User();
                user.setLogin(set.getString(1));
                user.setVorname(set.getString(3));
            } else {
                // Fehlerfall:
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        ((MyUI) UI.getCurrent()).setUser(user);
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        
    }
    
    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/HotelApp");
    }
}
