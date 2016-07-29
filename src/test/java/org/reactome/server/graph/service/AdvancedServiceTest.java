package org.reactome.server.graph.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.reactome.server.graph.domain.model.DatabaseObject;
import org.reactome.server.graph.domain.model.Pathway;
import org.reactome.server.graph.domain.model.PhysicalEntity;
import org.reactome.server.graph.exception.CustomQueryException;
import org.reactome.server.graph.service.helper.RelationshipDirection;
import org.reactome.server.graph.util.DatabaseObjectFactory;
import org.reactome.server.graph.util.JunitHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 05.06.16.
 */
public class AdvancedServiceTest extends BaseTest {

    private static final Long dbId = 5205685L;
    private static final Long dbId2 = 199420L;
    private static final String stId = "R-HSA-5205685";
    private static final String stId2 = "R-HSA-199420";

    @Autowired
    private AdvancedDatabaseObjectService advancedDatabaseObjectService;

    @BeforeClass
    public static void setUpClass() {
        logger.info(" --- !!! Running " + AdvancedServiceTest.class.getName() + "!!! --- \n");
    }

    // --------------------------------------- Enhanced Finder Methods -------------------------------------------------

    @Test
    public void findEnhancedObjectByIdTest() {

        logger.info("Started testing genericService.findAllByProperty");
        long start, time;
        start = System.currentTimeMillis();
        PhysicalEntity databaseObjectObserved = (PhysicalEntity) advancedDatabaseObjectService.findEnhancedObjectById("R-HSA-60140");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals("R-HSA-113454", databaseObjectObserved.getPositivelyRegulates().get(0).getRegulatedEntity().getStId());
        logger.info("Finished");
    }

    // --------------------------------------- Limited Finder Methods --------------------------------------------------

    @Test
    public void findLimitedObjectByIdTest() {

        logger.info("Started testing genericService.findAllByProperty");
        long start, time;
        start = System.currentTimeMillis();
        Pathway databaseObjectObserved = (Pathway) advancedDatabaseObjectService.findById("R-HSA-5205685", 10);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        databaseObjectObserved.getHasEvent();
//        assertTrue();
        logger.info("Finished");
    }


    // --------------------------------------- Generic Finder Methods --------------------------------------------------

    @Test
    public void findByPropertyTest() throws InvocationTargetException, IllegalAccessException {

        logger.info("Started testing genericService.findByProperty");
        long start, time;
        start = System.currentTimeMillis();
        DatabaseObject databaseObjectObserved = advancedDatabaseObjectService.findByProperty(DatabaseObject.class, "stId", stId, 1);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        start = System.currentTimeMillis();
        DatabaseObject databaseObjectExpected = DatabaseObjectFactory.createObject(dbId.toString());
        time = System.currentTimeMillis() - start;
        logger.info("GkInstance execution time: " + time + "ms");

        JunitHelper.assertDatabaseObjectsEqual(databaseObjectExpected, databaseObjectObserved);
        logger.info("Finished");
    }

    @Test
    public void findAllByPropertyTest() {

        logger.info("Started testing genericService.findAllByProperty");
        long start, time;
        start = System.currentTimeMillis();
        Collection<DatabaseObject> databaseObjectObserved = advancedDatabaseObjectService.findAllByProperty(DatabaseObject.class, "stId", stId, 1);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(1, databaseObjectObserved.size());
        logger.info("Finished");
    }

    // ---------------------- Methods with RelationshipDirection and Relationships -------------------------------------

    @Test
    public void findByDbIdWithRelationshipDirectionTest() throws InvocationTargetException, IllegalAccessException {
        logger.info("Started testing genericService.findByDbIdWithRelationshipDirectionTest");
        long start, time;
        start = System.currentTimeMillis();
        DatabaseObject databaseObjectObserved = advancedDatabaseObjectService.findById(dbId, RelationshipDirection.UNDIRECTED);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        start = System.currentTimeMillis();
        DatabaseObject databaseObjectExpected = DatabaseObjectFactory.createObject(dbId.toString());
        time = System.currentTimeMillis() - start;
        logger.info("GkInstance execution time: " + time + "ms");

        JunitHelper.assertDatabaseObjectsEqual(databaseObjectExpected, databaseObjectObserved);
        logger.info("Finished");
    }

    @Test
    public void findByStIdWithRelationshipDirectionTest() throws InvocationTargetException, IllegalAccessException {
        logger.info("Started testing genericService.findByStIdWithRelationshipDirectionTest");
        long start, time;
        start = System.currentTimeMillis();
        DatabaseObject databaseObjectObserved = advancedDatabaseObjectService.findById(stId, RelationshipDirection.UNDIRECTED);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        start = System.currentTimeMillis();
        DatabaseObject databaseObjectExpected = DatabaseObjectFactory.createObject(dbId.toString());
        time = System.currentTimeMillis() - start;
        logger.info("GkInstance execution time: " + time + "ms");

        JunitHelper.assertDatabaseObjectsEqual(databaseObjectExpected, databaseObjectObserved);
        logger.info("Finished");
    }

    @Test
    public void findByDbIdWithRelationshipDirectionAndRelationshipsTest() {
        logger.info("Started testing genericService.findByDbIdWithRelationshipDirectionAndRelationshipsTest");
        long start, time;
        start = System.currentTimeMillis();
        Pathway databaseObjectObserved = (Pathway) advancedDatabaseObjectService.findById(dbId, RelationshipDirection.OUTGOING, "hasEvent");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(8, databaseObjectObserved.getHasEvent().size());
        logger.info("Finished");
    }

    @Test
    public void findByStIdWithRelationshipDirectionAndRelationshipsTest() {
        logger.info("Started testing genericService.findByStIdWithRelationshipDirectionAndRelationshipsTest");
        long start, time;
        start = System.currentTimeMillis();
        Pathway databaseObjectObserved = (Pathway) advancedDatabaseObjectService.findById(stId, RelationshipDirection.OUTGOING, "hasEvent");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(8, databaseObjectObserved.getHasEvent().size());
        logger.info("Finished");
    }

    @Test
    public void findByDbIdsWithRelationshipDirectionTest() {
        logger.info("Started testing genericService.findByDbIdsWithRelationshipDirectionTest");
        long start, time;
        start = System.currentTimeMillis();
        Collection<DatabaseObject> databaseObjectObserved = advancedDatabaseObjectService.findByDbIds(Arrays.asList(dbId, dbId2), RelationshipDirection.OUTGOING);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(2, databaseObjectObserved.size());
        logger.info("Finished");
    }

    @Test
    public void findByStIdsWithRelationshipDirectionTest() {
        logger.info("Started testing genericService.findByStIdsWithRelationshipDirectionTest");
        long start, time;
        start = System.currentTimeMillis();
        Collection<DatabaseObject> databaseObjectObserved = advancedDatabaseObjectService.findByStIds(Arrays.asList(stId, stId2), RelationshipDirection.OUTGOING);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(2, databaseObjectObserved.size());
        logger.info("Finished");
    }

    @Test
    public void findByDbIdsWithRelationshipDirectionAndRelationshipsTest() {
        logger.info("Started testing genericService.findByDbIdsWithRelationshipDirectionAndRelationshipsTest");
        long start, time;
        start = System.currentTimeMillis();
        Collection<DatabaseObject> databaseObjectObserved = advancedDatabaseObjectService.findByDbIds(Arrays.asList(dbId, dbId2), RelationshipDirection.OUTGOING, "hasEvent", "referenceEntity");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(2, databaseObjectObserved.size());
        logger.info("Finished");
    }

    @Test
    public void findByStIdsWithRelationshipDirectionAndRelationshipsTest() {
        logger.info("Started testing genericService.findByStIdsWithRelationshipDirectionAndRelationshipsTest");
        long start, time;
        start = System.currentTimeMillis();
        Collection<DatabaseObject> databaseObjectObserved = advancedDatabaseObjectService.findByStIds(Arrays.asList(stId, stId2), RelationshipDirection.OUTGOING, "hasEvent", "referenceEntity");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(2, databaseObjectObserved.size());
        logger.info("Finished");
    }

    // ------------------------------ Methods with Custom Query -------------------------------------
    @Test
    public void customQueryWithCustomObjectTest() throws CustomQueryException {
        String query = "MATCH (p:Pathway{dbId:{dbId}})-[:hasEvent]->(m) RETURN p.dbId as dbId, p.displayName as name, Collect(m.dbId) AS events, Collect(m.dbId) AS eventsPrimitiveArray, Collect(m.displayName) AS eventsArray ";
        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("dbId", 1640170);
        CustomQueryResult small = advancedDatabaseObjectService.customQueryForObject(CustomQueryResult.class, query, parametersMap);

        System.out.println(small);
    }

    @Test
    public void customQueryListOfCustomObjectTest() throws CustomQueryException {
        String query = "MATCH (p:Pathway)-[:hasEvent]->(m) RETURN p.dbId as dbId, p.displayName as name, Collect(m.dbId) AS events, Collect(m.dbId) AS eventsPrimitiveArray, Collect(m.displayName) AS eventsArray LIMIT 20";
        Collection<CustomQueryResult> small = advancedDatabaseObjectService.customQueryForObjects(CustomQueryResult.class, query, null);
        System.out.println(small);
    }

    @Test
    public void customQueryDatabaseObjectTest() throws CustomQueryException {
        String query = "MATCH (p:Pathway{dbId:1640170})-[r:hasEvent]->(m) RETURN p,r,m";
        Pathway pathway = (Pathway) advancedDatabaseObjectService.customQueryForDatabaseObject(Pathway.class, query, null);
        System.out.println(pathway);
    }

    @Test
    public void customQueryListOfDatabaseObjectTest() throws CustomQueryException {
        String query = "MATCH (p:Pathway)<-[r:hasEvent]-(m) RETURN p,r,m LIMIT 10";
        Collection<Pathway> small = advancedDatabaseObjectService.customQueryForDatabaseObjects(Pathway.class, query, null);
        for (Pathway pathway : small) {
            pathway.getHasEvent();
        }
        System.out.println(small);
    }
}

