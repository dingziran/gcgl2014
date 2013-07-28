package com.dingziran.gcgl2014.todo;


import java.util.Date;

import com.dingziran.gcgl2014.domain.Project;
import com.dingziran.gcgl2014.domain.TodoList;
import com.dingziran.gcgl2014.domain.UserInfo;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class TodoEditor extends Window{
	private final Item item;
	private FormLayout form;
	private Button saveButton;
	private Button cancelButton;
	private final JPAContainer<TodoList> todos;
	private final FieldGroup binder;
	private Boolean isCreate;
	public TodoEditor(Item item, JPAContainer<TodoList> todos,Boolean isCreate) {
		this.item = item;
		this.todos=todos;
		this.isCreate=isCreate;
		form = new FormLayout();
		setCaption("填写任务信息");

		setContent(form);
		binder = new FieldGroup(item);
		 binder.setFieldFactory(new DefaultFieldGroupFieldFactory() {
	            @Override
	            public <T extends Field> T createField(final Class<?> type,
	                    final Class<T> fieldType) {
	                T field;
	                if (Date.class == type) {
	                    field = (T) new PopupDateField();
	                } else {
	                    field = super.createField(type, fieldType);
	                }
	                return field;
	            }
	        });
		form.addComponent(binder.buildAndBind("Name", "name"));
		form.addComponent(binder.buildAndBind("Description","description"));
		form.addComponent(binder.buildAndBind("StartDate", "startDate"));
		form.addComponent(binder.buildAndBind("EndDate", "endDate"));
		binder.setBuffered(true);
		
		
		addButton();
	}	
	private void addButton() {
		saveButton= new Button("Save");
		saveButton.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					if(isCreate)
						todos.addEntity(((BeanItem<TodoList>)item).getBean());
					close();
				} catch (CommitException e) {
					e.printStackTrace();
				}
			}
			
		});
		form.addComponent(saveButton);
		cancelButton=new Button("Cancel");
		cancelButton.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				binder.discard();
				close();
			}
			
		});
		form.addComponent(cancelButton);
		
	}

}
