package com.dva.springboot.app.ServiciosRest.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dva.springboot.app.ServiciosRest.Models.*;
import com.dva.springboot.app.ServiciosRest.Service.IClienteService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public List<Cliente> index(){
		return clienteService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  getById(@PathVariable Long id) {
		
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.getById(id);
		} catch (DataAccessException |NumberFormatException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		if(cliente == null) {
			response.put("mensaje", "El usuario con el id ".concat(id.toString()).concat(" No existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND );
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> guardar(@RequestBody Cliente cliente) {
		
		Cliente nuevoCliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
		   nuevoCliente = clienteService.save(cliente);	
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Usuario Guardado con exito");
		response.put("cliente", nuevoCliente);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@PathVariable Long id ,@RequestBody Cliente cliente) {
		Cliente clienteA = null;
		try {
			clienteA = clienteService.getById(id);
			clienteA.setNombre(cliente.getNombre());
			clienteA.setApellido(cliente.getApellido());
			clienteA.setEmail(cliente.getEmail());
			clienteA.setCreateAt(cliente.getCreateAt());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clienteService.save(clienteA);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ALREADY_REPORTED)
	public void eliminar(@PathVariable Long id) {
		this.clienteService.delete(id);
	}
	
}
