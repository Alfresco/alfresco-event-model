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

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * EventType
 *
 * @author Jared Ottley
 */
@JsonTypeInfo(use = Id.CLASS, include = As.EXISTING_PROPERTY, visible = true, property = "schema")
public class EventV1<R extends ResourceV1>
{
    // The property name (schema) is used in the JsonTypeInfo
    private String schema;
    private String type;
    private String streamPosition;
    private String principal;
    private R resource;

    public EventV1()
    {
        //NOOP
    }

    public EventV1(String type, String streamPosition, String principal, R resource)
    {
        this(type, EventV1.class, streamPosition, principal, resource);
    }

    public EventV1(String type, Class<? extends EventV1> schema, String streamPosition, String principal, R resource)
    {
        this.type = type;
        this.schema = schema.getName();
        this.streamPosition = streamPosition;
        this.principal = principal;
        this.resource = resource;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema(String schema)
    {
        this.schema = schema;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public String getStreamPosition()
    {
        return streamPosition;
    }

    public void setStreamPosition(String streamPosition)
    {
        this.streamPosition = streamPosition;
    }

    public void setPrincipal(String principal)

    {
        this.principal = principal;
    }

    public String getPrincipal()
    {
        return principal;
    }

    public void setResource(R resource)
    {
        this.resource = resource;
    }

    public R getResource()
    {
        return resource;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, schema, streamPosition, principal, resource);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof EventV1))
        {
            return false;
        }
        EventV1<?> event = (EventV1<?>) obj;
        return Objects.equals(type, event.type)
                    && Objects.equals(schema, event.schema)
                    && Objects.equals(streamPosition, event.streamPosition)
                    && Objects.equals(principal, event.principal)
                    && Objects.equals(resource, event.resource);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"EventV1\": ")
                .append(", \"type\": ").append("\"" + this.type + "\"")
                .append(", \"schema\": ").append("\"" + this.schema + "\"")
                .append(", \"streamPosition\": ").append("\"" + this.streamPosition + "\"")
                .append(", \"principal\": ").append("\"" + this.principal + "\"")
                .append(", \"resource\": ").append("\"" + (this.resource == null ? "null" : this.resource.toString()) + "\"")
                .append("}}");
        return builder.toString();
    }
}