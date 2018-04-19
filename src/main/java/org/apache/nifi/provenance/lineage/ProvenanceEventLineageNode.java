package org.apache.nifi.provenance.lineage;

import java.util.List;

import org.apache.nifi.provenance.ProvenanceEventType;

public interface ProvenanceEventLineageNode extends LineageNode {

    ProvenanceEventType getEventType();

    long getEventIdentifier();

    List<String> getParentUuids();

    List<String> getChildUuids();
}
