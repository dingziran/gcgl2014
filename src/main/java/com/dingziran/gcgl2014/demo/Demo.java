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
	private Button b1;
	private Button b2;
	public Demo(){
		menu=new VerticalLayout();
		b1=new Button("查看实体列表");
		b1.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				panel.setContent(new DemoList());
				
			}
			
		});
		
		b2=new Button("新建实体");
		b2.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				panel.setContent(new DemoCreate());
				
			}
			
		});
		
		
		
		menu.addComponent(b1);
		menu.addComponent(b2);
		panel=new Panel();

		addComponent(menu);
		addComponent(panel);
	}
}
