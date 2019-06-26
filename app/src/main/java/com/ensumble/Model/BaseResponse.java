package com.ensumble.Model;

import androidx.annotation.Nullable;

public class BaseResponse
{

    @Nullable
    private String code;
    @Nullable
    private String Status;


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
