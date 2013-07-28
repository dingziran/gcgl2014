package com.dingziran.gcgl2014;

import com.dingziran.gcgl2014.project.ProjectView;
import com.dingziran.gcgl2014.todo.TodoView;
import com.dingziran.gcgl2014.user.UserView;
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
		initJPAContainer();
		buildMainLayout();
		initListener();
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

		setExpandRatio(body, 1);
		setSizeFull();
	
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
		body.setExpandRatio(content, 1);
		
		
	}
	private void buildContent() {
		content.addTab(new UserView(),"用户列表");
		content.addTab(new ProjectView(),"项目列表");
		content.addTab(new TodoView(),"任务列表");
		
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
