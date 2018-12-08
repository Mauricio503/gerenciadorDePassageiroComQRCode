package br.edu.unoesc.servlet;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import br.edu.unoesc.DAO.ClienteDAO;
import br.edu.unoesc.DAO.DaoFactory;


@WebServlet("/QRCode")
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private ClienteDAO clienteDao = DaoFactory.get().clienteDao();
	
    public QRCodeServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("codigo") != null){
			String url = "http://172.18.19.189:8081/projetoSite2/controlePresenca?codigo="+request.getParameter("codigo");
			File arquivo = new File("arquivo.png");
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix;
			try {
				bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 350, 350);
				ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
				FileOutputStream fos = new FileOutputStream(arquivo);
				fos.write(pngOutputStream.toByteArray());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao Gerar QRCode","Mensagem", JOptionPane.INFORMATION_MESSAGE);
			}  
			int tamanho = (int) arquivo.length();

			response.setContentType("image/png"); 
			response.setContentLength(tamanho); 
			response.setHeader("Content-Disposition", "attachment; filename=QRCode.png");

			OutputStream output = response.getOutputStream();
			Files.copy(arquivo.toPath(), output);
		} else {
			request.setAttribute("clientes", clienteDao.listar());
			RequestDispatcher dispatcher = request.getRequestDispatcher("QRCode/gerador.jsp");
			dispatcher.forward(request, response);
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
