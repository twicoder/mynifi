package org.apache.nifi.reporting;

import java.util.List;

/**
 * The BulletinRepository provides a place to store and retrieve
 * {@link Bulletin}s that have been created by the NiFi Framework and the
 * Components that are running within the Framework.
 */
public interface BulletinRepository {

    /**
     * Adds a Bulletin to the repository.
     *
     * @param bulletin
     */
    void addBulletin(Bulletin bulletin);

    /**
     * Returns the capacity for the number of bulletins for the controller.
     *
     * @return
     */
    int getControllerBulletinCapacity();

    /**
     * Returns the capacity for the number of bulletins per component.
     *
     * @return
     */
    int getComponentBulletinCapacity();

    /**
     * Finds Bulletin's that meet the specified query.
     *
     * @param bulletinQuery
     * @return
     */
    List<Bulletin> findBulletins(BulletinQuery bulletinQuery);

    /**
     * Finds all bulletins for the specified group.
     *
     * @param groupId
     * @return
     */
    List<Bulletin> findBulletinsForGroupBySource(String groupId);

    /**
     * Finds all bulletins for the specified group.
     *
     * @param groupId
     * @param maxPerComponent
     * @return
     */
    List<Bulletin> findBulletinsForGroupBySource(String groupId, int maxPerComponent);

    /**
     * Finds all bulletins for the controller;
     *
     * @return
     */
    List<Bulletin> findBulletinsForController();

    /**
     * Finds all bulletins for the controller;
     *
     * @param max
     * @return
     */
    List<Bulletin> findBulletinsForController(int max);
}
