package com.dingziran.gcgl2014.todo;

import java.util.Date;

import com.dingziran.gcgl2014.domain.Project;
import com.dingziran.gcgl2014.domain.TodoList;
import com.dingziran.gcgl2014.domain.UserInfo;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class TodoEditor extends Window{
	private final Item item;
	private FormLayout form;
	private Button saveButton;
	private Button cancelButton;
	private final JPAContainer<TodoList> container;
	private final FieldGroup binder;
	private Boolean isCreate;
	private DefaultFieldFactory ff;
	public TodoEditor(Item item, JPAContainer<TodoList> container,Boolean isCreate) {
		this.item = item;
		this.container=container;
		this.isCreate=isCreate;
		form = new FormLayout();
		setCaption("填写项目信息");

		setContent(form);
		binder = new FieldGroup(item);
		binder.setFieldFactory(new DefaultFieldGroupFieldFactory(){
		    public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
		        // Null typed properties can not be edited
		        if (type == null) {
		            return null;
		        }

		        // Item field
		       //if (Item.class.isAssignableFrom(type)) {
		        //    return new Form();
		        //}

		        // Date field
		        if (Date.class.isAssignableFrom(type)) {
		            final DateField df = new DateField();
		            return (T) df;
		        }

		        // Boolean field
		        //if (Boolean.class.isAssignableFrom(type)) {
		        //    return new CheckBox();
		        //}

		        return createDefaultField(type, fieldType);
		    }
		});
		form.addComponent(binder.buildAndBind("Name", "name"));
		form.addComponent(binder.buildAndBind("Description","description"));
		form.addComponent(binder.buildAndBind("StartDate","startDate"));
		form.addComponent(binder.buildAndBind("EndDate","endDate"));
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
						container.addEntity(((BeanItem<TodoList>)item).getBean());
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
