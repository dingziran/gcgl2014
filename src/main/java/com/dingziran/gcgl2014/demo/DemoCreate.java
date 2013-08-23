package com.dingziran.gcgl2014.demo;

import java.util.Date;

import com.dingziran.gcgl2014.domain.demo.Car;
import com.dingziran.gcgl2014.domain.demo.SinglePerson;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
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
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;



public class DemoCreate extends Panel {
	FormLayout form=new FormLayout();
	JPAContainer<SinglePerson> persons=JPAContainerFactory.make(SinglePerson.class, "gcgl2014");
	JPAContainer<Car> cars=JPAContainerFactory.make(Car.class, "gcgl2014");
	FieldGroup binder=new FieldGroup();
	Button ok=new Button("ok");
	Button cancel=new Button("cancel");
	Car car;
	public DemoCreate(){
		car=new Car();
		car.setPerson(new SinglePerson("wonder"));
		Item c=new BeanItem(car);
		binder.setItemDataSource(c);
		build();		
		ok.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
					cars.addEntity(car);
				} catch (CommitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
	}
	public DemoCreate(Object value) {
		EntityItem<Car> thiscar=cars.getItem(value);
		binder.setItemDataSource(thiscar);
		build();		
		ok.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.commit();
				} catch (CommitException e) {
					// TODO Auto-generated catch block
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
		form.addComponent(binder.buildAndBind("Name","name"));
		form.addComponent(binder.buildAndBind("Owner","person"));
		form.addComponent(binder.buildAndBind("BuyDate","buyDate"));
		form.addComponent(ok);

		cancel.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				binder.discard();
			}
			
		});
		setContent(form);
		
	}
}
