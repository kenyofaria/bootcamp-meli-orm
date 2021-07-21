package com.dh.meli.services;

import javax.persistence.EntityManager;

import com.dh.meli.dao.PedidoDao;
import com.dh.meli.entity.Pedido;

public class PedidoService {
	
	private EntityManager em;
	private PedidoDao pedidoDao;
	
	public PedidoService(EntityManager em) {
		this.em = em;
		pedidoDao = new PedidoDao(em);
	}
	
	public void salva(Pedido pedido){
		//garanto as regras de negocio
		pedidoDao.cadastra(pedido);
		
	}
}
