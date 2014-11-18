package dk.jungdal;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;

/**
 */
public class Printere extends Base {
    /**
     * Constructor
     */
    public Printere()
    {
       
    	 
    	RepeatingView list = new RepeatingView("list");

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of printers configured: " + printServices.length);
 
        for (PrintService printer : printServices)
            System.out.println("Printer: " + printer.getName());
 
   	
    	
    	PrintService[] ser = PrintServiceLookup.lookupPrintServices(null, null);
    	 
    	        for (int i = 0; i < ser.length; ++i) {
    	            String p_name = ser[i].getName();
    	        	list.add(new Label(list.newChildId(), p_name));    	        
    	        }
    	 
    	add(list);
    	
    	Link status = new Link("status") {
            public void onClick() {
                setResponsePage(ImagePrinter.class);
            }
        };
        add(status);

        Link jobs = new Link("jobs") {
            public void onClick() {
                setResponsePage(Jobs.class);
            }
        };
        add(jobs);

        Link printere = new Link("printere") {
            public void onClick() {
                setResponsePage(Printere.class);
            }
        };
        add(printere);

        Link about = new Link("about") {
            public void onClick() {
                setResponsePage(About.class);
            }
        };
        add(about);

    }
}
