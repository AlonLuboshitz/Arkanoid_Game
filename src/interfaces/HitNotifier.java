package interfaces;
// id 312115090, Alon luboshitz.

/**
 * this class is notifier interface.
 */
public interface HitNotifier {
    /**
     * add hl as a listener.
     * @param hl the listener.
     */
    void addHitListener(HitListener hl);

    /**
     * removes hl from the listener list.
     * @param hl the listener.
     */
    void removeHitListener(HitListener hl);
}
