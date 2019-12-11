package cn.hnist.bookmanager.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Bookshelf implements Serializable {

    public Bookshelf(Integer bookshelfId, String bookshelfName, String bookshelfPosition) {
        this.bookshelfId = bookshelfId;
        this.bookshelfName = bookshelfName;
        this.bookshelfPosition = bookshelfPosition;
    }

    @ApiModelProperty(value = "主键")
    private Integer bookshelfId;

    @ApiModelProperty(value = "书架名")
    private String bookshelfName;

    @ApiModelProperty(value = "书架位置")
    private String bookshelfPosition;

    private static final long serialVersionUID = 1L;

    public Integer getBookshelfId() {
        return bookshelfId;
    }

    public void setBookshelfId(Integer bookshelfId) {
        this.bookshelfId = bookshelfId;
    }

    public String getBookshelfName() {
        return bookshelfName;
    }

    public void setBookshelfName(String bookshelfName) {
        this.bookshelfName = bookshelfName;
    }

    public String getBookshelfPosition() {
        return bookshelfPosition;
    }

    public void setBookshelfPosition(String bookshelfPosition) {
        this.bookshelfPosition = bookshelfPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookshelfId=").append(bookshelfId);
        sb.append(", bookshelfName=").append(bookshelfName);
        sb.append(", bookshelfPosition=").append(bookshelfPosition);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}