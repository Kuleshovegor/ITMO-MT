package booleanExpressions;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;

public class Tree {
    private final String value;
    private final LexicalAnalyzer.Token token;
    private final List<Tree> children;
    
    public Tree(String value, List<Tree> treeList) {
        this.value = value;
        this.children = treeList;
        this.token = null;
    }

    public Tree(String value) {
        this.value = value;
        this.children = List.of();
        this.token = null;
    }

    public Tree(LexicalAnalyzer.Token token) {
            if (token.equals(LexicalAnalyzer.EOF)) {
                this.value = "EOF";
            } else {
                this.value = token.body;
            }
            this.token = token;
            this.children = List.of();
        }

    private int getGraphviz(MutableGraph mutableGraph, int nowCount) {
        MutableNode node = mutNode(String.valueOf(nowCount)).add(Label.lines(value));
        var count = nowCount;

        for (Tree child : children) {
            count++;
            mutableGraph.add(
                    node.addLink(mutNode(String.valueOf(count)).add(Label.lines(child.value)))
            );
            count = child.getGraphviz(mutableGraph, count);
        }

        return count;
    }

    private void getGraphviz(MutableGraph mutableGraph) {
        getGraphviz(mutableGraph, 0);
    }

    public void writeGraphviz(String src) throws IOException {
         MutableGraph g = mutGraph("tmp").setDirected(true);

        getGraphviz(g);

        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(src));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder;
        if (children.isEmpty() && value != null && !value.equals("EPS")) {
            stringBuilder = new StringBuilder(value);
        } else {
            stringBuilder = new StringBuilder();
        }

        for (Tree child : children) {
            stringBuilder.append(child.toString());
        }

        return stringBuilder.toString();
    }
}

