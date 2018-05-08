package fr.adbonnin.xtra.jdownloader.folderwatch;

/**
 * @see <a href="https://github.com/mirror/jdownloader/blob/master/src/org/jdownloader/extensions/folderwatchV2/CrawlJobStorable.java>CrawlJobStorable.java</a>
 */
public class CrawlJob {

    private String filename;
    private int chunks;
    private Boolean autoConfirm;
    private boolean addOfflineLink = true;

    private Boolean autoStart;
    private Boolean forcedStart;
    private Boolean enabled;
    private String text;
    private boolean deepAnalyseEnabled;
    private String packageName;
    private Priority priority;
    private Boolean extractAfterDownload;

    private String downloadFolder;
    private String[] extractPasswords;
    private String downloadPassword;
    private boolean overwritePackagizerEnabled = true;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public Boolean getAutoConfirm() {
        return autoConfirm;
    }

    public void setAutoConfirm(Boolean autoConfirm) {
        this.autoConfirm = autoConfirm;
    }

    public boolean isAddOfflineLink() {
        return addOfflineLink;
    }

    public void setAddOfflineLink(boolean addOfflineLink) {
        this.addOfflineLink = addOfflineLink;
    }

    public Boolean getAutoStart() {
        return autoStart;
    }

    public void setAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
    }

    public Boolean getForcedStart() {
        return forcedStart;
    }

    public void setForcedStart(Boolean forcedStart) {
        this.forcedStart = forcedStart;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDeepAnalyseEnabled() {
        return deepAnalyseEnabled;
    }

    public void setDeepAnalyseEnabled(boolean deepAnalyseEnabled) {
        this.deepAnalyseEnabled = deepAnalyseEnabled;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setHighestPriority() {
        setPriority(Priority.HIGHEST);
    }

    public void setHigherPriority() {
        setPriority(Priority.HIGHER);
    }

    public void setHighPriority() {
        setPriority(Priority.HIGH);
    }

    public void setDefaultPriority() {
        setPriority(Priority.DEFAULT);
    }

    public void setLowerPriority() {
        setPriority(Priority.LOWER);
    }

    public Boolean getExtractAfterDownload() {
        return extractAfterDownload;
    }

    public void setExtractAfterDownload(Boolean extractAfterDownload) {
        this.extractAfterDownload = extractAfterDownload;
    }

    public String getDownloadFolder() {
        return downloadFolder;
    }

    public void setDownloadFolder(String downloadFolder) {
        this.downloadFolder = downloadFolder;
    }

    public String[] getExtractPasswords() {
        return extractPasswords;
    }

    public void setExtractPasswords(String[] extractPasswords) {
        this.extractPasswords = extractPasswords;
    }

    public String getDownloadPassword() {
        return downloadPassword;
    }

    public void setDownloadPassword(String downloadPassword) {
        this.downloadPassword = downloadPassword;
    }

    public boolean isOverwritePackagizerEnabled() {
        return overwritePackagizerEnabled;
    }

    public void setOverwritePackagizerEnabled(boolean overwritePackagizerEnabled) {
        this.overwritePackagizerEnabled = overwritePackagizerEnabled;
    }

    public enum Priority {
        HIGHEST,
        HIGHER,
        HIGH,
        DEFAULT,
        LOWER
    }
}
