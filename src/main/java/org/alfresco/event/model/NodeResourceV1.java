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
 * Node resource object implementation
 */
public class NodeResourceV1 extends ResourceV1
{
    protected String nodeType;

    public NodeResourceV1()
    {
        //NOOP
    }

    public NodeResourceV1(String id, List<HierarchyEntry> primaryHierarchy, String nodeType)
    {
        this(id, NodeResourceV1.class, primaryHierarchy, nodeType);
    }

    public NodeResourceV1(String id, Class<? extends ResourceV1> schema, List<HierarchyEntry> primaryHierarchy, String nodeType)
    {
        super(id, schema, primaryHierarchy);
        this.nodeType = nodeType;
    }

    public String getNodeType()
    {
        return nodeType;
    }

    public void setNodeType(String nodeType)
    {
        this.nodeType = nodeType;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), nodeType);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof NodeResourceV1))
        {
            return false;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        NodeResourceV1 that = (NodeResourceV1) obj;
        return Objects.equals(nodeType, that.nodeType);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"NodeResourceV1\": ")
                .append("{\"id\": ").append("\"" + this.id + "\"")
                .append(", \"schema\": ").append("\"" + this.schema + "\"")
                .append(", \"primaryHierarchy\": ").append((this.primaryHierarchy == null ? "null" : "\"" + this.primaryHierarchy.toString() + "\""))
                .append(", \"nodeType\": ").append("\"" + this.nodeType + "\"")
                .append("}}");
        return builder.toString();
    }
}