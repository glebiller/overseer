package fr.kissy.overseer.dto;

import org.quartz.JobKey;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
public class JobKeyDTO {
    private String name;
    private String group;

    public JobKeyDTO() {
    }

    public JobKeyDTO(JobKey key) {
        this.name = key.getName();
        this.group = key.getGroup();
    }

    public JobKeyDTO(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
