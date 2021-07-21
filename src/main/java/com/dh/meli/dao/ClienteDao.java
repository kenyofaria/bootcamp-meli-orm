package com.dh.meli.dao;

import javax.persistence.EntityManager;

import com.dh.meli.entity.Cliente;

public class ClienteDao {

	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastra(Cliente cliente) {
		this.em.persist(cliente);
	}
}
