package com.dh.meli.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.dh.meli.entity.Cliente;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Cliente cliente = new Cliente("mogueno", 'm', LocalDate.of(1995, Month.JANUARY, 21));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("eNois");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		
		em.clear();
		
		System.out.println("-----------------------------------------------------------------------");
		
		TypedQuery<Cliente> qry = em.createQuery("from Cliente", Cliente.class);
		List<Cliente> clientes = qry.getResultList();
		clientes.forEach(c -> System.out.println(c));
		
		System.out.println("-----------------------------------------------------------------------");
		
		TypedQuery<Cliente> qryNome = em.createQuery("from Cliente where nome = :pNome", Cliente.class);
		qryNome.setParameter("pNome", "mogueno");
		List<Cliente> moguenos = qryNome.getResultList();
		moguenos.forEach(System.out::println);

	}
}
