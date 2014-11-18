package dk.jungdal;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class Jobs extends Base {
 
//    public static BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(2000);

//	BlockingQueue<Message> q = new ArrayBlockingQueue<Message>(2000);

	BlockingQueue<Message> q;
	
	
	private   List<String> FileFolders = new ArrayList<String>();
    
    
    
	// Banana is selected by default
	private String selectedFolder = "";
	private Printer pr;
	public Jobs(final PageParameters parameters) throws IOException
    {
 
		
			
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

		File f = new File("/Users/peterjungdal/Documents/hotfolder"); // current directory

		FileFilter directoryFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};

		File[] files = f.listFiles(directoryFilter);
		for (File file : files) {
			if (file.isDirectory()) {
				FileFolders.add(new String(file.getCanonicalPath()));
			}
		}
		
	 
	 
			add(new FeedbackPanel("feedback"));
	 
			ListChoice<String> listFolders = new ListChoice<String>("Folder",
					new PropertyModel<String>(this, "selectedFolder"), FileFolders);
	 
			listFolders.setMaxRows(FileFolders.size());
	 
			Form<?> form1 = new Form<Void>("form1") {
				@Override
				protected void onSubmit() {

					
			Scheduler scheduler;
				try {

					
					scheduler = StdSchedulerFactory.getDefaultScheduler();

			         if (!scheduler.checkExists(new JobKey("PrinterJob","group1")))
			         {
			        		pr=new Printer("Lexmark_X543")	;
			         }

			      JobDetail jd = scheduler.getJobDetail(new JobKey("PrinterJob","group1"));
			      q=(BlockingQueue<Message>) jd.getJobDataMap().get("queue") ;  
			         
//					JobDataMap map = new JobDataMap(scheduler.getJobDetail(new JobKey("PrinterJob","group1")).getJobDataMap());

				} catch (SchedulerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					info("Printer : " + selectedFolder);
					File dir = new File(selectedFolder);
					String[] extensions = new String[] { "tif", "bmp", "jpg" };
					List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
					for (File file : files) {

						

			                try {
								q.add(new Message(file.getCanonicalPath()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						
					}
 
					
				}
			};
	 
			add(form1);
			form1.add(listFolders);
	 
        


    }
    
    }



