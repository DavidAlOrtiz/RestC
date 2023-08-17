package com.dva.springboot.app.ServiciosRest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dva.springboot.app.ServiciosRest.Models.Cliente;
import com.dva.springboot.app.ServiciosRest.Models.DAO.IClienteDAO;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	IClienteDAO serviceCliente;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> getAll() {
		return (List<Cliente>) serviceCliente.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente getById(Long id) {
		return serviceCliente.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return serviceCliente.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		serviceCliente.deleteById(id);
		
	}

}
