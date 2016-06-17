package org.hbrs.se2.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.Role;
import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author Felix
 */
public class RoleDAO extends AbstractDAO {

    public static RoleDAO dao = null;

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(User u) {
        Statement st = this.getStatement();

        ResultSet rs = null;

        try {
            rs = st.executeQuery("SELECT * "
                    + "FROM realm.user_to_rolle "
                    + "WHERE login = \'" + u.getLogin() + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }
        
        List<Role> liste = new ArrayList<>();
        Role rolle = null;
        
        try {
            while(rs.next()) {
                rolle = new Role();
                rolle.setBezeichnung(rs.getString(2));
                liste.add(rolle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
}
