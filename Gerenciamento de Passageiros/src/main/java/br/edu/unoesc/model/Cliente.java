package br.edu.unoesc.model;


import org.bson.types.ObjectId;


public class Cliente {

	private ObjectId id;
	private String nomeCompleto;
	private String cpf;
	private String endereco;
	private String cidade;
	private String cep;
	private String email;
	private String telefone;
	private Long valor;
	private boolean gerarCobranca;

	
	public Cliente() {
	}

	public Cliente(ObjectId id, String nomeCompleto, String cpf, String endereco, String cidade, String cep,
			String email, String telefone, Long valor, boolean gerarCobranca) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cidade = cidade;
		this.cep = cep;
		this.email = email;
		this.telefone = telefone;
		this.valor = valor;
		this.gerarCobranca = gerarCobranca;
	}

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public boolean isGerarCobranca() {
		return gerarCobranca;
	}

	public void setGerarCobranca(boolean gerarCobranca) {
		this.gerarCobranca = gerarCobranca;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
