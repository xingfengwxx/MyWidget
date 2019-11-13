package com.wangxingxing.widget.lsn10.bean;

public class Girl {
    private String name;
    private int icon;
    private int age;

    public Girl(String name, int icon, int age) {
        this.name = name;
        this.icon = icon;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
