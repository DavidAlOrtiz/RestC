package com.dva.springboot.app.ServiciosRest.Service;
import java.util.*;
import com.dva.springboot.app.ServiciosRest.Models.*;

public interface IClienteService {
		
	List<Cliente> getAll();
	Cliente getById(Long id);
	Cliente save(Cliente cliente);
	void delete(Long id);
	
}
