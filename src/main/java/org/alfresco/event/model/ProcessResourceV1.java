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

/**
 * Process resource object implementation
 */
public class ProcessResourceV1 extends ResourceV1
{
    public ProcessResourceV1()
    {
        //NOOP
    }

    public ProcessResourceV1(String id, List<HierarchyEntry> primaryHierarchy)
    {
        super(id, ProcessResourceV1.class, primaryHierarchy);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"ProcessResourceV1\": ")
                .append("{\"id\": ").append("\"" + this.id + "\"")
                .append(", \"schema\": ").append("\"" + this.schema + "\"")
                .append(", \"primaryHierarchy\": ").append((this.primaryHierarchy == null ? "null" : "\"" + this.primaryHierarchy.toString() + "\""))
                .append("}}");
        return builder.toString();
    }
}