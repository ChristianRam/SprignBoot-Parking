package com.bolsadeideas.springboot.app.model.service;

import java.util.List;

import com.bolsadeideas.springboot.app.model.entity.Detalle;
import com.bolsadeideas.springboot.app.model.entity.Puesto;

public interface IPuestoService {
	
	public List<Puesto> findAll();
	
	public Puesto findOnePuesto(Long id);
	
	public void savePuesto(Puesto puesto);
	
	public void deletePuesto(Long id);
	
	public Detalle findDetalleByPuesto(Puesto puesto);
	
	public void saveDetalle(Detalle detalle);
	
	public void deleteDetalle(Long id);
	
}
