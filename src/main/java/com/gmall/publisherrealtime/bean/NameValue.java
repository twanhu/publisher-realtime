package com.gmall.publisherrealtime.bean;

import lombok.*;

@NoArgsConstructor  //无参构造
@AllArgsConstructor     //全参构造
@Data   // 相当于 @Getter + @Setter
public class NameValue {
    private String name;
    private Object value;
}
