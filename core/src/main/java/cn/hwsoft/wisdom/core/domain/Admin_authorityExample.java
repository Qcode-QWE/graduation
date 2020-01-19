package cn.hwsoft.wisdom.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Admin_authorityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Admin_authorityExample() {
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

        public Criteria andAdmin_idIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdmin_idIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdmin_idEqualTo(Integer value) {
            addCriterion("admin_id =", value, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idNotEqualTo(Integer value) {
            addCriterion("admin_id <>", value, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idGreaterThan(Integer value) {
            addCriterion("admin_id >", value, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_id >=", value, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idLessThan(Integer value) {
            addCriterion("admin_id <", value, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idLessThanOrEqualTo(Integer value) {
            addCriterion("admin_id <=", value, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idIn(List<Integer> values) {
            addCriterion("admin_id in", values, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idNotIn(List<Integer> values) {
            addCriterion("admin_id not in", values, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idBetween(Integer value1, Integer value2) {
            addCriterion("admin_id between", value1, value2, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAdmin_idNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_id not between", value1, value2, "admin_id");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewIsNull() {
            addCriterion("appeal_view is null");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewIsNotNull() {
            addCriterion("appeal_view is not null");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewEqualTo(Byte value) {
            addCriterion("appeal_view =", value, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewNotEqualTo(Byte value) {
            addCriterion("appeal_view <>", value, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewGreaterThan(Byte value) {
            addCriterion("appeal_view >", value, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewGreaterThanOrEqualTo(Byte value) {
            addCriterion("appeal_view >=", value, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewLessThan(Byte value) {
            addCriterion("appeal_view <", value, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewLessThanOrEqualTo(Byte value) {
            addCriterion("appeal_view <=", value, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewIn(List<Byte> values) {
            addCriterion("appeal_view in", values, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewNotIn(List<Byte> values) {
            addCriterion("appeal_view not in", values, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewBetween(Byte value1, Byte value2) {
            addCriterion("appeal_view between", value1, value2, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_viewNotBetween(Byte value1, Byte value2) {
            addCriterion("appeal_view not between", value1, value2, "appeal_view");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadIsNull() {
            addCriterion("appeal_download is null");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadIsNotNull() {
            addCriterion("appeal_download is not null");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadEqualTo(Byte value) {
            addCriterion("appeal_download =", value, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadNotEqualTo(Byte value) {
            addCriterion("appeal_download <>", value, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadGreaterThan(Byte value) {
            addCriterion("appeal_download >", value, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadGreaterThanOrEqualTo(Byte value) {
            addCriterion("appeal_download >=", value, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadLessThan(Byte value) {
            addCriterion("appeal_download <", value, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadLessThanOrEqualTo(Byte value) {
            addCriterion("appeal_download <=", value, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadIn(List<Byte> values) {
            addCriterion("appeal_download in", values, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadNotIn(List<Byte> values) {
            addCriterion("appeal_download not in", values, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadBetween(Byte value1, Byte value2) {
            addCriterion("appeal_download between", value1, value2, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andAppeal_downloadNotBetween(Byte value1, Byte value2) {
            addCriterion("appeal_download not between", value1, value2, "appeal_download");
            return (Criteria) this;
        }

        public Criteria andInform_viewIsNull() {
            addCriterion("inform_view is null");
            return (Criteria) this;
        }

        public Criteria andInform_viewIsNotNull() {
            addCriterion("inform_view is not null");
            return (Criteria) this;
        }

        public Criteria andInform_viewEqualTo(Byte value) {
            addCriterion("inform_view =", value, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewNotEqualTo(Byte value) {
            addCriterion("inform_view <>", value, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewGreaterThan(Byte value) {
            addCriterion("inform_view >", value, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewGreaterThanOrEqualTo(Byte value) {
            addCriterion("inform_view >=", value, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewLessThan(Byte value) {
            addCriterion("inform_view <", value, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewLessThanOrEqualTo(Byte value) {
            addCriterion("inform_view <=", value, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewIn(List<Byte> values) {
            addCriterion("inform_view in", values, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewNotIn(List<Byte> values) {
            addCriterion("inform_view not in", values, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewBetween(Byte value1, Byte value2) {
            addCriterion("inform_view between", value1, value2, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_viewNotBetween(Byte value1, Byte value2) {
            addCriterion("inform_view not between", value1, value2, "inform_view");
            return (Criteria) this;
        }

        public Criteria andInform_downloadIsNull() {
            addCriterion("inform_download is null");
            return (Criteria) this;
        }

        public Criteria andInform_downloadIsNotNull() {
            addCriterion("inform_download is not null");
            return (Criteria) this;
        }

        public Criteria andInform_downloadEqualTo(Byte value) {
            addCriterion("inform_download =", value, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadNotEqualTo(Byte value) {
            addCriterion("inform_download <>", value, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadGreaterThan(Byte value) {
            addCriterion("inform_download >", value, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadGreaterThanOrEqualTo(Byte value) {
            addCriterion("inform_download >=", value, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadLessThan(Byte value) {
            addCriterion("inform_download <", value, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadLessThanOrEqualTo(Byte value) {
            addCriterion("inform_download <=", value, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadIn(List<Byte> values) {
            addCriterion("inform_download in", values, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadNotIn(List<Byte> values) {
            addCriterion("inform_download not in", values, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadBetween(Byte value1, Byte value2) {
            addCriterion("inform_download between", value1, value2, "inform_download");
            return (Criteria) this;
        }

        public Criteria andInform_downloadNotBetween(Byte value1, Byte value2) {
            addCriterion("inform_download not between", value1, value2, "inform_download");
            return (Criteria) this;
        }

        public Criteria andSue_viewIsNull() {
            addCriterion("sue_view is null");
            return (Criteria) this;
        }

        public Criteria andSue_viewIsNotNull() {
            addCriterion("sue_view is not null");
            return (Criteria) this;
        }

        public Criteria andSue_viewEqualTo(Byte value) {
            addCriterion("sue_view =", value, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewNotEqualTo(Byte value) {
            addCriterion("sue_view <>", value, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewGreaterThan(Byte value) {
            addCriterion("sue_view >", value, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewGreaterThanOrEqualTo(Byte value) {
            addCriterion("sue_view >=", value, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewLessThan(Byte value) {
            addCriterion("sue_view <", value, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewLessThanOrEqualTo(Byte value) {
            addCriterion("sue_view <=", value, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewIn(List<Byte> values) {
            addCriterion("sue_view in", values, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewNotIn(List<Byte> values) {
            addCriterion("sue_view not in", values, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewBetween(Byte value1, Byte value2) {
            addCriterion("sue_view between", value1, value2, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_viewNotBetween(Byte value1, Byte value2) {
            addCriterion("sue_view not between", value1, value2, "sue_view");
            return (Criteria) this;
        }

        public Criteria andSue_downloadIsNull() {
            addCriterion("sue_download is null");
            return (Criteria) this;
        }

        public Criteria andSue_downloadIsNotNull() {
            addCriterion("sue_download is not null");
            return (Criteria) this;
        }

        public Criteria andSue_downloadEqualTo(Byte value) {
            addCriterion("sue_download =", value, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadNotEqualTo(Byte value) {
            addCriterion("sue_download <>", value, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadGreaterThan(Byte value) {
            addCriterion("sue_download >", value, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadGreaterThanOrEqualTo(Byte value) {
            addCriterion("sue_download >=", value, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadLessThan(Byte value) {
            addCriterion("sue_download <", value, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadLessThanOrEqualTo(Byte value) {
            addCriterion("sue_download <=", value, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadIn(List<Byte> values) {
            addCriterion("sue_download in", values, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadNotIn(List<Byte> values) {
            addCriterion("sue_download not in", values, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadBetween(Byte value1, Byte value2) {
            addCriterion("sue_download between", value1, value2, "sue_download");
            return (Criteria) this;
        }

        public Criteria andSue_downloadNotBetween(Byte value1, Byte value2) {
            addCriterion("sue_download not between", value1, value2, "sue_download");
            return (Criteria) this;
        }

        public Criteria andUser_dealIsNull() {
            addCriterion("user_deal is null");
            return (Criteria) this;
        }

        public Criteria andUser_dealIsNotNull() {
            addCriterion("user_deal is not null");
            return (Criteria) this;
        }

        public Criteria andUser_dealEqualTo(Byte value) {
            addCriterion("user_deal =", value, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealNotEqualTo(Byte value) {
            addCriterion("user_deal <>", value, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealGreaterThan(Byte value) {
            addCriterion("user_deal >", value, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_deal >=", value, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealLessThan(Byte value) {
            addCriterion("user_deal <", value, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealLessThanOrEqualTo(Byte value) {
            addCriterion("user_deal <=", value, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealIn(List<Byte> values) {
            addCriterion("user_deal in", values, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealNotIn(List<Byte> values) {
            addCriterion("user_deal not in", values, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealBetween(Byte value1, Byte value2) {
            addCriterion("user_deal between", value1, value2, "user_deal");
            return (Criteria) this;
        }

        public Criteria andUser_dealNotBetween(Byte value1, Byte value2) {
            addCriterion("user_deal not between", value1, value2, "user_deal");
            return (Criteria) this;
        }

        public Criteria andHelp_replyIsNull() {
            addCriterion("help_reply is null");
            return (Criteria) this;
        }

        public Criteria andHelp_replyIsNotNull() {
            addCriterion("help_reply is not null");
            return (Criteria) this;
        }

        public Criteria andHelp_replyEqualTo(Byte value) {
            addCriterion("help_reply =", value, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyNotEqualTo(Byte value) {
            addCriterion("help_reply <>", value, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyGreaterThan(Byte value) {
            addCriterion("help_reply >", value, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyGreaterThanOrEqualTo(Byte value) {
            addCriterion("help_reply >=", value, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyLessThan(Byte value) {
            addCriterion("help_reply <", value, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyLessThanOrEqualTo(Byte value) {
            addCriterion("help_reply <=", value, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyIn(List<Byte> values) {
            addCriterion("help_reply in", values, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyNotIn(List<Byte> values) {
            addCriterion("help_reply not in", values, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyBetween(Byte value1, Byte value2) {
            addCriterion("help_reply between", value1, value2, "help_reply");
            return (Criteria) this;
        }

        public Criteria andHelp_replyNotBetween(Byte value1, Byte value2) {
            addCriterion("help_reply not between", value1, value2, "help_reply");
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