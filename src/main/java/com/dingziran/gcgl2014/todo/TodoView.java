package com.dingziran.gcgl2014.todo;

import com.dingziran.gcgl2014.domain.Project;
import com.dingziran.gcgl2014.domain.TodoList;
import com.dingziran.gcgl2014.domain.UserInfo;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class TodoView extends Panel{
	private Table table;
	private VerticalLayout ts;
    private Button newButton;
    private Button deleteButton;
    private Button editButton;
    private HorizontalLayout toolbar;
	private JPAContainer<TodoList> container;
	private String[] visibleCol=new String[]{"name","description","startDate","endDate","project.name"};
	public TodoView(){
		initJPAContainer();
		buildMainLayout();
		initListener();
	}
	private void initJPAContainer() {
		container=JPAContainerFactory.make(TodoList.class,"gcgl2014");
		container.addNestedContainerProperty("project.name");
		
	}
	private void initListener() {

        table.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                setModificationsEnabled(event.getProperty().getValue() != null);
            }

            private void setModificationsEnabled(boolean b) {
                deleteButton.setEnabled(b);
                editButton.setEnabled(b);
            }
        });
        
		newButton.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {

				final BeanItem<TodoList> item=new BeanItem<TodoList>(new TodoList());
				TodoEditor editor=new TodoEditor(item,container,true);
				UI.getCurrent().addWindow(editor);
				
			}
		});
		
		editButton.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
                UI.getCurrent().addWindow(
                        new TodoEditor(table.getItem(table.getValue()),container,false));
				
			}
			
		});
		
        deleteButton.addClickListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
            	container.removeItem(table.getValue());
            }
        });
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
		editButton.setEnabled(false);
        deleteButton.setEnabled(false);
		
		toolbar.addComponent(newButton);
		toolbar.addComponent(editButton);
		toolbar.addComponent(deleteButton);
		
		ts.addComponent(toolbar);
		
		table = new Table(null,container);
		table.setVisibleColumns(visibleCol);
		table.setSizeFull();
		table.setSelectable(true);
        table.setImmediate(true);
		
		ts.addComponent(table);
		ts.setExpandRatio(table, 1);

		
		
		
	}
}
