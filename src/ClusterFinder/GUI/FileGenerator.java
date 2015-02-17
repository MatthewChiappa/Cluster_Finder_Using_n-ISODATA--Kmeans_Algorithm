package ClusterFinder.GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileGenerator extends javax.swing.JDialog {
    
    public File file;

    public FileGenerator(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File newFile) {
        this.file = newFile;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        buttons = new javax.swing.JPanel();
        help = new javax.swing.JButton();
        generate = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        outputPanel = new javax.swing.JPanel();
        outputLabel = new javax.swing.JLabel();
        fileName = new javax.swing.JLabel();
        fileText = new javax.swing.JTextField();
        extLabel = new javax.swing.JLabel();
        countPanel = new javax.swing.JPanel();
        randomLabel = new javax.swing.JLabel();
        dimLabel = new javax.swing.JLabel();
        noLabel = new javax.swing.JLabel();
        dimension = new javax.swing.JTextField();
        numOfSample = new javax.swing.JTextField();
        rangePanel = new javax.swing.JPanel();
        rangeLabel = new javax.swing.JLabel();
        from = new javax.swing.JLabel();
        to = new javax.swing.JLabel();
        fromText = new javax.swing.JTextField();
        toText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Generator");

        titlePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        title.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("N-Dimension Random Number File Generator");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        buttons.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        help.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        help.setText("Help");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        generate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        generate.setText("Generate File");
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsLayout = new javax.swing.GroupLayout(buttons);
        buttons.setLayout(buttonsLayout);
        buttonsLayout.setHorizontalGroup(
            buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(help)
                .addGap(107, 107, 107)
                .addComponent(generate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clear)
                .addGap(24, 24, 24))
        );
        buttonsLayout.setVerticalGroup(
            buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(help)
                    .addComponent(generate)
                    .addComponent(clear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        outputPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        outputLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        outputLabel.setText("Output File:");

        fileName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fileName.setText("File Name:");

        fileText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fileText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fileText.setText("output");
        fileText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileTextActionPerformed(evt);
            }
        });

        extLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        extLabel.setText(".txt");

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(fileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileText, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extLabel)
                .addGap(24, 24, 24))
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputLabel)
                .addGap(35, 35, 35)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileName)
                    .addComponent(fileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(extLabel))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        countPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        randomLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        randomLabel.setText("Random Number Count:");

        dimLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dimLabel.setText("Dimension: ");

        noLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        noLabel.setText("No. of Sample:");

        dimension.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        dimension.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dimension.setText("3");
        dimension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dimensionActionPerformed(evt);
            }
        });

        numOfSample.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        numOfSample.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numOfSample.setText("50");

        javax.swing.GroupLayout countPanelLayout = new javax.swing.GroupLayout(countPanel);
        countPanel.setLayout(countPanelLayout);
        countPanelLayout.setHorizontalGroup(
            countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(countPanelLayout.createSequentialGroup()
                .addGroup(countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(countPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(randomLabel))
                    .addGroup(countPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noLabel)
                            .addComponent(dimLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dimension)
                            .addComponent(numOfSample, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        countPanelLayout.setVerticalGroup(
            countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(countPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(randomLabel)
                .addGap(18, 18, 18)
                .addGroup(countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dimLabel)
                    .addComponent(dimension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(countPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noLabel)
                    .addComponent(numOfSample, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        rangePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        rangeLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rangeLabel.setText("Range of Each Random Each:");

        from.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        from.setText("From:");

        to.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        to.setText("To:");

        fromText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fromText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fromText.setText("-10");

        toText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        toText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        toText.setText("50");
        toText.setToolTipText("");

        javax.swing.GroupLayout rangePanelLayout = new javax.swing.GroupLayout(rangePanel);
        rangePanel.setLayout(rangePanelLayout);
        rangePanelLayout.setHorizontalGroup(
            rangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rangePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rangeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rangePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(from)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(to)
                .addGap(18, 18, 18)
                .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        rangePanelLayout.setVerticalGroup(
            rangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rangePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rangeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(from)
                    .addComponent(to)
                    .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rangePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(countPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileTextActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        fromText.setText("");
        toText.setText("");
        fileText.setText("");
        dimension.setText("");
        numOfSample.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
        final JPanel panel = new JPanel();
        Random rand = new Random();
        int fromNum = Integer.parseInt(fromText.getText());
        int toNum = Integer.parseInt(toText.getText());
        int numOfDimen = Integer.parseInt(dimension.getText());
        int numSample = Integer.parseInt(numOfSample.getText());
        file = new File(fileText.getText() + ".txt");
        
        try {
            
            if(fromNum > toNum) {
                JOptionPane.showMessageDialog(panel, "From Exceeds To."
                        , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if(numSample < 0) {
                JOptionPane.showMessageDialog(panel, "No. of Sample can't be less than 0."
                        , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!file.exists())
                file.createNewFile();
            else{
                JOptionPane.showMessageDialog(panel, "File Exists."
                        , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                
                for (int i = 1; i < Integer.parseInt(dimension.getText()) + 1; i++)
                    bw.write("  " + i + ".\t\t");
                bw.write("\n\n");
                
                for (int i = 0; i < numSample; i++) {
                    for(int x = 0; x < numOfDimen; x++){
                        double randNum = (double) (rand.nextFloat()
                                + rand.nextInt(toNum - fromNum) + fromNum);
                        double roundNum = (double)Math.round(randNum * 1000) / 1000;
                        String string = Double.toString(roundNum);
                        bw.write(string + "\t\t");
                    }
                    bw.write("\n");
                }
                
                JOptionPane.showMessageDialog (panel, "File Successfully Generated!"
                        , "Success!", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            }
        } catch (IOException ex) {
            System.out.println("FAILURE: " + ex);
        }
        
    }//GEN-LAST:event_generateActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        final JPanel panel = new JPanel();
        JOptionPane.showMessageDialog (panel, "Help information will be put here."
                        , "Help", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_helpActionPerformed

    private void dimensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dimensionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dimensionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FileGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FileGenerator dialog = new FileGenerator(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttons;
    private javax.swing.JButton clear;
    private javax.swing.JPanel countPanel;
    private javax.swing.JLabel dimLabel;
    public javax.swing.JTextField dimension;
    private javax.swing.JLabel extLabel;
    private javax.swing.JLabel fileName;
    public javax.swing.JTextField fileText;
    private javax.swing.JLabel from;
    public javax.swing.JTextField fromText;
    private javax.swing.JButton generate;
    private javax.swing.JButton help;
    private javax.swing.JLabel noLabel;
    public javax.swing.JTextField numOfSample;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JLabel randomLabel;
    private javax.swing.JLabel rangeLabel;
    private javax.swing.JPanel rangePanel;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel to;
    public javax.swing.JTextField toText;
    // End of variables declaration//GEN-END:variables
}
