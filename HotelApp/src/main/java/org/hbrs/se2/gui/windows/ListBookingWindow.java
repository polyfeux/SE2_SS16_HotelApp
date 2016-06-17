package org.hbrs.se2.gui.windows;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.List;
import org.hbrs.se2.model.objects.dto.BookingDetail;
import org.hbrs.se2.process.control.BookingProcess;

/**
 *
 * @author Felix
 */
public class ListBookingWindow extends Window {

    private int currentID;
    private List<BookingDetail> liste;

    public ListBookingWindow() {
        super("Liste aller Buchungen");
        center();
        VerticalLayout layout = new VerticalLayout();

        // erzuge die Tabelle anhand des DTO BookingDetail
        final BeanContainer<Integer, BookingDetail> data = new BeanContainer<>(BookingDetail.class);
        data.setBeanIdProperty("id");
        final Table table = new Table("Liste ihrer Buchungen:", data);
        table.setSizeFull();
        table.setSelectable(true);

        // Hole alle Buchungen für einen User ab:
        liste = BookingProcess.getInstance().getAllBookingsForUser();

        // Befülle die Daten in die Tabelle (einfaches Datenmodell)
        data.addAll(liste);
        table.setPageLength(table.size());
        this.setSizeFull();
        layout.addComponent(table);

        Button deleteButton = new Button("Storniere Reise");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                // Lösche die Buchung
                BookingProcess.getInstance().deleteBookingByID(currentID);

                // lokale Löschung der Tabelle
                data.removeAllItems();

                // Tabelle neu laden und darstellen (sichere Variante)
                liste = BookingProcess.getInstance().getAllBookingsForUser();
                data.addAll(liste);
                table.setPageLength(table.size());
            }
        });

        Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(emptyLabel);
        layout.addComponent(deleteButton);
        layout.setComponentAlignment(deleteButton, Alignment.MIDDLE_CENTER);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                System.out.println(event.getItemId());
                ListBookingWindow.this.currentID = (Integer) event.getItemId();
            }
        });

        this.setContent(layout);
    }
}
