package ClusterFinder.GUI;

import ClusterFinder.Algorithms.ClusterValidation.AltDunnIndex;
import ClusterFinder.Algorithms.ClusterValidation.ClassificationEntropy;
import ClusterFinder.Algorithms.ClusterValidation.DunnsIndex;
import ClusterFinder.Algorithms.ClusterValidation.PartitionCoefficent;
import ClusterFinder.Algorithms.ClusterValidation.PartitionIndex;
import ClusterFinder.Algorithms.ClusterValidation.SeparationIndex;
import ClusterFinder.Algorithms.ClusterValidation.ValidationAlgorithm;
import ClusterFinder.Algorithms.ClusterValidation.XieBeniIndex;
import ClusterFinder.Algorithms.ClusteringAlgorithm;
import ClusterFinder.Algorithms.FuzzyCMeans;
import ClusterFinder.Algorithms.FuzzyIsodata;
import ClusterFinder.Algorithms.GathGeva;
import ClusterFinder.Algorithms.GustafsonKessel;
import ClusterFinder.Algorithms.Isodata;
import ClusterFinder.Algorithms.KMeans;
import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
import ClusterFinder.Algorithms.StabilityMeasures.AverageProportionNOverlap;
import ClusterFinder.Algorithms.StabilityMeasures.StabilityAlgorithm;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;

public class Main extends JFrame {
    
    double mainMin, mainMax;
    Plot3DPanel plot3D;
    Plot2DPanel plot2D;
    double[] x;
    double[] y;
    double[] z;
    boolean scat, pl, ste, didPlot;
    int sampleSize;
    ArrayList<DataPoint> means;
    ArrayList<Cluster> clusters;
    ArrayList<double[][]> graphs = new ArrayList<>();
    
    private JCheckBox clusterCheck;
    private JCheckBox coordCheck;
    private JTextField dimText;
    private JComboBox dropDown;
    private JPanel graphPanel;
    private JCheckBox groupCheck;
    private JTextField iText;
    private JTextField kText;
    private JTextField lText;
    private JTextField minText;
    private JTextField noText;
    private JTextField ocText;
    private JTextField onText;
    private JTextField osText;
    private JTextField outputFileText;
    private JTextArea outputText;
    private JCheckBox paramCheck;
    private JRadioButton plot;
    private JRadioButton scatter;
    private JCheckBox screenShotCheck;
    private JRadioButton stem;
    public File file = null;
    public File outFile = null;
    public File outFileValid = null;
    public JFrame f = new JFrame("Loading");
    
    public Main() {
        plot3D = new Plot3DPanel();
        plot2D = new Plot2DPanel();
        
        initComponents();        
    }
    
    private void initComponents() {
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel();
        JLabel author = new JLabel();
        JButton kMeans = new JButton();
        JButton isodata = new JButton();
        JPanel dropdownPanel = new JPanel();
        JLabel algorithm = new JLabel();
        dropDown = new JComboBox();
        JPanel plotParam = new JPanel();
        JLabel plotLabel = new JLabel();
        graphPanel = new JPanel();
        JPanel paramPanel = new JPanel();
        JLabel parameters = new JLabel();
        JLabel on = new JLabel();
        JLabel oc = new JLabel();
        JLabel os = new JLabel();
        JLabel k = new JLabel();
        JLabel l = new JLabel();
        JLabel iLabel = new JLabel();
        JLabel no = new JLabel();
        JLabel min = new JLabel();
        JButton help = new JButton();
        onText = new JTextField();
        ocText = new JTextField();
        osText = new JTextField();
        kText = new JTextField();
        lText = new JTextField();
        iText = new JTextField();
        noText = new JTextField();
        minText = new JTextField();
        JPanel radioPanel = new JPanel();
        plot = new JRadioButton();
        scatter = new JRadioButton();
        JLabel plotType = new JLabel();
        stem = new JRadioButton();
        JPanel buttonPanel = new JPanel();
        JButton plotButton = new JButton();
        JButton calcButton = new JButton();
        JButton resetButton = new JButton();
        JButton copyright = new JButton();
        JPanel filePanel = new JPanel();
        JButton fileButton = new JButton();
        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel();
        JLabel dimLabel = new JLabel();
        dimText = new JTextField();
        JButton browse = new JButton();
        JButton inputView = new JButton();
        JPanel fileOutput = new JPanel();
        JLabel fileOutputLabel = new JLabel();
        JLabel fileName = new JLabel();
        outputFileText = new JTextField();
        paramCheck = new JCheckBox();
        clusterCheck = new JCheckBox();
        coordCheck = new JCheckBox();
        groupCheck = new JCheckBox();
        screenShotCheck = new JCheckBox();
        JButton save = new JButton();
        JButton outputView = new JButton();
        JPanel outputPanel = new JPanel();
        JLabel outputLabel = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        outputText = new JTextArea();
        outputText.setText("Please input a file and select an algorithm.");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 450));
        
        titlePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        
        title.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Cluster Finder Using n-ISODATA/Kmeans Algorithm");
        
        author.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        author.setHorizontalAlignment(SwingConstants.CENTER);
        author.setText("by Matthew Chiappa");
        
        kMeans.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        kMeans.setText("K Means");
        kMeans.addActionListener((ActionEvent evt) -> {
            if (dropDown.getSelectedIndex() == 2) {
                Random rand = new Random();
                int num = rand.nextInt(4) + 2;
                dropDown.setSelectedIndex(num);
            } else {
                dropDown.setSelectedIndex(2);
            }
        });
        
        isodata.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isodata.setText("ISODATA");
        isodata.addActionListener((ActionEvent evt) -> {
            if (dropDown.getSelectedIndex() == 0) {
                dropDown.setSelectedIndex(1);
            } else {
                dropDown.setSelectedIndex(0);
            }
        });
        
        GroupLayout titlePanelLayout = new GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
                titlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(kMeans, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(titlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(titlePanelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(author, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE))
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(isodata, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
                titlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(titlePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(titlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(kMeans, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(isodata, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                .addGroup(titlePanelLayout.createSequentialGroup()
                                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(author)))
                        .addGap(14, 14, 14))
        );
        
        dropdownPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        algorithm.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        algorithm.setText("Algorithm:");
        
        dropDown.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dropDown.setModel(new DefaultComboBoxModel(new String[]{"ISODATA", "Fuzzy ISODATA", "K Means",
            "Fuzzy C Means", "Gustafson Kessel", "Gath-Geva"}));
        dropDown.setToolTipText("");
        dropDown.addActionListener((ActionEvent evt) -> {
            if (dropDown.getSelectedIndex() == 0 || dropDown.getSelectedIndex() == 1) {
                onText.setText("20");
                ocText.setText("15");
                osText.setText("10");
                lText.setText("2");
                noText.setText("1");
                minText.setText("100");
                onText.setEnabled(true);
                ocText.setEnabled(true);
                osText.setEnabled(true);
                lText.setEnabled(true);
                noText.setEnabled(true);
                minText.setEnabled(true);
            } else if (dropDown.getSelectedIndex() > 1) {
                onText.setText("");
                ocText.setText("");
                osText.setText("");
                lText.setText("");
                noText.setText("");
                minText.setText("");
                onText.setEnabled(false);
                ocText.setEnabled(false);
                osText.setEnabled(false);
                lText.setEnabled(false);
                noText.setEnabled(false);
                minText.setEnabled(false);
            }
        });
        
        javax.swing.GroupLayout dropdownPanelLayout = new javax.swing.GroupLayout(dropdownPanel);
        dropdownPanel.setLayout(dropdownPanelLayout);
        dropdownPanelLayout.setHorizontalGroup(
                dropdownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dropdownPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dropdownPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(algorithm)
                        .addGap(45, 45, 45))
        );
        dropdownPanelLayout.setVerticalGroup(
                dropdownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dropdownPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(algorithm, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
        );
        
        plotParam.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        
        plotLabel.setFont(new Font("Arial", 1, 14)); // NOI18N
        plotLabel.setText("Plot:");
        
        GroupLayout graphPanelLayout = new GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 394, Short.MAX_VALUE)
                .addComponent(plot3D)
        );
        graphPanelLayout.setVerticalGroup(
                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(plot3D)
        );
        
        parameters.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        parameters.setText("Parameters:");
        
        on.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        on.setText("ON:");
        
        oc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        oc.setText("OC:");
        
        os.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        os.setText("OS:");
        
        k.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        k.setText("K:");
        
        l.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        l.setText("L:");
        
        iLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        iLabel.setText("I:");
        
        no.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        no.setText("NO:");
        
        min.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        min.setText("MIN:");
        
        HelpDialog helpD = new HelpDialog(this, true);
        helpD.setLocationRelativeTo(null);
        helpD.setResizable(false);
        help.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        help.setText("Help");
        help.addActionListener((ActionEvent evt) -> {
            helpD.setVisible(true);
        });
        
        onText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        onText.setHorizontalAlignment(JTextField.CENTER);
        onText.setText("20");
        
        ocText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ocText.setHorizontalAlignment(JTextField.CENTER);
        ocText.setText("15");
        
        osText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        osText.setHorizontalAlignment(JTextField.CENTER);
        osText.setText("10");
        
        kText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        kText.setHorizontalAlignment(JTextField.CENTER);
        kText.setText("8");
        
        lText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lText.setHorizontalAlignment(JTextField.CENTER);
        lText.setText("2");
        
        iText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        iText.setHorizontalAlignment(JTextField.CENTER);
        iText.setText("10");
        
        noText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noText.setHorizontalAlignment(JTextField.CENTER);
        noText.setText("1");
        
        minText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        minText.setHorizontalAlignment(JTextField.CENTER);
        minText.setText("100");
        
        javax.swing.GroupLayout paramPanelLayout = new javax.swing.GroupLayout(paramPanel);
        paramPanel.setLayout(paramPanelLayout);
        paramPanelLayout.setHorizontalGroup(
                paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paramPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(os)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addComponent(osText, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(min)
                                        .addGap(21, 21, 21)
                                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(minText, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(paramPanelLayout.createSequentialGroup()
                                                        .addComponent(help)
                                                        .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(l)
                                        .addGap(36, 36, 36)
                                        .addComponent(lText))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(k)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(kText, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(iLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(iText))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(oc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ocText, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(parameters)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(paramPanelLayout.createSequentialGroup()
                                        .addComponent(on)
                                        .addGap(25, 25, 25)
                                        .addComponent(onText)))
                        .addContainerGap())
        );
        paramPanelLayout.setVerticalGroup(
                paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paramPanelLayout.createSequentialGroup()
                        .addComponent(parameters)
                        .addGap(20, 20, 20)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(on)
                                .addComponent(onText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(oc)
                                .addComponent(ocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(osText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(os))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(k)
                                .addComponent(kText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(l)
                                .addComponent(lText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(iText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(iLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(paramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(minText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(min))
                        .addGap(18, 18, 18)
                        .addComponent(help)
                        .addContainerGap(12, Short.MAX_VALUE))
        );
        
        GroupLayout plotParamLayout = new GroupLayout(plotParam);
        plotParam.setLayout(plotParamLayout);
        plotParamLayout.setHorizontalGroup(
                plotParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(plotParamLayout.createSequentialGroup()
                        .addGroup(plotParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(plotParamLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(plotLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(plotParamLayout.createSequentialGroup()
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(graphPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))
                        .addComponent(paramPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );
        plotParamLayout.setVerticalGroup(
                plotParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(plotParamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(plotParamLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(plotParamLayout.createSequentialGroup()
                                        .addComponent(plotLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(paramPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        
        radioPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        // radio buttons
        plot.setText("Plot");
        plot.addActionListener((ActionEvent evt) -> {
            pl = true;
            scat = false;
            ste = false;
            scatter.setSelected(false);
            stem.setSelected(false);
        });        
        
        scatter.setText("Scatter");
        scatter.addActionListener((ActionEvent evt) -> {
            scat = true;
            ste = false;
            pl = false;
            plot.setSelected(false);
            stem.setSelected(false);
        });
        
        plotType.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        plotType.setText("Plot Type:");
        
        stem.setText("Stem");
        stem.addActionListener((ActionEvent evt) -> {
            pl = false;
            scat = false;
            ste = true;
            scatter.setSelected(false);
            plot.setSelected(false);
        });
        
        GroupLayout radioPanelLayout = new GroupLayout(radioPanel);
        radioPanel.setLayout(radioPanelLayout);
        radioPanelLayout.setHorizontalGroup(
                radioPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(radioPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plotType)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plot)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scatter)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stem)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        radioPanelLayout.setVerticalGroup(
                radioPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(radioPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(plot)
                        .addComponent(scatter)
                        .addComponent(plotType)
                        .addComponent(stem))
        );
        
        buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        
        plotButton.setFont(new Font("Arial", 0, 10)); // NOI18N
        plotButton.setText("Plot");
        plotButton.addActionListener((ActionEvent evt) -> {            
            if (file == null) {
                JOptionPane.showMessageDialog(Main.this, "Please choose a file or create one.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (Integer.parseInt(dimText.getText()) == 2) {
                
                try {
                    Scanner scan = new Scanner(file);
                    int arrSize = 0;
                    
                    while (scan.hasNextLine()) {
                        arrSize++;
                        scan.nextLine();
                    }
                    
                    scan = new Scanner(file);
                    x = new double[arrSize];
                    y = new double[arrSize];
                    int i = 0;
                    
                    scan.nextLine();
                    scan.nextLine();
                    
                    while (scan.hasNext()) {
                        x[i] = scan.nextDouble();
                        y[i] = scan.nextDouble();
                        i++;
                        scan.nextLine();
                    }
                    
                    graphPanel.remove(plot3D);
                    graphPanel.remove(plot2D);
                    
                    plot2D = new Plot2DPanel();
                    if (pl) {
                        plot2D.addLinePlot("2D Plot", Color.GREEN, x, y);
                    } else if (scat) {
                        plot2D.addScatterPlot("2D Plot", Color.GREEN, x, y);
                    } else if (ste) {
                        plot2D.addScatterPlot("2D Scatter Plot", Color.RED, x, y);
                        plot2D.addLinePlot("2D Line Plot", Color.GREEN, x, y);
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    graphPanel.setLayout(graphPanelLayout);
                    graphPanelLayout.setHorizontalGroup(
                            graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 394, Short.MAX_VALUE)
                            .addComponent(plot2D)
                    );
                    graphPanelLayout.setVerticalGroup(
                            graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(plot2D)
                    );
                    
                    graphPanel.repaint();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Integer.parseInt(dimText.getText()) >= 3) {
                try {
                    Scanner scan = new Scanner(file);
                    int arrSize = 0;
                    
                    while (scan.hasNextLine()) {
                        arrSize++;
                        scan.nextLine();
                    }
                    
                    sampleSize = arrSize - 2;
                    
                    scan = new Scanner(file);
                    x = new double[arrSize];
                    y = new double[arrSize];
                    z = new double[arrSize];
                    int i = 0;
                    
                    while (scan.hasNextLine()) {
                        if (scan.hasNextDouble()) {
                            break;
                        }
                        scan.nextLine();
                    }
                    
                    while (scan.hasNextLine()) {
                        x[i] = scan.nextDouble();
                        y[i] = scan.nextDouble();
                        z[i] = scan.nextDouble();
                        i++;
                        scan.nextLine();
                    }
                    
                    graphPanel.remove(plot3D);
                    graphPanel.remove(plot2D);
                    
                    plot3D = new Plot3DPanel();
                    if (pl) {
                        plot3D.addLinePlot("3D Plot", Color.GREEN, x, y, z);
                    } else if (scat) {
                        plot3D.addScatterPlot("3D Plot", Color.GREEN, x, y, z);
                    } else if (ste) {
                        plot3D.addLinePlot("3D Line Plot", Color.GREEN, x, y, z);
                        plot3D.addScatterPlot("3D Scatter Plot", Color.RED, x, y, z);
                    } else {
                        JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    graphPanel.setLayout(graphPanelLayout);
                    graphPanelLayout.setHorizontalGroup(
                            graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 394, Short.MAX_VALUE)
                            .addComponent(plot3D)
                    );
                    graphPanelLayout.setVerticalGroup(
                            graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(plot3D)
                    );
                    
                    graphPanel.repaint();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(Main.this, "The file can only be in 2 or 3 dimensions.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            outputText.setText("Graph Plotted. Press calculate to run the agorithm.");
            didPlot = true;
        });
        
        calcButton.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        calcButton.setText("Calculate");
        calcButton.addActionListener((ActionEvent evt) -> {
            
            JLabel label = new JLabel("Please Wait.");
            JPanel panel = new JPanel();
            panel.add(label);
            f.add(panel);
            f.repaint();
            f.setSize(300, 100);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            
            if (didPlot) {
                Random rand = new Random();
                clusters = new ArrayList<>();
                boolean threeD = false;
                
                switch (dimText.getText()) {
                    case "2":
                        graphPanel.remove(plot3D);
                        graphPanel.remove(plot2D);
                        plot2D = new Plot2DPanel();
                        break;
                    default:
                        graphPanel.remove(plot3D);
                        graphPanel.remove(plot2D);
                        plot3D = new Plot3DPanel();
                        threeD = true;
                        break;
                }
                
                if (dropDown.getSelectedIndex() == 0) {
                    ClusteringAlgorithm iso = new Isodata(Double.parseDouble(onText.getText()),
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                            Integer.parseInt(kText.getText()), Integer.parseInt(lText.getText()),
                            Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()),
                            Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = iso.getClusters();
                } else if (dropDown.getSelectedIndex() == 1) {
                    ClusteringAlgorithm iso = new FuzzyIsodata(Double.parseDouble(onText.getText()),
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                            Integer.parseInt(kText.getText()), Integer.parseInt(lText.getText()),
                            Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()),
                            Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = iso.getClusters();
                } else if (dropDown.getSelectedIndex() == 2) {
                    ClusteringAlgorithm kmeans = new KMeans(Integer.parseInt(kText.getText()),
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = kmeans.getClusters();                    
                } else if (dropDown.getSelectedIndex() == 3) {
                    ClusteringAlgorithm fuzzyKmeans = new FuzzyCMeans(Integer.parseInt(kText.getText()),
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = fuzzyKmeans.getClusters();                    
                } else if (dropDown.getSelectedIndex() == 4) {
                    ClusteringAlgorithm gkmeans = new GustafsonKessel(Integer.parseInt(kText.getText()),
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = gkmeans.getClusters();                    
                } else if (dropDown.getSelectedIndex() == 5) {
                    ClusteringAlgorithm gmeans = new GathGeva(Integer.parseInt(kText.getText()),
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = gmeans.getClusters();                    
                }
                
                int clustCount = 1;
                
                for (Cluster newClust : clusters) {
                    ArrayList<DataPoint> data = newClust.getData();

                    // fix
                    /*Iterator<DataPoint> it = data.iterator();
                     while(it.hasNext()){
                     DataPoint pt = it.next();
                     if(newClust.distanceFromCenter(pt) < Double.parseDouble(minText.getText()))
                     it.remove();
                     }*/
                    float r = rand.nextFloat();
                    float g = rand.nextFloat();
                    float b = rand.nextFloat();                    
                    Color randomColor = new Color(r, g, b);
                    
                    x = new double[data.size()];
                    y = new double[data.size()];
                    int i = 0;
                    
                    String clustString = "Cluster: " + clustCount;
                    
                    if (Integer.parseInt(dimText.getText()) == 2) {
                        
                        for (DataPoint point : data) {
                            x[i] = point.getX();
                            y[i] = point.getY();
                            
                            i++;
                        }
                        
                        if (pl) {
                            plot2D.addLinePlot(clustString, randomColor, x, y);
                        } else if (scat) {
                            plot2D.addScatterPlot(clustString, randomColor, x, y);
                        } else if (ste) {
                            plot2D.addScatterPlot(clustString, randomColor, x, y);
                            plot2D.addLinePlot(clustString, randomColor, x, y);
                        } else {
                            JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } else if (Integer.parseInt(dimText.getText()) >= 3) {
                        z = new double[data.size()];
                        
                        for (DataPoint point : data) {
                            x[i] = point.getX();
                            y[i] = point.getY();
                            z[i] = point.getZ();
                            
                            i++;
                        }
                        
                        if (pl) {
                            plot3D.addLinePlot(clustString, randomColor, x, y, z);
                        } else if (scat) {
                            plot3D.addScatterPlot(clustString, randomColor, x, y, z);
                        } else if (ste) {
                            plot3D.addLinePlot(clustString, randomColor, x, y, z);
                            plot3D.addScatterPlot(clustString, randomColor, x, y, z);
                        } else {
                            JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }
                    
                    clustCount++;
                }

                // add the means to the plot
                double[] meanX = new double[clusters.size()];
                double[] meanY = new double[clusters.size()];
                double[] meanZ = new double[clusters.size()];
                means = new ArrayList<>();
                
                for (int i = 0; i < clusters.size(); i++) {
                    DataPoint mean = clusters.get(i).getCentroid();
                    means.add(mean);
                    meanX[i] = mean.getX();
                    meanY[i] = mean.getY();
                    meanZ[i] = mean.getZ();
                    
                }
                
                if (threeD) {
                    plot3D.addScatterPlot("Means", Color.RED, meanX, meanY, meanZ);
                } else {
                    plot2D.addScatterPlot("Means", Color.RED, meanX, meanY);
                }
                
                switch (dimText.getText()) {
                    case "2":
                        graphPanel.setLayout(graphPanelLayout);
                        graphPanelLayout.setHorizontalGroup(
                                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 394, Short.MAX_VALUE)
                                .addComponent(plot2D)
                        );
                        graphPanelLayout.setVerticalGroup(
                                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(plot2D)
                        );
                        
                        graphPanel.repaint();
                        break;
                    default:
                        graphPanel.setLayout(graphPanelLayout);
                        graphPanelLayout.setHorizontalGroup(
                                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 394, Short.MAX_VALUE)
                                .addComponent(plot3D)
                        );
                        graphPanelLayout.setVerticalGroup(
                                graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(plot3D)
                        );
                        
                        graphPanel.repaint();
                        break;
                }
                outputText.setText("Algorithm successfully ran."
                        + "Press save to take a snapshot of the graph and write the txt file");
                
                createClustValGraphs(0);
            } else {
                JOptionPane.showMessageDialog(Main.this, "You must plot the graph before calculation.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            JOptionPane pane = new JOptionPane(
                    "Would you like to show Stability Measures?");
            Object[] options = new String[]{"No", "Yes"};
            pane.setOptions(options);
            JDialog dialog = pane.createDialog(this, "Stability Measures");
            dialog.setVisible(true);
            Object obj = pane.getValue();
            if (options[1].equals(obj)) {
                createStabilityGraphs();
            }
        
        });

        // read from the file to plot graph
        resetButton.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener((ActionEvent evt) -> {
            graphPanel.remove(plot3D);
            graphPanel.remove(plot2D);
            plot3D = new Plot3DPanel();
            
            graphPanel.setLayout(graphPanelLayout);
            graphPanelLayout.setHorizontalGroup(
                    graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 394, Short.MAX_VALUE)
                    .addComponent(plot3D)
            );
            graphPanelLayout.setVerticalGroup(
                    graphPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(plot3D)
            );
            
            graphPanel.repaint();
            plot.setSelected(false);
            scatter.setSelected(false);
            stem.setSelected(false);
            didPlot = false;
            
            outputText.setText("Please input a file and select an algorithm.");
        });
        
        copyright.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        copyright.setText("Copyrights");
        copyright.addActionListener((ActionEvent evt) -> {
            String copyStr = "JMathTools Copyright:\n\n" + "Copyright (c) 2003, Yann RICHET\n"
                    + "All rights reserved.\n"
                    + "\n\n"
                    + "Apache Copyright:\n\n"
                    + "Copyright [2015] [Matthew Chiappa]\n"
                    + "Licensed under the Apache License, Version 2.0 (the License)\n"
                    + "you may not use this file except in compliance with the License.\n"
                    + "You may obtain a copy of the License at \n"
                    + "http://www.apache.org/licenses/LICENSE-2.0\n"
                    + "Unless required by applicable law or agreed to in writing, software\n"
                    + "distributed under the License is distributed on an AS IS BASIS,\n"
                    + "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n"
                    + "See the License for the specific language governing permissions and\n"
                    + "limitations under the License.";
            JOptionPane.showMessageDialog(Main.this, copyStr,
                    "Copyrights", JOptionPane.INFORMATION_MESSAGE);
        });
        
        GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addComponent(plotButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(copyright, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
        );
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(buttonPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(plotButton)
                                .addComponent(calcButton)
                                .addComponent(resetButton)
                                .addComponent(copyright))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        FileGenerator frame = new FileGenerator(this, true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        fileButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fileButton.setText("File Generator");
        fileButton.addActionListener((ActionEvent evt) -> {
            frame.setFile(null);
            frame.fromText.setText("-10");
            frame.toText.setText("50");
            frame.fileText.setText("output");
            frame.dimension.setText("3");
            frame.numOfSample.setText("50");
            frame.setVisible(true);
            if (frame.getFile() != null && frame.getFile().exists()) {
                file = frame.getFile();
            }
        });
        
        inputPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        
        inputLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        inputLabel.setText("Input(Text File):");
        
        dimLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dimLabel.setText("Dimensions:");
        
        dimText.setHorizontalAlignment(JTextField.CENTER);
        dimText.setText("3");
        
        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt", "text");
        fc.setFileFilter(filter);
        
        browse.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        browse.setText("Browse");
        browse.addActionListener((ActionEvent e) -> {
            int returnVal = fc.showOpenDialog(Main.this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }            
        });
        
        inputView.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        inputView.setText("View");
        inputView.addActionListener((ActionEvent evt) -> {
            if (file == null) {
                JOptionPane.showMessageDialog(Main.this, "File has not been chosen.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Desktop.getDesktop().edit(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Main.this, "Error loading the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        GroupLayout inputPanelLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
                inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(inputLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(inputPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                        .addComponent(dimLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dimText))
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                        .addComponent(browse, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(inputView, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
        );
        inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(inputPanelLayout.createSequentialGroup()
                        .addComponent(inputLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dimLabel)
                                .addComponent(dimText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(inputPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(browse)
                                .addComponent(inputView))
                        .addGap(0, 8, Short.MAX_VALUE))
        );
        
        fileOutput.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        fileOutputLabel.setFont(new Font("Arial", 1, 12)); // NOI18N
        fileOutputLabel.setText("Output:");
        
        fileName.setFont(new Font("Arial", 0, 12)); // NOI18N
        fileName.setText("File Name: ");
        
        outputFileText.setFont(new Font("Arial", 0, 12)); // NOI18N
        outputFileText.setHorizontalAlignment(JTextField.CENTER);
        outputFileText.setText("output");
        
        paramCheck.setFont(new Font("Arial", 0, 12)); // NOI18N
        paramCheck.setText("Input Parameters");
        
        clusterCheck.setFont(new Font("Arial", 0, 12)); // NOI18N
        clusterCheck.setText("Number of Clusters");
        
        coordCheck.setFont(new Font("Arial", 0, 12)); // NOI18N
        coordCheck.setText("Coordinates of Centers");
        
        groupCheck.setFont(new Font("Arial", 0, 12)); // NOI18N
        groupCheck.setText("Cluster Groups");
        
        screenShotCheck.setFont(new Font("Arial", 0, 12)); // NOI18N
        screenShotCheck.setText("Screenshot");
        
        save.setFont(new Font("Arial", 0, 12)); // NOI18N
        save.setText("Save");
        save.addActionListener((ActionEvent evt) -> {
            outFile = new File(outputFileText.getText() + ".txt");
            
            try {
                if (!outFile.exists() && file.exists()) {
                    outFile.createNewFile();
                } else {
                    JOptionPane.showMessageDialog(Main.this, "File already exists or there is no file to read from.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("--------------------------------------------------\n"
                            + "Cluster Finder Using n-ISODATA/Kmeans Algorithm by Matthew Chiappa\n"
                            + "--------------------------------------------------\n\n");
                    
                    DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date newDate = new Date();
                    bw.write("Date and Time: " + date.format(newDate) + "\n");
                    if (dropDown.getSelectedIndex() == 0) {
                        bw.write("Algorithm Used: Isodata\n\n");
                    } else if (dropDown.getSelectedIndex() == 1) {
                        bw.write("Algorithm Used: Fuzzy Isodata\n\n");
                    } else if (dropDown.getSelectedIndex() == 2) {
                        bw.write("Algorithm Used: K Means\n\n");
                    } else if (dropDown.getSelectedIndex() == 3) {
                        bw.write("Algorithm Used: Fuzzy C Means\n\n");
                    } else if (dropDown.getSelectedIndex() == 4) {
                        bw.write("Algorithm Used: Gustafson Kessel\n\n");
                    } else if (dropDown.getSelectedIndex() == 5) {
                        bw.write("Algorithm Used: Gath-Geva\n\n");
                    }
                    
                    if (paramCheck.isSelected()) {
                        String name = outFile.getName();
                        int pos = name.lastIndexOf(".");
                        if (pos > 0) {
                            name = name.substring(0, pos);
                        }
                        name += ".jpeg";
                        
                        bw.write("--------------------------------------------------\n"
                                + "Input Parameters\n"
                                + "--------------------------------------------------\n"
                                + "File Names: " + outFile.getName() + ", " + name
                                + "\nInput Dimension: " + dimText.getText()
                                + "\nInput Sample Size: (" + sampleSize
                                + ", " + dimText.getText() + ")\n\n");
                        if (dropDown.getSelectedIndex() == 0 || dropDown.getSelectedIndex() == 1) {
                            bw.write("ON: " + onText.getText() + "\n");
                            bw.write("OC: " + ocText.getText() + "\n");
                            bw.write("OS: " + osText.getText() + "\n");
                            bw.write("K: " + kText.getText() + "\n");
                            bw.write("L: " + lText.getText() + "\n");
                            bw.write("I: " + iText.getText() + "\n");
                            bw.write("MIN: " + minText.getText() + "\n\n");
                        } else {
                            bw.write("K: " + kText.getText() + "\n\n");
                        }
                        
                    }
                    
                    if (clusterCheck.isSelected()) {
                        bw.write("--------------------------------------------------\n"
                                + "Number of Centers:\n"
                                + "--------------------------------------------------\n"
                                + kText.getText() + "\n\n");
                    }
                    
                    if (coordCheck.isSelected()) {
                        bw.write("--------------------------------------------------\n"
                                + "Coordinates of Centers:\n"
                                + "--------------------------------------------------\n");
                        int count = 1;
                        for (DataPoint point : means) {
                            bw.write("Cluster " + count + " Mean: \n");
                            switch (dimText.getText()) {
                                case "2":
                                    bw.write(Double.toString((double) Math.round(point.getX() * 1000) / 1000)
                                            + ", " + Double.toString((double) Math.round(point.getY() * 1000) / 1000) + "\n\n");
                                    break;
                                case "3":
                                    bw.write(Double.toString((double) Math.round(point.getX() * 1000) / 1000)
                                            + ", " + Double.toString((double) Math.round(point.getY() * 1000) / 1000)
                                            + ", " + Double.toString((double) Math.round(point.getZ() * 1000) / 1000) + "\n\n");
                                    break;
                                default:
                                    String string;
                                    
                                    string = (Double.toString((double) Math.round(point.getX() * 1000) / 1000)
                                            + ", " + Double.toString((double) Math.round(point.getY() * 1000) / 1000)
                                            + ", " + Double.toString((double) Math.round(point.getZ() * 1000) / 1000));
                                    
                                    for (int i = 0; i < point.getExtraParams().length - 3; i++) {
                                        string += ", " + (double) Math.round(point.getExtraParams()[i] * 1000) / 1000;
                                    }
                                    
                                    bw.write(string + "\n\n");
                                    break;
                            }
                            count++;
                        }
                    }
                    
                    if (groupCheck.isSelected()) {
                        bw.write("--------------------------------------------------\n"
                                + "Cluster Groups:\n"
                                + "--------------------------------------------------\n");
                        
                        int count = 1;
                        for (Cluster clust : clusters) {                            
                            ArrayList<DataPoint> points = clust.getData();
                            bw.write("Cluster " + count + ":\n");
                            for (DataPoint point : points) {
                                switch (dimText.getText()) {
                                    case "2":
                                        bw.write(Double.toString(point.getX())
                                                + ", " + Double.toString(point.getY()) + "\n");
                                        break;
                                    case "3":
                                        bw.write(Double.toString(point.getX())
                                                + ", " + Double.toString(point.getY())
                                                + ", " + Double.toString(point.getZ()) + "\n");
                                        break;
                                    default:
                                        String string;
                                        
                                        string = (Double.toString(point.getX())
                                                + ", " + point.getY() + ", " + point.getZ());
                                        
                                        for (int i = 0; i < point.getExtraParams().length; i++) {
                                            string += ", " + point.getExtraParams()[i];
                                        }
                                        
                                        bw.write(string + "\n");
                                        break;
                                }
                            }
                            bw.write("\n\n");
                            count++;
                        }
                        if (dropDown.getSelectedIndex() == 1 || dropDown.getSelectedIndex() >= 3) {
                            fuzzyPatterns(bw);
                        }
                    }
                    
                    if (screenShotCheck.isSelected()) {
                        BufferedImage bufImage = new BufferedImage(graphPanel.getSize().width,
                                graphPanel.getSize().height, BufferedImage.TYPE_INT_RGB);
                        bufImage = bufImage.getSubimage(0, 0, bufImage.getWidth(), bufImage.getHeight());
                        graphPanel.paint(bufImage.createGraphics());
                        File imageFile = new File(outputFileText.getText() + ".jpeg");
                        try {
                            imageFile.createNewFile();
                            ImageIO.write(bufImage, "jpeg", imageFile);
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                    
                    JOptionPane pane = new JOptionPane(
                            "Would you like to generate a txt file for the\n"
                                    + "Cluster Validation Measures?");
                    Object[] options = new String[]{"No", "Yes"};
                    pane.setOptions(options);
                    JDialog dialog = pane.createDialog(this, "Cluster Validation Measures");
                    dialog.setVisible(true);
                    Object obj = pane.getValue();
                    if (options[1].equals(obj)){
                        createClustValGraphs(1);
                        createClustValidTxt();
                    }
                    
                    outputText.setText("File successfully generated."
                            + "Press reset to plot a new set of points.");
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(Main.this, "File could not be generated!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            
            JOptionPane.showMessageDialog(Main.this, "File Successfully Generated!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            
        });
        
        outputView.setFont(new Font("Arial", 0, 12)); // NOI18N
        outputView.setText("View");
        outputView.addActionListener((ActionEvent evt) -> {
            if (outFile != null) {
                try {
                    String name = outFile.getName();
                    int pos = name.lastIndexOf(".");
                    if (pos > 0) {
                        name = name.substring(0, pos);
                    }
                    
                    Desktop.getDesktop().edit(outFile);
                    File pic = new File(name + ".jpeg");
                    
                    if (pic.exists()) {
                        Desktop.getDesktop().open(pic);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else {
                JOptionPane.showMessageDialog(Main.this, "The output file has to be created first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if(outFileValid != null){
                try {
                    Desktop.getDesktop().edit(outFileValid);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        GroupLayout fileOutputLayout = new GroupLayout(fileOutput);
        fileOutput.setLayout(fileOutputLayout);
        fileOutputLayout.setHorizontalGroup(
                fileOutputLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(fileOutputLayout.createSequentialGroup()
                        .addComponent(fileOutputLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(fileOutputLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(fileOutputLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(fileOutputLayout.createSequentialGroup()
                                        .addComponent(fileName)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(outputFileText))
                                .addGroup(fileOutputLayout.createSequentialGroup()
                                        .addGroup(fileOutputLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(paramCheck)
                                                .addComponent(clusterCheck)
                                                .addComponent(coordCheck)
                                                .addComponent(groupCheck)
                                                .addComponent(screenShotCheck))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(fileOutputLayout.createSequentialGroup()
                                        .addComponent(save, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(outputView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        fileOutputLayout.setVerticalGroup(
                fileOutputLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(fileOutputLayout.createSequentialGroup()
                        .addComponent(fileOutputLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(fileOutputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(fileName)
                                .addComponent(outputFileText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paramCheck)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clusterCheck)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(coordCheck)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupCheck)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(screenShotCheck)
                        .addGap(12, 12, 12)
                        .addGroup(fileOutputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(save)
                                .addComponent(outputView))
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        
        GroupLayout filePanelLayout = new GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
                filePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(filePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(filePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fileOutput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, filePanelLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fileButton)
                        .addGap(44, 44, 44))
        );
        filePanelLayout.setVerticalGroup(
                filePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(filePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fileButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileOutput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        
        outputPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        outputLabel.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        outputLabel.setText("Output:");
        outputLabel.setToolTipText("");
        
        jScrollPane1.setBackground(UIManager.getDefaults().getColor("CheckBox.background"));
        
        outputText.setColumns(10);
        outputText.setRows(3);
        outputText.setEditable(false);
        jScrollPane1.setViewportView(outputText);
        
        GroupLayout outputPanelLayout = new GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
                outputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(outputPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(outputPanelLayout.createSequentialGroup()
                        .addComponent(outputLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        outputPanelLayout.setVerticalGroup(
                outputPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(outputPanelLayout.createSequentialGroup()
                        .addComponent(outputLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addContainerGap())
        );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(radioPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(69, 69, 69))
                                                .addComponent(buttonPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(outputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(plotParam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(filePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dropdownPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropdownPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(plotParam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(radioPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(outputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(filePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();
        setTitle("n-ISODATA/KMeans Clustering");
        setLocation(100, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 565));
        setMinimumSize(new Dimension(900, 565));
        setResizable(false);
        setVisible(true);
    }
    
    private void fuzzyPatterns(BufferedWriter bw) {
        int count = 1;
        
        findMinMax();

        // iterate through each cluster
        for (Cluster clust : clusters) {
            try {
                bw.write("Patterns for Cluster " + count + ":\n\n");
                bw.write("Pattern\t\t\t\t");
                int count2 = 1;
                for (Cluster title : clusters) {
                    bw.write("Cluster " + count2 + "\t");
                    count2++;
                }
                bw.write("Min Distance" + "\t" + "Max Distance" + "\n");

                // find membership of each point
                for (DataPoint point : clust.getData()) {
                    // print the pattern
                    bw.write(point.printPoint() + "\t\t");

                    // create doble array to hold membership values
                    double[] memberships = new double[clusters.size()];

                    // iterate through clusters to find the range of distance to cacluate
                    // membership
                    for (int k = 0; k < clusters.size(); k++) {
                        memberships[k] = findMembership(point, clusters.get(k));
                    }

                    // write the memberships to file (make sure to round in the method)
                    for (int j = 0; j < memberships.length; j++) {
                        bw.write(memberships[j] + "\t");
                    }

                    // distance to clusters code goes HERE
                    double distMin = Double.MAX_VALUE;
                    double distMax = Double.MIN_VALUE;
                    
                    for (Cluster cluster : clusters) {
                        if (distMin > cluster.distanceFromCenter(point)) {
                            distMin = (double) Math.round(cluster.distanceFromCenter(point) * 1000000) / 1000000;
                        } else if (distMax < cluster.distanceFromCenter(point)) {
                            distMax = (double) Math.round(cluster.distanceFromCenter(point) * 1000000) / 1000000;
                        }
                    }
                    
                    bw.write(distMin + "\t" + distMax + "\n");
                    
                }
                
                bw.write("\n\n");
                distanceToClusterCenter(bw, count-1);
                count++;
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private void distanceToClusterCenter(BufferedWriter bw, int count) {

        try {
            bw.write("Distances for Cluster " + ++count + ":\n\n");
            count--;
            bw.write("Pattern\t\t\t\t");
            int count2 = 1;
            for (Cluster title : clusters) {
                bw.write("Cluster " + count2 + "\t");
                count2++;
            }
            bw.write("\n");
            
            for (DataPoint point : clusters.get(count).getData()) {
                bw.write(point.printPoint() + "\t\t");

                for (Cluster cluster : clusters) 
                    bw.write(((double) Math.round(cluster
                            .distanceFromCenter(point) * 1000000) / 1000000) + "\t");
                
                bw.write("\n");
            }

            bw.write("\n\n");

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private double findMembership(DataPoint point, Cluster clust) {
        double x1 = changeRange(clust.distanceFromCenter(point), mainMin, mainMax);
        return (double) Math.round((-x1 + 1) * 1000000) / 1000000;
    }
    
    private double changeRange(double oldVal, double oldMin, double oldMax) {
        return ((oldVal - oldMin) / (oldMax - oldMin));
    }
    
    public void findMinMax() {
        mainMin = Double.MAX_VALUE;
        mainMax = Double.MIN_VALUE;        
        
        clusters.stream().forEach((temp) -> {
            temp.getData().stream().forEach((point2) -> {
                clusters.stream().forEach((temp2) -> {
                    temp2.getData().stream().forEach((point3) -> {
                        if (point2.distanceToPoint(point3) > mainMax) {
                            mainMax = point2.distanceToPoint(point3);
                        } else if (point2.distanceToPoint(point3) < mainMin) {
                            mainMin = point2.distanceToPoint(point3);
                        }
                    });
                });
            });
        });
    }
    
    public static void main(String[] args) {
        
        new Main();
        
    }
    
    private void createClustValGraphs(int ind) {
        
        Plot2DPanel plotVer1 = new Plot2DPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }            
        };
        plotVer1.setAxisLabel(0, "    Clusters");
        plotVer1.setAxisLabel(1, "    PC, CE, SC, S, XB, DI, ADI");
        plotVer1.setFixedBounds(1, 0.0, 2.0);
        
        
        Plot2DPanel plotVer2_1 = new Plot2DPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }            
        };
        plotVer2_1.setAxisLabel(0, "    Clusters");
        plotVer2_1.setAxisLabel(1, "    PC, CE, SC, S, XB, DI, ADI");
        plotVer2_1.setFixedBounds(1, 0.0, 2.0);
        
        Plot2DPanel plotVer2_2 = new Plot2DPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }            
        };
        plotVer2_2.setAxisLabel(0, "    Clusters");
        plotVer2_2.setAxisLabel(1, "    PC, CE, SC, S, XB, DI, ADI");
        plotVer2_2.setFixedBounds(1, 0.0, 2.0);
        
        
        
        
        double[] partitions = new double[5];
        double[] clasArr = new double[5];
        double[] index = new double[5];        
        double[] sep = new double[5];
        double[] beni = new double[5];
        double[] dunn = new double[5];
        double[] altdunn = new double[5];
        ValidationAlgorithm coeff, clas, partInd, sepInd, xie, 
                dunnsInd, altDunnInd;
        
        ArrayList<Cluster> tempClust = null;
        ClusteringAlgorithm alg = null;
        
        for (int i = 2; i < 7; i++) {
            switch (dropDown.getSelectedIndex()) {
                case 0:
                    alg = new Isodata(Double.parseDouble(onText.getText()),
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                            i, Integer.parseInt(lText.getText()),
                            Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()),
                            Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 1:
                    alg = new FuzzyIsodata(Double.parseDouble(onText.getText()),
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                            i, Integer.parseInt(lText.getText()),
                            Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()),
                            Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 2:
                    alg = new KMeans(i, Integer.parseInt(iText.getText()),
                            file, Integer.parseInt(dimText.getText()));
                    break;
                case 3:
                    alg = new FuzzyCMeans(i,
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 4:
                    alg = new GustafsonKessel(i,
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 5:
                    alg = new GathGeva(i, Integer.parseInt(iText.getText()),
                            file, Integer.parseInt(dimText.getText()));
                    break;
                default:
                    break;
            }
            if(alg != null)
                tempClust = alg.getClusters();
            
            coeff = new PartitionCoefficent(tempClust);
            clas = new ClassificationEntropy(tempClust);
            partInd = new PartitionIndex(tempClust);
            sepInd = new SeparationIndex(tempClust);
            xie = new XieBeniIndex(tempClust);
            dunnsInd = new DunnsIndex(tempClust);
            altDunnInd = new AltDunnIndex(tempClust);
            
            partitions[i - 2] = coeff.getResult();
            clasArr[i - 2] = clas.getResult();
            index[i - 2] = partInd.getResult();
            sep[i - 2] = sepInd.getResult();
            beni[i - 2] = xie.getResult();
            dunn[i - 2] = dunnsInd.getResult();
            altdunn[i - 2] = altDunnInd.getResult();
            
        }
        
        double[][] graphPoints1 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints1[k][0] = k + 2;
            if (Double.isNaN(partitions[k])) {
                partitions[k] = 0;
            }
            graphPoints1[k][1] = partitions[k];//changeRange(partitions[k], 0, 0.35);
            if (graphPoints1[k][1] > 1) {
                graphPoints1[k][1] = 0.99999;
            }
        }
        
        graphs.add(graphPoints1);
        plotVer1.addLinePlot("Partition Coefficent", Color.BLUE, graphPoints1);
        plotVer2_1.addLinePlot("Partition Coefficent", Color.BLUE, graphPoints1);
        plotVer1 = incThickness(plotVer1, graphPoints1, Color.BLUE);
        plotVer2_1 = incThickness(plotVer2_1, graphPoints1, Color.BLUE);
        
        double[][] graphPoints2 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints2[k][0] = k + 2;
            if (Double.isNaN(clasArr[k])) {
                clasArr[k] = 0;
            }
            graphPoints2[k][1] = clasArr[k];//changeRange(clasArr[k], 0, 0.4);
        }
        
        graphs.add(graphPoints2);
        plotVer1.addLinePlot("Classification Entropy", Color.GREEN, graphPoints2);
        plotVer2_1.addLinePlot("Classification Entropy", Color.GREEN, graphPoints2);
        plotVer1 = incThickness(plotVer1, graphPoints2, Color.GREEN);
        plotVer2_1 = incThickness(plotVer2_1, graphPoints2, Color.GREEN);
        
        double[][] graphPoints3 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints3[k][0] = k + 2;
            if (Double.isNaN(index[k])) {
                index[k] = 0;
            }
            graphPoints3[k][1] = index[k];
        }
        
        graphs.add(graphPoints3);
        plotVer1.addLinePlot("Partition Index", new Color(75,0,130), graphPoints3);
        plotVer2_1.addLinePlot("Partition Index", new Color(75,0,130), graphPoints3);
        plotVer1 = incThickness(plotVer1, graphPoints3, new Color(75,0,130));
        plotVer2_1 = incThickness(plotVer2_1, graphPoints3, new Color(75,0,130));
        
        double[][] graphPoints4 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints4[k][0] = k + 2;
            if (Double.isNaN(sep[k])) {
                sep[k] = 0;
            }
            graphPoints4[k][1] = sep[k];
        }
        
        graphs.add(graphPoints4);
        plotVer1.addLinePlot("Seperation Index", new Color(178,170,0), graphPoints4);
        plotVer2_2.addLinePlot("Seperation Index", new Color(178,170,0), graphPoints4);
        plotVer1 = incThickness(plotVer1, graphPoints4, new Color(178,170,0));
        plotVer2_2 = incThickness(plotVer2_2, graphPoints4, new Color(178,170,0));
        
        double[][] graphPoints5 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints5[k][0] = k + 2;
            if (Double.isNaN(beni[k])) {
                beni[k] = 0;
            }
            graphPoints5[k][1] = beni[k];
        }
        
        graphs.add(graphPoints5);
        //plotVer1.addLinePlot("Xie and Beni’s Index", new Color(112,128,144), graphPoints5);
        //plotVer2_2.addLinePlot("Xie and Beni’s Index", new Color(112,128,144), graphPoints5);
        //plotVer1 = incThickness(plotVer1, graphPoints5, new Color(112,128,144));
        //plotVer2_2 = incThickness(plotVer2_2, graphPoints5, new Color(112,128,144));
        
        double[][] graphPoints6 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints6[k][0] = k + 2;
            if (Double.isNaN(dunn[k])) {
                dunn[k] = 0;
            }
            graphPoints6[k][1] = dunn[k];
        }
        
        graphs.add(graphPoints6);
        plotVer1.addLinePlot("Dunn's Index", Color.RED, graphPoints6);
        plotVer2_2.addLinePlot("Dunn's Index", Color.RED, graphPoints6);
        plotVer1 = incThickness(plotVer1, graphPoints6, Color.RED);
        plotVer2_2 = incThickness(plotVer2_2, graphPoints6, Color.RED);
        
        double[][] graphPoints7 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints7[k][0] = k + 2;
            if (Double.isNaN(altdunn[k])) {
                altdunn[k] = 0;
            }
            graphPoints7[k][1] = altdunn[k];
        }
        
        graphs.add(graphPoints7);
        plotVer1.addLinePlot("Alternative Dunn's Index", new Color(139,69,19), graphPoints7);
        plotVer2_2.addLinePlot("Alternative Dunn's Index", new Color(139,69,19), graphPoints7);
        plotVer1 = incThickness(plotVer1, graphPoints7, new Color(139,69,19));
        plotVer2_2 = incThickness(plotVer2_2, graphPoints7, new Color(139,69,19));
        
        f.setVisible(false);
        
        JFrame ver1 = new JFrame();
        JFrame ver2 = new JFrame();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        if (ind == 0) {
            Object[] options = {"Version 3", "Version 2", "Version 1"};
            int ver = JOptionPane.showOptionDialog(this,
                    "Please Choose a Version:", "Version Select",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);

            if (ver == 2) {
                panel1.add(plotVer1);
                ver1.add(panel1);
                ver1.setSize(450, 350);
                ver1.setLocation(600, 500);
                ver1.setVisible(true);
            }
            else if (ver == 1) {
                panel1.add(plotVer2_1);
                panel2.add(plotVer2_2);
                ver1.add(panel1);
                ver2.add(panel2);
                ver1.setSize(450, 350);
                ver1.setLocation(100, 500);
                ver2.setSize(450, 350);
                ver2.setLocation(600, 500);
                ver1.setVisible(true);
                ver2.setVisible(true);
            }
            else {
                plotVer2_1.addScatterPlot("Partition Coefficent", Color.BLUE, graphPoints1);
                plotVer2_1.addBarPlot("Partition Index", new Color(75,0,130), graphPoints3);
                plotVer2_2.addScatterPlot("Seperation Index", new Color(178,170,0), graphPoints4);
                panel1.add(plotVer2_1);
                panel2.add(plotVer2_2);
                ver1.add(panel1);
                ver2.add(panel2);
                ver1.setSize(450, 350);
                ver1.setLocation(100, 500);
                ver2.setSize(450, 350);
                ver2.setLocation(600, 500);
                ver1.setVisible(true);
                ver2.setVisible(true);
            }
        }
        
        GraphLegend legend = new GraphLegend();
        legend.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        legend.setLocation(1120, 600);
        legend.setResizable(false);
        if(ind == 0)
            legend.setVisible(true);
    }

    private void createClustValidTxt() {

        outFileValid = new File(outputFileText.getText() + "_Clust_Validation.txt");
            
            try {
                
                if (!outFileValid.exists())
                    outFileValid.createNewFile();
                else {
                    JOptionPane.showMessageDialog(Main.this, 
                            "File already exists or there is no file to read from.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                FileWriter fw = new FileWriter(outFileValid.getAbsoluteFile());
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                
                    bw.write("-----------------------------------------------------------\n"
                            + "\t\tCluster Validation Measures\n"
                            + "-----------------------------------------------------------\n\n\n");
                    
                    int count = 0;
                    
                    bw.write("Measures\t\t\t");
                    for(int i = 2; i < 7; i++)
                        bw.write(i + " Cluster(s)\t");
                    
                    bw.write("\n\nPartition Coefficient\t\t");
                                        
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                    bw.write("\nClassification Entropy\t\t");
                    
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                    bw.write("\nPartition Index\t\t");
                    
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                    bw.write("\nSeperation Index\t\t");
                    
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                    bw.write("\nXie and Beni’s Index\t\t");
                    
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                    bw.write("\nDunn's Index\t\t");
                    
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                    bw.write("\nAlternative Dunn's Index\t\t");
                    
                    for(int i = 0; i < 5; i++)
                        bw.write((double) Math.round(graphs.get(count)[i][1] * 1000000) / 1000000 + "\t");
                    count++;
                    
                } 
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(Main.this, "File could not be generated!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
    }

    private Plot2DPanel incThickness(Plot2DPanel plot, double[][] graphPoints, Color col) {
        for (int k = 0; k < 5; k++){
            for(int i = 0; i < graphPoints[1].length; i++)
                graphPoints[1][i] *= 1.1;
        
            plot.addLinePlot("", col, graphPoints);
        
            for(int i = 0; i < graphPoints[1].length; i++)
                graphPoints[1][i] *= 0.9;
            
            plot.addLinePlot("", col, graphPoints);
        }
        return plot;
    }

    private void createStabilityGraphs() {
        f = new JFrame("Loading");
        JLabel label = new JLabel("Please Wait.");
        JPanel panel = new JPanel();
        panel.add(label);
        f.add(panel);
        f.repaint();
        f.setSize(300, 100);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        Plot2DPanel plotVer1 = new Plot2DPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }            
        };
        plotVer1.setAxisLabel(0, "    Clusters");
        plotVer1.setAxisLabel(1, "    APN");
        plotVer1.setFixedBounds(1, 0.0, 1.0);
        
        double[] apn = new double[5];
        
        @SuppressWarnings("UnusedAssignment")
        StabilityAlgorithm apnMes = null;  
        ClusteringAlgorithm alg = null;
        
        for (int i = 2; i < 7; i++) {
            switch (dropDown.getSelectedIndex()) {
                case 0:
                    alg = new Isodata(Double.parseDouble(onText.getText()),
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                            i, Integer.parseInt(lText.getText()),
                            Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()),
                            Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 1:
                    alg = new FuzzyIsodata(Double.parseDouble(onText.getText()),
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                            i, Integer.parseInt(lText.getText()),
                            Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()),
                            Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 2:
                    alg = new KMeans(i, Integer.parseInt(iText.getText()),
                            file, Integer.parseInt(dimText.getText()));
                    break;
                case 3:
                    alg = new FuzzyCMeans(i,
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 4:
                    alg = new GustafsonKessel(i,
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    break;
                case 5:
                    alg = new GathGeva(i, Integer.parseInt(iText.getText()),
                            file, Integer.parseInt(dimText.getText()));
                    break;
                default:
                    break;
            }
           
            apnMes = new AverageProportionNOverlap(alg);
            apn[i - 2] = apnMes.getResult();
            
        }
        
        double[][] graphPoints1 = new double[5][2];
        for (int k = 0; k < 5; k++) {
            graphPoints1[k][0] = k + 2;
            graphPoints1[k][1] = changeRange(apn[k], 0, 0.35);
            if (graphPoints1[k][1] > 1) {
                graphPoints1[k][1] = 0.99999;
            }
            else if(Double.isNaN(graphPoints1[k][1]))
                graphPoints1[k][1] = 0;
        }
        
        graphs.add(graphPoints1);
        plotVer1.addLinePlot("Average Proportion of Non-Overlap", Color.BLUE, graphPoints1);
        plotVer1 = incThickness(plotVer1, graphPoints1, Color.BLUE);
        
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        panel1.add(plotVer1);
        frame.add(panel1);
        frame.setSize(450, 350);
        frame.setLocation(600, 100);
        frame.setVisible(true);
        
        StabilityGraphLegend legend2 = new StabilityGraphLegend();
        legend2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        legend2.setLocation(1200, 100);
        legend2.setResizable(false);
        legend2.setVisible(true);
        
        f.setVisible(false);
    }
}
