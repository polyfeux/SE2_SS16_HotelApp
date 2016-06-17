package org.hbrs.se2.model.objects.dto;

import java.util.Date;

/**
 *
 * @author Felix
 */
public class BookingRequest {
    private Date anreise = null;
    private Date abreise = null;
    private String iban = null;
    private int number;
    private Hotel hotel;

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
    
}
