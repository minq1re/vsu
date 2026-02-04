package ru.vsu.cs.loseva;

import ru.vsu.cs.loseva.graph.AdjListsGraph;
import ru.vsu.cs.loseva.graph.AdjMatrixGraph;

public class Example {
    public static void main(String[] args){
        AdjListsGraph graph1 = new AdjListsGraph();
        graph1.addAdge(0, 1);
        graph1.addAdge(0, 2);
        graph1.addAdge(1, 2);
        graph1.addAdge(2, 3);
        graph1.addAdge(3, 4);
        graph1.addAdge(4, 5);

        System.out.println("Работа кода для графов, заданных списком смежности:");
        graph1.colorGraph();
        System.out.println();

        AdjMatrixGraph graph2 = new AdjMatrixGraph();
        graph2.addAdge(0, 1);
        graph2.addAdge(0, 2);
        graph2.addAdge(1, 2);
        graph2.addAdge(2, 3);
        graph2.addAdge(3, 4);
        graph2.addAdge(4, 5);

        System.out.println("Работа кода для графов, заданных матрицей смежности:");
        graph2.colorGraph();
    }
}
