package com.gggw.model;
import lombok.Data;

import java.util.Date;

@Data
public class MidPoint {
    private Integer id;

    private Date createtime;

    private Integer orderId;

    private Double lg;

    private Double lt;

    private Double accuracy;

    private Double speed;
}