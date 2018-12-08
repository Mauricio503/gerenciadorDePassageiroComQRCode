package br.edu.unoesc.DAO;


public class DaoFactory {
	
private static DaoFactory daoFactory;
	
	public static DaoFactory get(){
		if(daoFactory == null){
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}

	public ClienteDAO clienteDao() {
		return new ClienteJDBC();
	}
	
	
	public FinanceiroDAO financeiroDao(){
		return new FinanceiroJDBC();
	}
	
	public ControlePresencaDAO controlePresencaDao(){
		return new ControlePresencaJDBC();
	}
}
