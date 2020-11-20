
package com.example.tool.demo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mengq
 * @date 2020-11-05 21:09
 * @desc
 */
@Data
public class ExportResultModel implements Serializable {

    private int total;

    private List<?> list;

}