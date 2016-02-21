/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doodly.api.objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gokumar
 */
@XmlRootElement
public class PathResponse {
    private Path path = null;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public PathResponse() {
        super();
    }
}
