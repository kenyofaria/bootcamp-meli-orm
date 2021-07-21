package com.dh.meli.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(name="sexo", nullable = true)
	private char sexo;
	
	private LocalDate dataNascimento;
	

	public Cliente() {
		super();
	}

	public Cliente(String nome, char sexo, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome + " " + this.sexo;
	}
}
