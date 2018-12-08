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

@WebServlet("/alterarCliente")
public class ClienteAlterarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClienteAlterarServlet() {
    }

    private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectId codigo = new ObjectId(request.getParameter("codigo"));

		request.setAttribute("cliente", clienteDao.get(codigo));
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("alterarCliente/formulario.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente clienteUpdate = new Cliente();
		ObjectId id = new ObjectId(request.getParameter("id"));		
		
		clienteUpdate.setId(id);
		clienteUpdate.setNomeCompleto(request.getParameter("nome"));
		clienteUpdate.setCpf(request.getParameter("cpf"));
		clienteUpdate.setEndereco(request.getParameter("endereco"));
		clienteUpdate.setCidade(request.getParameter("cidade"));
		clienteUpdate.setCep(request.getParameter("cep"));
		clienteUpdate.setTelefone(request.getParameter("telefone"));
		clienteUpdate.setEmail(request.getParameter("email"));
		clienteUpdate.setValor(Long.parseLong(request.getParameter("valor")));
		String gerarC = request.getParameter("fazerCobranca");
		if(gerarC != null){
			clienteUpdate.setGerarCobranca(true);
		} else{
			clienteUpdate.setGerarCobranca(false);
		}

		clienteDao.alterar(clienteUpdate);
		
		response.sendRedirect("cliente");
	}

}
