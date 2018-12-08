package br.edu.unoesc.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import br.edu.unoesc.DAO.ClienteDAO;
import br.edu.unoesc.DAO.DaoFactory;
import br.edu.unoesc.DAO.FinanceiroDAO;
import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.Financeiro;
import br.edu.unoesc.model.Meses;

@WebServlet("/financeiroCobranca")
public class FinanceiroCobrancaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FinanceiroDAO financeiroDao = DaoFactory.get().financeiroDao();
	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
	private String mes;
	private Integer ano;
	
    public FinanceiroCobrancaServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("codigo") != null && request.getParameter("opcao") == null){
			ObjectId codigo = new ObjectId(request.getParameter("codigo"));
			Financeiro finan = new Financeiro();
			finan = financeiroDao.get(codigo);
			if(finan.isPago() == false){
				finan.setPago(true);
			} else {
				finan.setPago(false);
			}
			financeiroDao.alterar(finan);
		}
		if(request.getParameter("codigo") != null && request.getParameter("opcao") != null){
			ObjectId codigo = new ObjectId(request.getParameter("codigo"));
			financeiroDao.excluir(codigo);
		}
		
		request.setAttribute("ano", LocalDate.now().getYear());
		request.setAttribute("meses", Meses.values());
		if(request.getParameter("mes") != null && request.getParameter("ano") != null){
			mes = request.getParameter("mes");
			ano = Integer.parseInt(request.getParameter("ano"));
		}
		request.setAttribute("mes", mes);
		if(mes != null && ano != null){
			request.setAttribute("listaFinanceiro",financeiroDao.listarMesAndAno(mes,ano));
			mes = null;
			ano = null;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("financeiroCobranca/formulario.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		mes = request.getParameter("mes");
		ano = Integer.parseInt(request.getParameter("ano"));
		List<Financeiro> listaFinanceiro = financeiroDao.listarMesAndAno(mes,ano);
		List<Cliente> listaCliente = clienteDao.listar();
		boolean financeiroCliente = false;
		
		for (Cliente cliente : listaCliente) {
			for (Financeiro financeiro2 : listaFinanceiro) {
				if(cliente.getId() == financeiro2.getCliente().getId()
						&& cliente.isGerarCobranca() == true){
					financeiroCliente = true;
				}
			}
			if(financeiroCliente != false || listaFinanceiro.isEmpty() && cliente.isGerarCobranca() == true){
				Date data = new Date();
				Financeiro financeiro = new Financeiro(null,data,cliente,mes,ano,false);
				financeiroDao.inserir(financeiro);
				financeiroCliente = false;
			}
		}
		
		response.sendRedirect("financeiroCobranca");
	}

}
