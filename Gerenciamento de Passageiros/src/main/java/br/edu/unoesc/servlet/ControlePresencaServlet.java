package br.edu.unoesc.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import br.edu.unoesc.model.Cliente;
import br.edu.unoesc.model.ControlePresenca;

@WebServlet("/controlePresenca")
public class ControlePresencaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControlePresencaDAO controlePresencaDao = DaoFactory.get().controlePresencaDao();
	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
	private String dataPesquisada;
       
    public ControlePresencaServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date data = new Date();
		ControlePresenca controlePresenca = new ControlePresenca();
		if(request.getParameter("codigo") != null){
			ObjectId codigo = new ObjectId(request.getParameter("codigo"));	
			Cliente cliente = new Cliente();
			cliente = clienteDao.get(codigo);
			ControlePresenca presenca = controlePresencaDao.consultarClienteEData(cliente, data);
			if(presenca == null){
				controlePresenca = new ControlePresenca(null,cliente,data,true);
			}else if(presenca.isIndo_voltando() == false){
				controlePresenca = new ControlePresenca(null,cliente,data,true);
			} else {
				controlePresenca = new ControlePresenca(null,cliente,data,false);
			}
			controlePresencaDao.inserir(controlePresenca);
			RequestDispatcher dispatcher = request.getRequestDispatcher("controlePresenca/index.jsp");
			dispatcher.forward(request, response);
		}
		
		if(dataPesquisada == null){
			request.setAttribute("listaControlePresenca", controlePresencaDao.listarPorData(data));
			request.setAttribute("data", LocalDate.now());
		} else {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date dataFormatada = null;
			
				try {
					dataFormatada = formato.parse(dataPesquisada);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
			request.setAttribute("listaControlePresenca", controlePresencaDao.listarPorData(dataFormatada));
			request.setAttribute("data",dataPesquisada);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("controlePresenca/lista.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		dataPesquisada = request.getParameter("data");
		response.sendRedirect("controlePresenca");
	}

}
