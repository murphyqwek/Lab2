package org.example.lab2.beans;

import java.util.ArrayList;

public class ResultStorageBean {
    private ArrayList<ResultBean> resultList = new ArrayList<>();

    public void setResultList(ArrayList<ResultBean> resultList) {
        this.resultList = resultList;
    }
    public ArrayList<ResultBean> getResultList() {
        return resultList;
    }
}
