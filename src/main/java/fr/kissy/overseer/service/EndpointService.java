package fr.kissy.overseer.service;

import com.google.common.collect.Lists;
import fr.kissy.overseer.dto.EndpointDTO;
import fr.kissy.overseer.dto.JobKeyDTO;
import fr.kissy.overseer.enums.EnumStatus;
import fr.kissy.overseer.job.PingJob;
import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class EndpointService {
    @Autowired
    private Scheduler scheduler;

    @SuppressWarnings("unchecked")
    public List<EndpointDTO> list() {
        List<EndpointDTO> endpointDTOs = Lists.newArrayList();
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    endpointDTOs.add(new EndpointDTO(jobDetail, triggers));
                }
            }
        } catch (SchedulerException e) {
        }
        return endpointDTOs;
    }

    public EndpointDTO create(EndpointDTO endpointDTO) {
        JobDetail jobDetail = JobBuilder.newJob(PingJob.class)
                .withIdentity(endpointDTO.getKey().getName(), endpointDTO.getKey().getGroup())
                .withDescription(endpointDTO.getDescription())
                .usingJobData(EndpointDTO.URL_KEY, endpointDTO.getUrl())
                .usingJobData(EndpointDTO.INTERVAL_KEY, endpointDTO.getInterval())
                .usingJobData(EndpointDTO.STATUS_KEY, endpointDTO.getStatus().name())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(endpointDTO.getKey().getName(), endpointDTO.getKey().getGroup())
                .startNow().withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(endpointDTO.getInterval())
                                .repeatForever()
                ).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
        }
        return endpointDTO;
    }

    public void delete(JobKeyDTO jobKeyDTO) {
        try {
            scheduler.deleteJob(new JobKey(jobKeyDTO.getName(), jobKeyDTO.getGroup()));
        } catch (SchedulerException e) {
        }
    }
}
