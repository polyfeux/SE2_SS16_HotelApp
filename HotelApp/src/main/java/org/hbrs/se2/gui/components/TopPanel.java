package org.hbrs.se2.gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.gui.windows.ListBookingWindow;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.LoginControl;
import org.hbrs.se2.services.util.Roles;

/**
 *
 * @author Felix
 */
public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        Label headLabel = new Label("MeinHotel - <i>das Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitel");

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        HorizontalLayout horLayout = new HorizontalLayout();

//        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;
        if (user != null) {
            vorname = user.getVorname();
        }

        Label loggedLabel = new Label("Welcome: " + vorname + "!");
        loggedLabel.setSizeUndefined();
        loggedLabel.addStyleName("loggedLabel");

        horLayout.addComponent(loggedLabel);
        horLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);

        MenuBar bar = new MenuBar();
        MenuItem item1 = bar.addItem("Menu", null);

        // Logout des Users
        item1.addItem("Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                LoginControl.logoutUser();
            }
        });

        if (user.hasRole(Roles.POWER_USER)) {
            item1.addItem("Cancel", FontAwesome.UNLINK, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
                    ListBookingWindow window = new ListBookingWindow();
                    UI.getCurrent().addWindow(window);
                }
            });
        }

        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);
    }
}
