package org.reactome.server.graph.domain.relationship;

import org.reactome.server.graph.domain.model.Person;
import org.reactome.server.graph.domain.model.Publication;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.Objects;

@RelationshipProperties
public class PublicationAuthor implements Comparable<PublicationAuthor> {

    @Id @GeneratedValue
    private Long id;

    @TargetNode
    private Publication publication;

    private int order;
    private Person author;

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(publication, ((PublicationAuthor) o).publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication);
    }

    @Override
    public int compareTo(PublicationAuthor o) {
        return this.order - o.order;
    }
}