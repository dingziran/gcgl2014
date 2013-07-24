package com.dingziran.gcgl2014.user;

import com.dingziran.gcgl2014.domain.UserInfo;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class UserView extends Panel{
	private Table table;
	private VerticalLayout ts;
    private Button newButton;
    private Button deleteButton;
    private Button editButton;
    private HorizontalLayout toolbar;
	private JPAContainer<UserInfo> users;
	public UserView(){
		initJPAContainer();
		buildMainLayout();
		initListener();
	}
	private void initJPAContainer() {
		users=JPAContainerFactory.make(UserInfo.class,"gcgl2014");
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		
	}
	private void buildMainLayout() {
		setSizeFull();
		ts=new VerticalLayout();
		ts.setSizeFull();
		setContent(ts);
		
		toolbar=new HorizontalLayout();
		
		newButton=new Button("create");
		editButton=new Button("edit");
		deleteButton=new Button("delete");
		
		toolbar.addComponent(newButton);
		toolbar.addComponent(editButton);
		toolbar.addComponent(deleteButton);
		
		ts.addComponent(toolbar);
		
		table = new Table(null,users);
		table.setSizeFull();
		
		ts.addComponent(table);
		ts.setExpandRatio(table, 1);

		
		
		
	}
}
