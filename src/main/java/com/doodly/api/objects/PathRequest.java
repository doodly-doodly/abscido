/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.api.objects;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gokumar
 */
@XmlRootElement
public class PathRequest {
    private List<Path> existingPaths;
    private Node from;
    private Node to;

    public List<Path> getExistingPaths() {
        return existingPaths;
    }

    public void setExistingPaths(List<Path> existingPaths) {
        this.existingPaths = existingPaths;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }
}
