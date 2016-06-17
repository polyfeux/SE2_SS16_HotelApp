package org.hbrs.se2.gui.views;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.gui.windows.BookingWindow;
import org.hbrs.se2.model.objects.dto.Hotel;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.HotelSearch;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class MainView extends VerticalLayout implements View {

    private int anzahlSuche = 0;
    private Hotel hotelSelektiert = null;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = ((MyUI) UI.getCurrent()).getUser();

        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        } else {
            this.setUp();
        }
    }

    public void setUp() {
        this.addComponent(new TopPanel());

        this.addComponent(new Label("<hr />", ContentMode.HTML));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        final TextField textField = new TextField();
        Button button = new Button("Suche", FontAwesome.SEARCH);

        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;
        if (user != null) {
            vorname = user.getVorname();
        }

        Label labelText = new Label(vorname + ", gebe Ort ein: ");

        horizontalLayout.addComponent(labelText);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);
        horizontalLayout.addComponent(textField);
        horizontalLayout.addComponent(new Label("&nbsp;", ContentMode.HTML));
        horizontalLayout.addComponent(button);

        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        // die Tabelle (Video 2):
        final BeanContainer<Integer, Hotel> data = new BeanContainer<>(Hotel.class);

        // Primary-Key fuer Tabelle festlegen:
        data.setBeanIdProperty("id");

        final Table table = new Table("Treffer", data);

        table.setSizeFull();
        table.setSelectable(true);

        final Button buchenButton = new Button("Buche");
        buchenButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (MainView.this.hotelSelektiert == null) {
                    return;
                } else {
                    // oeffne neues Fenster (hotelSelektiert) zur Buchung.
                    System.out.println("Hotel selektiert: " + MainView.this.hotelSelektiert.getName());
                    
                    BookingWindow window = new BookingWindow(MainView.this.hotelSelektiert);
                    UI.getCurrent().addWindow(window);
                }
            }
        });

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                System.out.println("Zeile selektiert: " + event.getItemId().toString());
                BeanItem<Hotel> hotelBean = data.getItem(event.getItemId());
                hotelSelektiert = hotelBean.getBean();
            }
        });

        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String ort = textField.getValue();
                if (ort.equals("")) {
                    Notification.show(null, "Bitte Ort eingeben!", Notification.Type.WARNING_MESSAGE);
                } else {
                    addComponent(table);
                    MainView.this.anzahlSuche++;
                    table.setCaption("Treffer f\u00fcr " + ort + " (Anzahl der Suchen: " + MainView.this.anzahlSuche + ")"
                            + ((User) ((MyUI) UI.getCurrent()).getUser()).getVorname());
                    List<Hotel> liste = HotelSearch.getInstance().getHotelByOrt(ort);
                    data.removeAllItems();
                    data.addAll(liste);
                    table.setPageLength(table.size());
                    addComponent(buchenButton);
                    setComponentAlignment(buchenButton, Alignment.MIDDLE_CENTER);
                }
            }
        });

        setMargin(true);
        setSpacing(true);
    }

}
