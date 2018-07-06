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

/**
 * Hierarchy entry
 */
public class HierarchyEntry
{
    protected String id;
    protected String type;

    public HierarchyEntry()
    {
        //NOOP
    }

    public HierarchyEntry(String id, String type)
    {
        this.id = id;
        this.type = type;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, type);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof HierarchyEntry))
        {
            return false;
        }
        HierarchyEntry that = (HierarchyEntry) obj;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"HierarchyEntry\": ")
                .append("{\"id\": ").append("\"" + this.id + "\"")
                .append(", \"type\": ").append("\"" + this.type + "\"")
                .append("}}");
        return builder.toString();
    }
}