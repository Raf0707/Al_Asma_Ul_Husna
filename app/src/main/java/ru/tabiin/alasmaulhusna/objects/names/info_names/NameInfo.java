package ru.tabiin.alasmaulhusna.objects.names.info_names;

public class NameInfo {
    private String arabicName;
    private String transcriptName;
    private String translateName;
    private String infoName;

    public NameInfo() {

    }

    public NameInfo(String arabicName, String transcriptName,
                    String translateName, String infoName) {
        this.arabicName = arabicName;
        this.transcriptName = transcriptName;
        this.translateName = translateName;
        this.infoName = infoName;
    }
    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getTranscriptName() {
        return transcriptName;
    }

    public void setTranscriptName(String transcriptName) {
        this.transcriptName = transcriptName;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}
