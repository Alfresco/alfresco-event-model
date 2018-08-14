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

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Jamal Kaabi-Mofrad
 */
@JsonInclude(Include.NON_NULL)
public class ActivityResourceV1 extends ActivitiCloudRuntimeResourceV1
{
    // Only relevant for certain events e.g. the filed won't be
    // included in the Activity Completed event.
    private String activityName;
    private String activityType;
    private String elementId;
    private String processDefinitionId;
    private String processInstanceId;
    // Only relevant for Activity Cancelled event.
    private String cause;

    public ActivityResourceV1()
    {
    }

    public ActivityResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, ActivityResourceV1.class, primaryHierarchy);
    }

    public String getActivityName()
    {
        return activityName;
    }

    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getActivityType()
    {
        return activityType;
    }

    public void setActivityType(String activityType)
    {
        this.activityType = activityType;
    }

    public String getElementId()
    {
        return elementId;
    }

    public void setElementId(String elementId)
    {
        this.elementId = elementId;
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
        return Objects.hash(super.hashCode(), activityName, activityType, elementId,
                    processDefinitionId, processInstanceId, cause);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof ActivityResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        ActivityResourceV1 that = (ActivityResourceV1) obj;
        return Objects.equals(activityName, that.activityName)
                    && Objects.equals(activityType, that.activityType)
                    && Objects.equals(elementId, that.elementId)
                    && Objects.equals(processDefinitionId, that.processDefinitionId)
                    && Objects.equals(processInstanceId, that.processInstanceId)
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
                    .append(", \"processInstanceId\": ").append("\"" + this.processInstanceId + "\"")
                    .append(", \"activityName\": ").append("\"" + this.activityName + "\"")
                    .append(", \"activityType\": ").append("\"" + this.activityType + "\"")
                    .append(", \"elementId\": ").append("\"" + this.elementId + "\"")
                    .append(", \"cause\": ").append("\"" + this.cause + "\"")
                    .append("}}");
        return builder.toString();
    }
}
