
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Scanner;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class Invoice {
	


	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		
		String path = "C:\\Users\\lenovo\\Desktop\\Pantham_tech\\Pantham_bill.pdf";
		PdfWriter pdfwriter = new PdfWriter(path);
		PdfDocument pdfdocument = new PdfDocument(pdfwriter);
		pdfdocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfdocument);
		document.setBorder(Border.NO_BORDER);

		
		
		String imgSrc = "Images\\pantham.PNG";
		ImageData data = ImageDataFactory.create(imgSrc);
		Image image1 = new Image(data);
		image1.scaleAbsolute(260,111);
		
		
		
		String imgSrc1 = "Images\\Pantham_contacts.PNG";
		
		ImageData data1 = ImageDataFactory.create(imgSrc1);
		Image image2 = new Image(data1);
		image2.scaleAbsolute(35,70);
		
		
		
		String imgSrc2 = "Images\\Watermark_Pantham.PNG";
		PdfCanvas canvas = new PdfCanvas(pdfdocument.addNewPage());
		ImageData data2 = ImageDataFactory.create(imgSrc2);
		Image image3 = new Image(data2);
		image3.scaleAbsolute(600, 200);
		canvas.saveState();
		PdfExtGState state = new PdfExtGState();
		state.setFillOpacity(0.2f);
		canvas.setExtGState(state);
		canvas.addImage(data2, 220f, 320f, false);
		canvas.restoreState();
	
		
		// table 1
		float columnwidth[] = {200,90,80,170};
		Table t1 = new Table(columnwidth);
		
		
		System.out.println("Enter the Invoice no ");
		String invoice;
		Scanner sc = new Scanner(System.in);
		invoice = sc.next();
		String in;
		in = "Invoice No : " + invoice;
		
		
		System.out.println("Enter the Date");
		String date;
	    date = sc.next();
	    String Date;
	    Date = "Invoice Date : " + date;
	    
	
		//t1-1
		t1.addCell(new Cell(2,2).add(image1).setBackgroundColor(Color.CYAN ,0.5f).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell(1,3).add("INVOICE").setBold().setFontSize(55f).setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(Color.CYAN ,0.5f).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell(1,2).add(in).setBold().setFontSize(15f).setTextAlignment(TextAlignment.LEFT).setBackgroundColor(Color.CYAN ,0.5f).setBorder(Border.NO_BORDER));
	
		//t1-2
		t1.addCell(new Cell(0,2).add("").setBackgroundColor(Color.CYAN ,0.5f).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell(0,2).add(Date).setBold().setFontSize(15f).setTextAlignment(TextAlignment.LEFT).setBackgroundColor(Color.CYAN,0.5f).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell(1,2).add("").setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		
		//t1-3
		t1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		
		
		System.out.println("Enter The Client Name");
		String name;
		name = sc.next();
		
		System.out.println("Enter The Client Address");
		String Address;
		Address = sc.next();
	
		t1.addCell(new Cell(0,4).add("BILLED TO").setBold().setBorder(Border.NO_BORDER));
		t1.addCell(new Cell(0,4).add(name).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell(0,4).add(Address).setBorder(Border.NO_BORDER));
		
		
		System.out.println("Enter no of Client Email address to be added");
		int emailno;
		emailno = sc.nextInt();
		
		for(int i=1;i<=emailno;i++){
			
		System.out.println("Enter the " + i + " Email Address" );
		String EMAIL;
		EMAIL = sc.next();
		t1.addCell(new Cell(0,4).add(EMAIL).setBorder(Border.NO_BORDER));
		}
		
		
		
		// table 2
		float columnwidth2[] = {62,162,112,112,112};
		Table t2 = new Table(columnwidth2);
		
		//t2-1
		t2.addCell(new Cell().add("S.NO").setBackgroundColor(Color.CYAN ).setBold());
		t2.addCell(new Cell().add("DESCRIPTION").setBackgroundColor(Color.CYAN ).setBold());
		t2.addCell(new Cell().add("UNIT  COST").setBackgroundColor(Color.CYAN ).setBold());
		t2.addCell(new Cell().add("QUATITY").setBackgroundColor(Color.CYAN ).setBold());
		t2.addCell(new Cell().add("AMOUNT").setBackgroundColor(Color.CYAN ).setBold());
		
		
		int subtotal=0;
		int subQuantity =0;
		int Discount=0;
		int SGST=0;
		int CGST=0;
		int TGST=0;
		int Invoicetotal=0;
		
		System.out.println("Enter the No Items Purchased");
		int N;
		N = sc.nextInt();
		
		for(int i=1;i<=N;i++)
		{
			
			String SNO = String.valueOf(i);
			System.out.println("Enter the " + i + " Item Name");
			String item;
			item = sc.next();
			
			System.out.println("Enter the Unit cost");
			String cost;
			cost= sc.next();
			int COST = Integer.parseInt(cost);
			
			System.out.println("Enter the Quantity");
			String Quantity;
			Quantity= sc.next();
			int QUANT = Integer.parseInt(Quantity);
			
			int Total = COST * QUANT;
			String total = String.valueOf(Total);
		
			subtotal = subtotal +Total;
			subQuantity = subQuantity + QUANT;
			
			SGST = subtotal*9/100;
			CGST =  subtotal*9/100;
			Discount =  subtotal*5/100;
			
			TGST = SGST+CGST;
			
			Invoicetotal =  subtotal+TGST-Discount;
			
			
			t2.addCell(new Cell().add(SNO).setBackgroundColor(Color.CYAN , 0.2f));
			t2.addCell(new Cell().add(item).setBackgroundColor(Color.CYAN , 0.2f));
			t2.addCell(new Cell().add(cost).setBackgroundColor(Color.CYAN , 0.2f));
			t2.addCell(new Cell().add(Quantity).setBackgroundColor(Color.CYAN , 0.2f));
			t2.addCell(new Cell().add(total).setBackgroundColor(Color.CYAN , 0.2f));
		
		}
		String Sub = String.valueOf(subtotal);
		String Quan = String.valueOf(subQuantity);
		String sgst = String.valueOf(SGST);
		String cgst = String.valueOf(CGST);
		String discount = String.valueOf(Discount);
		String grandtotal= String.valueOf(Invoicetotal);
		
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("SUBQUANTITY").setBold().setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add(Quan).setBorder(Border.NO_BORDER));
		
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("SUBTOTAL").setBold().setBorder(Border.NO_BORDER));
	    t2.addCell(new Cell().add(Sub).setBorder(Border.NO_BORDER));
		
	    t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("DISCOUNT-5%").setBold().setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add(discount).setBorder(Border.NO_BORDER));
		
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("SGST-9%").setBold().setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add(sgst).setBorder(Border.NO_BORDER));
		
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("CGST-9%").setBold().setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add(cgst).setBorder(Border.NO_BORDER));
		
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		t2.addCell(new Cell().add("INVOICE TOTAL").setBold().setBorder(Border.NO_BORDER).setBackgroundColor(Color.CYAN ).setBold());
		t2.addCell(new Cell().add(grandtotal).setBorder(Border.NO_BORDER).setBackgroundColor(Color.CYAN ).setBold());
		
		
		//TABLE 3
		float columnwidth3[] = {62,140};
		Table t3 = new Table(columnwidth3);
		
		t3.addCell(new Cell(4,0).add(image2).setBorder(Border.NO_BORDER));
		t3.addCell(new Cell().add("+917995685339").setFontSize(14f).setBorder(Border.NO_BORDER));
		
		t3.addCell(new Cell().add("satyapantham1@gmail.com").setFontSize(14f).setBorder(Border.NO_BORDER));
		t3.addCell(new Cell().add("Visakhapatnam").setFontSize(14f).setBorder(Border.NO_BORDER));
		
		t3.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		
		
		Text text = new Text("\n");
		Paragraph p1 = new Paragraph().add(text);
		
		document.add(t1);
		
		document.add(p1);
		document.add(t2);
		document.add(p1);
	
		document.add(t3);
		
		document.close();
		System.out.println("Your Invoice Got generated");


	}

}
