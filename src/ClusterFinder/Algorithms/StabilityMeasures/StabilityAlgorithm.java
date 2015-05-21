package ClusterFinder.Algorithms.StabilityMeasures;

import ClusterFinder.Algorithms.ClusteringAlgorithm;
import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class StabilityAlgorithm {
    
    double result = 0;
    ClusteringAlgorithm algorithm;
    private final ArrayList<Cluster> clusters;
    File path = null;
    
    public StabilityAlgorithm(ClusteringAlgorithm alg){
        this.algorithm = alg;
        this.clusters = alg.getClusters();
    }
    
    public abstract void run();
    public abstract double getResult();
    
    public File createNew(int count) throws FileNotFoundException, IOException {
        int dim = algorithm.getDim();
        path = new File("temp.txt");
        path.createNewFile();

        int n = 0;
        n = clusters.stream().map((clust) -> clust.getData().size())
                .reduce(n, Integer::sum);
        n *= dim;

        Scanner scan = new Scanner(algorithm.getFile());

        while (scan.hasNextLine()) {
            if (scan.hasNextDouble()) {
                break;
            }
            scan.nextLine();
        }

        int att = count + 1;
        System.out.println("File w/ Attribute " + att + " Removed and " 
                + algorithm.getClusters().size() + " Clusters:");
        FileWriter fw = new FileWriter(path.getAbsoluteFile());
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            while (scan.hasNext()) {
                // transform file
                String currentline = scan.nextLine();

                String[] items = currentline.split("\t");
                double[] points = new double[items.length];

                for (int i = 0; i < items.length; i++) {
                    points[i] = Double.parseDouble(items[i]);
                }
                
                points[count] = 0;

                for (int j = 0; j < points.length; j++) {
                    if(points[j] != 0){
                        bw.write(points[j] + "\t");
                        System.out.print(points[j] + "\t");
                    }
                }
                bw.write("\n");
                System.out.println();
            }
            System.out.println("\n\n\n\n");
        }

        return path;
    }
    
}
