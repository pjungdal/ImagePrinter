package dk.jungdal;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.BlockingQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;

@DisallowConcurrentExecution
public class PrintJob implements Job {


    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        
        BlockingQueue<Message> queue = (BlockingQueue<Message>) data.get("queue");
        PrinterStatus pstat = (PrinterStatus) data.get("status");
        System.out.println(pstat.GetPrinterName());
        
        
        int size = queue.size();
        System.out.println("Size = " + size);
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        AttributeSet pas = new HashAttributeSet();
        pas.add(new PrinterName( pstat.GetPrinterName(),null));

        PrintService pss[] = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, pas);
     
        
        if (pss.length == 0)
          throw new RuntimeException("No printer services available.");
  
        try {
           while (size > 0) {
                
        	   String fileName=queue.take().getFile();
        	   System.out.println("Taking from queue: " +fileName );

        	        
                    PrintService ps = pss[0];
                    System.out.println("Printing to " + ps);
                    DocPrintJob job = ps.createPrintJob();
                    FileInputStream fin = new FileInputStream(fileName);
                    Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
//        	        PrintJobWatcher watcher = new PrintJobWatcher(job); 
//                    job.print(doc, pras);
//        	        watcher.waitForDone();

                    fin.close();
                  }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }}

    
    static class PrintJobWatcher {
        // true iff it is safe to close the print job's input stream
        boolean done = false;

     PrintJobWatcher(DocPrintJob job) {
            // Add a listener to the print job
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }
                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }
                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }
                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }
                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }
 
        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    
    }
    }


