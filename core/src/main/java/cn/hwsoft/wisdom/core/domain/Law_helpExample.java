package cn.hwsoft.wisdom.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Setter
@Getter
public class Law_helpExample {
    private Integer startRow; //分页参数：起始行

    private Integer pageSize;//分页参数：分页大小

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Law_helpExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idIsNull() {
            addCriterion("from_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idIsNotNull() {
            addCriterion("from_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idEqualTo(Integer value) {
            addCriterion("from_user_id =", value, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idNotEqualTo(Integer value) {
            addCriterion("from_user_id <>", value, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idGreaterThan(Integer value) {
            addCriterion("from_user_id >", value, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_user_id >=", value, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idLessThan(Integer value) {
            addCriterion("from_user_id <", value, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idLessThanOrEqualTo(Integer value) {
            addCriterion("from_user_id <=", value, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idIn(List<Integer> values) {
            addCriterion("from_user_id in", values, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idNotIn(List<Integer> values) {
            addCriterion("from_user_id not in", values, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idBetween(Integer value1, Integer value2) {
            addCriterion("from_user_id between", value1, value2, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andFrom_user_idNotBetween(Integer value1, Integer value2) {
            addCriterion("from_user_id not between", value1, value2, "from_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idIsNull() {
            addCriterion("to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andTo_user_idIsNotNull() {
            addCriterion("to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andTo_user_idEqualTo(Integer value) {
            addCriterion("to_user_id =", value, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idNotEqualTo(Integer value) {
            addCriterion("to_user_id <>", value, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idGreaterThan(Integer value) {
            addCriterion("to_user_id >", value, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_user_id >=", value, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idLessThan(Integer value) {
            addCriterion("to_user_id <", value, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idLessThanOrEqualTo(Integer value) {
            addCriterion("to_user_id <=", value, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idIn(List<Integer> values) {
            addCriterion("to_user_id in", values, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idNotIn(List<Integer> values) {
            addCriterion("to_user_id not in", values, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idBetween(Integer value1, Integer value2) {
            addCriterion("to_user_id between", value1, value2, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andTo_user_idNotBetween(Integer value1, Integer value2) {
            addCriterion("to_user_id not between", value1, value2, "to_user_id");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andFrom_markIsNull() {
            addCriterion("from_mark is null");
            return (Criteria) this;
        }

        public Criteria andFrom_markIsNotNull() {
            addCriterion("from_mark is not null");
            return (Criteria) this;
        }

        public Criteria andFrom_markEqualTo(Byte value) {
            addCriterion("from_mark =", value, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markNotEqualTo(Byte value) {
            addCriterion("from_mark <>", value, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markGreaterThan(Byte value) {
            addCriterion("from_mark >", value, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markGreaterThanOrEqualTo(Byte value) {
            addCriterion("from_mark >=", value, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markLessThan(Byte value) {
            addCriterion("from_mark <", value, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markLessThanOrEqualTo(Byte value) {
            addCriterion("from_mark <=", value, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markIn(List<Byte> values) {
            addCriterion("from_mark in", values, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markNotIn(List<Byte> values) {
            addCriterion("from_mark not in", values, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markBetween(Byte value1, Byte value2) {
            addCriterion("from_mark between", value1, value2, "from_mark");
            return (Criteria) this;
        }

        public Criteria andFrom_markNotBetween(Byte value1, Byte value2) {
            addCriterion("from_mark not between", value1, value2, "from_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markIsNull() {
            addCriterion("reply_mark is null");
            return (Criteria) this;
        }

        public Criteria andReply_markIsNotNull() {
            addCriterion("reply_mark is not null");
            return (Criteria) this;
        }

        public Criteria andReply_markEqualTo(Byte value) {
            addCriterion("reply_mark =", value, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markNotEqualTo(Byte value) {
            addCriterion("reply_mark <>", value, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markGreaterThan(Byte value) {
            addCriterion("reply_mark >", value, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markGreaterThanOrEqualTo(Byte value) {
            addCriterion("reply_mark >=", value, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markLessThan(Byte value) {
            addCriterion("reply_mark <", value, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markLessThanOrEqualTo(Byte value) {
            addCriterion("reply_mark <=", value, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markIn(List<Byte> values) {
            addCriterion("reply_mark in", values, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markNotIn(List<Byte> values) {
            addCriterion("reply_mark not in", values, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markBetween(Byte value1, Byte value2) {
            addCriterion("reply_mark between", value1, value2, "reply_mark");
            return (Criteria) this;
        }

        public Criteria andReply_markNotBetween(Byte value1, Byte value2) {
            addCriterion("reply_mark not between", value1, value2, "reply_mark");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(Byte value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(Byte value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(Byte value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(Byte value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(Byte value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(Byte value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<Byte> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<Byte> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(Byte value1, Byte value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(Byte value1, Byte value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Integer value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Integer value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Integer value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Integer value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Integer value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Integer> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Integer> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Integer value1, Integer value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Integer value1, Integer value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
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