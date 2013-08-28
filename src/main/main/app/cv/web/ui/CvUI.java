package app.cv.web.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author: sk_y
 */

@Theme("cv")
public class CvUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("WELCOME TO CV APP"));
        verticalLayout.addComponent(new Button("Press Ok"));


        this.setContent(verticalLayout);
    }
}
