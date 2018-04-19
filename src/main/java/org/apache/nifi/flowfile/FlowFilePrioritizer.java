package org.apache.nifi.flowfile;

import java.util.Comparator;

/**
 * Provides a mechanism to prioritize flow file objects based on their
 * attributes. The actual flow file content will not be available for comparison
 * so if features of that content are necessary for prioritization it should be
 * extracted to be used as an attribute of the flow file.
 *
 * @author none
 */
public interface FlowFilePrioritizer extends Comparator<FlowFile> {
}
