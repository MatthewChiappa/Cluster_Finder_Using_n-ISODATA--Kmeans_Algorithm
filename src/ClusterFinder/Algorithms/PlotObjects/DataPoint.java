package ClusterFinder.Algorithms.PlotObjects;

public class DataPoint {
    
    private final double x;
    private final double y;
    private final double z;
    public double[] extraParam;
    
    public DataPoint(double x, double y, double z, double[] extraParam) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.extraParam = extraParam;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
    
    public double[] getExtraParams() {
        return extraParam;
    }
    
    public boolean compareTo(DataPoint newPoint) {
        return x == newPoint.getX() && y == newPoint.getY()
                && z == newPoint.getZ();
    }
    
    public String printPoint() {
        return (x + ", " + y + ", " + z);
    }
    
}
