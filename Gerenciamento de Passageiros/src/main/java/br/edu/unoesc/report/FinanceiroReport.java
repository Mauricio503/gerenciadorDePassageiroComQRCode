package br.edu.unoesc.report;

import java.io.InputStream;
import java.util.List;

import br.edu.unoesc.model.Financeiro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FinanceiroReport {

	
	public void gerarRelatorio(List<Financeiro> financeiro) throws JRException{
		
		InputStream fonte = FinanceiroReport.class.getResourceAsStream("/report/financeiro.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(fonte);
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(financeiro));
		JasperViewer.viewReport(print,false);
	}
}
