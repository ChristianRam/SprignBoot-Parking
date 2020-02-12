package com.bolsadeideas.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.model.dao.IDetalleDao;
import com.bolsadeideas.springboot.app.model.dao.IPuestoDao;
import com.bolsadeideas.springboot.app.model.entity.Detalle;
import com.bolsadeideas.springboot.app.model.entity.Puesto;

@Service
public class PuestoServiceImpl implements IPuestoService{

	@Autowired 
	private IPuestoDao puestoDao;
	
	@Autowired
	private IDetalleDao detalleDao;
	
	
	@Override
	public List<Puesto> findAll() {
		
		return (List<Puesto>) puestoDao.findAll();
	}


	@Override
	public Puesto findOnePuesto(Long id) {
		
		return puestoDao.findById(id).orElse(new Puesto());
	}


	@Override
	public void savePuesto(Puesto puesto) {
		
		puestoDao.save(puesto);
	}


	@Override
	public void saveDetalle(Detalle detalle) {
		
		detalleDao.save(detalle);
		
	}


	@Override
	public void deletePuesto(Long id) {
	
		puestoDao.deleteById(id);
	}


	@Override
	public void deleteDetalle(Long id) {
		
		detalleDao.deleteById(id);
	}


	@Override
	public Detalle findDetalleByPuesto(Puesto puesto) {
		
		return detalleDao.findDetalleByPuesto(puesto);
	}

}
