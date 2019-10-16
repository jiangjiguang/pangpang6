package com.pangpang6.hadoop.common;

import com.pangpang6.utils.MyDateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClickBean implements Comparable<ClickBean> {
    private String dt;
    private int type;
    private int custormerId;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCustormerId() {
        return custormerId;
    }

    public void setCustormerId(int custormerId) {
        this.custormerId = custormerId;
    }

    @Override
    public int compareTo(ClickBean o) {
        Date date1 = MyDateUtils.str2Date(this.dt);
        Date date2 = MyDateUtils.str2Date(o.dt);
        if (date1.before(date2)) {
            return -1;
        } else if (date1.after(date2)) {
            return 1;
        } else {
            return 0;
        }
    }
}
