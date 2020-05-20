package com.example.pcr.demo.tread;

/**
 * @author chengri.piao
 * @Title:
 * @Package
 * @Description:
 * @date 2020/5/19 16:08
 */
public class CASCase {
    public volatile int value;

    public void add() {
        value++;
    }
}
