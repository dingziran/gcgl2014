package com.dingziran.gcgl2014.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;



import java.util.Set;

import org.eclipse.persistence.indirection.IndirectSet;

import com.dingziran.gcgl2014.domain.demo.Car;
import com.dingziran.gcgl2014.domain.demo.SinglePerson;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.MultiSelectConverter;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TwinColSelect;



public class DemoCreate extends Panel {
	FormLayout form=new FormLayout();
	JPAContainer<SinglePerson> persons=JPAContainerFactory.make(SinglePerson.class, "gcgl2014");
	JPAContainer<Car> cars=JPAContainerFactory.make(Car.class, "gcgl2014");
	FieldGroup binder=new FieldGroup();
	Button ok=new Button("ok");
	Button cancel=new Button("cancel");
	Car car;
	EntityItem<Car> ei;
	Object value;
	ListSelect users=new ListSelect("Users");
	public DemoCreate() {
		car=new Car();
		car.setPerson(new SinglePerson());
		car.setUsers(new HashSet<SinglePerson>());
		Item c=new BeanItem<Car>(car);
		binder.setItemDataSource(c);
		build();		
		ok.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					Set sets=(Set) users.getValue();
					for(Object o:sets){
						SinglePerson sp=persons.getItem(o).getEntity();
						//sp.setBeUsed(car);
						car.getUsers().add(sp);
					}
					cars.addEntity(car);
				} catch (CommitException e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}
	public DemoCreate(Object value2) {
		this.value=value2;
		System.out.println("**************");
		System.out.println("**************");
		System.out.println("**************");
		System.out.println("*value.toString:"+value.toString());
		ei=cars.getItem(value);
		System.out.println("*ei.entity.users.size:"+ei.getEntity().getUsers().size());
		System.out.println("*ei.entity.users:"+ei.getEntity().getUsers());
		binder.setItemDataSource(ei);
		build();		
		Set<SinglePerson> s=ei.getEntity().getUsers();
		Set v=new HashSet();
		
		for(SinglePerson sp:s){
			v.add(sp.getId());
		}
		users.setValue(v);
		ok.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					ei=cars.getItem(value);
					ei.setBuffered(true);
					Car c=ei.getEntity();
					binder.commit();
					Set sets=(Set) users.getValue();
					ei.getEntity().setUsers(new HashSet<SinglePerson>());
					for(Object o:sets){
						SinglePerson sp=persons.getItem(o).getEntity();
						c.getUsers().add(sp);
					}
					System.out.println("*ei.entity.users.size:"+cars.getItem(ei.getItemId()).getEntity().getUsers().size());
					ei.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}
	private void build() {
		binder.setBuffered(true);
		binder.setFieldFactory(new DefaultFieldGroupFieldFactory(){
		    public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
		        if (type == null) {
		            return null;
		        }
		        if (Date.class.isAssignableFrom(type)) {
		            final DateField df = new DateField();
		            return (T) df;
		        }
		        if(SinglePerson.class.isAssignableFrom(type)){
		        	final AbstractSelect nativeSelect=new NativeSelect();
		        	nativeSelect.setMultiSelect(false);
		            nativeSelect.setCaption("Owner");
		            nativeSelect.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		            nativeSelect.setItemCaptionPropertyId("name");
		            nativeSelect.setContainerDataSource(persons);
		            nativeSelect.setConverter(new SingleSelectConverter(nativeSelect));
		            return (T)nativeSelect;
		        }
		        // Boolean field
		        //if (Boolean.class.isAssignableFrom(type)) {
		        //    return new CheckBox();
		        //}

		        return createDefaultField(type, fieldType);
		    }
		});
		buildUsers();
		form.addComponent(binder.buildAndBind("Name","name"));
		form.addComponent(binder.buildAndBind("Owner","person"));
		form.addComponent(binder.buildAndBind("BuyDate","buyDate"));
		form.addComponent(users);
		form.addComponent(ok);
		form.addComponent(cancel);

		cancel.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				binder.discard();
				users.discard();
			}
			
		});
		setContent(form);
		
	}
	private void buildUsers() {
		users.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		users.setItemCaptionPropertyId("name");
		users.setContainerDataSource(persons);
		users.setRows(persons.size());
		users.setMultiSelect(true);
		//binder.bind(users, "users");
		
	}
}
