package com.hp.opta.flex.antlr.model;

import java.io.Serializable;

/**
 * Created by zeev on 4/10/16.
 */
public class FlexToken implements Serializable {

    private String name;
    private TOKEN_TYPE type;
    private String format;
    private int index;


    public FlexToken(String name, TOKEN_TYPE type, String format, int index) {
        this.name = name;
        this.type = type;
        this.format = format;
        this.index = index;
    }


    public static FlexToken create(String name, String type, String format, int index1, int index2, int index3) {
        if ((index1 != index2) && (index2 != index3) && (index1 != index3) && (index3 != -1)) {
            return null;
        } else {
            return new FlexToken(name, TOKEN_TYPE.valueOf(type), format, index1);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TOKEN_TYPE getType() {
        return type;
    }

    public void setType(TOKEN_TYPE type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
