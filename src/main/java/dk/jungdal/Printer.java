

// Start nyt Printer job
// Opret en kø, og knyt til dette job


package dk.jungdal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Printer {

public static	BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(2000);

	
	public Printer(String PrinterName){
		
		PrinterStatus pstat = new PrinterStatus();
		pstat.SetPrinterName("Lexmark_X543");

		
		try {
		Scheduler scheduler;
			scheduler = StdSchedulerFactory.getDefaultScheduler();


			JobDataMap map = new JobDataMap();
            map.put("queue", queue);
            map.put("foo", "bar");
            map.put("status", pstat);


     JobDetail job = JobBuilder.newJob(PrintJob.class)
                .withIdentity("PrinterJob", "group1")
                .usingJobData(map)
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("listUsersTrigger", "group")
                .build();

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
//        scheduler.shutdown(false);

    } catch (SchedulerException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
	
}
}
