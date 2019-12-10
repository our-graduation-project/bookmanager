package cn.hnist.bookmanager.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Book implements Serializable {
    @ApiModelProperty(value = "ID编号")
    private Integer bookId;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "图片")
    private String bookPicture;

    @ApiModelProperty(value = "书号")
    private String bookIsbn;

    @ApiModelProperty(value = "图书类型，参考图书类型表")
    private Integer bookType;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "总数量")
    private Integer bookCount;

    @ApiModelProperty(value = "在馆数量")
    private Integer bookNum;

    @ApiModelProperty(value = "所属书架，参考书架表")
    private Integer bookshelf;

    @ApiModelProperty(value = "图书简介")
    private String introduce;

    private static final long serialVersionUID = 1L;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Integer getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(Integer bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bookId=").append(bookId);
        sb.append(", bookName=").append(bookName);
        sb.append(", bookPicture=").append(bookPicture);
        sb.append(", bookIsbn=").append(bookIsbn);
        sb.append(", bookType=").append(bookType);
        sb.append(", author=").append(author);
        sb.append(", price=").append(price);
        sb.append(", bookCount=").append(bookCount);
        sb.append(", bookNum=").append(bookNum);
        sb.append(", bookshelf=").append(bookshelf);
        sb.append(", introduce=").append(introduce);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}