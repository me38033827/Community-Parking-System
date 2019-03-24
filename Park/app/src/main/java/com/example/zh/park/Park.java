package com.example.zh.park;

/**
 * Created by zh on 16/7/24.
 */
public class Park {
    private String courtname;
    private String price;
    private String remark;
    private int id;

    public Park(String courtname, String price, String remark, int id) {
        this.courtname = courtname;
        this.price = price;
        this.remark = remark;
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public String getRemark() {
        return remark;
    }

    public int getId() {
        return id;
    }

    public String getCourtname() {
        return courtname;
    }
}
