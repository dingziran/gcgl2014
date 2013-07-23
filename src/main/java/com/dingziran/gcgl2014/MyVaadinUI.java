package com.dingziran.gcgl2014;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Theme("reindeer")
public class MyVaadinUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
        setContent(new MainView());
    }

}
