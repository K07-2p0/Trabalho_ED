package Graphs;

import java.util.Iterator;
import Exceptions.EmptyCollectionException;

/**
 * Interface GraphADT define as operações para uma estrutura de grafo.
 */
public interface GraphADT<T> {
    /** Adiciona um vértice ao grafo */
    void addVertex(T vertex);

    /** Remove um vértice do grafo */
    void removeVertex(T vertex);

    /** Adiciona uma aresta entre dois vértices */
    void addEdge(T vertex1, T vertex2);

    /** Remove uma aresta entre dois vértices */
    void removeEdge(T vertex1, T vertex2);

    /** Retorna um iterador para a travessia em largura (BFS) */
    Iterator<T> iteratorBFS(T startVertex) throws EmptyCollectionException;

    /** Retorna um iterador para a travessia em profundidade (DFS) */
    Iterator<T> iteratorDFS(T startVertex) throws EmptyCollectionException;

    /** Retorna um iterador com o caminho mais curto entre dois vértices */
    Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyCollectionException;

    /** Verifica se o grafo está vazio */
    boolean isEmpty();

    /** Verifica se o grafo é conexo */
    boolean isConnected() throws EmptyCollectionException;

    /** Retorna o número de vértices */
    int size();

    /** Representação em string */
    String toString();
}