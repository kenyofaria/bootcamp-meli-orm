package com.dh.meli.dao;

import javax.persistence.EntityManager;

import com.dh.meli.entity.Produto;

public class ProdutoDao {

	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastra(Produto produto) {
		this.em.persist(produto);
	}
}
