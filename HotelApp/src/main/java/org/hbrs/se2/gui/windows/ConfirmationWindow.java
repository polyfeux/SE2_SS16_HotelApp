package org.hbrs.se2.gui.windows;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author Felix
 */
public class ConfirmationWindow extends Window {

    public ConfirmationWindow(String text) {
        super("Confirmation");
        center();

        // Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button buchungsButton = new Button("OK");
        buchungsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        
        content.addComponent(buchungsButton);
        content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
    }
}
