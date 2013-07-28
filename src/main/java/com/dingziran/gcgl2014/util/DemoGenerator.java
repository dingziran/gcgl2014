package com.dingziran.gcgl2014.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.dingziran.gcgl2014.domain.Project;
import com.dingziran.gcgl2014.domain.UserInfo;

public class DemoGenerator {
	final static String[] usernames={"张三","李四","王五","路人甲","路人乙","路人丙"};
	final static String[] signatures={"今天天气不错","早起写代码","最近电影很好看"};
	final static String[] projectnames={"三峡水坝","神州20号","北航食堂改建","事业单位改革"};
	final static String[] projectdes={"本项目由XXX投资","国家250工程之一","宇宙自然基金项目","开源项目，为非洲儿童捐款捐物"};
	public static void create(){
		EntityManager em = Persistence.createEntityManagerFactory("gcgl2014").createEntityManager();
		em.getTransaction().begin();
		Random r=new Random(32);
		Set<UserInfo> users=new HashSet<UserInfo>();
		for(String name:usernames){
			UserInfo ui=new UserInfo();
			ui.setName(name);
			ui.setSignature(signatures[r.nextInt(signatures.length)]);
			users.add(ui);
			Project p=new Project();
			p.setName(projectnames[r.nextInt(projectnames.length)]);
			p.setDescription(projectdes[r.nextInt(projectdes.length)]);
			em.persist(ui);
			p.setMembers(users);
			em.persist(p);
		}
		em.getTransaction().commit();
		
	}
}
