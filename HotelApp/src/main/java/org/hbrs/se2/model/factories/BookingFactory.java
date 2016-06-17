package org.hbrs.se2.model.factories;

import java.util.Date;
import org.hbrs.se2.model.objects.dto.BookingRequest;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.model.objects.entities.Booking;

/**
 *
 * @author Felix
 */
public class BookingFactory {

    public static Booking createBooking(BookingRequest request, User user) {
        Booking book = new Booking();
        
        book.setAnreise(request.getAnreise());
        book.setAbreise(request.getAbreise());
        book.setHotel(request.getHotel());
        book.setIban(request.getIban());
        book.setNumber(request.getNumber());
        
        // User gehört zu einer Buchung (siehe ER-Modell)
        book.setUser(user);
        
        // zusätzliches Attribut
        book.setDatumBuchung(new Date());
        
        // book.setID ... wird später bei der Ablage in die Datenbank hinzugefügt
        
        return book;
    }

}
