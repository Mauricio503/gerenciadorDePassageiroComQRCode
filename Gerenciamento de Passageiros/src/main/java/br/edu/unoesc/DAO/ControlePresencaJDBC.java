package br.edu.unoesc.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.ControlePresenca;

public class ControlePresencaJDBC implements ControlePresencaDAO {

	@Override
	public void inserir(ControlePresenca entidade) {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);

		controlePresencaList.insertOne(entidade);

	}

	@Override
	public void alterar(ControlePresenca entidade) {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);

		Bson update = new Document("_id", entidade.getId());
		Bson operacaoUpdate = new Document("$set", entidade);
		controlePresencaList.updateOne(update, operacaoUpdate);

	}

	@Override
	public void excluir(ObjectId codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ControlePresenca> listar() {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);

		List<ControlePresenca> contList = new ArrayList<>();
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			contList.add(controlePresenca);
		}
		;

		return contList;
	}

	@Override
	public ControlePresenca get(ObjectId codigo) {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);

		ControlePresenca controlePresencaPesquisado = null;
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			if (controlePresenca.getId().equals(codigo)) {
				controlePresencaPesquisado = controlePresenca;
			}
		}
		;
		return controlePresencaPesquisado;
	}

	@Override
	public List<ControlePresenca> listarPorData(Date data) {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		List<ControlePresenca> contList = new ArrayList<>();
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			if (sdf.format(controlePresenca.getData()).equals(sdf.format(data))) {
				contList.add(controlePresenca);
			}

		}
		return contList;
	}
	
	@Override 
	public ControlePresenca consultarClienteEData(Cliente cliente, Date data){
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);
		Date date = new Date();
		ControlePresenca contPresenca = null;
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			if(controlePresenca.getCliente().getId().equals(cliente.getId()) && controlePresenca.getData().getDay() == data.getDay()
					&& controlePresenca.getData().getMonth() == data.getMonth() && controlePresenca.getData().getYear() == date.getYear()){
				contPresenca = controlePresenca;
			}
		}
		return contPresenca;
		
	}

	@Override
	public List<ControlePresenca> listarPorDuasDatasECliente(Date dataInicial,Date dataFinal, Cliente cliente) {
		
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);
		
		List<ControlePresenca> contList = new ArrayList<>();
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			if (controlePresenca.getData().after(dataInicial)
					&& controlePresenca.getData().before(dataFinal)
					&& controlePresenca.getCliente().getId().equals(cliente.getId())) {
				contList.add(controlePresenca);
			}
		}
		return contList;
	}

	@Override
	public List<ControlePresenca> listarPorDuasDatas(Date dataInicial, Date dataFinal) {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);
		List<ControlePresenca> contList = new ArrayList<>();
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			if (controlePresenca.getData().after(dataInicial)
					&& controlePresenca.getData().before(dataFinal)
					) {
				contList.add(controlePresenca);
			}
		}
		
		return contList;
	}

	@Override
	public List<ControlePresenca> listarPorCliente(Cliente cliente) {
		MongoCollection<ControlePresenca> controlePresencaList = ConexaoMongo.db().getCollection("controlePresenca",
				ControlePresenca.class);
		
		List<ControlePresenca> contList = new ArrayList<>();
		for (ControlePresenca controlePresenca : controlePresencaList.find()) {
			if (controlePresenca.getCliente().getId().equals(cliente.getId())) {
				contList.add(controlePresenca);
			}
		}
		return contList;
	}

}
