package com.javier.rodriguez.ApiRestSpring.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javier.rodriguez.ApiRestSpring.dao.entity.ActorEntity;



@Repository
public interface ActorEntityRepository extends CrudRepository<ActorEntity, Integer> {
	
	//	public ActorEntity findByfirstName(String name);

}
