package org.hbrs.se2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.BookingDetail;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.model.objects.entities.Booking;

/**
 *
 * @author Felix
 */
public class BuchungDAO extends AbstractDAO {

    public static BuchungDAO dao = null;

    private BuchungDAO() {

    }

    public static BuchungDAO getInstance() {
        if (dao == null) {
            dao = new BuchungDAO();
        }
        return dao;
    }

    public boolean addBooking(Booking b) {
        String sql = "insert into realm.booking values (default,?,?,?,?,?,?,?);";
        PreparedStatement st = this.getPreparedStatement(sql);

        try {
            st.setDate(1, new java.sql.Date(b.getAnreise().getTime()));
            st.setDate(2, new java.sql.Date(b.getAbreise().getTime()));
            st.setString(3, b.getIban());
            st.setInt(4, b.getNumber());
            st.setString(5, b.getUser().getLogin());
            st.setDate(6, new java.sql.Date(b.getDatumBuchung().getTime()));
            st.setInt(7, b.getHotel().getId());

            st.executeUpdate();

            //nachträgliches Setzen der Buchungs-ID
            setBuchungsID(b);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deleteBookingBy(int id) {
        Statement st = this.getStatement();

        try {
            st.execute("DELETE FROM realm.booking WHERE booking.id = \'" + id + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setBuchungsID(Booking b) {
        Statement st = this.getStatement();

        ResultSet rs = null;

        try {
            rs = st.executeQuery("SELECT max(id) "
                    + "FROM realm.booking;");
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int currentValue = 0;
        try {
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        b.setId(currentValue);
    }

    public List<BookingDetail> getAllBookingsForUser(User user) {
        Statement st = this.getStatement();

        ResultSet rs = null;

        try {
            rs = st.executeQuery("SELECT hotel.name, booking.id, booking.anreise, booking.abreise, booking.datumbuchung "
                    + "FROM realm.booking JOIN realm.hotel "
                    + "ON (booking.hotelid = hotel.id) "
                    + "WHERE booking.userid = \'" + user.getLogin() + "\'");
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }

        List<BookingDetail> liste = new ArrayList<>();
        BookingDetail booking = null;

        try {
            while (rs.next()) {
                booking = new BookingDetail();
                booking.setHotel(rs.getString(1));
                booking.setId(rs.getInt(2));
                booking.setAnreise(rs.getDate(3));
                booking.setAbreise(rs.getDate(4));
                booking.setDatumBuchung(rs.getDate(5));

                liste.add(booking);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
}
