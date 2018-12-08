package br.edu.unoesc.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class ControlePresenca {
	
	private ObjectId id;
	private Cliente cliente;
	private Date data;
	private boolean indo_voltando;
	
	
	public ControlePresenca() {
	}

	public ControlePresenca(ObjectId id, Cliente cliente, Date data, boolean indo_voltando) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.data = data;
		this.indo_voltando = indo_voltando;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isIndo_voltando() {
		return indo_voltando;
	}

	public void setIndo_voltando(boolean indo_voltando) {
		this.indo_voltando = indo_voltando;
	}
	
}
