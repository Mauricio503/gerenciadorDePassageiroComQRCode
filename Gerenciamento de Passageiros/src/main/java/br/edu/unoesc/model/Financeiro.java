package br.edu.unoesc.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class Financeiro {
	
	private ObjectId id;
	private Date data;
	private Cliente cliente;
	private String mes;
	private Integer ano;
	private boolean pago;
	
	public Financeiro() {
	}


	public Financeiro(ObjectId id, Date data, Cliente cliente, String mes, Integer ano, boolean pago) {
		super();
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.mes = mes;
		this.ano = ano;
		this.pago = pago;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

}
