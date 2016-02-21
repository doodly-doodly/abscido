/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.algorithms;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author gokumar
 * @param <T>
 */
public class FindAllPaths<T> {

    private final Graph<T> graph;

    /**
     * Takes in a graph. This graph should not be changed by the client
     *
     * @param graph
     */
    public FindAllPaths(Graph<T> graph) {
        if (graph == null) {
            throw new NullPointerException("The input graph cannot be null.");
        }
        this.graph = graph;
    }

    private void validate(T source, T destination) {

        if (source == null) {
            throw new NullPointerException("The source: " + source + " cannot be  null.");
        }
        if (destination == null) {
            throw new NullPointerException("The destination: " + destination + " cannot be  null.");
        }
        if (source.equals(destination)) {
            throw new IllegalArgumentException("The source and destination: " + source + " cannot be the same.");
        }
    }

    /**
     * Returns the list of paths, where path itself is a list of nodes.
     *
     * @param source the source node
     * @param destination the destination node
     * @return List of all paths
     */
    public List<List<T>> getAllPaths(T source, T destination) {
        validate(source, destination);

        List<List<T>> paths = new ArrayList<>();
        recursive(source, destination, paths, (LinkedHashSet<T>) new LinkedHashSet<>());
        return paths;
    }

    public double getCost(List<T> path) {
        int lngth = path.size();
        int cost = 0;
        for (int indx = 0; indx < lngth - 1; indx++) {
            cost += graph.edgesFrom(path.get(indx)).get(path.get(indx + 1));
        }
        return cost;

    }

    private void recursive(T current, T destination, List<List<T>> paths, LinkedHashSet<T> path) {
        path.add(current);

        if (current.equals(destination)) {
            paths.add(new ArrayList<>(path));
            path.remove(current);
            return;
        }

        final Set<T> edges = graph.edgesFrom(current).keySet();

        for (T t : edges) {
            if (!path.contains(t)) {
                recursive(t, destination, paths, path);
            }
        }

        path.remove(current);
    }
}
