package com.example.tool.compare;

import com.example.tool.compare.anno.FieldAliasAttr;
import lombok.Data;


@Data
public class BaseInfo extends Base1Info {

    @FieldAliasAttr(alias = "app主键")
    private String appId;

}