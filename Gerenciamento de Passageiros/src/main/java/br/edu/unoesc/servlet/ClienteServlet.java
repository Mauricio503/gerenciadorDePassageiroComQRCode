package br.edu.unoesc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;


import br.edu.unoesc.DAO.ClienteDAO;
import br.edu.unoesc.DAO.DaoFactory;
import br.edu.unoesc.model.Cliente;


@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
       
    public ClienteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("codigo") != null){
			ObjectId codigo = new ObjectId(request.getParameter("codigo"));
			clienteDao.excluir(codigo);
		}
		request.setAttribute("clientes", clienteDao.listar());
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/lista.jsp");
				dispatcher.forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente clienteNovo = new Cliente();		
		
		clienteNovo.setNomeCompleto(request.getParameter("nome"));
		clienteNovo.setCpf(request.getParameter("cpf"));
		clienteNovo.setEndereco(request.getParameter("endereco"));
		clienteNovo.setCidade(request.getParameter("cidade"));
		clienteNovo.setCep(request.getParameter("cep"));
		clienteNovo.setTelefone(request.getParameter("telefone"));
		clienteNovo.setEmail(request.getParameter("email"));
		clienteNovo.setValor(Long.parseLong(request.getParameter("valor")));
		String gerarC = request.getParameter("fazerCobranca");
		if(gerarC != null){
			clienteNovo.setGerarCobranca(true);
		}else{
			clienteNovo.setGerarCobranca(false);
		}

		clienteDao.inserir(clienteNovo);
		
		response.sendRedirect("cliente");
	}

}
