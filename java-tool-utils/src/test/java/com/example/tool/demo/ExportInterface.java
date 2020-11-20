package com.example.tool.demo;

import com.example.tool.demo.model.BasePageModel;

import java.util.List;

/**
 * @author mengq
 * @date 2020-11-05 21:13
 * @desc
 */
public interface ExportInterface {

    List<?> doExport(BasePageModel pageModel);
}
