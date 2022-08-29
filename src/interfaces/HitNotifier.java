package interfaces;
//ID:316081975

import listeners.HitListener;

/**
 * The HitListener interface indicates that objects that implement it
 * send notifications when they are being hit.
 *
 * @author Daniel Tal
 * @version 1.0
 */
public interface HitNotifier {

    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl is the added listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl is the removed listener.
     */
    void removeHitListener(HitListener hl);
}