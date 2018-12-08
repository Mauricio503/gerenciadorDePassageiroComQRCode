package br.edu.unoesc.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import br.edu.unoesc.DAO.ControlePresencaDAO;
import br.edu.unoesc.DAO.DaoFactory;
import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.ControlePresenca;
import br.edu.unoesc.model.Meses;
import br.edu.unoesc.report.PresencaReport;
import net.sf.jasperreports.engine.JRException;

@WebServlet("/relatorioPresenca")
public class RelatorioControlePresencaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioControlePresencaServlet() {
    }
    
	private ControlePresencaDAO controlePresencaDao = DaoFactory.get().controlePresencaDao();
	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("clientes", clienteDao.listar());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("relatorioPresenca/gerar.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ControlePresenca> controlePresencaList = null;
		if(!request.getParameter("dataInicial").isEmpty() && !request.getParameter("dataFinal").isEmpty()
				&& !request.getParameter("cliente").isEmpty()){
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			ObjectId codigo = new ObjectId(request.getParameter("cliente"));
			Cliente cliente = clienteDao.get(codigo);
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dataI = null;
			Date dataF = null;
				try {
					dataI = formato.parse(dataInicial);
					dataF = formato.parse(dataFinal);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			controlePresencaList = 	controlePresencaDao.listarPorDuasDatasECliente(dataI, dataF, cliente);
		}else if(!request.getParameter("dataInicial").isEmpty() && !request.getParameter("dataFinal").isEmpty()
				&& request.getParameter("cliente").isEmpty()){
			String dataInicial = request.getParameter("dataInicial");
			String dataFinal = request.getParameter("dataFinal");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dataI = null;
			Date dataF = null;
			
				try {
					dataI = formato.parse(dataInicial);
					Calendar c = Calendar.getInstance();
					c.setTime(dataI);
					c.add(Calendar.DATE, -1);
					dataI = c.getTime();
					
					dataF = formato.parse(dataFinal);
					Calendar e = Calendar.getInstance();
					e.setTime(dataF);
					e.add(Calendar.DATE, +1);
					dataF = e.getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			controlePresencaList = 	controlePresencaDao.listarPorDuasDatas(dataI, dataF);
		} else if(request.getParameter("dataInicial").isEmpty() && request.getParameter("dataFinal").isEmpty()
				&& !request.getParameter("cliente").isEmpty()){
			ObjectId codigo = new ObjectId(request.getParameter("cliente"));
			Cliente cliente = clienteDao.get(codigo);
			controlePresencaList = 	controlePresencaDao.listarPorCliente(cliente);
		}
		
		
		PresencaReport presenca = new PresencaReport();
		try {
			presenca.gerarRelatorio(controlePresencaList);
		} catch (JRException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("relatorioPresenca");
	}

}
