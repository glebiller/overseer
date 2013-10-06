package fr.kissy.overseer.dto;

import fr.kissy.overseer.enums.EnumStatus;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.util.List;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class EndpointDTO {
    public static final String URL_KEY = "url";
    public static final String INTERVAL_KEY = "interval";
    public static final String STATUS_KEY = "status";

    private JobKeyDTO key;
    private String description;
    private String url;
    private Integer interval;
    private EnumStatus status = EnumStatus.DOWN;
    private Boolean paused = false;

    public EndpointDTO() {
    }

    public EndpointDTO(JobDetail jobDetail, List<Trigger> triggers) {
        this.key = new JobKeyDTO(jobDetail.getKey());
        this.description = jobDetail.getDescription();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        this.url = jobDataMap.getString(URL_KEY);
        this.interval = jobDataMap.getIntValue(INTERVAL_KEY);
        this.status = EnumStatus.valueOf(jobDataMap.getString(STATUS_KEY));
        this.paused = triggers.isEmpty() || triggers.get(0).getNextFireTime() == null;
    }

    public JobKeyDTO getKey() {
        return key;
    }

    public void setKey(JobKeyDTO key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }
}
