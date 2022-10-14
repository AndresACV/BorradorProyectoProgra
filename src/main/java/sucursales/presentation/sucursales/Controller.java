package sucursales.presentation.sucursales;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import java.util.List;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) throws Exception {
        Sucursal s = new Sucursal();
        s.setReferencia("");
        model.setSucursales(Service.instance().sucursalesSearch(s));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(String filtro) throws Exception {
        Sucursal s = new Sucursal();
        s.setReferencia("");
        List<Sucursal> rows = Service.instance().sucursalesSearch(s);
        model.setSucursales(rows);
        model.commit();
    }

    public void eliminar(String codigo) throws Exception {
        List<Sucursal> rows = Service.instance().eliminarSucursal(codigo);
        model.setSucursales(rows);
        this.buscar("");
        model.commit();
    }

    public void preAgregar(){
        Application.controllerSucursal.preAgregar();
    }

    public void editar(int row){
        String referencia = model.getSucursales().get(row).getReferencia();
        Sucursal e=null;
        try {
//            e= Service.instance().sucursalGet(referencia);
            Application.controllerSucursal.editar(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Cell getCell(Paragraph paragraph, TextAlignment alignment, boolean hasBorder) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        if(!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    private Cell getCell(Image image, HorizontalAlignment alignment, boolean hasBorder) {
        Cell cell = new Cell().add(image);
        image.setHorizontalAlignment(alignment);
        cell.setPadding(0);
        if(!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    public void imprimir()throws Exception{
        String dest="sucursales.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);
        document.setMargins(10, 10, 10, 10);

        Table header = new Table(1);
        Image image = new Image(ImageDataFactory.create("src/main/resources/CompanyLogo.jpg"));
        image = image.scaleToFit(300, 300);

        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        header.addCell(getCell(new Paragraph("SECR: Sucursales y Empleados Costa Rica").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER,false));
        header.addCell(getCell(image, HorizontalAlignment.CENTER,false));
        document.add(header);

        document.add(new Paragraph(""));document.add(new Paragraph(""));

        Color bkg = ColorConstants.BLUE;
        Color frg= ColorConstants.BLACK;
        Table body = new Table(4);
        body.setWidth(500);
        body.setHorizontalAlignment(HorizontalAlignment.CENTER);
        body.addCell(getCell(new Paragraph("Codigo").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Referencia").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("Direccion").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(getCell(new Paragraph("%Zonaje").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));

        for(Sucursal s: model.getSucursales()){
            body.addCell(getCell(new Paragraph(s.getCodigo()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(s.getReferencia()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(s.getDireccion()),TextAlignment.CENTER,true));
            body.addCell(getCell(new Paragraph(String.valueOf(s.getPorcentajeZonaje())),TextAlignment.CENTER,true));
        }

        document.add(body);
        document.close();
    }
}