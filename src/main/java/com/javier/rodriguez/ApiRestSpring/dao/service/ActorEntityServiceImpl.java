package com.javier.rodriguez.ApiRestSpring.dao.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javier.rodriguez.ApiRestSpring.dao.entity.ActorEntity;
import com.javier.rodriguez.ApiRestSpring.dao.interfaces.ActorEntityService;
import com.javier.rodriguez.ApiRestSpring.dao.repository.ActorEntityRepository;


@Service
@Transactional
public class ActorEntityServiceImpl implements ActorEntityService
{
	private ActorEntityRepository repository;

	public ActorEntityServiceImpl() {
		
	}
	
	@Autowired
	public ActorEntityServiceImpl(ActorEntityRepository repository) {
		this.repository=repository;
	}
	
	@Override
	public ActorEntity getEntityById(int id) {
		// TODO Auto-generated method stub
		try {
			
			return this.repository.findById(id).get();
		}catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
	
	}

	@Override
	public List<ActorEntity> getAllEntities() {
		
		List<ActorEntity> list = new ArrayList<>();
		this.repository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public ActorEntity addEntity(ActorEntity entity) {
		// TODO Auto-generated method stub
		try {
			return this.repository.save(entity);
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateEntity(ActorEntity entity) {
		
	   try {
		
		   this.repository.save(entity);
		   return true;
		   
	   } catch (Exception e) {
		   e.printStackTrace();
		   return false;
	   }
		
	}

	@Override
	public boolean deleteEntity(int id) {
		// TODO Auto-generated method stub
		
		try {
			this.repository.deleteById(id);
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}