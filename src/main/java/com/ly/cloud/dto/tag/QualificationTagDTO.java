package com.ly.cloud.dto.tag;

import java.util.List;

public class QualificationTagDTO {
    private List<String> tags; // 标签
    private List<String> ids; // 资格项id

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
