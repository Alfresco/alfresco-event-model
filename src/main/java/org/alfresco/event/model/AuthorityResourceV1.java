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
 * Authority resource object.
 *
 * @author Jamal Kaabi-Mofrad
 */
public class AuthorityResourceV1 extends ResourceV1
{
    private String authorityName;
    // Only relevant for Authority Added to/Removed from Group Event
    @JsonInclude(Include.NON_NULL)
    private String parentGroup;
    // Only relevant for Group Deleted Event
    @JsonInclude(Include.NON_NULL)
    private Boolean cascade;

    public AuthorityResourceV1()
    {
        //NOOP
    }

    public AuthorityResourceV1(String id, List<HierarchyEntry> primaryHierarchy, String authorityName)
    {
        super(id, AuthorityResourceV1.class, primaryHierarchy);
        this.authorityName = authorityName;
    }

    public String getAuthorityName()
    {
        return authorityName;
    }

    public void setAuthorityName(String authorityName)
    {
        this.authorityName = authorityName;
    }

    public String getParentGroup()
    {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup)
    {
        this.parentGroup = parentGroup;
    }

    public Boolean getCascade()
    {
        return cascade;
    }

    public void setCascade(Boolean cascade)
    {
        this.cascade = cascade;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), authorityName, parentGroup, cascade);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof AuthorityResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        AuthorityResourceV1 that = (AuthorityResourceV1) obj;
        return Objects.equals(authorityName, that.authorityName)
                    && Objects.equals(parentGroup, that.parentGroup)
                    && Objects.equals(cascade, that.cascade);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(250);
        sb.append("{\"AuthorityResourceV1\": ")
                    .append("{\"id\": ").append("\"" + this.id + "\"")
                    .append(", \"schema\": ").append("\"" + this.schema + "\"")
                    .append(", \"primaryHierarchy\": ").append((this.primaryHierarchy == null ? "null" : "\"" + this.primaryHierarchy.toString() + "\""))
                    .append(", \"authorityName\": ").append("\"" + this.authorityName + "\"")
                    .append(", \"parentGroup\": ").append("\"" + this.parentGroup + "\"")
                    .append(", \"cascade\": ").append("\"" + this.cascade + "\"")
                    .append("}}");
        return sb.toString();
    }
}
