package com.ruoyi.common.redis.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    //创建 ListUtil 的一个对象
    private static ListUtil instance = new ListUtil();

    //让构造函数为 private，这样该类就不会被实例化
    private ListUtil(){}

    //获取唯一可用的对象
    public synchronized static ListUtil getInstance(){
        if (instance==null) {
            instance=new ListUtil();
        }
        return instance;
    }

    public <T> List<T> getList1(){
        List<T> list1 = new ArrayList<>();
        return list1;
    }
    public <T> List<T> getList2(){
        List<T> list2 = new ArrayList<>();
        return list2;
    }
    public <T> List<T> getList3(){
        List<T> list3 = new ArrayList<>();
        return list3;
    }
}
