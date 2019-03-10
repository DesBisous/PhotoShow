package com.forms;

/**
 * Created by Benson on 2016/5/23.
 */
public class GraphForm {
    private String note;
    private String order;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "GraphForm{" +
                "note='" + note + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
