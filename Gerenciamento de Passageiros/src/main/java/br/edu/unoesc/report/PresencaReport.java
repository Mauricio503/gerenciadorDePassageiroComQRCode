package br.edu.unoesc.report;

import java.io.InputStream;
import java.util.List;

import br.edu.unoesc.model.ControlePresenca;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class PresencaReport {
	
public void gerarRelatorio(List<ControlePresenca> controlePresenca) throws JRException{
		
		InputStream fonte = PresencaReport.class.getResourceAsStream("/report/presenca.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(fonte);
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(controlePresenca));
		JasperViewer.viewReport(print,false);
	}
}
