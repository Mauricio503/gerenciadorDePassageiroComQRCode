package br.edu.unoesc.servlet;

import java.io.IOException;
import java.time.LocalDate;
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
import br.edu.unoesc.report.FinanceiroReport;
import net.sf.jasperreports.engine.JRException;


@WebServlet("/relatorioFinanceiro")
public class RelatorioFinanceiroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FinanceiroDAO financeiroDao = DaoFactory.get().financeiroDao();
	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
	
    public RelatorioFinanceiroServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	request.setAttribute("ano", LocalDate.now().getYear());
    	request.setAttribute("meses", Meses.values());
    	request.setAttribute("clientes", clienteDao.listar());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("relatorioFinanceiro/gerar.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Integer ano = Integer.parseInt(request.getParameter("ano"));
		List<Financeiro> financeiroList = null;
		if(request.getParameter("mes") != null && request.getParameter("cliente").isEmpty()
				&& request.getParameter("situacao").isEmpty()){
			String mes = request.getParameter("mes");
			financeiroList = financeiroDao.listarMesAndAno(mes, ano);
		} else if (request.getParameter("mes") != null && !request.getParameter("cliente").isEmpty()
				&& request.getParameter("situacao").isEmpty()){
			String mes = request.getParameter("mes");
			ObjectId codigo = new ObjectId(request.getParameter("cliente"));
			Cliente cliente = clienteDao.get(codigo);
			financeiroList = financeiroDao.listarMesAndAnoAndCliente(mes, ano,cliente);
		} else if(request.getParameter("mes") != null && !request.getParameter("cliente").isEmpty()
				&& !request.getParameter("situacao").isEmpty()){
			String mes = request.getParameter("mes");
			ObjectId codigo = new ObjectId(request.getParameter("cliente"));
			Cliente cliente = clienteDao.get(codigo);
			String situacao = request.getParameter("situacao");
			boolean sit = true; 
			if(situacao.equals("pago")){
				sit = true; 
			} else {
				sit = false; 
			}
			financeiroList = financeiroDao.listarMesAndAnoAndClienteAndSituacao(mes, ano,cliente,sit);
		} else if (request.getParameter("mes") != null && request.getParameter("cliente").isEmpty()
				&& !request.getParameter("situacao").isEmpty()){
			String mes = request.getParameter("mes");
			String situacao = request.getParameter("situacao");
			boolean sit = true; 
			if(situacao.equals("pago")){
				sit = true; 
			} else {
				sit = false; 
			}
			financeiroList = financeiroDao.listarMesAndAnoAndSituacao(mes, ano, sit);
		}else if (request.getParameter("mes") == null && !request.getParameter("cliente").isEmpty()
				&& !request.getParameter("situacao").isEmpty()){
			ObjectId codigo = new ObjectId(request.getParameter("cliente"));
			Cliente cliente = clienteDao.get(codigo);
			String situacao = request.getParameter("situacao");
			boolean sit = true; 
			if(situacao.equals("pago")){
				sit = true; 
			} else {
				sit = false; 
			}
			financeiroList = financeiroDao.listarClienteAndSituacao(cliente, sit);
		}
		
		FinanceiroReport finan = new FinanceiroReport();
		try {
			finan.gerarRelatorio(financeiroList);
		} catch (JRException e) {	
			e.printStackTrace();
		}
		
		response.sendRedirect("relatorioFinanceiro");
		
	}

}
