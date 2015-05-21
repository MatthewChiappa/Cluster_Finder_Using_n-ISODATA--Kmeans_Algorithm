package ClusterFinder.Algorithms.StabilityMeasures;

import ClusterFinder.Algorithms.*;
import ClusterFinder.Algorithms.PlotObjects.Cluster;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AverageProportionNOverlap extends StabilityAlgorithm {

    public AverageProportionNOverlap(ClusteringAlgorithm alg) {
        super(alg);
        run();
        path.delete();
    }

    @Override
    public double getResult() {
        return result;
    }

    @Override
    public void run() {

        System.out.println("Original File: ");
        algorithm.getClusters().stream().forEach((clust) -> {
            clust.printCluster();
        });
        System.out.println("\n\n\n\n");
        
        int dim = algorithm.getDim();
        double unions = 0, nNew = 0, n = 0, m = 0;

        for (int i = 0; i < dim; i++) {

            try {
                path = createNew(i);
                ClusteringAlgorithm newAlg = null;

                if ((algorithm instanceof FuzzyIsodata)) {
                    newAlg = new FuzzyIsodata((FuzzyIsodata) algorithm, path);
                } else if (algorithm instanceof Isodata) {
                    newAlg = new Isodata((Isodata) algorithm, path);
                } else if ((algorithm instanceof FuzzyCMeans)) {
                    newAlg = new FuzzyCMeans((FuzzyCMeans) algorithm, path);
                } else if ((algorithm instanceof GustafsonKessel)) {
                    newAlg = new GustafsonKessel((GustafsonKessel) algorithm, path);
                } else if ((algorithm instanceof GathGeva)) {
                    newAlg = new GathGeva((GathGeva) algorithm, path);
                } else if ((algorithm instanceof KMeans)) {
                    newAlg = new KMeans((KMeans) algorithm, path);
                }

                if (newAlg != null) {
                    for (Cluster oldClust : algorithm.getClusters()) {
                        for (Cluster newClust : newAlg.getClusters()) {
                            nNew = newClust.getData().size();
                            for(int k = 0; k < newClust.getData().size()
                                    && k < oldClust.getData().size(); k++)
                                if(newClust.getData().get(k)
                                        .similarTo(oldClust.getData().get(k), i))
                                    unions++;
                        }

                        n += oldClust.getData().size();
                        result += abs(1 - (unions / nNew));
                        unions = 0;
                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AverageProportionNOverlap.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AverageProportionNOverlap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        result *= 1 / pow(n, 2);
    }
    
}
