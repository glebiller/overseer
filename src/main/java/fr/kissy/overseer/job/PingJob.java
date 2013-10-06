package fr.kissy.overseer.job;

import fr.kissy.overseer.dto.EndpointDTO;
import fr.kissy.overseer.enums.EnumStatus;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class PingJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        String host = context.getMergedJobDataMap().getString(EndpointDTO.URL_KEY);
        EnumStatus pingStatus = EnumStatus.DOWN;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(host).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                pingStatus = EnumStatus.UP;
            }
        } catch (Exception e) {
        }

        LOGGER.info("Pinged {} and returned status is {}", host, pingStatus);
        context.getJobDetail().getJobDataMap().put(EndpointDTO.STATUS_KEY, pingStatus.name());
    }
}
