package org.hbrs.se2.gui.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.hbrs.se2.gui.views.LoginView;
import org.hbrs.se2.gui.views.MainView;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.services.util.Views;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("org.bonn.hbrs.se2.hotelapp.MyAppWidgetset")
@Title("MeinHotel")
@PreserveOnRefresh
public class MyUI extends UI {
    
    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        System.out.println("LOG: neues UI-Objekt erzeugt. Session-ID: " + VaadinSession.getCurrent().toString());
        
        Navigator navi = new Navigator(this, this);

        navi.addView(Views.MAIN, MainView.class);
        navi.addView(Views.LOGIN, LoginView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
    }
    
    public MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
