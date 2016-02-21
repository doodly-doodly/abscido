/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.algorithms;

import com.doodly.api.objects.Node;
import com.doodly.api.objects.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gokumar
 */
public class FindPathIntersection {
    public List<Path> intersection(List<Path> paths, List<String> newRequestPath) {
        List<Path> intersectionPaths = new ArrayList<>();
        for (Path path : paths) {
            String str1 = newRequestPath.get(0);
            String str2 = newRequestPath.get(1);
            Node node1 = new Node();
            node1.setName(str1);
            Node node2 = new Node();
            node2.setName(str2);
            int indx1 = path.getNodes().indexOf(node1);
            int indx2 = path.getNodes().indexOf(node2);
            if (indx1 == -1 || indx2 == -1) {
            } else if (indx2 > indx1) {
                intersectionPaths.add(path);
            }
        }
        return intersectionPaths;
    }
}
