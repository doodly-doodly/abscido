/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.api.objects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author gokumar
 */
public class Path implements Comparable<Path> {

    private List<Node> path = new ArrayList<>();
    private String id = null;
    private double cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addPath(Node node) {
        path.add(node);
    }

    @Override
    public int compareTo(Path o) {
        double compareQuantity = ((Path) o).getCost();

        //ascending order
        return (int) (this.cost - compareQuantity);
    }

    public static Comparator<Path> PathComparator = new Comparator<Path>() {

        @Override
        public int compare(Path path1, Path path2) {

            Double cost1 = path1.getCost();
            Double cost2 = path2.getCost();

            //ascending order
            return cost1.compareTo(cost2);
        }

    };
}
