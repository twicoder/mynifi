package org.apache.nifi.processor;

import org.apache.nifi.processor.exception.ProcessException;

public abstract class AbstractProcessor extends AbstractSessionFactoryProcessor {

    @Override
    public final void onTrigger(final ProcessContext context, final ProcessSessionFactory sessionFactory) throws ProcessException {
        final ProcessSession session = sessionFactory.createSession();
        try {
            onTrigger(context, session);
            session.commit();
        } catch (final Throwable t) {
            getLogger().error("{} failed to process due to {}; rolling back session", new Object[]{this, t});
            session.rollback(true);
            throw t;
        }
    }

    public abstract void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException;
}