package com.dh.meli.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.dh.meli.entity.ItemPedido;
import com.dh.meli.entity.Pedido;

public class PedidoDao {

	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastra(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void altera(Pedido pedido) {
		TypedQuery<ItemPedido> qry = this.em.createQuery("from ItemPedido where pedido.id =: pId", ItemPedido.class);
		qry.setParameter("pId", pedido.getId());
		List<ItemPedido> itensBD = qry.getResultList();
		List<ItemPedido> novaLista = pedido.getItens();
		
		List<ItemPedido> itensParaRemocao = obtemItensParaExclusao(itensBD, novaLista);
		itensParaRemocao.forEach(item -> removeItem(item));
		this.em.persist(pedido);
	}

	private List<ItemPedido> obtemItensParaExclusao(List<ItemPedido> itensBD, List<ItemPedido> novaLista) {
		List<ItemPedido> itensParaRemocao = itensBD.stream().filter(
				   itemBD -> {			
					return !novaLista.stream()
					  .anyMatch(novoItem -> novoItem.getId().equals(itemBD.getId()));
				   }) 
	              .collect(Collectors.toList());
		return itensParaRemocao;
	}
	
	private void removeItem(ItemPedido item) {
		item = this.em.merge(item);
		this.em.remove(item);
	}

	public void exclui(Pedido pedido) {
		this.em.remove(pedido);
	}
	
	public Pedido getById(Long id) {
		return this.em.find(Pedido.class, id);
	}
}
