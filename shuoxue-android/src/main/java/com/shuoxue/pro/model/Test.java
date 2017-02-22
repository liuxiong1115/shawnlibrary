package com.shuoxue.pro.model;

import java.io.Serializable;

/**
 * 作者：${刘雄} on 2017/1/21 14:57
 * 邮箱：orange_lx0120@126.com
 */
public class Test implements Serializable {

    /**
     * testSoluUrl : 。。。
     * testType : 3
     * testOrder : 2
     * testId : 107
     * crtTime : 1484617103000
     * examId : 73
     * testAnswers : 13
     * testScore : 10
     * testTextUrl : 求<img src="../images/test-image/73/ba6.png" mce_src="../images/test-image/73/ba6.png" />（    ）<br/>
     */

    private String testSoluUrl;
    private int testType;
    private int testOrder;
    private int testId;
    private long crtTime;
    private int examId;
    private String testAnswers;
    private int testScore;
    private String testTextUrl;

    public String getTestSoluUrl() {
        return testSoluUrl;
    }

    public void setTestSoluUrl(String testSoluUrl) {
        this.testSoluUrl = testSoluUrl;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public int getTestOrder() {
        return testOrder;
    }

    public void setTestOrder(int testOrder) {
        this.testOrder = testOrder;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public long getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(long crtTime) {
        this.crtTime = crtTime;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getTestAnswers() {
        return testAnswers;
    }

    public void setTestAnswers(String testAnswers) {
        this.testAnswers = testAnswers;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    public String getTestTextUrl() {
        return testTextUrl;
    }

    public void setTestTextUrl(String testTextUrl) {
        this.testTextUrl = testTextUrl;
    }
}
