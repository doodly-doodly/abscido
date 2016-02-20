/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.api.objects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gokumar
 */
@XmlRootElement
public class PathsResponse {

    private List<Path> paths = new ArrayList<>();

    public List<Path> getPaths() {
        return paths;
    }
    
    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public PathsResponse() {
        super();
    }

    public void addPaths(Path path){
        paths.add(path);
    }
}
