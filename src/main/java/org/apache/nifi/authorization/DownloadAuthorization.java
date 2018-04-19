package org.apache.nifi.authorization;

/**
 * Represents a decision whether authorization is granted to download content.
 */
public class DownloadAuthorization {

    private static enum Result {
        Approved,
        Denied;
    };

    private static final DownloadAuthorization APPROVED = new DownloadAuthorization(Result.Approved, null);

    private final Result result;
    private final String explanation;

    /**
     * Creates a new DownloadAuthorization with the specified result and explanation.
     *
     * @param result
     * @param explanation
     */
    private DownloadAuthorization(Result result, String explanation) {
        if (Result.Denied.equals(result) && explanation == null) {
            throw new IllegalArgumentException("An explanation is required when the download request is denied.");
        }

        this.result = result;
        this.explanation = explanation;
    }

    /**
     * Whether or not the download request is approved.
     *
     * @return
     */
    public boolean isApproved() {
        return Result.Approved.equals(result);
    }

    /**
     * If the download request is denied, the reason why. Null otherwise.
     *
     * @return
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Creates a new approved DownloadAuthorization.
     *
     * @return
     */
    public static DownloadAuthorization approved() {
        return APPROVED;
    }

    /**
     * Creates a new denied DownloadAuthorization with the specified explanation.
     *
     * @param explanation
     * @return
     * @throws IllegalArgumentException     if explanation is null
     */
    public static DownloadAuthorization denied(String explanation) {
        return new DownloadAuthorization(Result.Denied, explanation);
    }
}
