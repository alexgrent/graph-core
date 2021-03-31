//package org.reactome.server.graph.service;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.reactome.server.graph.domain.model.Person;
//import org.reactome.server.graph.domain.model.Publication;
//import org.reactome.server.graph.util.DatabaseObjectFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Objects;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
///**
// * @author Antonio Fabregat <fabregat@ebi.ac.uk>
// */
//public class PublicationTest extends BaseTest {
//
//    @Autowired
//    private DatabaseObjectService databaseObjectService;
//
//    @BeforeClass
//    public static void setUpClass() {
//        logger.info(" --- !!! Running " + PublicationTest.class.getName() + " !!! --- \n");
//    }
//
//    @Test
//    public void findAuthorsList() {
//        logger.info("Started testing publicationTest.findAuthorsList");
//
//        long start, time;
//        start = System.currentTimeMillis();
//        Publication publicationGDB = databaseObjectService.findById("6799811");
//        Publication publicationRDB = DatabaseObjectFactory.createObject("6799811");
//
//        assertTrue("Different sizes", Objects.equals(publicationGDB.getAuthor().size(), publicationRDB.getAuthor().size()));
//
//        for (int i = 0; i < publicationRDB.getAuthor().size(); i++) {
//            Person pRDB = publicationRDB.getAuthor().get(i);
//            Person pGDB = publicationGDB.getAuthor().get(i);
//            assertTrue(pGDB + " differs of " + pRDB, Objects.equals(pRDB, pGDB));
//        }
//
//        time = System.currentTimeMillis() - start;
//        logger.info("Comparison execution time: " + time + "ms");
//
//        logger.info("Finished");
//    }
//
//    @Test
//    public void personPublications() {
//        logger.info("Started testing publicationTest.personPublications");
//
//        long start, time;
//        start = System.currentTimeMillis();
//
//        Person person = databaseObjectService.findById(6799816);
//        assertFalse(person.getPublications().isEmpty());
//
//        time = System.currentTimeMillis() - start;
//        logger.info("Comparison execution time: " + time + "ms");
//
//        logger.info("Finished");
//    }
//}