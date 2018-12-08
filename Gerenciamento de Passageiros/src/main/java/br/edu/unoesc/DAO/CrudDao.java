package br.edu.unoesc.DAO;

import java.util.List;

import org.bson.types.ObjectId;

public interface CrudDao<T> {

	void inserir(T entidade);
	
	void alterar(T entidade);
	
	void excluir(ObjectId codigo);
	
	List<T> listar();
	
	T get(ObjectId codigo);
}
