package com.spring.aop.trial.repository;

import java.util.List;
import java.util.Objects;

import com.spring.aop.trial.configuration.MongoConfig;
import com.spring.aop.trial.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	MongoConfig mongoConfig;

	MongoTemplate mongoTemplate;

	public UserRepository(MongoConfig mongoConfig){
		this.mongoConfig = mongoConfig;
		this.mongoTemplate = this.mongoConfig.mongoTemplate(this.mongoConfig.mongoClient());
	}

	public void saveUser(User user){
		mongoTemplate.save(user);
	}

	public List<User> getAllUsers(){
		return mongoTemplate.findAll(User.class);
	}


	public User getUserByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query,User.class);
	}
}
