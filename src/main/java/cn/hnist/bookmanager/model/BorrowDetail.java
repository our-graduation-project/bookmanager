package cn.hnist.bookmanager.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class BorrowDetail implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer borrowId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "书籍ID")
    private Integer bookId;

    @ApiModelProperty(value = "借阅日期")
    private Date borrowDate;

    @ApiModelProperty(value = "应还日期")
    private Date shouldReturnDate;

    @ApiModelProperty(value = "实还日期")
    private Date realReturnDate;

    @ApiModelProperty(value = "罚金")
    private Double fine;

    @ApiModelProperty(value = "状态(1.借出未还 2.期限内还 3.逾期还 4.逾期未还 5.续借，无法再次续借 6.遗失）")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public String getBorrowDateStr() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(borrowDate);
        return dateString;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }



    public Date getShouldReturnDate() {
        return shouldReturnDate;
    }
    public String getShouldReturnDateStr() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(shouldReturnDate);
        return dateString;
    }

    public void setShouldReturnDate(Date shouldReturnDate) {
        this.shouldReturnDate = shouldReturnDate;
    }

    public Date getRealReturnDate() {
        return realReturnDate;
    }



    public void setRealReturnDate(Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", borrowId=").append(borrowId);
        sb.append(", userId=").append(userId);
        sb.append(", bookId=").append(bookId);
        sb.append(", borrowDate=").append(borrowDate);
        sb.append(", shouldReturnDate=").append(shouldReturnDate);
        sb.append(", realReturnDate=").append(realReturnDate);
        sb.append(", fine=").append(fine);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}