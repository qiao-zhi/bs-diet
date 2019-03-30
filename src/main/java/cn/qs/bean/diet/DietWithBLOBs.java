package cn.qs.bean.diet;

public class DietWithBLOBs extends Diet {
    private String diseasename;

    private String suitablefood;

    private String unsuitablefood;

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename == null ? null : diseasename.trim();
    }

    public String getSuitablefood() {
        return suitablefood;
    }

    public void setSuitablefood(String suitablefood) {
        this.suitablefood = suitablefood == null ? null : suitablefood.trim();
    }

    public String getUnsuitablefood() {
        return unsuitablefood;
    }

    public void setUnsuitablefood(String unsuitablefood) {
        this.unsuitablefood = unsuitablefood == null ? null : unsuitablefood.trim();
    }
}