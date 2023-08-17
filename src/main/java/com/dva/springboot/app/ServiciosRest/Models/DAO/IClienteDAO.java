package com.dva.springboot.app.ServiciosRest.Models.DAO;

import org.springframework.data.repository.CrudRepository;

import com.dva.springboot.app.ServiciosRest.Models.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {

}
