package spring.quartz;

import java.util.Map.Entry;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//@Service("myJob")
public class MyJob implements Job{
	

    @Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
    	Set<Entry<String, Object>> entrySet = jobDataMap.entrySet();
    	Notice notice = null;
    	for (Entry<String, Object> entry : entrySet) {
    		if (entry.getKey().equals("notice")) {
    			notice = (Notice)entry.getValue();
    		}
    	}
    	if (notice != null) {
//    		System.out.println(Thread.currentThread().getName() + "->" + DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ": " + notice);
    	}
    }
}
