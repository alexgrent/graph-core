package uk.ac.ebi.reactome.qualityassurance.tests;

import uk.ac.ebi.reactome.qualityassurance.QATest;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 08.03.16.
 */
@SuppressWarnings("unused")
@QATest
public class QualityAssuranceTest022 extends QualityAssuranceAbstract {

    @Override
    String getName() {
        return "QATest022-PhysicalEntityWithoutCompartment";
    }

    @Override
    String getQuery() {
        return "Match (n:PhysicalEntity)<-[:created]-(a) Where NOT (n)-[:compartment]-() RETURN n.dbId AS dbId, " +
                "n.stableIdentifier AS stId, n.displayName AS name, a.displayName as author";
    }
}

