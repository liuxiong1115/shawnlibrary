package com.shuoxue.pro.model;

import java.io.Serializable;

/**
 * 作者：${刘雄} on 2017/1/21 14:55
 * 邮箱：orange_lx0120@126.com
 */
public class Exam implements Serializable {


    /**
     * crtTime : 1484617103000
     * examMinutes : 25
     * examId : 73
     * examTime : 1484979900000
     * courseId : 25
     * endTime : 3004
     * examName : 新试卷
     * teacherId : 35
     * totTests : 3
     */

    private long crtTime;
    private int examMinutes;
    private int examId;
    private long examTime;
    private int courseId;
    private int endTime;
    private String examName;
    private int teacherId;
    private int totTests;

    public long getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(long crtTime) {
        this.crtTime = crtTime;
    }

    public int getExamMinutes() {
        return examMinutes;
    }

    public void setExamMinutes(int examMinutes) {
        this.examMinutes = examMinutes;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public long getExamTime() {
        return examTime;
    }

    public void setExamTime(long examTime) {
        this.examTime = examTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTotTests() {
        return totTests;
    }

    public void setTotTests(int totTests) {
        this.totTests = totTests;
    }
}
