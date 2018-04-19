package org.apache.nifi.provenance.lineage;

public interface LineageEdge {

    String getUuid();

    LineageNode getSource();

    LineageNode getDestination();
}
