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
public class SequenceFlowResourceV1 extends ActivitiCloudRuntimeResourceV1
{
    private String processInstanceId;
    private String processDefinitionId;
    private String sourceActivityElementId;
    private String sourceActivityName;
    private String sourceActivityType;
    private String targetActivityElementId;
    private String targetActivityName;
    private String targetActivityType;

    public SequenceFlowResourceV1()
    {
    }

    public SequenceFlowResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, SequenceFlowResourceV1.class, primaryHierarchy);
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionId()
    {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }

    public String getSourceActivityName()
    {
        return sourceActivityName;
    }

    public void setSourceActivityName(String sourceActivityName)
    {
        this.sourceActivityName = sourceActivityName;
    }

    public String getSourceActivityElementId()
    {
        return sourceActivityElementId;
    }

    public void setSourceActivityElementId(String sourceActivityElementId)
    {
        this.sourceActivityElementId = sourceActivityElementId;
    }

    public String getSourceActivityType()
    {
        return sourceActivityType;
    }

    public void setSourceActivityType(String sourceActivityType)
    {
        this.sourceActivityType = sourceActivityType;
    }

    public String getTargetActivityElementId()
    {
        return targetActivityElementId;
    }

    public void setTargetActivityElementId(String targetActivityElementId)
    {
        this.targetActivityElementId = targetActivityElementId;
    }

    public String getTargetActivityName()
    {
        return targetActivityName;
    }

    public void setTargetActivityName(String targetActivityName)
    {
        this.targetActivityName = targetActivityName;
    }

    public String getTargetActivityType()
    {
        return targetActivityType;
    }

    public void setTargetActivityType(String targetActivityType)
    {
        this.targetActivityType = targetActivityType;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), processInstanceId, processDefinitionId, sourceActivityElementId,
                    sourceActivityName, sourceActivityType, targetActivityElementId,
                    targetActivityName, targetActivityType);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof SequenceFlowResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        SequenceFlowResourceV1 that = (SequenceFlowResourceV1) obj;
        return Objects.equals(processInstanceId, that.processInstanceId)
                    && Objects.equals(processDefinitionId, that.processDefinitionId)
                    && Objects.equals(sourceActivityElementId, that.sourceActivityElementId)
                    && Objects.equals(sourceActivityName, that.sourceActivityName)
                    && Objects.equals(sourceActivityType, that.sourceActivityType)
                    && Objects.equals(targetActivityElementId, that.targetActivityElementId)
                    && Objects.equals(targetActivityName, that.targetActivityName)
                    && Objects.equals(targetActivityType, that.targetActivityType);
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
                    .append(", \"sourceActivityElementId\": ").append("\"" + this.sourceActivityElementId + "\"")
                    .append(", \"sourceActivityName\": ").append("\"" + this.sourceActivityName + "\"")
                    .append(", \"sourceActivityType\": ").append("\"" + this.sourceActivityType + "\"")
                    .append(", \"targetActivityElementId\": ").append("\"" + this.targetActivityElementId + "\"")
                    .append(", \"targetActivityName\": ").append("\"" + this.targetActivityName + "\"")
                    .append(", \"targetActivityType\": ").append("\"" + this.targetActivityType + "\"")
                    .append("}}");
        return builder.toString();
    }
}
