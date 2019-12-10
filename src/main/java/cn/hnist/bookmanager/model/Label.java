package cn.hnist.bookmanager.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Label implements Serializable {

    public Label(Integer labelId, String labelName) {
        this.labelId = labelId;
        this.labelName = labelName;
    }

    public Label(String labelName) {
        this.labelName = labelName;
    }

    @ApiModelProperty(value = "标签ID")
    private Integer labelId;

    @ApiModelProperty(value = "标签名")
    private String labelName;

    private static final long serialVersionUID = 1L;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", labelId=").append(labelId);
        sb.append(", labelName=").append(labelName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}