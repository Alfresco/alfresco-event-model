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
public class TaskCandidateResourceV1 extends ActivitiCloudRuntimeResourceV1
{
    private String taskId;
    // Only relevant for TASK_CANDIDATE_GROUP_...
    private String groupId;
    // Only relevant for TASK_CANDIDATE_USER_...
    private String userId;

    public TaskCandidateResourceV1()
    {
    }

    public TaskCandidateResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, TaskCandidateResourceV1.class, primaryHierarchy);
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), taskId, groupId, userId);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof TaskCandidateResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        TaskCandidateResourceV1 that = (TaskCandidateResourceV1) obj;
        return Objects.equals(taskId, that.taskId)
                    && Objects.equals(groupId, that.groupId)
                    && Objects.equals(userId, that.userId);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(250);
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
                    .append(", \"taskId\": ").append("\"" + this.taskId + "\"")
                    .append(", \"groupId\": ").append("\"" + this.groupId + "\"")
                    .append(", \"userId\": ").append("\"" + this.userId + "\"")
                    .append("}}");
        return builder.toString();
    }
}
