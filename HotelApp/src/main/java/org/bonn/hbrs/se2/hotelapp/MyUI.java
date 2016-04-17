package org.bonn.hbrs.se2.hotelapp;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("org.bonn.hbrs.se2.hotelapp.MyAppWidgetset")
@Title("MeinHotel")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        HorizontalLayout layoutH = new HorizontalLayout();
        
        Label labelText = new Label("Gebe Ort ein: ");
        final TextField textField = new TextField();
        Button button = new Button("Suche", FontAwesome.SEARCH);
        
        layoutH.addComponent(labelText);
        layoutH.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);
        layoutH.addComponent(textField);
        layoutH.addComponent(new Label("&nbsp;", ContentMode.HTML));
        layoutH.addComponent(button);
        
        layout.addComponent(layoutH);
        layout.setComponentAlignment(layoutH, Alignment.MIDDLE_CENTER);
        
        button.addClickListener( e -> {
            layout.addComponent(new Label("Ort: " + textField.getValue()));
        });
        
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
