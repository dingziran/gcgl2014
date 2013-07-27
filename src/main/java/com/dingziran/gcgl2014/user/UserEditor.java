package com.dingziran.gcgl2014.user;

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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class UserEditor extends Window{
	private final Item item;
	private FormLayout form;
	private Button saveButton;
	private Button cancelButton;
	private final JPAContainer<UserInfo> users;
	private final FieldGroup binder;
	private Boolean isCreate;
	public UserEditor(Item item, JPAContainer<UserInfo> users,Boolean isCreate) {
		this.item = item;
		this.users=users;
		this.isCreate=isCreate;
		form = new FormLayout();
		setCaption("填写用户信息");

		setContent(form);
		binder = new FieldGroup(item);
		binder.setFieldFactory(new DefaultFieldGroupFieldFactory());
		form.addComponent(binder.buildAndBind("Name", "name"));
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
						users.addEntity(((BeanItem<UserInfo>)item).getBean());
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
