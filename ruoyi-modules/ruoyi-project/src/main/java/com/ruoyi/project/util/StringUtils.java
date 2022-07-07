/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ruoyi.project.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zheng Jie
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 自动生成编号
     *
     * @param prefix 前缀，往往是一串字符串
     * @param oldCode 要查询的最后一条数据的编号
     * @param patten 时间格式
     * @return
     */
    public static String getCode(String prefix, String oldCode, String patten){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        String nowDate = sdf.format(date);
        String code = "";
        System.out.println("oldCode==:"+oldCode);
        //获得第一个点的位置
        int index = oldCode.indexOf("-");
        System.out.println("获得第一个点的位置:"+index);
        //根据第一个点的位置 获得第二个点的位置
        index = oldCode.indexOf("-", index + 1);
        System.out.println("根据第一个点的位置 获得第二个点的位置:"+index);
        //根据第二个点的位置，截取 字符串。得到结果 result
        String result = oldCode.substring(index + 1);
        if(oldCode.contains(nowDate)){
            Long orser=Long.valueOf(result);
            orser++;
            if (orser<10){
                code=String.valueOf(prefix+"-"+nowDate+"-000"+orser);
            }
            if (orser>=10&&orser<100){
                code=String.valueOf(prefix+"-"+nowDate+"-00"+orser);
            }
            if (orser>=100&&orser<1000){
                code=String.valueOf(prefix+"-"+nowDate+"-0"+orser);
            }
        }else {
            code=prefix+"-"+nowDate+"-"+"0001";
        }
        return  code;
    }

}
