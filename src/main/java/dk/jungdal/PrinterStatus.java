package dk.jungdal;

public class PrinterStatus {

private String Printer_Name;

	PrinterStatus(){
		
	}
	
	void SetPrinterName(String name){
		Printer_Name = name;
	}
	
	String GetPrinterName(){
		return (Printer_Name);
	}

}
