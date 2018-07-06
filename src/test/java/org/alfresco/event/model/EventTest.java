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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.alfresco.event.TestUtil;
import org.alfresco.event.databind.EventObjectMapperFactory;
import org.alfresco.event.model.ResourceModel.NodeResourceExtended;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Event Tests
 *
 * @author Jared Ottley
 * @author Jamal Kaabi-Mofrad
 */
public class EventTest
{
    private static final ObjectMapper OBJECT_MAPPER = EventObjectMapperFactory.createInstance();

    @Test
    public void testEventEquals()
    {
        ResourceV1 resource = new ResourceV1();
        EventV1<ResourceV1> event1 = new EventV1<>("TestType", "1520534888663-k8T3",
                    "PrincipalTest", resource);
        EventV1<ResourceV1> event2 = new EventV1<>("TestType", "1520534888663-k8T3",
                    "PrincipalTest", resource);

        assertEquals(event1, event2);

        // Change the schema
        event2.setSchema(EventTest.class.getName());
        assertNotEquals(event1, event2);
        // Reset
        event2.setSchema(event1.getSchema());
        assertEquals(event1, event2);

        // Change the resource
        event2.setResource(null);
        assertNotEquals(event1, event2);
        // Reset
        event2.setResource(resource);
        assertEquals(event1, event2);

        // Change the type
        event2.setType("ModifiedType");
        assertNotEquals(event1, event2);
        // Reset
        event2.setType(event1.getType());
        assertEquals(event1, event2);

        // Change the streamPosition
        event2.setStreamPosition(System.currentTimeMillis() + "-A1B2");
        assertNotEquals(event1, event2);
        // Reset
        event2.setStreamPosition(event1.getStreamPosition());
        assertEquals(event1, event2);

        // Change the Principal
        event2.setPrincipal("NewPrincipal");
        assertNotEquals(event1, event2);
    }

    @Test
    public void testResourceEquals()
    {
        ResourceV1 resource1 = new ResourceV1("TestResourceId", TestUtil.getTestNodeHierarchy());
        ResourceV1 resource2 = new ResourceV1("TestResourceId", TestUtil.getTestNodeHierarchy());

        assertEquals(resource1, resource2);

        // Change the schema
        resource2.setSchema(EventTest.class.getName());
        assertNotEquals(resource1, resource2);
        // Reset
        resource2.setSchema(resource1.getSchema());
        assertEquals(resource1, resource2);

        // Change the id
        resource2.setId(UUID.randomUUID().toString());
        assertNotEquals(resource1, resource2);
        // Reset
        resource2.setId(resource1.getId());
        assertEquals(resource1, resource2);

        // Change the hierarchyEntry
        List<HierarchyEntry> hierarchyEntries = new ArrayList<>(resource1.getPrimaryHierarchy());
        hierarchyEntries.add(new HierarchyEntry(UUID.randomUUID().toString(), "Node"));
        resource2.setPrimaryHierarchy(hierarchyEntries);
        assertNotEquals(resource1, resource2);
    }

    @Test
    public void tesEventMarshalling() throws Exception
    {
        ResourceV1 resource = new ResourceV1("TestResourceId", TestUtil.getTestNodeHierarchy());
        EventV1<ResourceV1> baseEvent = new EventV1<>("TestType", "1520534888663-k8T3",
                    "PrincipalTest", resource);

        String result = OBJECT_MAPPER.writeValueAsString(baseEvent);
        String baseEventJson = TestUtil.getFile("EventV1.json");

        JSONAssert.assertEquals(baseEventJson, result, false);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBaseEventUnmarshalling() throws Exception
    {
        String baseEventJson = TestUtil.getFile("EventV1.json");
        EventV1<ResourceV1> result = OBJECT_MAPPER.readValue(baseEventJson, EventV1.class);

        ResourceV1 resource = new ResourceV1("TestResourceId", TestUtil.getTestNodeHierarchy());
        EventV1<ResourceV1> baseEvent = new EventV1<>("TestType", "1520534888663-k8T3",
                    "PrincipalTest", resource);

        assertEquals(baseEvent.getType(), result.getType());
        assertEquals(baseEvent.getSchema(), result.getSchema());
        assertEquals(baseEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(baseEvent.getPrincipal(), result.getPrincipal());
        assertEquals(baseEvent.getResource(), result.getResource());
    }

    @Test
    public void testContentCreatedEventMarshalling() throws Exception
    {
        NodeResourceV1 resource = new NodeResourceV1("d6e573b7-6cbd-4913-a8a7-bb21f4f9201d", TestUtil.getTestNodeHierarchy(), "cm:content");
        EventV1<NodeResourceV1> contentCreatedEvent = new EventV1<>("CONTENT_CREATED",
                    "1520534888663-k8T3", "PrincipalTest", resource);

        String result = OBJECT_MAPPER.writeValueAsString(contentCreatedEvent);
        String contentCreatedEventJson = TestUtil.getFile("NodeResourceV1.json");

        JSONAssert.assertEquals(contentCreatedEventJson, result, false);
    }

    @Test
    public void testContentCreatedEventUnmarshalling() throws Exception
    {
        String contentCreatedEventJson = TestUtil.getFile("NodeResourceV1.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(contentCreatedEventJson, EventV1.class);

        NodeResourceV1 resource = new NodeResourceV1("d6e573b7-6cbd-4913-a8a7-bb21f4f9201d",
                    TestUtil.getTestNodeHierarchy(), "cm:content");
        EventV1<NodeResourceV1> contentCreatedEvent = new EventV1<>("CONTENT_CREATED",
                    "1520534888663-k8T3", "PrincipalTest", resource);

        assertEquals("CONTENT_CREATED", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(contentCreatedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(contentCreatedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(contentCreatedEvent.getResource(), result.getResource());
    }

    @Test
    public void testProcessStartedEventMarshalling() throws Exception
    {
        ProcessResourceV1 resource = new ProcessResourceV1("d6e573b7-6cbd-4913-a8a7-bb21f4f9201d",
                    TestUtil.getTestProcessHierarchy());
        EventV1<ProcessResourceV1> processStartedEvent = new EventV1<>("PROCESS_STARTED",
                    "1520534888663-k8T3", "PrincipalTest", resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("ProcessResourceV1.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testProcessStartedEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("ProcessResourceV1.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        ProcessResourceV1 resource = new ProcessResourceV1("d6e573b7-6cbd-4913-a8a7-bb21f4f9201d",
                    TestUtil.getTestProcessHierarchy());
        EventV1<ProcessResourceV1> processStartedEvent = new EventV1<>("PROCESS_STARTED",
                    "1520534888663-k8T3", "PrincipalTest", resource);

        assertEquals("PROCESS_STARTED", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(processStartedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(processStartedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(processStartedEvent.getResource(), result.getResource());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testPolymorphism() throws Exception
    {
        Map<String, String> props = new HashMap<>(2);
        props.put("testName", "test");
        props.put("testCreatedDate", new Date().toString());
        ResourceV1 resource = new NodeResourceExtended("TestResourceId", TestUtil.getTestNodeHierarchy(),
                    "cm:content", props);
        EventV1<ResourceV1> event = new EventV1<>("TestType", "1520534888663-k8T3",
                    "PrincipalTest", resource);

        String result = OBJECT_MAPPER.writeValueAsString(event);
        EventV1<NodeResourceExtended> unmarshallingResult = (EventV1<NodeResourceExtended>) OBJECT_MAPPER.readValue(result, EventV1.class);

        assertNotNull(unmarshallingResult);
        assertEquals(EventV1.class.getName(), unmarshallingResult.getClass().getName());
        assertNotNull(unmarshallingResult.getResource());
        assertEquals("The SubType is not correct.", NodeResourceExtended.class.getName(),
                    unmarshallingResult.getResource().getClass().getName());
        assertEquals(event.getSchema(), unmarshallingResult.getSchema());
        assertEquals(resource.getSchema(), unmarshallingResult.getResource().getSchema());
        assertEquals(((NodeResourceExtended)resource).getExtraProps(),
                    unmarshallingResult.getResource().getExtraProps());
    }

    @Test
    public void testAuthorityAddedEventMarshallingUnmarshalling() throws Exception
    {
        AuthorityResourceV1 resource = new AuthorityResourceV1("a1ca3d2e-4690-4462-bbab-a42a998d07da",
                    Collections.emptyList(), "testUser");
        resource.setParentGroup("Group_testParent");
        EventV1<AuthorityResourceV1> authEvent = new EventV1<>("AUTHADDEDTOGROUP",
                    "1520534888663-k8T3", "testPrincipal", resource);

        String marshallingResult = OBJECT_MAPPER.writeValueAsString(authEvent);
        String authorityEventJson = TestUtil.getFile("AddedToGroup-AuthorityResource.json");

        // Test marshaling
        JSONAssert.assertEquals(authorityEventJson, marshallingResult, false);

        // Test unmarshalling
        EventV1<?> unmarshallingResult = OBJECT_MAPPER.readValue(authorityEventJson, EventV1.class);
        // Relies on the object equals method
        assertEquals(authEvent, unmarshallingResult);
    }

    @Test
    public void testAuthorityDeletedEventMarshallingUnmarshalling() throws Exception
    {
        AuthorityResourceV1 resource = new AuthorityResourceV1("a1ca3d2e-4690-4462-bbab-a42a998d07da",
                    Collections.emptyList(), "Group_testGroup");
        resource.setCascade(Boolean.TRUE);
        EventV1<AuthorityResourceV1> authEvent = new EventV1<>("GROUPDELETED",
                    "1520534888663-k8T3", "testPrincipal", resource);

        String marshallingResult = OBJECT_MAPPER.writeValueAsString(authEvent);
        String authorityEventJson = TestUtil.getFile("GroupDeleted-AuthorityResource.json");

        // Test marshaling
        JSONAssert.assertEquals(authorityEventJson, marshallingResult, false);

        // Test unmarshalling
        EventV1<?> unmarshallingResult = OBJECT_MAPPER.readValue(authorityEventJson, EventV1.class);
        // Relies on the object equals method
        assertEquals(authEvent, unmarshallingResult);
    }

    @Test
    public void testPermissionGrantedEventMarshallingUnmarshalling() throws Exception
    {
        PermissionResourceV1 resource = new PermissionResourceV1("9afb4f9e-82ca-42ea-a68f-158718894f65",
                    TestUtil.getTestNodeHierarchy(), "cm:folder");
        resource.setAuthority("GROUP_TestGroup");
        resource.setPermission("SiteContributor");
        EventV1<PermissionResourceV1> permissionEvent = new EventV1<>("LOCALPERMISSIONGRANTED",
                    "1520534888663-A4G82B", "testPrincipal", resource);

        String marshallingResult = OBJECT_MAPPER.writeValueAsString(permissionEvent);
        String permissionEventJson = TestUtil.getFile("PermGranted-PermissionResource.json");

        // Test marshaling
        JSONAssert.assertEquals(permissionEventJson, marshallingResult, false);

        // Test unmarshalling
        EventV1<?> unmarshallingResult = OBJECT_MAPPER.readValue(permissionEventJson, EventV1.class);
        // Relies on the object equals method
        assertEquals(permissionEvent, unmarshallingResult);
    }

    @Test
    public void testInheritPermissionDisabledEventMarshallingUnmarshalling() throws Exception
    {
        PermissionResourceV1 resource = new PermissionResourceV1("9afb4f9e-82ca-42ea-a68f-158718894f65",
                    TestUtil.getTestNodeHierarchy(), "cm:folder");
        resource.setAsync(Boolean.FALSE);
        EventV1<PermissionResourceV1> permissionEvent = new EventV1<>("INHERITPERMISSIONSDISABLED",
                    "1520534888663-A4G82B", "testPrincipal", resource);

        String marshallingResult = OBJECT_MAPPER.writeValueAsString(permissionEvent);
        String permissionEventJson = TestUtil.getFile("InheritPermDisabled-PermissionResource.json");

        // Test marshaling
        JSONAssert.assertEquals(permissionEventJson, marshallingResult, false);

        // Test unmarshalling
        EventV1<?> unmarshallingResult = OBJECT_MAPPER.readValue(permissionEventJson, EventV1.class);
        // Relies on the object equals method
        assertEquals(permissionEvent, unmarshallingResult);
    }
}
