package org.hbrs.se2.process.control;

import java.util.ArrayList;
import java.util.List;
import org.hbrs.se2.model.dao.HotelDAO;
import org.hbrs.se2.model.objects.dto.Hotel;

/**
 *
 * @author Felix
 */
public class HotelSearch {

    private HotelSearch() {

    }

    public static HotelSearch search = null;

    public static HotelSearch getInstance() {
        if (search == null) {
            search = new HotelSearch();
        }
        return search;
    }

    public List<Hotel> getHotelByOrt(String ort) {
        return HotelDAO.getInstance().getHotelByLocation(ort);
    }
}
