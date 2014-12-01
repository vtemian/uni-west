package tramways.graph.engine;

import tramways.graph.interfaces.IEdge;
import tramways.graph.interfaces.IWritebleGraph;

import java.util.List;

public class DijstraGraph<T> implements IWritebleGraph<T>{
    @Override
    public void addNewEdge(IEdge<T> edge) {

    }

    @Override
    public void addNewNode(T node) {

    }

    @Override
    public void removeNode(T node) {

    }

    @Override
    public void removeEdge(IEdge<T> node) {

    }

    @Override
    public List<T> getNodes() {
        return null;
    }

    @Override
    public List<IEdge<T>> getEdges() {
        return null;
    }
}
