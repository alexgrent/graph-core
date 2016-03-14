package uk.ac.ebi.reactome.qualityassurance.tests;

import uk.ac.ebi.reactome.qualityassurance.QATest;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 07.03.16.
 */
@SuppressWarnings("unused")
@QATest
public class QualityAssuranceTest013 extends QualityAssuranceAbstract {

    @Override
    String getName() {
        return "QATest013-ReferenceEntityWithoutIdentifier";
    }

    @Override
    String getQuery() {
        return "Match (n:ReferenceEntity)<-[:created]-(a) Where n.identifier is NULL RETURN n.dbId AS dbId, " +
                "n.stableIdentifier AS stId, n.displayName AS name, a.displayName as author";
    }
}