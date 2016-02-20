/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.api.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gokumar
 */
public class Path {
    private List<Node> path = new ArrayList<>();

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }
    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public void addPath(Node node){
        path.add(node);
    }
}
