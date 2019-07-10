package com.drp.models;

import com.drp.constants.SystemConstant;

public class JsonResult {
    private int result = SystemConstant.FAILED;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
