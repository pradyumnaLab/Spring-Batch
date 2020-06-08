package com.pk.batch.BatchDemo.Batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pk.batch.BatchDemo.model.User;
import com.pk.batch.BatchDemo.repository.UserDao;

@Component
public class DBWriter implements ItemWriter<User> {

	@Autowired
	private UserDao dao;

	@Override
	public void write(List<? extends User> items) throws Exception {
		System.out.println(items.size());
		dao.saveAll(items);
	}
}