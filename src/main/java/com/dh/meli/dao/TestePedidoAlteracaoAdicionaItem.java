package com.dh.meli.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dh.meli.entity.Cliente;
import com.dh.meli.entity.Pedido;
import com.dh.meli.entity.Produto;
import com.dh.meli.entity.Situacao;

public class TestePedidoAlteracaoAdicionaItem {

	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("eNois");
		EntityManager em = factory.createEntityManager();
		
		
		Cliente cliente = new Cliente("kenyo", 'm', LocalDate.of(2000, Month.FEBRUARY, 25));
		Produto produto = new Produto("iphone 12", new BigDecimal(10000));
		
		
		try {
			em.getTransaction().begin();
			
			ClienteDao clienteDao = new ClienteDao(em);
			clienteDao.cadastra(cliente);
			
			ProdutoDao produtoDao = new ProdutoDao(em);
			produtoDao.cadastra(produto);
			
			Pedido pedido = new Pedido(LocalDate.now(), Situacao.ABERTO, cliente);
			pedido.adicionaItem(produto, 3, new BigDecimal(10000));
			
			PedidoDao pedidoDao = new PedidoDao(em);
			pedidoDao.cadastra(pedido);
				
			em.getTransaction().commit();
			
			
			//--------------------------------------------------------------------
			
			
			em.getTransaction().begin();
			Produto mouse = new Produto("mouse massa demais", new BigDecimal(300));
			produtoDao.cadastra(mouse);
			
			pedido.adicionaItem(mouse, 3, new BigDecimal(252));
			pedidoDao.cadastra(pedido);
			
			//pedidoDao.exclui(pedido);
			em.getTransaction().commit();
			
			
			
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}
}
