package br.edu.unoesc.DAO;

import java.util.Date;
import java.util.List;

import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.ControlePresenca;

public interface ControlePresencaDAO extends CrudDao<ControlePresenca> {

	List<ControlePresenca> listarPorData(Date data);
	
	List<ControlePresenca> listarPorDuasDatasECliente(Date dataInicial ,Date dataFinal,Cliente cliente);
	
	List<ControlePresenca> listarPorDuasDatas(Date dataInicial ,Date dataFinal);
	
	List<ControlePresenca> listarPorCliente(Cliente cliente);

	ControlePresenca consultarClienteEData(Cliente cliente, Date data);

}
