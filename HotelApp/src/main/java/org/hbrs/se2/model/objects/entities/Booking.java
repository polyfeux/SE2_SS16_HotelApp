package org.hbrs.se2.model.objects.entities;

import java.util.Date;
import org.hbrs.se2.model.objects.dto.Hotel;
import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author Felix
 */
public class Booking {

    private int id;

    private Date anreise = null;
    private Date abreise = null;
    private String iban = null;
    private int number;
    private Hotel hotel;
    private User user;
    private Date datumBuchung;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAnreise() {
        return anreise;
    }

    public void setAnreise(Date anreise) {
        this.anreise = anreise;
    }

    public Date getAbreise() {
        return abreise;
    }

    public void setAbreise(Date abreise) {
        this.abreise = abreise;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Date getDatumBuchung() {
        return datumBuchung;
    }

    public void setDatumBuchung(Date date) {
        this.datumBuchung = date;
    }
}
