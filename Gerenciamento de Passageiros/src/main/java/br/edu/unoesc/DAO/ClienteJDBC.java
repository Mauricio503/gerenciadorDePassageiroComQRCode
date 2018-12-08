package br.edu.unoesc.DAO;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import br.edu.unoesc.model.Cliente;
;

public class ClienteJDBC implements ClienteDAO{

	@Override
	public void inserir(Cliente cliente) {
		MongoCollection<Cliente> clientes = ConexaoMongo.db()
				.getCollection("cliente",Cliente.class);
		
		clientes.insertOne(cliente);
		
	}

	@Override
	public void alterar(Cliente cliente) {
		MongoCollection<Cliente> clientes = ConexaoMongo.db()
				.getCollection("cliente",Cliente.class);
		
		Bson update = new Document("_id",cliente.getId());
		Bson operacaoUpdate = new Document("$set",cliente);
		clientes.updateOne(update, operacaoUpdate);
	}

	@Override
	public void excluir(ObjectId codigo) {
		MongoCollection<Cliente> clientes = ConexaoMongo.db()
				.getCollection("cliente",Cliente.class);
		Bson id = new Document("_id",codigo);
		clientes.deleteOne(id);
		
	}

	@Override
	public List<Cliente> listar() {
		MongoCollection<Cliente> clientes = ConexaoMongo.db()
				.getCollection("cliente",Cliente.class);
		
		List<Cliente> clientesList = new ArrayList<>();
		for(Cliente cliente: clientes.find()){
			clientesList.add(cliente);
		};
		
		return clientesList;
	}

	@Override
	public Cliente get(ObjectId codigo) {
		MongoCollection<Cliente> clientes = ConexaoMongo.db()
				.getCollection("cliente",Cliente.class);

		Cliente clientePesquisado = null;
		for(Cliente cliente: clientes.find()){
			if(cliente.getId().equals(codigo)){
				clientePesquisado = cliente;
			}
		};
		return clientePesquisado;
	}

}
