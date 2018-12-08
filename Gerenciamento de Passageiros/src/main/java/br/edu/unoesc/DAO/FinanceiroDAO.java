package br.edu.unoesc.DAO;

import java.util.List;

import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.Financeiro;

public interface FinanceiroDAO extends CrudDao<Financeiro> {

	 List<Financeiro> listarMesAndAno(String mes,Integer ano);
	 
	 List<Financeiro> listarMesAndAnoAndCliente(String mes,Integer ano, Cliente cliente);
	 
	 List<Financeiro> listarMesAndAnoAndClienteAndSituacao(String mes,Integer ano, 
			 Cliente cliente, boolean situacao);
	 
	 List<Financeiro> listarMesAndAnoAndSituacao(String mes,Integer ano, boolean situacao);
	 
	 List<Financeiro> listarClienteAndSituacao(Cliente cliente, boolean situacao);
	 
}
