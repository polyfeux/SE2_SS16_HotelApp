package org.hbrs.se2.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.Hotel;

/**
 *
 * @author Felix
 */
public class HotelDAO extends AbstractDAO {

    public static HotelDAO dao = null;

    private HotelDAO() {

    }

    public static HotelDAO getInstance() {
        if (dao == null) {
            dao = new HotelDAO();
        }
        return dao;
    }

    public List<Hotel> getHotelByLocation(String ort) {
        Statement st = this.getStatement();

        ResultSet rs = null;

        try {
            rs = st.executeQuery("SELECT * "
                    + "FROM realm.hotel "
                    + "WHERE ort = \'" + ort + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }

        List<Hotel> liste = new ArrayList<>();
        Hotel h = null;

        try {
            while (rs.next()) {
                h = new Hotel();
                h.setId(rs.getInt(1));
                h.setName(rs.getString(2));
                h.setOrt(rs.getString(3));
                h.setDescription(rs.getString(4));
                liste.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
}
