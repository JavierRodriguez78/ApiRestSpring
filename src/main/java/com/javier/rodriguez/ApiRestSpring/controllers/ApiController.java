package com.javier.rodriguez.ApiRestSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javier.rodriguez.ApiRestSpring.dao.entity.ActorEntity;
import com.javier.rodriguez.ApiRestSpring.dao.interfaces.ActorEntityService;

@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private ActorEntityService actorService;
	
	@GetMapping("/hola")
	public String HolaMundo() {
		return "Hola mundo";
		
	}
	
	@RequestMapping(value="actors", method=RequestMethod.GET, produces="application/json" )
	public ResponseEntity<List<ActorEntity>> getActors()
	{
		List<ActorEntity> list = actorService.getAllEntities();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	

	@RequestMapping(value ="actors/{id}", method=RequestMethod.GET, produces="application/json" )
	public ResponseEntity<ActorEntity> getActor(@PathVariable int id){
		
		ActorEntity actor = actorService.getEntityById(id);
		System.out.println(actor);
		return  (actor== null)?  new ResponseEntity<>(HttpStatus.CONFLICT): new ResponseEntity<>(actor, HttpStatus.OK);
	}
	
	@RequestMapping(value="actors", method=RequestMethod.POST )
	public ResponseEntity<Void> addActor(@RequestBody ActorEntity actor, UriComponentsBuilder builder)
	{
	   actorService.addEntity(actor);
	   return new ResponseEntity<>(HttpStatus.OK);
	

	}
	
	@RequestMapping(value="actors/{id}", method=RequestMethod.DELETE )
	public ResponseEntity<Void> deleteActor(@PathVariable int id)
	{
		Boolean result = actorService.deleteEntity(id);
		return (!result)?  new ResponseEntity<>(HttpStatus.CONFLICT): new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="actors/{id}", method=RequestMethod.PUT, produces="application/json")
	public ResponseEntity<ActorEntity> modifyActor(@PathVariable int id, @RequestBody ActorEntity actor ){
		ActorEntity actorRes = actorService.getEntityById(id);
		if(actorRes == null) return new ResponseEntity<>(actorRes, HttpStatus.NOT_FOUND);
		actorRes.setFirst_name(actor.getFirst_name());
		actorRes.setLast_name(actor.getLast_name());
		Boolean result = actorService.updateEntity(actorRes);
		return (result)? new ResponseEntity<>(actorRes, HttpStatus.OK): new ResponseEntity<>(actorRes, HttpStatus.CONFLICT);
		
	}
}
