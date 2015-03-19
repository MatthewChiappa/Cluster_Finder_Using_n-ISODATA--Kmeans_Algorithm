package ClusterFinder.GUI;

import ClusterFinder.Algorithms.FuzzyIsodata;
import ClusterFinder.Algorithms.FuzzyCMeans;
import ClusterFinder.Algorithms.GathGeva;
import ClusterFinder.Algorithms.GustafsonKessel;
import ClusterFinder.Algorithms.Isodata;
import ClusterFinder.Algorithms.KMeans;
import ClusterFinder.Algorithms.PlotObjects.Cluster;
import ClusterFinder.Algorithms.PlotObjects.DataPoint;
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

public class Main extends JFrame{
    
    Plot3DPanel plot3D;
    Plot2DPanel plot2D;
    double[] x;
    double[] y;
    double[] z;
    boolean scat, pl ,ste, didPlot;
    int sampleSize;
    ArrayList<DataPoint> means;
    ArrayList<Cluster> clusters;
    
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
            if(dropDown.getSelectedIndex() == 2){
                Random rand = new Random();
                int num = rand.nextInt(4)+2;
                dropDown.setSelectedIndex(num);
            }
            else
                dropDown.setSelectedIndex(2);
        });

        isodata.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        isodata.setText("ISODATA");
        isodata.addActionListener((ActionEvent evt) -> {
            if(dropDown.getSelectedIndex() == 0)
                dropDown.setSelectedIndex(1);
            else
                dropDown.setSelectedIndex(0);
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
        dropDown.setModel(new DefaultComboBoxModel(new String[] { "ISODATA", "Fuzzy ISODATA", "K Means",
            "Fuzzy C Means", "Gustafson Kessel", "Gath-Geva"}));
        dropDown.setToolTipText("");
        dropDown.addActionListener((ActionEvent evt) -> {
            if(dropDown.getSelectedIndex() == 0 || dropDown.getSelectedIndex() == 1){
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
            }
            else if(dropDown.getSelectedIndex() > 1){
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
            if(file == null){
                JOptionPane.showMessageDialog(Main.this, "Please choose a file or create one."
                            , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (Integer.parseInt(dimText.getText()) == 2) {
                
                    try {
                        Scanner scan = new Scanner(file);
                        int arrSize = 0;
                        
                        while(scan.hasNextLine()){
                            arrSize++;
                            scan.nextLine();
                        }
                        
                        scan = new Scanner(file);
                        x = new double[arrSize];
                        y = new double[arrSize];
                        int i = 0;
                        
                        scan.nextLine();
                        scan.nextLine();
                        
                        while(scan.hasNext()){
                            x[i] = scan.nextDouble();
                            y[i] = scan.nextDouble();
                            i++;
                            scan.nextLine();
                        }
                        
                        graphPanel.remove(plot3D);
                        graphPanel.remove(plot2D);
                        
                        plot2D = new Plot2DPanel();
                        if(pl) 
                            plot2D.addLinePlot("2D Plot", Color.GREEN, x, y);
                        else if(scat)
                            plot2D.addScatterPlot("2D Plot", Color.GREEN, x, y);
                        else if(ste){
                            plot2D.addScatterPlot("2D Scatter Plot", Color.RED, x, y);
                            plot2D.addLinePlot("2D Line Plot", Color.GREEN, x, y);
                        }
                        else
                            JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph."
                            , "Error", JOptionPane.ERROR_MESSAGE);
                        
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
            if(Integer.parseInt(dimText.getText()) >= 3) {
                    try {
                        Scanner scan = new Scanner(file);
                        int arrSize = 0;
                        
                        while(scan.hasNextLine()){
                            arrSize++;
                            scan.nextLine();
                        }
                                                
                        sampleSize = arrSize-2;
                        
                        scan = new Scanner(file);
                        x = new double[arrSize];
                        y = new double[arrSize];
                        z = new double[arrSize];
                        int i = 0;
                        
                        while (scan.hasNextLine()) {
                            if (scan.hasNextDouble())
                                break;
                            scan.nextLine();
                        }
                        
                        while(scan.hasNext()){
                            x[i] = scan.nextDouble();
                            y[i] = scan.nextDouble();
                            z[i] = scan.nextDouble();
                            i++;
                            scan.nextLine();
                        }
                        
                        graphPanel.remove(plot3D);
                        graphPanel.remove(plot2D);
                        
                        plot3D = new Plot3DPanel();
                        if(pl) 
                            plot3D.addLinePlot("3D Plot", Color.GREEN, x, y, z);
                        else if(scat)
                            plot3D.addScatterPlot("3D Plot", Color.GREEN, x, y, z);
                        else if(ste) {
                            plot3D.addLinePlot("3D Line Plot", Color.GREEN, x, y, z);
                            plot3D.addScatterPlot("3D Scatter Plot", Color.RED, x, y, z);
                        }
                        else
                            JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph."
                            , "Error", JOptionPane.ERROR_MESSAGE);
                        
                            
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
            }
                else
                    JOptionPane.showMessageDialog(Main.this, "The file can only be in 2 or 3 dimensions."
                            , "Error", JOptionPane.ERROR_MESSAGE);
                   
            
            outputText.setText("Graph Plotted. Press calculate to run the agorithm.");
            didPlot = true;
        });

        calcButton.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        calcButton.setText("Calculate");
        calcButton.addActionListener((ActionEvent evt) -> {
            if(didPlot){
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
                
                if(dropDown.getSelectedIndex() == 0) {
                    Isodata iso = new Isodata(Double.parseDouble(onText.getText()), 
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                                Integer.parseInt(kText.getText()), Integer.parseInt(lText.getText()),
                                    Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()), 
                                        Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = iso.getClusters();
                }
                else if(dropDown.getSelectedIndex() == 1) {
                    FuzzyIsodata iso = new FuzzyIsodata(Double.parseDouble(onText.getText()), 
                            Double.parseDouble(ocText.getText()), Double.parseDouble(osText.getText()),
                                Integer.parseInt(kText.getText()), Integer.parseInt(lText.getText()),
                                    Integer.parseInt(iText.getText()), Double.parseDouble(noText.getText()), 
                                        Double.parseDouble(minText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = iso.getClusters();
                }
                else if(dropDown.getSelectedIndex() == 2) {
                    KMeans kmeans = new KMeans(Integer.parseInt(kText.getText()), 
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = kmeans.getClusters(); 
                }
                else if(dropDown.getSelectedIndex() == 3) {
                    FuzzyCMeans fuzzyKmeans = new FuzzyCMeans(Integer.parseInt(kText.getText()), 
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = fuzzyKmeans.getClusters(); 
                }
                else if(dropDown.getSelectedIndex() == 4) {
                    GustafsonKessel gkmeans = new GustafsonKessel(Integer.parseInt(kText.getText()), 
                            Integer.parseInt(iText.getText()), file, Integer.parseInt(dimText.getText()));
                    
                    clusters = gkmeans.getClusters(); 
                }
                else if(dropDown.getSelectedIndex() == 5) {
                    GathGeva gmeans = new GathGeva(Integer.parseInt(kText.getText()), 
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
                    
                    if(Integer.parseInt(dimText.getText()) == 2) {
                       
                            for (DataPoint point: data) {
                                x[i] = point.getX();
                                y[i] = point.getY();
                                
                                i++;
                            }
                            
                            if(pl) 
                                plot2D.addLinePlot(clustString, randomColor, x, y);
                            else if(scat)
                                plot2D.addScatterPlot(clustString, randomColor, x, y);
                            else if(ste){
                                plot2D.addScatterPlot(clustString, randomColor, x, y);
                                plot2D.addLinePlot(clustString, randomColor, x, y);
                            }
                            else
                                JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph."
                                        , "Error", JOptionPane.ERROR_MESSAGE);
                            
                    }
                        else if(Integer.parseInt(dimText.getText()) >= 3) {
                            z = new double[data.size()];
                            
                            for (DataPoint point: data) {
                                x[i] = point.getX();
                                y[i] = point.getY();
                                z[i] = point.getZ();
                                    
                                i++;
                            }
                            
                            if(pl) 
                                plot3D.addLinePlot(clustString, randomColor, x, y, z);
                            else if(scat)
                                plot3D.addScatterPlot(clustString, randomColor, x, y, z);
                            else if(ste) {
                                plot3D.addLinePlot(clustString, randomColor, x, y, z);
                                plot3D.addScatterPlot(clustString, randomColor, x, y, z);
                            }
                            else
                                JOptionPane.showMessageDialog(Main.this, "Please choose a type of graph."
                                        , "Error", JOptionPane.ERROR_MESSAGE);
                            
                    }
                    
                    clustCount++;
                }
                
                // add the means to the plot
                double[] meanX = new double[clusters.size()];
                double[] meanY = new double[clusters.size()];
                double[] meanZ = new double[clusters.size()];
                means = new ArrayList<>();
                
                for(int i = 0; i < clusters.size(); i++){
                    DataPoint mean = clusters.get(i).getCentroid();
                    means.add(mean);
                    meanX[i] = mean.getX();
                    meanY[i] = mean.getY();
                    meanZ[i] = mean.getZ();
                    
                }
                
                if(threeD)
                    plot3D.addScatterPlot("Means", Color.RED, meanX, meanY, meanZ);
                else
                    plot2D.addScatterPlot("Means", Color.RED, meanX, meanY);
                
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
            outputText.setText("Algorithm successfully ran." + 
                    "Press save to take a snapshot of the graph and write the txt file");
                }    
            else {
                JOptionPane.showMessageDialog(Main.this, "You must plot the graph before calculation."
                            , "Error", JOptionPane.ERROR_MESSAGE);
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
            String copyStr = "JMathTools Copyright:\n\n" + "Copyright (c) 2003, Yann RICHET\n" +
                                        "All rights reserved.\n" +
                                        "\n" +
        "Redistribution and use in source and binary forms, with or without modification, \n" +
        "are permitted provided that the following conditions are met:\n" +
                        "\n" +
        "* Redistributions of source code must retain the above copyright notice, this list \n" +
        "  of conditions and the following disclaimer.\n" +
        "* Redistributions in binary form must reproduce the above copyright notice, this \n" +
        "  list of conditions and the following disclaimer in the documentation and/or \n" +
        "  other materials provided with the distribution.\n" +
        "* Neither the name of JMATHTOOLS nor the names of its contributors may be used to \n" +
        "  endorse or promote products derived from this software without specific prior \n" +
        "  written permission.\n" +
                        "\n" +
        "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY \n" +
        "EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES \n" +
        "OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT \n" +
        "SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, \n" +
        "INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED \n" +
        "TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR \n" +
        "BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN \n" +
        "CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN \n" +
        "ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF \n" +
                    "SUCH DAMAGE.";
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
            if(frame.getFile() != null && frame.getFile().exists()){
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
            if(file == null) {
                JOptionPane.showMessageDialog(Main.this, "File has not been chosen."
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    Desktop.getDesktop().edit(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Main.this, "Error loading the file."
                            , "Error", JOptionPane.ERROR_MESSAGE);
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
                if(!outFile.exists() && file.exists()) {
                    outFile.createNewFile();
                }
                else {
                    JOptionPane.showMessageDialog(Main.this, "File already exists or there is no file to read from."
                            , "Error", JOptionPane.ERROR_MESSAGE);
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
                    if(dropDown.getSelectedIndex() == 0)
                        bw.write("Algorithm Used: Isodata\n\n");
                    else if(dropDown.getSelectedIndex() == 1)
                        bw.write("Algorithm Used: Fuzzy Isodata\n\n");
                    else if(dropDown.getSelectedIndex() == 2)
                        bw.write("Algorithm Used: K Means\n\n");    
                    else if (dropDown.getSelectedIndex() == 3)
                        bw.write("Algorithm Used: Fuzzy C Means\n\n");
                    else if (dropDown.getSelectedIndex() == 4)
                        bw.write("Algorithm Used: Gustafson Kessel\n\n");
                    else if (dropDown.getSelectedIndex() == 5)
                        bw.write("Algorithm Used: Gath-Geva\n\n");
                    
                    if(paramCheck.isSelected()) {
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
                        if(dropDown.getSelectedIndex() == 0 || dropDown.getSelectedIndex() == 1){
                            bw.write("ON: " + onText.getText() + "\n");
                            bw.write("OC: " + ocText.getText() + "\n");
                            bw.write("OS: " + osText.getText() + "\n");
                            bw.write("K: " + kText.getText() + "\n");
                            bw.write("L: " + lText.getText() + "\n");
                            bw.write("I: " + iText.getText() + "\n");
                            bw.write("MIN: " + minText.getText() + "\n\n");
                        }
                        else 
                            bw.write("K: " + kText.getText() + "\n\n");
                        
                    }
                    
                    if(clusterCheck.isSelected())
                        bw.write("--------------------------------------------------\n" 
                                + "Number of Centers:\n"
                                    + "--------------------------------------------------\n"
                                        + kText.getText() + "\n\n");
                    
                    if(coordCheck.isSelected()) {
                        bw.write("--------------------------------------------------\n"
                                + "Coordinates of Centers:\n" 
                                    + "--------------------------------------------------\n");
                        int count = 1;
                        for (DataPoint point : means){
                            bw.write("Cluster " + count + " Mean: \n");
                            switch (dimText.getText()) {
                                case "2":
                                    bw.write(Double.toString((double)Math.round(point.getX() * 1000) / 1000) 
                                            + ", " + Double.toString((double)Math.round(point.getY() * 1000) / 1000) + "\n\n");
                                    break;
                                case "3":
                                    bw.write(Double.toString((double)Math.round(point.getX() * 1000) / 1000)
                                            + ", " + Double.toString((double)Math.round(point.getY() * 1000) / 1000)
                                            + ", " + Double.toString((double)Math.round(point.getZ() * 1000) / 1000) + "\n\n");
                                    break;
                                default:
                                    String string;
                                    
                                    string = (Double.toString((double)Math.round(point.getX() * 1000) / 1000)
                                            + ", " + Double.toString((double)Math.round(point.getY() * 1000) / 1000)
                                            + ", " + Double.toString((double)Math.round(point.getZ() * 1000) / 1000));
                                    
                                    for(int i = 0; i < point.getExtraParams().length-3; i++)
                                        string += ", " + (double)Math.round(point.getExtraParams()[i] * 1000) / 1000;
                                    
                                    bw.write(string + "\n\n");
                                    break;
                            }
                        count++;
                        }
                    }
                    
                    if(groupCheck.isSelected()) {
                        bw.write("--------------------------------------------------\n"
                                + "Cluster Groups:\n" 
                                    + "--------------------------------------------------\n");
                        
                        int count = 1;
                        for(Cluster clust: clusters){ 
                            ArrayList<DataPoint> points = clust.getData();
                            bw.write("Cluster " + count + ":\n");
                            for (DataPoint point : points){
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
                                                + ", " + point.getY()+ ", " + point.getZ());
                                    
                                        for(int i = 0; i < point.getExtraParams().length; i++)
                                            string += ", " + point.getExtraParams()[i];
                                    
                                        bw.write(string + "\n");
                                        break;
                                }
                            }
                            bw.write("\n\n");
                            count++;
                        }
                        
                    }
                    
                    if(screenShotCheck.isSelected()) {
                        BufferedImage bufImage = new BufferedImage(graphPanel.getSize().width,
                                graphPanel.getSize().height,BufferedImage.TYPE_INT_RGB);
                        bufImage = bufImage.getSubimage(0, 0, bufImage.getWidth(), bufImage.getHeight());
                        graphPanel.paint(bufImage.createGraphics());
                        File imageFile = new File(outputFileText.getText() + ".jpeg");
                        try{
                            imageFile.createNewFile();
                            ImageIO.write(bufImage, "jpeg", imageFile);
                        }catch(IOException ex){
                            System.out.println(ex);
                        }
                    }
                    outputText.setText("File successfully generated." + 
                            "Press reset to plot a new set of points.");
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog (Main.this, "File could not be generated!"
                        , "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            
            JOptionPane.showMessageDialog (Main.this, "File Successfully Generated!"
                        , "Success!", JOptionPane.INFORMATION_MESSAGE);
            
        });
        
        outputView.setFont(new Font("Arial", 0, 12)); // NOI18N
        outputView.setText("View");
        outputView.addActionListener((ActionEvent evt) -> {
            if(outFile != null) {
                try {
                    String name = outFile.getName();
                    int pos = name.lastIndexOf(".");
                    if (pos > 0) {
                        name = name.substring(0, pos);
                    }
                    
                    Desktop.getDesktop().edit(outFile);
                    File pic = new File(name + ".jpeg");
                    
                    if(pic.exists()){
                        Desktop.getDesktop().open(pic);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(Main.this, "The output file has to be created first."
                            , "Error", JOptionPane.ERROR_MESSAGE);
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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 565));
        setMinimumSize(new Dimension(900, 565));
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        
        new Main();
        
    }
    
}
