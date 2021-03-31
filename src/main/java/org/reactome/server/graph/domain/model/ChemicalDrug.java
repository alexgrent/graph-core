package org.reactome.server.graph.domain.model;

import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Node
public class ChemicalDrug extends Drug {

    public ChemicalDrug() {
    }

}
