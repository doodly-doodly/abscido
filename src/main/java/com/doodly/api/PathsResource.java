package com.doodly.api;

import com.doodly.algorithms.FindAllPaths;
import com.doodly.api.objects.PathsRequest;
import com.doodly.api.objects.PathsResponse;
import com.doodly.api.objects.Node;
import com.doodly.api.objects.Edge;
import com.doodly.algorithms.Graph;

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
@Path("/getAllPaths")
public class PathsResource {

    public PathsResource() {

    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public PathsResponse getAllPaths(PathsRequest request) throws Exception {
        Graph<String> graph = new Graph<>();
        List<Node> nodes = request.getNodes();
        List<Edge> edges = request.getEdges();
        Node from = request.getFrom();
        Node to = request.getTo();

        for (Node node : nodes) {
            graph.addNode(node.getName());
        }

        for (Edge edge : edges) {
            graph.addEdge(edge.getFrom().getName(), edge.getTo().getName(), edge.getCost());
        }
        
//        Graph<String> graph = new Graph<>();
//        graph.addNode("s");
//        graph.addNode("o");
//        graph.addNode("p");
//        graph.addNode("q");
//        graph.addNode("r");
//        graph.addNode("t");
//
//        graph.addEdge("s", "o", 10);
//        graph.addEdge("s", "p", 20);
//        graph.addEdge("o", "q", 30);
//        graph.addEdge("p", "r", 10);
//        graph.addEdge("q", "r", 10);
//        graph.addEdge("q", "t", 10);
//        graph.addEdge("r", "t", 10);
//
//        //reverse
//        graph.addEdge("o", "s", 5);
//        graph.addEdge("p", "s", 20);
//        graph.addEdge("q", "o", 28);
//        graph.addEdge("r", "p", 15);
//        graph.addEdge("r", "q", 10);
//        graph.addEdge("t", "q", 4);
//        graph.addEdge("t", "r", 10);
//
//        FindAllPaths<String> findAllPaths = new FindAllPaths<>(graph);
//        List<List<String>> paths = findAllPaths.getAllPaths("t", "s");

        FindAllPaths<String> findAllPaths = new FindAllPaths<>(graph);
        List<List<String>> paths = findAllPaths.getAllPaths(from.getName(), to.getName());
        Node node;
        com.doodly.api.objects.Path costPath;
        PathsResponse pathsResponse = new PathsResponse();
        double cost;
        for (List<String> path : paths) {
            cost = findAllPaths.getCost(path);
            costPath = new com.doodly.api.objects.Path();
            for(String pathNode: path){
                node = new Node();
                node.setName(pathNode);
                costPath.addPath(node);
            }
            costPath.setCost(cost);
            pathsResponse.addPaths(costPath);
        }
        return pathsResponse;
    }
}
