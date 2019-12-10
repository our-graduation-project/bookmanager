package cn.hnist.bookmanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BorrowDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBorrowIdIsNull() {
            addCriterion("borrow_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIsNotNull() {
            addCriterion("borrow_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowIdEqualTo(Integer value) {
            addCriterion("borrow_id =", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotEqualTo(Integer value) {
            addCriterion("borrow_id <>", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdGreaterThan(Integer value) {
            addCriterion("borrow_id >", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_id >=", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdLessThan(Integer value) {
            addCriterion("borrow_id <", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_id <=", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIn(List<Integer> values) {
            addCriterion("borrow_id in", values, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotIn(List<Integer> values) {
            addCriterion("borrow_id not in", values, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdBetween(Integer value1, Integer value2) {
            addCriterion("borrow_id between", value1, value2, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_id not between", value1, value2, "borrowId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBorrowDateIsNull() {
            addCriterion("borrow_date is null");
            return (Criteria) this;
        }

        public Criteria andBorrowDateIsNotNull() {
            addCriterion("borrow_date is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowDateEqualTo(Date value) {
            addCriterion("borrow_date =", value, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateNotEqualTo(Date value) {
            addCriterion("borrow_date <>", value, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateGreaterThan(Date value) {
            addCriterion("borrow_date >", value, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateGreaterThanOrEqualTo(Date value) {
            addCriterion("borrow_date >=", value, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateLessThan(Date value) {
            addCriterion("borrow_date <", value, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateLessThanOrEqualTo(Date value) {
            addCriterion("borrow_date <=", value, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateIn(List<Date> values) {
            addCriterion("borrow_date in", values, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateNotIn(List<Date> values) {
            addCriterion("borrow_date not in", values, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateBetween(Date value1, Date value2) {
            addCriterion("borrow_date between", value1, value2, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andBorrowDateNotBetween(Date value1, Date value2) {
            addCriterion("borrow_date not between", value1, value2, "borrowDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateIsNull() {
            addCriterion("should_return_date is null");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateIsNotNull() {
            addCriterion("should_return_date is not null");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateEqualTo(Date value) {
            addCriterion("should_return_date =", value, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateNotEqualTo(Date value) {
            addCriterion("should_return_date <>", value, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateGreaterThan(Date value) {
            addCriterion("should_return_date >", value, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateGreaterThanOrEqualTo(Date value) {
            addCriterion("should_return_date >=", value, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateLessThan(Date value) {
            addCriterion("should_return_date <", value, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateLessThanOrEqualTo(Date value) {
            addCriterion("should_return_date <=", value, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateIn(List<Date> values) {
            addCriterion("should_return_date in", values, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateNotIn(List<Date> values) {
            addCriterion("should_return_date not in", values, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateBetween(Date value1, Date value2) {
            addCriterion("should_return_date between", value1, value2, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andShouldReturnDateNotBetween(Date value1, Date value2) {
            addCriterion("should_return_date not between", value1, value2, "shouldReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateIsNull() {
            addCriterion("real_return_date is null");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateIsNotNull() {
            addCriterion("real_return_date is not null");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateEqualTo(Date value) {
            addCriterion("real_return_date =", value, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateNotEqualTo(Date value) {
            addCriterion("real_return_date <>", value, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateGreaterThan(Date value) {
            addCriterion("real_return_date >", value, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateGreaterThanOrEqualTo(Date value) {
            addCriterion("real_return_date >=", value, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateLessThan(Date value) {
            addCriterion("real_return_date <", value, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateLessThanOrEqualTo(Date value) {
            addCriterion("real_return_date <=", value, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateIn(List<Date> values) {
            addCriterion("real_return_date in", values, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateNotIn(List<Date> values) {
            addCriterion("real_return_date not in", values, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateBetween(Date value1, Date value2) {
            addCriterion("real_return_date between", value1, value2, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andRealReturnDateNotBetween(Date value1, Date value2) {
            addCriterion("real_return_date not between", value1, value2, "realReturnDate");
            return (Criteria) this;
        }

        public Criteria andFineIsNull() {
            addCriterion("fine is null");
            return (Criteria) this;
        }

        public Criteria andFineIsNotNull() {
            addCriterion("fine is not null");
            return (Criteria) this;
        }

        public Criteria andFineEqualTo(Double value) {
            addCriterion("fine =", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotEqualTo(Double value) {
            addCriterion("fine <>", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineGreaterThan(Double value) {
            addCriterion("fine >", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineGreaterThanOrEqualTo(Double value) {
            addCriterion("fine >=", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineLessThan(Double value) {
            addCriterion("fine <", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineLessThanOrEqualTo(Double value) {
            addCriterion("fine <=", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineIn(List<Double> values) {
            addCriterion("fine in", values, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotIn(List<Double> values) {
            addCriterion("fine not in", values, "fine");
            return (Criteria) this;
        }

        public Criteria andFineBetween(Double value1, Double value2) {
            addCriterion("fine between", value1, value2, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotBetween(Double value1, Double value2) {
            addCriterion("fine not between", value1, value2, "fine");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}