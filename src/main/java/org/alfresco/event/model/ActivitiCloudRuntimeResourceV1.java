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

/**
 *
 * Activiti Cloud Runtime resource object implementation
 *
 * @author Jamal Kaabi-Mofrad
 */
public class ActivitiCloudRuntimeResourceV1 extends ResourceV1
{
    protected String appName;
    protected String serviceFullName;
    protected String appVersion;
    protected String serviceName;
    protected String serviceVersion;
    protected String serviceType;
    protected String entityId;
    protected Long timestamp;

    public ActivitiCloudRuntimeResourceV1()
    {
        //NOOP
    }

    public ActivitiCloudRuntimeResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, ActivitiCloudRuntimeResourceV1.class, primaryHierarchy);
    }

    public ActivitiCloudRuntimeResourceV1(String id, Class<? extends ResourceV1> schema, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, schema, primaryHierarchy);
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getServiceFullName()
    {
        return serviceFullName;
    }

    public void setServiceFullName(String serviceFullName)
    {
        this.serviceFullName = serviceFullName;
    }

    public String getAppVersion()
    {
        return appVersion;
    }

    public void setAppVersion(String appVersion)
    {
        this.appVersion = appVersion;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceVersion()
    {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion)
    {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }

    public String getEntityId()
    {
        return entityId;
    }

    public void setEntityId(String entityId)
    {
        this.entityId = entityId;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), appName, serviceFullName, appVersion,
                    serviceName, serviceVersion, serviceType, entityId, timestamp);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof ActivitiCloudRuntimeResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        ActivitiCloudRuntimeResourceV1 that = (ActivitiCloudRuntimeResourceV1) obj;
        return Objects.equals(appName, that.appName)
                    && Objects.equals(serviceFullName, that.serviceFullName)
                    && Objects.equals(appVersion, that.appVersion)
                    && Objects.equals(serviceName, that.serviceName)
                    && Objects.equals(serviceVersion, that.serviceVersion)
                    && Objects.equals(serviceType, that.serviceType)
                    && Objects.equals(entityId, that.entityId)
                    && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(200);
        builder.append("{\"ActivitiCloudRuntimeResourceV1\": ")
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
                    .append(", \"timestamp\": ").append(this.timestamp)
                    .append("}}");
        return builder.toString();
    }
}
