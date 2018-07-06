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
import java.util.Map;

/**
 * @author Jamal Kaabi-Mofrad
 */
public class ResourceModel
{
    /**
     * Event model to test inner classes type handling
     *
     * @author Jamal Kaabi-Mofrad
     */
    public static class NodeResourceExtended extends NodeResourceV1
    {
        private Map<String, String> extraProps;

        public NodeResourceExtended()
        {
        }

        public NodeResourceExtended(String id, List<HierarchyEntry> primaryHierarchy, String nodeType, Map<String, String> extraProps)
        {
            super(id, NodeResourceExtended.class, primaryHierarchy, nodeType);
            this.extraProps = extraProps;
        }

        public Map<String, String> getExtraProps()
        {
            return extraProps;
        }

        public void setExtraProps(Map<String, String> extraProps)
        {
            this.extraProps = extraProps;
        }
    }
}
