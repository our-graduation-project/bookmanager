package cn.hnist.bookmanager.model;

import java.io.Serializable;

public class LabelRelation implements Serializable {
    private Integer labelRelationId;

    private Integer bookId;

    private Integer labelId;

    private static final long serialVersionUID = 1L;

    public Integer getLabelRelationId() {
        return labelRelationId;
    }

    public void setLabelRelationId(Integer labelRelationId) {
        this.labelRelationId = labelRelationId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", labelRelationId=").append(labelRelationId);
        sb.append(", bookId=").append(bookId);
        sb.append(", labelId=").append(labelId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}