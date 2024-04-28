package com.bpf.p2p;

import java.io.Serializable;

public class SchoolRecord implements Serializable {
    private float mathScore;
    private float chineseScore;
    private float englishScore;

    public float getMathScore() {
        return mathScore;
    }

    public void setMathScore(float mathScore) {
        this.mathScore = mathScore;
    }

    public float getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(float chineseScore) {
        this.chineseScore = chineseScore;
    }

    public float getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(float englishScore) {
        this.englishScore = englishScore;
    }

    public  Float getTotal(){
        return mathScore + chineseScore + englishScore;
    }

    @Override
    public String toString() {
        return "SchoolRecord{" +
                "mathScore=" + mathScore +
                ", chineseScore=" + chineseScore +
                ", englishScore=" + englishScore +
                '}';
    }
}
