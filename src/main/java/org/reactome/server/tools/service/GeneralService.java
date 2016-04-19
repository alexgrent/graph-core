package org.reactome.server.tools.service;

import org.neo4j.ogm.model.Result;
import org.reactome.server.tools.domain.model.DatabaseObject;
import org.reactome.server.tools.domain.model.Pathway;
import org.reactome.server.tools.domain.model.Species;
import org.reactome.server.tools.repository.GeneralRepository;
import org.reactome.server.tools.repository.util.RepositoryUtils;
import org.reactome.server.tools.service.helper.RelationshipDirection;
import org.reactome.server.tools.service.util.DatabaseObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 15.11.15.
 */
@Service
public class GeneralService {

    private static final Logger logger = LoggerFactory.getLogger(GeneralService.class);

    @Autowired
    private GeneralRepository generalRepository;

    public DatabaseObject getById(String id, RelationshipDirection direction) {
        id = DatabaseObjectUtils.trimId(id);
        if (DatabaseObjectUtils.isStId(id)) {
            return generalRepository.getByStableIdentifier(id, direction);
        } else if (DatabaseObjectUtils.isDbId(id)){
            return generalRepository.getByDbId(Long.parseLong(id), direction);
        }
        return null;
    }

    public DatabaseObject getById(String id, RelationshipDirection direction, String... relationships) {
        id = DatabaseObjectUtils.trimId(id);
        if (DatabaseObjectUtils.isStId(id)) {
            return generalRepository.getByStableIdentifier(id, direction, relationships);
        } else if (DatabaseObjectUtils.isDbId(id)){
            return generalRepository.getByDbId(Long.parseLong(id), direction, relationships);
        }
        return null;
    }

    public DatabaseObject getByDbId(Long dbId, RelationshipDirection direction) {
        return generalRepository.getByDbId(dbId, direction);
    }

    public DatabaseObject getByStableIdentifier(String stableIdentifier, RelationshipDirection direction) {
        return generalRepository.getByStableIdentifier(stableIdentifier, direction);
    }

    public DatabaseObject getByDbId (Long dbId, RelationshipDirection direction, String... relationships) {
        return generalRepository.getByDbId(dbId, direction, relationships);
    }

    public DatabaseObject getByStableIdentifier (String stableIdentifier, RelationshipDirection direction, String... relationships) {
        return generalRepository.getByStableIdentifier(stableIdentifier, direction, relationships);
    }

    public <T> Collection<T> findByProperty(Class<T> clazz, String property, Collection<Object> values, Integer depth) {
        return generalRepository.findByProperty(clazz, property, values, depth);
    }

    public Collection<DatabaseObject> getByDbIds(Collection<Long> dbIds, RelationshipDirection direction) {
        return generalRepository.getByDbIds(dbIds, direction);
    }

    public Collection<DatabaseObject> getByStableIdentifiers(Collection<String> stableIdentifiers, RelationshipDirection direction) {
        return generalRepository.getByStableIdentifiers(stableIdentifiers, direction);
    }

    public Collection<DatabaseObject> getByDbIds(Collection<Long> dbIds, RelationshipDirection direction, String... relationships) {
        return generalRepository.getByDbIds(dbIds, direction, relationships);
    }

    public Collection<DatabaseObject> getByStableIdentifiers(Collection<String> stableIdentifiers, RelationshipDirection direction, String... relationships) {
        return generalRepository.getByStableIdentifiers(stableIdentifiers, direction, relationships);
    }

    public <T> Collection<T> getObjectsByClassName(Class<T> clazz, Integer page, Integer offset) {
        return generalRepository.getObjectsByClassName(clazz, page, offset);
    }

    public <T> T findByProperty(Class<T> clazz, String property, Object value, Integer depth) {
        return generalRepository.findByProperty(clazz, property, value, depth);
    }



//
//    @Override
//    public Object findByPropertyWithRelations (String property, Object value, String... relationships){
//        return genericRepository.findByPropertyWithRelations(property, value, relationships);
//    }
//
//    @Override
//    public Object findByPropertyWithoutRelations (String property, Object value, String... relationships){
//        return genericRepository.findByPropertyWithoutRelations(property, value, relationships);
//    }

    //    TODO fix warning
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T>Collection<T> getObjectsByClassName(String className, Integer page, Integer offset) throws ClassNotFoundException {
//        return genericRepository.getObjectsByClassName(DatabaseObjectUtils.getClassForName(className),page,offset);
//    }

//    @Override
//    public <T>T findByProperty(Class<T> clazz, String property, Object value, Integer depth){
//        return genericRepository.findByProperty(clazz, property, value, depth);
//    }
//
//    @Override
//    public <T> T findById(Class<T> clazz, Long id, Integer depth){
//        return genericRepository.findById(clazz, id, depth);
//    }
//
//    @Override
//    public <T>T findByDbId(Class<T> clazz, Long dbId, Integer depth) {
//        return genericRepository.findByDbId(clazz, dbId, depth);
//    }
//
//    @Override
//    public <T>T  findByStableIdentifier(Class<T> clazz, String stableIdentifier, Integer depth) {
//        return genericRepository.findByStableIdentifier(clazz, stableIdentifier, depth);
//    }
//
//    @Override
//    public Collection<Pathway> getTopLevelPathways() {
//        return genericRepository.getTopLevelPathways();
//    }
//
//    @Override
//    public Collection<Pathway> getTopLevelPathways(Long speciesId) {
//        return genericRepository.getTopLevelPathways(speciesId);
//    }
//
//    @Override
//    public Collection<Pathway> getTopLevelPathways(String speciesName) {
//        return genericRepository.getTopLevelPathways(speciesName);
//    }
//
//    @Override
//    public Pathway getEventHierarchy(Long dbId) {
//        return genericRepository.getEventHierarchy(dbId);
//    }
//
//
//    public DatabaseObject getLocationsHierarchy(String stId){
//        return genericRepository.getLocationsHierarchy(stId);
//    }
//
//    public DatabaseObject getReferral(Long dbId, String relationshipName) {
//        return genericRepository.getReferral(dbId,relationshipName);
//    }
//
//    public Collection<DatabaseObject> getReferrals(Long dbId, String relationshipName){
//        return genericRepository.getReferrals(dbId, relationshipName);
//    }

//    public Set<PathwayBrowserNode> getLocationsInPathwayBrowserHierarchy(DatabaseObject databaseObject) {
//        return getLocationsInPathwayBrowserTree(databaseObject).getLeaves();
//    }
//
//    public PathwayBrowserNode getLocationsInPathwayBrowserTree(DatabaseObject databaseObject) {
//
//        Result result = genericRepository.getLocationsInPathwayBrowser(databaseObject.getStableIdentifier());
//
//        PathwayBrowserNode root = createNode(databaseObject);
//        Map<String,PathwayBrowserNode> nodes = new HashMap<>();
//        PathwayBrowserNode previous = root;
//        nodes.put(root.getStId(),root);
//
//        int previousSize = 0;
//        for (Map<String, Object> stringObjectMap : result) {
//            ArrayList<Object>[] nodePairCollections = ((ArrayList<Object>[])stringObjectMap.get("nodePairCollection"));
//            int size = nodePairCollections.length;
//            if (size>previousSize) {
//                ArrayList<Object> objects = nodePairCollections[nodePairCollections.length-1];
//                previous = addNode(previous,nodes,objects);
//            } else {
//                previous = root;
//                for (ArrayList<Object> objects : nodePairCollections) {
//                    if (objects.get(0) == null ){
//                        continue;
//                    }
//                    previous = addNode(previous,nodes,objects);
//                }
//            }
//            previousSize = size;
//        }
//        return  root ;
//    }

//    private PathwayBrowserNode addNode(PathwayBrowserNode previous, Map<String,PathwayBrowserNode> nodes, ArrayList<Object> objects) {
//        PathwayBrowserNode node;
//
//
//        if (nodes.containsKey(objects.get(0))) {
//            node = nodes.get(objects.get(0));
//        } else {
//            node = createNode(objects);
//            nodes.put(node.getStId(),node);
//        }
//        if (!node.getType().equals("CatalystActivity") && !node.getType().contains("Regulation") && !node.getType().equals("EntityFunctionalStatus")) {
//            previous.addChild(node);
//            node.addParent(previous);
//            previous = node;
//        }
//        return previous;
//    }
//
//    private PathwayBrowserNode createNode(DatabaseObject databaseObject) {
//        PathwayBrowserNode node = new PathwayBrowserNode();
//        node.setStId(databaseObject.getStableIdentifier());
//        node.setName(databaseObject.getDisplayName());
//        node.setType(databaseObject.getSchemaClass());
//
//        if (databaseObject instanceof Event) {
//            Event event = (Event) databaseObject;
//            node.setSpecies(event.getSpeciesName());
//        } else if (databaseObject instanceof PhysicalEntity) {
//            PhysicalEntity physicalEntity = (PhysicalEntity) databaseObject;
//            node.setSpecies(physicalEntity.getSpeciesName());
//        } else {
////            logger.error
//            return null;
//        }
//        return node;
//    }
//
//    private PathwayBrowserNode createNode(ArrayList<Object> nodePairCollection) {
//        PathwayBrowserNode node = new PathwayBrowserNode();
//        node.setStId((String) nodePairCollection.get(0));
//        node.setName((String) nodePairCollection.get(1));
//        node.setDiagram((Boolean) nodePairCollection.get(2));
//        node.setSpecies((String) nodePairCollection.get(3));
//        List xx = (List) nodePairCollection.get(4);
//        Class<?> lowestClass = Object.class;
//
//        for (Object o1 : xx) {
//            try {
//                Class clazz = Class.forName("org.reactome.server.tools.domain.model." + o1.toString());
//                if (lowestClass.isAssignableFrom(clazz)) {
//                    lowestClass = clazz;
//                }
//            } catch (ClassNotFoundException e) {
////                e.printStackTrace();
//                //todo toplevel pathway!!
//                node.setType("Pathway");
//            }
//        }
//        node.setType(lowestClass.getSimpleName());
//        return node;
//    }



//
//    public Collection<DatabaseObject> findCollectionByPropertyWithRelationships (String property, Collection<Object> values, String... relationships)  {
//        return genericRepository.findCollectionByPropertyWithRelationships(property, values, relationships);
//    }
//
//
//
//    public Collection<Species> getSpecies() {
//        return genericRepository.getSpecies();
//    }

    public Result query (String query, Map<String,Object> map) {
        return generalRepository.query(query,map);
    }

    public Long countEntries(Class<?> clazz){
        return generalRepository.countEntries(clazz);
    }

    public void clearCache() {
        generalRepository.clearCache();
    }

    public boolean fitForService()  {
        return generalRepository.fitForService();
    }
}