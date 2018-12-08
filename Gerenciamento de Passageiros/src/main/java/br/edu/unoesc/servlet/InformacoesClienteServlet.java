package br.edu.unoesc.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import br.edu.unoesc.DAO.ClienteDAO;
import br.edu.unoesc.DAO.ControlePresencaDAO;
import br.edu.unoesc.DAO.DaoFactory;
import br.edu.unoesc.DAO.FinanceiroDAO;
import br.edu.unoesc.model.Cliente;

@WebServlet("/informacoesCliente")
public class InformacoesClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ControlePresencaDAO controlePresencaDao = DaoFactory.get().controlePresencaDao();
	private FinanceiroDAO financeiroDao = DaoFactory.get().financeiroDao();
	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
	
    public InformacoesClienteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectId codigo = new ObjectId(request.getParameter("codigo"));
		
		
		Cliente cliente = clienteDao.get(codigo);
		Calendar data5DiasAtras = Calendar.getInstance();
		data5DiasAtras.add(Calendar.DAY_OF_MONTH, -6);
		Date dataInicial = data5DiasAtras.getTime();
		Calendar dataDiaAtual = Calendar.getInstance();
		dataDiaAtual.add(Calendar.DAY_OF_MONTH, +1);
		Date dataFinal = dataDiaAtual.getTime();
		request.setAttribute("listaControlePresenca", controlePresencaDao.listarPorDuasDatasECliente(dataInicial,dataFinal,cliente));
		request.setAttribute("cliente", cliente);
		
		request.setAttribute("listaFinanceiro",financeiroDao.listarMesAndAnoAndCliente(numeroToString(LocalDate.now().getMonthValue()), Integer.valueOf(LocalDate.now().getYear()),
				cliente));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("informacoesCliente/lista.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	public String numeroToString(Integer numero){		
		if (numero == 1){
			return "Janeiro";
		}else if(numero == 2){
			return "Fevereiro";
		}else if(numero == 3){
			return "Março";
		}else if(numero == 4){
			return "Abril";
		}else if(numero == 5){
			return "Maio";
		}else if(numero == 6){
			return "Junho";
		}else if(numero == 7){
			return "Julho";
		}else if(numero == 8){
			return "Agosto";
		}else if(numero == 9){
			return "Setembro";
		}else if(numero == 10){
			return "Outubro";
		}else if(numero == 11){
			return "Novembro";
		}else if(numero == 12){
			return "Dezembro";
		}else{
			return null;
		}
	}
}
