/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.Conexao;

/**
 *
 * @author carol
 */
@Named
@SessionScoped
public class RelatorioControle implements Serializable {
    
    private String filtro;
    private Date dataInicial;
    private Date dataFinal;
    private String cpfcnpj;

    public void gerarRelatorioCidades() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelCidade.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("filtro","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
    public void gerarRelatorioEstado() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelEstado.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
    public void gerarRelatorioGrupoProduto() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelGrupoProduto.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
//            p.put("param","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
    public void gerarRelatorioGrupoServico() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelGrupoServico.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("filtro","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
     public void gerarRelatorioTaxaCartao() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelTaxaCartao.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("filtro","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
     public void gerarRelatorioServico() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelServico.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("filtro","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
    
     public void gerarRelatorioProduto() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelProduto.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("filtro","%"+ filtro +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
     
     public void gerarRelatorioColaborador() {
        try {
            JasperReport relatorio;
            String arquivoJasper = "RelColaborador.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();
            p.put("filtro","%"+ filtro +"%");
            p.put("cpfcnpj","%"+ cpfcnpj +"%");
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }
     public void gerarRelatorioOS() {
        try {            
            //gera relatorio com as classes do jasper
            HashMap p = new HashMap();            
            if (dataInicial != null && dataFinal != null) {
                p.put("datainicial", dataFormatada(dataInicial));
                p.put("datafinal", dataFormatada(dataFinal));
            }
            
            p.put("cliente", "%"+filtro+"%");
            
            JasperReport relatorio;
            String arquivoJasper = "relOrdemServico.jasper";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/reports/" + arquivoJasper), p, Conexao.getConnection());
            ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, dadosByte);
            exporter.exportReport();
            byte[] bytes = dadosByte.toByteArray();
            if (bytes != null && bytes.length > 0) {
                int recorte = arquivoJasper.indexOf(".");
                String nomePDF = arquivoJasper.substring(0, recorte);
                HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + nomePDF + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }
    
    
    private String dataFormatada(Date data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (data != null) {
            return format.format(data);
        } else {
            return "";
        }
    }

}
