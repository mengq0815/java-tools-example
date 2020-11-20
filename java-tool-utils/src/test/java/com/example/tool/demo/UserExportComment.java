package com.example.tool.demo;

import com.example.tool.demo.model.BasePageModel;
import com.example.tool.demo.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengq
 * @date 2020-11-05 21:09
 * @desc
 */
public class UserExportComment implements ExportInterface {


    @Override
    public List<UserInfo> doExport(BasePageModel param) {
        List<UserInfo> list;
        if (param.getPage() == 1) {
            list = new ArrayList<>();
            list.add(new UserInfo(1, "1", 1));
            return list;
        }

        if (param.getPage() == 2) {
            list = new ArrayList<>();
            list.add(new UserInfo(2, "2", 2));
            return list;
        }

        if (param.getPage() == 3) {
            list = new ArrayList<>();
            list.add(new UserInfo(3, "3", 3));
            return list;
        }

        return new ArrayList<>();
    }


}