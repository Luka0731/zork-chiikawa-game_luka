
package ch.noseryoung.blj.engine;

public class Exit {
    private final String targetPlaceId;
    private boolean isAccessible;
    private final String blockedMessage;

    public Exit(String targetPlaceId, boolean isAccessible, String blockedMessage) {
        this.targetPlaceId = targetPlaceId;
        this.isAccessible = isAccessible;
        this.blockedMessage = blockedMessage;
    }


    // |--- getters & setters ---|

    public String getTargetPlaceId() {
        return targetPlaceId;
    }

    public boolean isAccessible(Place currentPlace) {
        return isAccessible;
    }

    public String getBlockedMessage() {
        return blockedMessage;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }
}
