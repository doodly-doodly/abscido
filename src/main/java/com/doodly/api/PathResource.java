package com.doodly.api;

import com.doodly.algorithms.FindPathIntersection;
import com.doodly.api.objects.PathRequest;
import com.doodly.api.objects.PathResponse;
import com.doodly.algorithms.Graph;
import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author gokumar
 */
@Path("/getPath")
public class PathResource {

    public PathResource() {

    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public PathResponse getPath(PathRequest request) throws Exception {
        Graph<com.doodly.api.objects.Path> graph = new Graph<>();
        List<com.doodly.api.objects.Path> existingPaths = request.getExistingPaths();
        List<String> newRequest = new ArrayList<>();
        newRequest.add(request.getFrom().getName());
        newRequest.add(request.getTo().getName());

        FindPathIntersection fpi = new FindPathIntersection();
        List<com.doodly.api.objects.Path> intersections = fpi.intersection(existingPaths, newRequest);
        Collections.sort(intersections, com.doodly.api.objects.Path.PathComparator);
        PathResponse pathResponse = new PathResponse();
        pathResponse.setPath(intersections.get(0));
        return pathResponse;
    }
}
