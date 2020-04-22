package com.example.tool.compare;

import com.example.tool.compare.anno.FieldAliasAttr;
import lombok.Data;


@Data
public class Base1Info {

    @FieldAliasAttr(alias = "app主键111")
    private String appIdOne;

}