package br.edu.unoesc.DAO;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.Financeiro;

public class FinanceiroJDBC implements FinanceiroDAO {

	@Override
	public void inserir(Financeiro entidade) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		financeiroList.insertOne(entidade);
	}

	@Override
	public void alterar(Financeiro entidade) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		Bson update = new Document("_id",entidade.getId());
		Bson operacaoUpdate = new Document("$set",entidade);
		financeiroList.updateOne(update, operacaoUpdate);
	}

	@Override
	public void excluir(ObjectId codigo) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		Bson id = new Document("_id",codigo);
		financeiroList.deleteOne(id);
	}

	@Override
	public List<Financeiro> listar() {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		List<Financeiro> financeiroLista = new ArrayList<>();
		for(Financeiro financeiro: financeiroList.find()){
			financeiroLista.add(financeiro);
		};
		
		return financeiroLista;
	}

	@Override
	public Financeiro get(ObjectId codigo) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);

		Financeiro financeiroPesquisado = null;
		for(Financeiro financeiro: financeiroList.find()){
			if(financeiro.getId().equals(codigo)){
				financeiroPesquisado = financeiro;
			}
		}
		return financeiroPesquisado;
	}

	@Override
	public List<Financeiro> listarMesAndAno(String mes, Integer ano) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		List<Financeiro> financeiroLista = new ArrayList<>();
		for(Financeiro financeiro: financeiroList.find()){
			if(financeiro.getMes().equals(mes) && financeiro.getAno().equals(ano)){
				financeiroLista.add(financeiro);
			}
		};
		
		return financeiroLista;
	}

	@Override
	public List<Financeiro> listarMesAndAnoAndCliente(String mes, Integer ano, Cliente cliente) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		List<Financeiro> financeiroLista = new ArrayList<>();
		for(Financeiro financeiro: financeiroList.find()){
			if(financeiro.getMes().equals(mes) && financeiro.getAno().equals(ano)
					&& financeiro.getCliente().getId().equals(cliente.getId())){
				financeiroLista.add(financeiro);
			}
		};
		return financeiroLista;
	}

	@Override
	public List<Financeiro> listarMesAndAnoAndClienteAndSituacao(String mes, Integer ano, Cliente cliente,
			boolean situacao) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		List<Financeiro> financeiroLista = new ArrayList<>();
		for(Financeiro financeiro: financeiroList.find()){
			if(financeiro.getMes().equals(mes) && financeiro.getAno().equals(ano)
					&& financeiro.getCliente().getId().equals(cliente.getId())
					&& financeiro.isPago() == situacao){
				financeiroLista.add(financeiro);
			}
		};
		return financeiroLista;
	}

	@Override
	public List<Financeiro> listarMesAndAnoAndSituacao(String mes, Integer ano, boolean situacao) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		List<Financeiro> financeiroLista = new ArrayList<>();
		for(Financeiro financeiro: financeiroList.find()){
			if(financeiro.getMes().equals(mes) && financeiro.getAno().equals(ano)
					&& financeiro.isPago() == situacao){
				financeiroLista.add(financeiro);
			}
		};
		return financeiroLista;
	}

	@Override
	public List<Financeiro> listarClienteAndSituacao(Cliente cliente, boolean situacao) {
		MongoCollection<Financeiro> financeiroList = ConexaoMongo.db()
				.getCollection("financeiro",Financeiro.class);
		
		List<Financeiro> financeiroLista = new ArrayList<>();
		for(Financeiro financeiro: financeiroList.find()){
			if(financeiro.getCliente().getId().equals(cliente.getId())
					&& financeiro.isPago() == situacao){
				financeiroLista.add(financeiro);
			}
		};
		return financeiroLista;
	}


}
