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

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Resource object
 */
@JsonTypeInfo(use = Id.CLASS, include = As.EXISTING_PROPERTY, visible = true, property = "schema")
public class ResourceV1
{
    // The property name (schema) is used in the JsonTypeInfo
    protected String schema;
    protected String id;
    protected List<HierarchyEntry> primaryHierarchy;

    public ResourceV1()
    {
        //NOOP
    }

    public ResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        this(id, ResourceV1.class, primaryHierarchy);
    }

    public ResourceV1(String id, Class<? extends ResourceV1> schema, List<HierarchyEntry> primaryHierarchy)
    {
        this.id = id;
        this.schema = schema.getName();
        this.primaryHierarchy = primaryHierarchy;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema(String schema)
    {
        this.schema = schema;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<HierarchyEntry> getPrimaryHierarchy()
    {
        return primaryHierarchy;
    }

    public void setPrimaryHierarchy(List<HierarchyEntry> primaryHierarchy)
    {
        this.primaryHierarchy = primaryHierarchy;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, schema, primaryHierarchy);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof ResourceV1))
        {
            return false;
        }
        ResourceV1 resource = (ResourceV1) obj;
        return Objects.equals(id, resource.id)
                    && Objects.equals(schema, resource.schema)
                    && Objects.equals(primaryHierarchy, resource.primaryHierarchy);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"ResourceV1\": ")
                .append("{\"id\": ").append("\"" + this.id + "\"")
                .append(", \"schema\": ").append("\"" + this.schema + "\"")
                .append(", \"primaryHierarchy\": ").append((this.primaryHierarchy == null ? "null" : "\"" + this.primaryHierarchy.toString() + "\""))
                .append("}}");
        return builder.toString();
    }
}