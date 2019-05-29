package com.itheima.expandablelistview;

public class xjlInfo {

    //实体类总结：大致三步走
    /*
    定义
    get和setter方法
    Constructor（构建方法）和一个空构建方法

    */
    //定义
    private int logo;
    private String name;

    public xjlInfo() {
    }
    public xjlInfo(int logo, String name) {
        this.logo = logo;
        this.name = name;
    }

    //get和setter方法
    public int getLogo() {
        return logo;
    }
    public void setLogo(int logo) {
        this.logo = logo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
