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
package org.alfresco.event;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.event.model.HierarchyEntry;
import org.apache.commons.io.IOUtils;

public class TestUtil
{
    public static String getFile(String fileName)
    {

        String result = "";

        ClassLoader classLoader = TestUtil.class.getClassLoader();

        try
        {
          result = IOUtils.toString(classLoader.getResourceAsStream(fileName), StandardCharsets.UTF_8.name());
        } 
        catch (IOException e)
        {
          e.printStackTrace();
        }

        return result;
    }

    public static List<HierarchyEntry> getTestNodeHierarchy()
    {
        ArrayList<HierarchyEntry> primaryHierarchy = new ArrayList<>();
        primaryHierarchy.add(new HierarchyEntry("c1e352f2-bd5c-490f-ad99-0701203ed1d5", "Node"));
        primaryHierarchy.add(new HierarchyEntry("1b5b2150-2b05-43eb-87f7-c3e5120968e7", "Node"));
        return primaryHierarchy;
    }

    public static List<HierarchyEntry> getTestProcessHierarchy()
    {
        ArrayList<HierarchyEntry> primaryHierarchy = new ArrayList<>();
        primaryHierarchy.add(new HierarchyEntry("appId1", "Application"));
        primaryHierarchy.add(new HierarchyEntry("processDef1", "ProcessDefinition"));
        return primaryHierarchy;
    }
}