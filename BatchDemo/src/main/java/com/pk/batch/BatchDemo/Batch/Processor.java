package com.pk.batch.BatchDemo.Batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.pk.batch.BatchDemo.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {

		System.out.println("Inside Processor" + item.toString());
		if (item.getSalary() < 15000)
			item.setSalary(8000);
		item.setDate(new Date());
		return item;

	}
}
