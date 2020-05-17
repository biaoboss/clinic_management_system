package com.zx.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBC extends GridBagConstraints {
    
    public GBC(int gridX ,int GridY) {
        this.gridx = gridX;
        this.gridy = GridY;
    }
    public GBC(int gridX,int gridY, int gridWidth, int gridHeight) {
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
    }
    public GBC setFill(int fill) {
        this.fill=fill;
        return this;
    }
    public GBC setInsets(int distance) {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }
    public GBC setInsets(int horizontal,int vertical) {
        this.insets = new Insets(vertical, horizontal, vertical, horizontal);
        return this;
    }
    
    public GBC setInsets(int top ,int right ,int bottom,int left) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }
}
