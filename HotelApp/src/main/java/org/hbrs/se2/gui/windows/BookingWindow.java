package org.hbrs.se2.gui.windows;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.Date;
import org.hbrs.se2.model.objects.dto.BookingRequest;
import org.hbrs.se2.model.objects.dto.Hotel;
import org.hbrs.se2.process.control.BookingProcess;

/**
 *
 * @author Felix
 */
public class BookingWindow extends Window {
    
    public BookingWindow(final Hotel h) {
        super("Buchung");
        center();

        // Some basic content for the Window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Buchung für: " + h.getName()));
        content.setMargin(true);
        setContent(content);
        
        final DateField dateAnreise = new DateField();
        content.addComponent(dateAnreise);
        dateAnreise.setCaption("Anreise");
        dateAnreise.setDateFormat("yyyy-MM-dd");
        dateAnreise.setValue(new Date());
        
        final DateField dateAbreise = new DateField();
        content.addComponent(dateAbreise);
        dateAbreise.setCaption("Abreise");
        dateAbreise.setDateFormat("yyyy-MM-dd");
        dateAbreise.setValue(new Date());
        
        final ComboBox personNumber = new ComboBox();
        personNumber.setCaption("Anzahl Personen: ");
        content.addComponent(personNumber);
        personNumber.addItem(new Integer(1));
        personNumber.addItem(new Integer(2));
        personNumber.addItem(new Integer(3));
        
        final TextField ibanFeld = new TextField();
        ibanFeld.setCaption("IBAN:");
        content.addComponent(ibanFeld);
        
        Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
        content.addComponent(emptyLabel);

        // Enable the close button
        setClosable(true);

        // Implementierung Button
        Button buchungsButton = new Button("Buche");
        buchungsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                BookingRequest request = new BookingRequest();
                request.setAnreise(dateAnreise.getValue());
                request.setAbreise(dateAbreise.getValue());
                request.setIban(ibanFeld.getValue());
                request.setNumber((int) personNumber.getValue());
                request.setHotel(h);
                
                BookingProcess.getInstance().createBooking(request, BookingWindow.this);
            }
        });
        
        content.addComponent(buchungsButton);
        content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
        
    }
    
}
