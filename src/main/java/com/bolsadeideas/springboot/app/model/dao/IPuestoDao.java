package com.bolsadeideas.springboot.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.model.entity.Puesto;

public interface IPuestoDao extends CrudRepository<Puesto, Long> {

}
