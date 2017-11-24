package com.tiangong.chargingPile.dto;

/**
 * Created by Administrator on 2017/10/25.
 */

public class DataManagerDTO {
    private String name;
    private String ID;
    private String time;
    private String Result;
    private String remark;

    public DataManagerDTO(String name, String ID, String time, String result, String remark) {
        this.name = name;
        this.ID = ID;
        this.time = time;
        Result = result;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
