package com.doodly.api;

import com.doodly.algorithms.FindAllPaths;
import com.doodly.algorithms.FindPathIntersection;
import com.doodly.api.objects.PathsRequest;
import com.doodly.api.objects.Node;
import com.doodly.api.objects.Edge;
import com.doodly.algorithms.Graph;
import com.doodly.api.objects.PathResponse;
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
    public PathResponse getAllPaths(PathsRequest request) throws Exception {
        Graph<String> graph = new Graph<>();
        List<Node> nodes = request.getNodes();
        List<Edge> edges = request.getEdges();
        List<com.doodly.api.objects.Path> existingPaths = request.getExistingPaths();
        Node from = request.getFrom();
        Node to = request.getTo();

        if (existingPaths != null && existingPaths.size() > 0) {
            List<String> newRequest = new ArrayList<>();
            newRequest.add(request.getFrom().getName());
            newRequest.add(request.getTo().getName());

            FindPathIntersection fpi = new FindPathIntersection();
            List<com.doodly.api.objects.Path> intersections = fpi.intersection(existingPaths, newRequest);
            Collections.sort(intersections, com.doodly.api.objects.Path.PathComparator);
            PathResponse pathResponse = new PathResponse();
            pathResponse.setPath(intersections.get(0));
            return pathResponse;
        } else {
            for (Node node : nodes) {
                graph.addNode(node.getName());
            }

            for (Edge edge : edges) {
                graph.addEdge(edge.getFrom().getName(), edge.getTo().getName(), edge.getCost());
            }

            FindAllPaths<String> findAllPaths = new FindAllPaths<>(graph);
            List<List<String>> paths = findAllPaths.getAllPaths(from.getName(), to.getName());
            Node node;
            com.doodly.api.objects.Path costPath;
            double cost;
            List<com.doodly.api.objects.Path> shortlistedPaths = new ArrayList<>();
            for (List<String> path : paths) {
                cost = findAllPaths.getCost(path);
                costPath = new com.doodly.api.objects.Path();
                for (String pathNode : path) {
                    node = new Node();
                    node.setName(pathNode);
                    costPath.addPath(node);
                }
                costPath.setCost(cost);
                if(costPath.getNodes().size() > 2){
                    shortlistedPaths.add(costPath);
                }
            }
            Collections.sort(shortlistedPaths, com.doodly.api.objects.Path.PathComparator);
            PathResponse pathResponse = new PathResponse();
            pathResponse.setPath(shortlistedPaths.get(0));
            return pathResponse;
        }
    }
}
