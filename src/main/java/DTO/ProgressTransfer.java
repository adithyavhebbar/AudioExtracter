package DTO;

public class ProgressTransfer {
    private String description;
    private double progress;

    public ProgressTransfer() {
    }

    public ProgressTransfer(String description, double progress) {
        this.description = description;
        this.progress = progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
