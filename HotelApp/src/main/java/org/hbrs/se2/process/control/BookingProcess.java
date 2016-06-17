package org.hbrs.se2.process.control;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.List;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.gui.windows.ConfirmationWindow;
import org.hbrs.se2.model.dao.BuchungDAO;
import org.hbrs.se2.model.factories.BookingFactory;
import org.hbrs.se2.model.objects.dto.BookingDetail;
import org.hbrs.se2.model.objects.dto.BookingRequest;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.model.objects.entities.Booking;
import org.hbrs.se2.services.util.Roles;

/**
 *
 * @author Felix
 */
public class BookingProcess {
    
    public static BookingProcess process = null;
    
    private BookingProcess() {
        
    }
    
    public static BookingProcess getInstance() {
        if (process == null) {
            process = new BookingProcess();
        }
        return process;
    }
    
    public void createBooking(BookingRequest request, Window window) {
        //User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();
        
        Booking booking = BookingFactory.createBooking(request, user);
        
        boolean result = BuchungDAO.getInstance().addBooking(booking);

        // Navigation auf Basis der (un-) erfolgreichen Buchung
        if (result == true) {
            window.close();
            UI.getCurrent().addWindow(new ConfirmationWindow("Buchung erfolgreich! ID: " + booking.getId()));
        } else {
            // Fehlerbehandlung
        }
    }
    
    public void deleteBookingByID(int id) {
        BuchungDAO.getInstance().deleteBookingBy(id);
        UI.getCurrent().addWindow(new ConfirmationWindow("Die Reise wurde storniert!"));
    }

    public List<BookingDetail> getAllBookingsForUser() {
        // via Session-Objekt
        final User user = ((MyUI) UI.getCurrent()).getUser();
        return BuchungDAO.getInstance().getAllBookingsForUser(user);
    }
}
