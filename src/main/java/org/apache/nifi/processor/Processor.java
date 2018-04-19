package org.apache.nifi.processor;

import java.util.Set;

import org.apache.nifi.components.ConfigurableComponent;
import org.apache.nifi.processor.exception.ProcessException;

/**
 * <p>
 * Processor objects operate on FlowFile objects where the processors are linked
 * together via relationships forming a directed graph structure.</p>
 *
 * <p>
 * The validity of a processor once established is considered safe until a
 * configuration property of that processor is changed. Even if the property
 * change is itself valid it still brings the validity of the processor as a
 * whole into question. Therefore changing a processor as a whole must be
 * reverified. Also, changing any configuration property of a processor can also
 * imply that its supported relationships have changed.</p>
 *
 * <p>
 * Each Processor object is a single node in a flow graph. The same processor
 * object on a graph may be called concurrently to update its configuration
 * state and to perform 'onTrigger' actions. The framework also provides
 * numerous hooks via annotations that subclasses can use to control the
 * lifecycle of a given processor instance.</p>
 *
 * <p>
 * Processor objects are expected to be thread-safe and must have a public
 * default no-args constructor to facilitate the java service loader
 * mechanism.</p>
 *
 * @author none
 */
public interface Processor extends ConfigurableComponent {

    /**
     * Provides the processor with access to objects that may be of use
     * throughout the life of the Processor
     *
     * @param context
     */
    void initialize(ProcessorInitializationContext context);

    /**
     * @return Set of all relationships this processor expects to transfer a
     * flow file to. An empty set indicates this processor does not have any
     * destination relationships. Guaranteed non null.
     */
    Set<Relationship> getRelationships();

    /**
     * <p>
     * The method called when this processor is triggered to operate by the
     * controller. This method may be called concurrently from different
     * threads. When this method is called depends on how this processor is
     * configured within a controller to be triggered (timing or event
     * based).</p>
     *
     * @param context provides access to convenience methods for obtaining
     * property values, delaying the scheduling of the processor, provides
     * access to Controller Services, etc.
     * @param sessionFactory provides access to a {@link ProcessSession}, which
     * can be used for accessing FlowFiles, etc.
     *
     * @throws ProcessException if processing did not complete normally though
     * indicates the problem is an understood potential outcome of processing.
     * The controller/caller will handle these exceptions gracefully such as
     * logging, etc.. If another type of exception is allowed to propagate the
     * controller may no longer trigger this processor to operate, as this would
     * indicate a probable coding defect.
     */
    void onTrigger(ProcessContext context, ProcessSessionFactory sessionFactory) throws ProcessException;

}
