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
public class PermissionResourceV1 extends NodeResourceV1
{
    private String authority;
    private String permission;
    // Only relevant for Inherit Permissions Disabled Event
    private Boolean async;

    public PermissionResourceV1()
    {
        //NOOP
    }

    public PermissionResourceV1(String id, List<HierarchyEntry> primaryHierarchy, String nodeType)
    {
        super(id, PermissionResourceV1.class, primaryHierarchy, nodeType);
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public Boolean getAsync()
    {
        return async;
    }

    public void setAsync(Boolean async)
    {
        this.async = async;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), authority, permission, async);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof PermissionResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        PermissionResourceV1 that = (PermissionResourceV1) obj;
        return Objects.equals(authority, that.authority)
                    && Objects.equals(permission, that.permission)
                    && Objects.equals(async, that.async);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(300);
        sb.append("{\"PermissionResourceV1\": ")
                    .append("{\"id\": ").append("\"" + this.id + "\"")
                    .append(", \"schema\": ").append("\"" + this.schema + "\"")
                    .append(", \"primaryHierarchy\": ").append((this.primaryHierarchy == null ? "null" : "\"" + this.primaryHierarchy.toString() + "\""))
                    .append(", \"nodeType\": ").append("\"" + this.nodeType + "\"")
                    .append(", \"authority\": ").append("\"" + this.authority + "\"")
                    .append(", \"permission\": ").append("\"" + this.permission + "\"")
                    .append(", \"async\": ").append("\"" + this.async + "\"")
                    .append("}}");
        return sb.toString();
    }
}
