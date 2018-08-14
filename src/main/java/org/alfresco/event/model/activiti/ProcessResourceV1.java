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
package org.alfresco.event.model.activiti;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.alfresco.event.model.HierarchyEntry;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Process resource object implementation
 */
@JsonInclude(Include.NON_NULL)
public class ProcessResourceV1 extends ActivitiCloudRuntimeResourceV1
{
    private String processDefinitionId;
    // Only relevant for certain events e.g. Process Started/Created Event
    private String processDefinitionKey;
    private String status;
    private Date startDate;
    // Only relevant for Process Cancelled event.
    private String cause;

    public ProcessResourceV1()
    {
        //NOOP
    }

    public ProcessResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, ProcessResourceV1.class, primaryHierarchy);
    }

    public String getProcessDefinitionId()
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey()
    {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey)
    {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
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
        return Objects.hash(super.hashCode(), processDefinitionId, processDefinitionKey,
                    status, startDate, cause);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof ProcessResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        ProcessResourceV1 that = (ProcessResourceV1) obj;
        return Objects.equals(processDefinitionId, that.processDefinitionId)
                    && Objects.equals(processDefinitionKey, that.processDefinitionKey)
                    && Objects.equals(status, that.status)
                    && Objects.equals(startDate, that.startDate)
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
                .append(", \"processDefinitionId\": ").append("\"" + this.processDefinitionId + "\"")
                .append(", \"processDefinitionKey\": ").append("\"" + this.processDefinitionKey + "\"")
                .append(", \"status\": ").append("\"" + this.status + "\"")
                .append(", \"startDate\": ").append("\"" + this.startDate + "\"")
                .append(", \"cause\": ").append("\"" + this.cause + "\"")
                .append("}}");
        return builder.toString();
    }
}