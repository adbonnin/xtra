package fr.adbonnin.xtra.webclient;

public enum RedownloadMode {
    NO_REDOWNLOAD(false, false),
    WRITE_TO_NON_EXISTING_FILE(true, false),
    OVERWRITE_EXISTING_FILE(true, true);

    private final boolean redownload;

    private final boolean override;

    RedownloadMode(boolean redownload, boolean override) {
        this.override = override;
        this.redownload = redownload;
    }

    boolean override() {
        return override;
    }

    boolean redownload() {
        return redownload;
    }
}
