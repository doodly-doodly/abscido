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
 */
public class FindAllPaths<T> {
    private List<List<T>> intersection(List<List<T>> paths, List<T> newRequestPath) {
        List<List<T>> intersectionPaths = new ArrayList<>();
        for (List<T> path : paths) {
            T str1 = newRequestPath.get(0);
            T str2 = newRequestPath.get(1);
            int indx1 = path.indexOf(str1);
            int indx2 = path.indexOf(str2);
            if (indx1 == -1 || indx2 == -1) {
            } else if (indx2 > indx1) {
                intersectionPaths.add(path);
            }
        }
        return intersectionPaths;
    }

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
        recursive(source, destination, paths, new LinkedHashSet<>());
        System.out.println(paths);
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

    // so far this dude ignore's cycles.
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
    
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addNode("s");
        graph.addNode("o");
        graph.addNode("p");
        graph.addNode("q");
        graph.addNode("r");
        graph.addNode("t");

        graph.addEdge("s", "o", 10);
        graph.addEdge("s", "p", 20);
        graph.addEdge("o", "q", 30);
        graph.addEdge("p", "r", 10);
        graph.addEdge("q", "r", 10);
        graph.addEdge("q", "t", 10);
        graph.addEdge("r", "t", 10);

        //reverse
        graph.addEdge("o", "s", 5);
        graph.addEdge("p", "s", 20);
        graph.addEdge("q", "o", 28);
        graph.addEdge("r", "p", 15);
        graph.addEdge("r", "q", 10);
        graph.addEdge("t", "q", 4);
        graph.addEdge("t", "r", 10);

        FindAllPaths<String> findAllPaths = new FindAllPaths<>(graph);
        List<List<String>> paths = findAllPaths.getAllPaths("t", "s");
        findAllPaths.getAllPaths("s", "t");

        List<String> newRequest = new ArrayList<>();
        newRequest.add("t");
        newRequest.add("o");
        System.out.println("New Request Path: " + newRequest);
        List<List<String>> intersections = findAllPaths.intersection(paths, newRequest);
        System.out.println("Pick existing path: " + intersections);
        for(List<String> path: intersections){
            findAllPaths.getCost(path);
        }
    }
}
