package org.reactome.server.graph.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactome.server.graph.config.Neo4jConfig;
import org.reactome.server.graph.domain.model.DatabaseObject;
import org.reactome.server.graph.util.DatabaseObjectFactory;
import org.reactome.server.graph.util.JunitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assume.assumeTrue;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 03.03.16.
 */
@ContextConfiguration(classes = {Neo4jConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest {

    private static final Logger logger = LoggerFactory.getLogger("testLogger");

    private static Boolean checkedOnce = false;
    private static Boolean isFit = false;

    private static final Long dbId = 1912416L;
    private static final String stId = "R-HSA-1912416";

    @Autowired
    private EventService eventService;

    @Autowired
    private GeneralService generalService;

    @BeforeClass
    public static void setUpClass() {
        logger.info(" --- !!! Running DatabaseObjectServiceTests !!! --- \n");
    }

    @AfterClass
    public static void tearDownClass() {
        logger.info("\n\n");
    }

    @Before
    public void setUp() throws Exception {
        if (!checkedOnce) {
            isFit = generalService.fitForService();
            checkedOnce = true;
        }
        assumeTrue(isFit);
        generalService.clearCache();
        DatabaseObjectFactory.clearCache();
    }

    @Test
    public void testFindByDbId() throws InvocationTargetException, IllegalAccessException {

        logger.info("Started testing eventService.findByDbId");
        long start, time;
        start = System.currentTimeMillis();
        DatabaseObject databaseObjectObserved = eventService.findByDbId(dbId);
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
    public void findByStIdTest() throws InvocationTargetException, IllegalAccessException {

        logger.info("Started testing eventService.findByStId");
        long start, time;
        start = System.currentTimeMillis();
        DatabaseObject databaseObjectObserved = eventService.findByStId(stId);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        start = System.currentTimeMillis();
        DatabaseObject databaseObjectExpected = DatabaseObjectFactory.createObject(stId);
        time = System.currentTimeMillis() - start;
        logger.info("GkInstance execution time: " + time + "ms");

        JunitHelper.assertDatabaseObjectsEqual(databaseObjectExpected, databaseObjectObserved);
        logger.info("Finished");
    }

    /**
     * Execution times do not give fair estimation since GkInstance does not fill any of the legacy fields.
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void findByIdWithLegacyFields() throws InvocationTargetException, IllegalAccessException {

        logger.info("Started testing eventService.findByIdWithLegacyFields");
        long start, time;
        start = System.currentTimeMillis();
        DatabaseObject databaseObjectObserved = eventService.findByIdWithLegacyFields(dbId.toString());
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        start = System.currentTimeMillis();
        DatabaseObject databaseObjectExpected = DatabaseObjectFactory.createObject(stId);
        time = System.currentTimeMillis() - start;
        logger.info("GkInstance execution time: " + time + "ms");

        JunitHelper.assertDatabaseObjectsEqual(databaseObjectExpected, databaseObjectObserved);
        logger.info("Finished");
    }
}