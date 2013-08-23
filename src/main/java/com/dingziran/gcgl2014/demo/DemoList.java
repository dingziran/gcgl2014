package com.dingziran.gcgl2014.demo;

import java.util.Date;
import java.util.Locale;


import java.util.Set;

import com.dingziran.gcgl2014.domain.demo.Car;
import com.dingziran.gcgl2014.domain.demo.SinglePerson;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DemoList extends Panel {
	private Table t;
	private VerticalLayout vl=new VerticalLayout();
	private Button edit=new Button("edit");
	private Button delete=new Button("delete");
	JPAContainer<SinglePerson> persons=JPAContainerFactory.make(SinglePerson.class, "gcgl2014");
	JPAContainer<Car> cars=JPAContainerFactory.make(Car.class, "gcgl2014");
	private String[] visibleCol=new String[]{"id","name","person.name","buyDate","users"};
	public DemoList(){
		Car car=new Car();
		car.setName("benz");
		car.getUsers().add(new SinglePerson("bob"));
		car.getUsers().add(new SinglePerson("john"));
		car.getUsers().add(new SinglePerson("lisa"));
		SinglePerson sp=new SinglePerson("dingziran");
		car.getUsers().add(sp);
		car.setBuyDate(new Date());
		car.setPerson(sp);
		
		cars.addEntity(car);
		cars.addNestedContainerProperty("person.name");
		
		
		t=new Table("Cars",cars);
		t.setVisibleColumns(visibleCol);
		t.setColumnHeader("person.name", "owner");
		t.setSelectable(true);
		t.setConverter("users", new SetConverter());
		vl.addComponent(t);
		
		edit.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				setContent(new DemoCreate(t.getValue()));	
			}
			
		});        
		delete.addClickListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
            	cars.removeItem(t.getValue());
            }
        });
		
		vl.addComponent(edit);
		vl.addComponent(delete);
		
		setContent(vl);
	}
}
class SetConverter implements Converter<String, Set<SinglePerson>>{

	@Override
	public Set<SinglePerson> convertToModel(String value, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertToPresentation(Set<SinglePerson> value, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		String s="";
		for(SinglePerson sp:value){
			s+=sp.getName()+" ";
		}
		return s;
	}

	@Override
	public Class<Set<SinglePerson>> getModelType() {
		return null;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}
	
}
