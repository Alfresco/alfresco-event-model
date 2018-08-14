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
public class VariableResourceV1<T> extends ActivitiCloudRuntimeResourceV1
{
    private String name;
    private String type;
    private String processInstanceId;
    private Boolean taskVariable;
    // Only relevant for certain events e.g. the filed won't be
    // included in the Variable Deleted event.
    @JsonInclude(Include.NON_NULL)
    private T value;

    public VariableResourceV1()
    {
    }

    public VariableResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, VariableResourceV1.class, primaryHierarchy);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getProcessInstanceId()
    {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }

    public Boolean getTaskVariable()
    {
        return taskVariable;
    }

    public void setTaskVariable(Boolean taskVariable)
    {
        this.taskVariable = taskVariable;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), name, type, processInstanceId, taskVariable, value);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof VariableResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        VariableResourceV1<?> that = (VariableResourceV1<?>) obj;
        return Objects.equals(name, that.name)
                    && Objects.equals(type, that.type)
                    && Objects.equals(processInstanceId, that.processInstanceId)
                    && Objects.equals(taskVariable, that.taskVariable)
                    && Objects.equals(value, that.value);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"VariableResourceV1\": ")
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
                    .append(", \"type\": ").append("\"" + this.type + "\"")
                    .append(", \"processInstanceId\": ").append("\"" + this.processInstanceId + "\"")
                    .append(", \"taskVariable\": ").append(this.taskVariable)
                    .append(", \"value\": ").append((this.value instanceof Number ? this.value : "\"" + this.value + "\""))
                    .append("}}");
        return builder.toString();
    }
}
