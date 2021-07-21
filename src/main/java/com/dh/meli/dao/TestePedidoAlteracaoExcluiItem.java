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

public class TestePedidoAlteracaoExcluiItem {

	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("eNois");
		EntityManager em = factory.createEntityManager();
		
		
		try {
			em.getTransaction().begin();
			
			Cliente cliente = new Cliente("kenyo", 'm', LocalDate.of(2000, Month.FEBRUARY, 25));
			ClienteDao clienteDao = new ClienteDao(em);
			clienteDao.cadastra(cliente);
			
			ProdutoDao produtoDao = new ProdutoDao(em);
			
			Produto iphone = new Produto("iphone 12", new BigDecimal(10000));
			produtoDao.cadastra(iphone);
			
			Produto mouse = new Produto("mouse", new BigDecimal(300));
			produtoDao.cadastra(mouse);
			
			Produto teclado = new Produto("teclado", new BigDecimal(300));
			produtoDao.cadastra(teclado);
			
			Produto monitor = new Produto("monitor", new BigDecimal(1500));
			produtoDao.cadastra(monitor);
			
			Pedido pedido = new Pedido(LocalDate.now(), Situacao.ABERTO, cliente);
			pedido.adicionaItem(iphone, 1, new BigDecimal(10000));
			pedido.adicionaItem(mouse, 1, new BigDecimal(300));
			pedido.adicionaItem(teclado, 2, new BigDecimal(3000));
			pedido.adicionaItem(monitor, 1, new BigDecimal(3000));
			
			PedidoDao pedidoDao = new PedidoDao(em);
			pedidoDao.cadastra(pedido);
				
			em.getTransaction().commit();
			
			
			//--------------------------------------------------------------------
			
			
			em.getTransaction().begin();
		
			Pedido pedidoExistente = pedidoDao.getById(1L);
			pedidoExistente.getItens().remove(1);
			pedidoDao.altera(pedidoExistente);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}
}
