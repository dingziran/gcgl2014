package com.dingziran.gcgl2014;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.Reindeer;

public class MainView extends VerticalLayout{
	private MenuBar menu;
	private VerticalLayout body;
	private HorizontalLayout head;
	private TabSheet content;
	public MainView (){
		buildMainLayout();
		initListener();
		initJPAContainer();
	}
	private void initJPAContainer() {
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		
	}
	private void buildMainLayout() {
		menu=new MenuBar();
		menu.setWidth("100%");
		buildMenu();
		addComponent(menu);
		
		body=new VerticalLayout();
		body.setSizeFull();
		body.setMargin(true);
		buildBody();
		addComponent(body);
	
	}
	private void buildBody() {
		
		//build head
		head=new HorizontalLayout();
		Label logo=new Label("Project Management Tool");
		logo.addStyleName(Reindeer.LABEL_H2);
		head.addComponent(logo);
		head.setHeight("60px");

		content=new TabSheet();
		content.setSizeFull();
		buildContent();
		
		body.addComponent(head);
		body.addComponent(content);
		
		
	}
	private void buildContent() {
		// TODO Auto-generated method stub
		
	}
	private void buildMenu() {

		MenuBar.MenuItem manage=menu.addItem("管理",  null);

		MenuBar.Command com1=new MenuBar.Command() {
			
			public void menuSelected(MenuItem selectedItem) {
				Notification.show(selectedItem.getText()+"Clicked!!");
				
			}
		};
		manage.addItem("用户管理", com1);
		manage.addItem("项目管理", com1);	
	}
}
