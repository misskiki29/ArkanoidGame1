package game;

import listeners.HitListener;
/**
 * An interface for objects that can notify hit listeners
 * about hit events.
 */
public interface HitNotifier {
    /**
     * Adds the given hit listener to the list of listeners.
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);
    /**
     * Removes the given hit listener from the list of listeners.
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
