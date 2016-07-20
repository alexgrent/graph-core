package org.reactome.server.graph.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
@NodeEntity
public class InterChainCrosslinkedResidue extends CrosslinkedResidue {

    @Relationship(type = "equivalentTo", direction = Relationship.UNDIRECTED)
    private List<InterChainCrosslinkedResidue> equivalentTo;

    @Relationship(type = "secondReferenceSequence", direction = Relationship.OUTGOING)
    private Set<ReferenceSequence> secondReferenceSequence;
    
    public InterChainCrosslinkedResidue() {}

    public List<InterChainCrosslinkedResidue> getEquivalentTo() {
        return equivalentTo;
    }

    public void setEquivalentTo(List<InterChainCrosslinkedResidue> equivalentTo) {
        this.equivalentTo = equivalentTo;
    }

    public Set<ReferenceSequence> getSecondReferenceSequence() {
        return secondReferenceSequence;
    }

    public void setSecondReferenceSequence(Set<ReferenceSequence> secondReferenceSequence) {
        this.secondReferenceSequence = secondReferenceSequence;
    }
}
