package com.ensumble.Model;

import androidx.annotation.Nullable;

public class BaseResponse
{

    @Nullable
    private String code;
    @Nullable
    private String Status;
    @Nullable
    private String check;
    @Nullable
    private String counts;
    @Nullable
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Nullable
    public String getCheck() {
        return check;
    }

    public void setCheck(@Nullable String check) {
        this.check = check;
    }

    @Nullable
    public String getCounts() {
        return counts;
    }

    public void setCounts(@Nullable String counts) {
        this.counts = counts;
    }

    @Nullable
    public String getCode() {
        return code;
    }

    public void setCode(@Nullable String code) {
        this.code = code;
    }

    @Nullable
    public String getStatus() {
        return Status;
    }

    public void setStatus(@Nullable String status) {
        Status = status;
    }
}
