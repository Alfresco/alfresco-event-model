/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.event.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Jamal Kaabi-Mofrad
 */
@JsonInclude(Include.NON_NULL)
public class TaskResourceV1 extends ActivitiCloudRuntimeResourceV1
{
    private String name;
    private String processDefinitionId;
    private String processInstanceId;
    private Integer priority;
    private String status;
    private String assignee;
    private Date createdDate;
    private Date claimedDate;
    // Only relevant for Task Cancelled event.
    private String cause;

    public TaskResourceV1()
    {
    }

    public TaskResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, TaskResourceV1.class, primaryHierarchy);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProcessDefinitionId()
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getAssignee()
    {
        return assignee;
    }

    public void setAssignee(String assignee)
    {
        this.assignee = assignee;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public Date getClaimedDate()
    {
        return claimedDate;
    }

    public void setClaimedDate(Date claimedDate)
    {
        this.claimedDate = claimedDate;
    }

    public String getCause()
    {
        return cause;
    }

    public void setCause(String cause)
    {
        this.cause = cause;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), name, processDefinitionId, processInstanceId,
                    priority, status, assignee, createdDate, claimedDate, cause);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof TaskResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        TaskResourceV1 that = (TaskResourceV1) obj;
        return Objects.equals(name, that.name)
                    && Objects.equals(processDefinitionId, that.processDefinitionId)
                    && Objects.equals(processInstanceId, that.processInstanceId)
                    && Objects.equals(priority, that.priority)
                    && Objects.equals(status, that.status)
                    && Objects.equals(assignee, that.assignee)
                    && Objects.equals(createdDate, that.createdDate)
                    && Objects.equals(claimedDate, that.claimedDate)
                    && Objects.equals(cause, that.cause);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(300);
        builder.append("{\"ProcessResourceV1\": ")
                    .append("{\"id\": ").append("\"" + this.id + "\"")
                    .append(", \"schema\": ").append("\"" + this.schema + "\"")
                    .append(", \"primaryHierarchy\": ").append((this.primaryHierarchy == null ? "null" : "\"" + this.primaryHierarchy.toString() + "\""))
                    .append(", \"appName\": ").append("\"" + this.appName + "\"")
                    .append(", \"serviceFullName\": ").append("\"" + this.serviceFullName + "\"")
                    .append(", \"appVersion\": ").append("\"" + this.appVersion + "\"")
                    .append(", \"serviceName\": ").append("\"" + this.serviceName + "\"")
                    .append(", \"serviceVersion\": ").append("\"" + this.serviceVersion + "\"")
                    .append(", \"serviceType\": ").append("\"" + this.serviceType + "\"")
                    .append(", \"entityId\": ").append("\"" + this.entityId + "\"")
                    .append(", \"timestamp\": ").append("\"" + this.timestamp + "\"")
                    .append(", \"name\": ").append("\"" + this.name + "\"")
                    .append(", \"processDefinitionId\": ").append("\"" + this.processDefinitionId + "\"")
                    .append(", \"processInstanceId\": ").append("\"" + this.processInstanceId + "\"")
                    .append(", \"priority\": ").append(this.priority)
                    .append(", \"status\": ").append("\"" + this.status + "\"")
                    .append(", \"assignee\": ").append("\"" + this.assignee + "\"")
                    .append(", \"createdDate\": ").append("\"" + this.createdDate + "\"")
                    .append(", \"claimedDate\": ").append("\"" + this.claimedDate + "\"")
                    .append(", \"cause\": ").append("\"" + this.cause + "\"")
                    .append("}}");
        return builder.toString();
    }
}
