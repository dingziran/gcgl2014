package com.dingziran.gcgl2014.demo;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Demo extends HorizontalLayout{
	private VerticalLayout menu;
	private Panel panel;
	private Button list;
	public Demo(){
		menu=new VerticalLayout();
		list=new Button("list");
		list.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				panel.setContent(new DemoList());
				
			}
			
		});
		
		menu.addComponent(list);
		panel=new Panel();

		addComponent(menu);
		addComponent(panel);
	}
}
