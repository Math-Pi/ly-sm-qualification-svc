package com.ly.cloud.dto.qualification;

public class QualificationMatchInfo {
    private String id;
    private String fhtj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFhtj() {
        return fhtj;
    }

    public void setFhtj(String fhtj) {
        this.fhtj = fhtj;
    }

    public void concat(String fhtj,String linkStr) {
        this.fhtj = this.fhtj + linkStr+fhtj;
    }
}
