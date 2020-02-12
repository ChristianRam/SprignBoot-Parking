package com.bolsadeideas.springboot.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.model.entity.Detalle;
import com.bolsadeideas.springboot.app.model.entity.Puesto;


public interface IDetalleDao extends CrudRepository<Detalle, Long>{
	
	public Detalle findDetalleByPuesto(Puesto puesto);

}
