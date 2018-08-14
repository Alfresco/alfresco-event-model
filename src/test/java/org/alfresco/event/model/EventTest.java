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
import org.alfresco.event.model.acs.AuthorityResourceV1;
import org.alfresco.event.model.acs.NodeResourceV1;
import org.alfresco.event.model.acs.PermissionResourceV1;
import org.alfresco.event.model.activiti.ActivitiCloudRuntimeResourceV1;
import org.alfresco.event.model.activiti.ActivityResourceV1;
import org.alfresco.event.model.activiti.ProcessResourceV1;
import org.alfresco.event.model.activiti.SequenceFlowResourceV1;
import org.alfresco.event.model.activiti.TaskCandidateResourceV1;
import org.alfresco.event.model.activiti.TaskResourceV1;
import org.alfresco.event.model.activiti.VariableResourceV1;
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
        ProcessResourceV1 resource = new ProcessResourceV1("40ea9d40-f9e3-4c0f-ad81-7b6b6bfeed9f",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668660L);
        resource.setEntityId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessDefinitionKey("SimpleProcess");
        resource.setStatus("RUNNING");
        EventV1<ProcessResourceV1> processStartedEvent = new EventV1<>("PROCESS_CREATED",
                    "1534258238351-nsexzn", "PrincipalTest", resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("ProcessCreated-ProcessResource.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testProcessStartedEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("ProcessCreated-ProcessResource.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        ProcessResourceV1 resource = new ProcessResourceV1("40ea9d40-f9e3-4c0f-ad81-7b6b6bfeed9f",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668660L);
        resource.setEntityId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessDefinitionKey("SimpleProcess");
        resource.setStatus("RUNNING");
        EventV1<ProcessResourceV1> processStartedEvent = new EventV1<>("PROCESS_CREATED",
                    "1534258238351-nsexzn", "PrincipalTest", resource);

        assertEquals("PROCESS_CREATED", result.getType());
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

    @Test
    public void testTaskAssignedEventMarshalling() throws Exception
    {
        TaskResourceV1 resource = new TaskResourceV1( "ed88df66-b0b2-4685-aca6-b7c4cad27752",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156841285L);
        resource.setEntityId("3688b0ad-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessInstanceId("3680c167-9ee4-11e8-9079-0242ac110008");
        resource.setName("Perform action");
        resource.setStatus("ASSIGNED");
        resource.setPriority(50);
        resource.setAssignee("PrincipalTest");
        resource.setCreatedDate(new Date(1534156363940L));
        resource.setClaimedDate(new Date(1534156841281L));
        EventV1<TaskResourceV1> processStartedEvent = new EventV1<>("TASK_ASSIGNED",
                    "1534258408610-zimakg", null, resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("TaskAssigned-TaskCandidateResource.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testTaskAssignedEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("TaskAssigned-TaskCandidateResource.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        TaskResourceV1 resource = new TaskResourceV1( "ed88df66-b0b2-4685-aca6-b7c4cad27752",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156841285L);
        resource.setEntityId("3688b0ad-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessInstanceId("3680c167-9ee4-11e8-9079-0242ac110008");
        resource.setName("Perform action");
        resource.setStatus("ASSIGNED");
        resource.setPriority(50);
        resource.setAssignee("PrincipalTest");
        resource.setCreatedDate(new Date(1534156363940L));
        resource.setClaimedDate(new Date(1534156841281L));
        EventV1<TaskResourceV1> processStartedEvent = new EventV1<>("TASK_ASSIGNED",
                    "1534258408610-zimakg", null, resource);

        assertEquals("TASK_ASSIGNED", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(processStartedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(processStartedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(processStartedEvent.getResource(), result.getResource());
    }

    @Test
    public void testVariableCreatedEventMarshalling() throws Exception
    {
        VariableResourceV1<String> resource = new VariableResourceV1<>("64744214-37cc-4a8a-a9e7-45663f16ea26", null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668660L);
        resource.setEntityId("firstName");
        resource.setProcessInstanceId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setName("firstName");
        resource.setType("string");
        resource.setTaskVariable(false);
        resource.setValue("Principal");
        EventV1<VariableResourceV1> processStartedEvent = new EventV1<>("VARIABLE_CREATED", "1534258238464-rborhs", null, resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("VariableCreated-VariableResource.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testVariableCreatedEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("VariableCreated-VariableResource.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        VariableResourceV1<String> resource = new VariableResourceV1<>("64744214-37cc-4a8a-a9e7-45663f16ea26", null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668660L);
        resource.setEntityId("firstName");
        resource.setProcessInstanceId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setName("firstName");
        resource.setType("string");
        resource.setTaskVariable(false);
        resource.setValue("Principal");
        EventV1<VariableResourceV1> processStartedEvent = new EventV1<>("VARIABLE_CREATED", "1534258238464-rborhs", null, resource);

        assertEquals("VARIABLE_CREATED", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(processStartedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(processStartedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(processStartedEvent.getResource(), result.getResource());
    }

    @Test
    public void testActivityCompletedEventMarshalling() throws Exception
    {
        ActivityResourceV1 resource = new ActivityResourceV1( "8dcd30a9-4abf-4564-a11a-21c36df8cbae",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668661L);
        resource.setEntityId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessInstanceId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setActivityType("startEvent");
        resource.setElementId("startEvent1");
        EventV1<ActivityResourceV1> processStartedEvent = new EventV1<>("ACTIVITY_COMPLETED",
                    "1534258238523-7l15ko", null, resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("ActivityCompleted-ActivityResource.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testActivityCompletedEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("ActivityCompleted-ActivityResource.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        ActivityResourceV1 resource = new ActivityResourceV1( "8dcd30a9-4abf-4564-a11a-21c36df8cbae",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668661L);
        resource.setEntityId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessInstanceId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setActivityType("startEvent");
        resource.setElementId("startEvent1");
        EventV1<ActivityResourceV1> processStartedEvent = new EventV1<>("ACTIVITY_COMPLETED",
                    "1534258238523-7l15ko", null, resource);

        assertEquals("ACTIVITY_COMPLETED", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(processStartedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(processStartedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(processStartedEvent.getResource(), result.getResource());
    }

    @Test
    public void testSequenceFlowTakenEventMarshalling() throws Exception
    {
        SequenceFlowResourceV1 resource = new SequenceFlowResourceV1("513ef0b4-f2aa-483e-bba4-affe562e2292",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668661L);
        resource.setEntityId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessInstanceId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setSourceActivityElementId("startEvent1");
        resource.setSourceActivityType("org.activiti.bpmn.model.StartEvent");
        resource.setTargetActivityName("Perform action");
        resource.setTargetActivityElementId("sid-CDFE7219-4627-43E9-8CA8-866CC38EBA94");
        resource.setTargetActivityType("org.activiti.bpmn.model.UserTask");

        EventV1<SequenceFlowResourceV1> processStartedEvent = new EventV1<>("SEQUENCE_FLOW_TAKEN",
                    "1534258238530-uccs3n", null, resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("SequenceFlowTaken-SequenceFlowResource.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testSequenceFlowTakenEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("SequenceFlowTaken-SequenceFlowResource.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        SequenceFlowResourceV1 resource = new SequenceFlowResourceV1("513ef0b4-f2aa-483e-bba4-affe562e2292",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668661L);
        resource.setEntityId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setProcessDefinitionId("SimpleProcess:1:49f47a41-9edd-11e8-9079-0242ac110008");
        resource.setProcessInstanceId("ec285b4f-9ee4-11e8-9079-0242ac110008");
        resource.setSourceActivityElementId("startEvent1");
        resource.setSourceActivityType("org.activiti.bpmn.model.StartEvent");
        resource.setTargetActivityName("Perform action");
        resource.setTargetActivityElementId("sid-CDFE7219-4627-43E9-8CA8-866CC38EBA94");
        resource.setTargetActivityType("org.activiti.bpmn.model.UserTask");

        EventV1<SequenceFlowResourceV1> processStartedEvent = new EventV1<>("SEQUENCE_FLOW_TAKEN",
                    "1534258238530-uccs3n", null, resource);

        assertEquals("SEQUENCE_FLOW_TAKEN", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(processStartedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(processStartedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(processStartedEvent.getResource(), result.getResource());
    }

    @Test
    public void testGroupTaskCandidateEventMarshalling() throws Exception
    {
        TaskCandidateResourceV1 resource = new TaskCandidateResourceV1( "76f5ef22-71c3-4886-a26a-f2004ceb2f5d",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668661L);
        resource.setEntityId("hr");
        resource.setTaskId("ec288265-9ee4-11e8-9079-0242ac110008");
        resource.setGroupId("hr");

        EventV1<TaskCandidateResourceV1> processStartedEvent = new EventV1<>("TASK_CANDIDATE_GROUP_ADDED",
                    "1534258238552-twdsoh", null, resource);

        String result = OBJECT_MAPPER.writeValueAsString(processStartedEvent);
        String processStartedEventJson = TestUtil.getFile("GroupAdded-TaskCandidateResource.json");

        JSONAssert.assertEquals(processStartedEventJson, result, false);
    }

    @Test
    public void testGroupTaskCandidateEventUnmarshalling() throws Exception
    {
        String processStartedEventJson = TestUtil.getFile("GroupAdded-TaskCandidateResource.json");
        EventV1<?> result = OBJECT_MAPPER.readValue(processStartedEventJson, EventV1.class);

        TaskCandidateResourceV1 resource = new TaskCandidateResourceV1( "76f5ef22-71c3-4886-a26a-f2004ceb2f5d",
                    null);
        setCommonActivitiResourceValues(resource);
        resource.setTimestamp(1534156668661L);
        resource.setEntityId("hr");
        resource.setTaskId("ec288265-9ee4-11e8-9079-0242ac110008");
        resource.setGroupId("hr");

        EventV1<TaskCandidateResourceV1> processStartedEvent = new EventV1<>("TASK_CANDIDATE_GROUP_ADDED",
                    "1534258238552-twdsoh", null, resource);

        assertEquals("TASK_CANDIDATE_GROUP_ADDED", result.getType());
        assertEquals(EventV1.class.getName(), result.getSchema());
        assertEquals(processStartedEvent.getStreamPosition(), result.getStreamPosition());
        assertEquals(processStartedEvent.getPrincipal(), result.getPrincipal());
        assertEquals(processStartedEvent.getResource(), result.getResource());
    }


    private void setCommonActivitiResourceValues(ActivitiCloudRuntimeResourceV1 resource)
    {
        resource.setAppName("default-app");
        resource.setAppVersion("");
        resource.setServiceFullName("rb-my-app");
        resource.setServiceVersion("");
        resource.setServiceName("rb-my-app");
        resource.setServiceType("runtime-bundle");
    }
}
