package com.dingziran.gcgl2014.demo;

import com.dingziran.gcgl2014.domain.demo.SinglePerson;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class DemoList extends Panel {
	private Table t;
	JPAContainer<SinglePerson> persons=JPAContainerFactory.make(SinglePerson.class, "gcgl2014");
	public DemoList(){
		SinglePerson sp=new SinglePerson();
		sp.setName("dingziran");
		persons.addEntity(sp);
		t=new Table("The Simple Person Example",persons);
		setContent(t);
	}
}
