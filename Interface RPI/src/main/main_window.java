/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Alexandre
 */
public class main_window extends javax.swing.JFrame {

    /**
     * Creates new form main_window
     */
    public main_window() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Button_control = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton_plus = new javax.swing.JButton();
        jButton_down = new javax.swing.JButton();
        jButton_up = new javax.swing.JButton();
        jButton_minus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton_cancel = new javax.swing.JButton();
        jButton_ok = new javax.swing.JButton();
        jPanel_options = new javax.swing.JPanel();
        jButton_deco = new javax.swing.JButton();
        jButton_shutdown = new javax.swing.JButton();
        jButton_clavier = new javax.swing.JButton();
        jPanel_Clavier = new javax.swing.JPanel();
        jButton_number_1 = new javax.swing.JButton();
        jButton_number_2 = new javax.swing.JButton();
        jButton_number_3 = new javax.swing.JButton();
        jButton_number_4 = new javax.swing.JButton();
        jButton_number_5 = new javax.swing.JButton();
        jButton_number_6 = new javax.swing.JButton();
        jButton_number_7 = new javax.swing.JButton();
        jButton_number_8 = new javax.swing.JButton();
        jButton_number_9 = new javax.swing.JButton();
        jButton_number_0 = new javax.swing.JButton();
        jButton_number_cancel = new javax.swing.JButton();
        jPanel_auth = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_Main = new javax.swing.JPanel();
        jSplitPane = new javax.swing.JSplitPane();
        jPanel_Button = new javax.swing.JPanel();
        jButton_modifier = new javax.swing.JButton();
        jButton_options = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel_view = new javax.swing.JPanel();
        jTextField_barcode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel_type = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jButton_plus.setText("+");
        jButton_plus.setAlignmentY(0.0F);
        jButton_plus.setPreferredSize(new java.awt.Dimension(50, 50));

        jButton_down.setText("▼");
        jButton_down.setPreferredSize(new java.awt.Dimension(50, 50));

        jButton_up.setText("▲");
        jButton_up.setAlignmentY(0.0F);
        jButton_up.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton_up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_upMouseClicked(evt);
            }
        });
        jButton_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_upActionPerformed(evt);
            }
        });

        jButton_minus.setText("-");
        jButton_minus.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_minusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_down, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jButton_up, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_minus, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton_plus, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_plus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_up, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_minus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton_cancel.setText("Annuler");
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });

        jButton_ok.setText("Ok");
        jButton_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_ok, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_Button_controlLayout = new javax.swing.GroupLayout(jPanel_Button_control);
        jPanel_Button_control.setLayout(jPanel_Button_controlLayout);
        jPanel_Button_controlLayout.setHorizontalGroup(
            jPanel_Button_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Button_controlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Button_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_Button_controlLayout.setVerticalGroup(
            jPanel_Button_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Button_controlLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Button_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jButton_deco.setText("Déconnexion");
        jButton_deco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_decoActionPerformed(evt);
            }
        });

        jButton_shutdown.setText("Arrêter");
        jButton_shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_shutdownActionPerformed(evt);
            }
        });

        jButton_clavier.setText("Clavier Numérique");
        jButton_clavier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clavierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_optionsLayout = new javax.swing.GroupLayout(jPanel_options);
        jPanel_options.setLayout(jPanel_optionsLayout);
        jPanel_optionsLayout.setHorizontalGroup(
            jPanel_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_deco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_shutdown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_clavier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_optionsLayout.setVerticalGroup(
            jPanel_optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_clavier, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_deco, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton_shutdown, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jButton_number_1.setText("1");
        jButton_number_1.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_number_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_number_1ActionPerformed(evt);
            }
        });

        jButton_number_2.setText("2");

        jButton_number_3.setText("3");

        jButton_number_4.setText("4");

        jButton_number_5.setText("5");

        jButton_number_6.setText("6");

        jButton_number_7.setText("7");

        jButton_number_8.setText("8");

        jButton_number_9.setText("9");

        jButton_number_0.setText("0");
        jButton_number_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_number_0ActionPerformed(evt);
            }
        });

        jButton_number_cancel.setText("Annuler");
        jButton_number_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_number_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ClavierLayout = new javax.swing.GroupLayout(jPanel_Clavier);
        jPanel_Clavier.setLayout(jPanel_ClavierLayout);
        jPanel_ClavierLayout.setHorizontalGroup(
            jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_number_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                                .addGroup(jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_number_0)
                                    .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                                        .addComponent(jButton_number_7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_number_8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_number_9))
                            .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                                .addComponent(jButton_number_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_number_2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_number_3))
                            .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                                .addComponent(jButton_number_4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_number_5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_number_6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel_ClavierLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_number_0, jButton_number_2, jButton_number_3, jButton_number_4, jButton_number_5, jButton_number_6, jButton_number_7, jButton_number_8, jButton_number_9});

        jPanel_ClavierLayout.setVerticalGroup(
            jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ClavierLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_number_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_number_2)
                    .addComponent(jButton_number_3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_number_4)
                    .addComponent(jButton_number_5)
                    .addComponent(jButton_number_6))
                .addGap(9, 9, 9)
                .addGroup(jPanel_ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_number_8)
                    .addComponent(jButton_number_7)
                    .addComponent(jButton_number_9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_number_0)
                .addGap(40, 40, 40)
                .addComponent(jButton_number_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ClavierLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_number_0, jButton_number_1, jButton_number_2, jButton_number_3, jButton_number_4, jButton_number_5, jButton_number_6, jButton_number_7, jButton_number_8, jButton_number_9});

        jLabel2.setText("Place holder");

        javax.swing.GroupLayout jPanel_authLayout = new javax.swing.GroupLayout(jPanel_auth);
        jPanel_auth.setLayout(jPanel_authLayout);
        jPanel_authLayout.setHorizontalGroup(
            jPanel_authLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_authLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel_authLayout.setVerticalGroup(
            jPanel_authLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_authLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(480, 320));

        jButton_modifier.setText("Modifier");
        jButton_modifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_modifierMouseClicked(evt);
            }
        });
        jButton_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modifierActionPerformed(evt);
            }
        });

        jButton_options.setText("Paramètres");
        jButton_options.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_optionsMouseClicked(evt);
            }
        });
        jButton_options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_optionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ButtonLayout = new javax.swing.GroupLayout(jPanel_Button);
        jPanel_Button.setLayout(jPanel_ButtonLayout);
        jPanel_ButtonLayout.setHorizontalGroup(
            jPanel_ButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_modifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_ButtonLayout.createSequentialGroup()
                        .addComponent(jButton_options, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel_ButtonLayout.setVerticalGroup(
            jPanel_ButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ButtonLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton_modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton_options, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jSplitPane.setLeftComponent(jPanel_Button);

        jTextField_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_barcodeActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel_type.setText("Boîte");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/myimageapp/-Barcode_32896.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel_viewLayout = new javax.swing.GroupLayout(jPanel_view);
        jPanel_view.setLayout(jPanel_viewLayout);
        jPanel_viewLayout.setHorizontalGroup(
            jPanel_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_viewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_viewLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_type)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel_viewLayout.setVerticalGroup(
            jPanel_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_viewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_type)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane.setRightComponent(jPanel_view);

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane)
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modifierActionPerformed
        jSplitPane.setLeftComponent(jPanel_Button_control);
        repaint();
        revalidate();
    }//GEN-LAST:event_jButton_modifierActionPerformed

    private void jTextField_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_barcodeActionPerformed

    private void jButton_modifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_modifierMouseClicked
         jSplitPane.setLeftComponent(jPanel_Button_control);
    }//GEN-LAST:event_jButton_modifierMouseClicked

    private void jButton_optionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_optionsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_optionsMouseClicked

    private void jButton_upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_upMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_upMouseClicked

    private void jButton_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_upActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_upActionPerformed

    private void jButton_minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_minusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_minusActionPerformed

    private void jButton_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okActionPerformed
     jSplitPane.setLeftComponent(jPanel_Button);
    }//GEN-LAST:event_jButton_okActionPerformed

    private void jButton_shutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_shutdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_shutdownActionPerformed

    private void jButton_decoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_decoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_decoActionPerformed

    private void jButton_number_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_number_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_number_1ActionPerformed

    private void jButton_number_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_number_0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_number_0ActionPerformed

    private void jButton_optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_optionsActionPerformed
        jSplitPane.setLeftComponent(jPanel_options);
    }//GEN-LAST:event_jButton_optionsActionPerformed

    private void jButton_clavierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clavierActionPerformed
        jSplitPane.setLeftComponent(jPanel_Clavier);
    }//GEN-LAST:event_jButton_clavierActionPerformed

    private void jButton_number_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_number_cancelActionPerformed
        jSplitPane.setLeftComponent(jPanel_Button);
    }//GEN-LAST:event_jButton_number_cancelActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        jSplitPane.setLeftComponent(jPanel_Button);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_cancelActionPerformed

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
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_clavier;
    private javax.swing.JButton jButton_deco;
    private javax.swing.JButton jButton_down;
    private javax.swing.JButton jButton_minus;
    private javax.swing.JButton jButton_modifier;
    private javax.swing.JButton jButton_number_0;
    private javax.swing.JButton jButton_number_1;
    private javax.swing.JButton jButton_number_2;
    private javax.swing.JButton jButton_number_3;
    private javax.swing.JButton jButton_number_4;
    private javax.swing.JButton jButton_number_5;
    private javax.swing.JButton jButton_number_6;
    private javax.swing.JButton jButton_number_7;
    private javax.swing.JButton jButton_number_8;
    private javax.swing.JButton jButton_number_9;
    private javax.swing.JButton jButton_number_cancel;
    private javax.swing.JButton jButton_ok;
    private javax.swing.JButton jButton_options;
    private javax.swing.JButton jButton_plus;
    private javax.swing.JButton jButton_shutdown;
    private javax.swing.JButton jButton_up;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_type;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_Button;
    private javax.swing.JPanel jPanel_Button_control;
    private javax.swing.JPanel jPanel_Clavier;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_auth;
    private javax.swing.JPanel jPanel_options;
    private javax.swing.JPanel jPanel_view;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JTextField jTextField_barcode;
    // End of variables declaration//GEN-END:variables
}