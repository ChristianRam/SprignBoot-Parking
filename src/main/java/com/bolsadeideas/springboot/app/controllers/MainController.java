package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.model.entity.Detalle;
import com.bolsadeideas.springboot.app.model.entity.Puesto;
import com.bolsadeideas.springboot.app.model.service.IPuestoService;

@Controller
@SessionAttributes("detalle")
public class MainController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IPuestoService puestoService;
	

	@GetMapping(value = {"/listar", "/"})
	public String listar(Model model) {
		
		List<Puesto> puestos = puestoService.findAll();
		
		model.addAttribute("titulo", "Parqueadero Universidad Surcolombiana");
		model.addAttribute("puestos", puestos);
		
		return "listar";
	}
	
	
	@GetMapping("/form/{id}")
	public String añadir(@PathVariable(name = "id") Long id, Model model) {

		Puesto puesto = puestoService.findOnePuesto(id);
		puesto.setEstado(true);
		
		Detalle detalle = new Detalle();
		
		detalle.setPuesto(puesto);

		model.addAttribute("detalle", detalle);
		model.addAttribute("titulo", "Añadir");

		return "form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Detalle detalle, BindingResult result, Model model, RedirectAttributes flash, 
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Añadir");
			return "/form";
		}
		
		puestoService.saveDetalle(detalle);
		puestoService.savePuesto(detalle.getPuesto());
		
		String mensajeFlash = "Puesto añadido con exito";
				
		flash.addFlashAttribute("success", mensajeFlash);
		status.setComplete();
		
		return "redirect:/listar";
	}
	
	@GetMapping("/ver/{id}")
	public String verDetalle(@PathVariable(name = "id") Long id, Model model) {
		
		Puesto puesto = puestoService.findOnePuesto(id);
		
		Detalle detalle = puestoService.findDetalleByPuesto(puesto);
		
		
		model.addAttribute("titulo", "Detalles");
		model.addAttribute("detalle", detalle);
		
		return "ver";
	}
	
	@GetMapping("/vaciar/{id}")
	public String vaciar(@PathVariable(name = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			
			Puesto puesto = puestoService.findOnePuesto(id);
			
			if(puesto.getId() != null) {
				
				Detalle detalle = puestoService.findDetalleByPuesto(puesto);
				
				puesto.setEstado(false);
				puestoService.deleteDetalle(detalle.getId());
				
				flash.addFlashAttribute("info", "Puesto vaciado con exito!");
				return "redirect:/listar";
			}			
		}
		
		flash.addFlashAttribute("error", "Este puesto se encuentra disponible o no existe");
		return "redirect:/listar";
	}
}
