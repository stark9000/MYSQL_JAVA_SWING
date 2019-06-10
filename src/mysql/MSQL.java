/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Saliya
 */
public class MSQL extends javax.swing.JFrame {

    private SQL MYSQL;
    private ArrayList<String> TABLE_COLUMNS;
    private int ROW_HEIGHT = 30;

    /**
     * Creates new form MSQL
     */
    public MSQL() {
        initComponents();
        MYSQL = SQL.getInstence();
        TABLE_COLUMNS = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MYSQL-JAVA");

        jButton1.setText("getData");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("select data from :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "city", "country", "countrylanguage" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jComboBox1.getSelectedIndex() == -1) {
            return;
        }
        try {
            MYSQL.EstablishConnection();
            ResultSet rs = MYSQL.getMetadata().getColumns(null, null, jComboBox1.getSelectedItem().toString(), "%");
            while (rs.next()) {
                String column = rs.getString("COLUMN_NAME");
                TABLE_COLUMNS.add(column);
            }
            prepTableSpec(jTable1);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MSQL().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
public void prepTableSpec(JTable jtable1) {
        DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableStructureChanged();
        if (jtable1.getCellEditor() != null) {
            jtable1.getCellEditor().stopCellEditing();
        }
        String[] columns = TABLE_COLUMNS.stream().toArray(size -> new String[size]);
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jtable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
            }
        });
        jtable1.setModel(model);

        try {
            String SQL_CITY = "SELECT * FROM " + jComboBox1.getSelectedItem().toString();
            ResultSet resset = MYSQL.setCommand(SQL_CITY);
            setData(jtable1, resset);
            TABLE_COLUMNS.clear();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void setData(JTable jTable, ResultSet resultSet) {
        ArrayList<ArrayList<Object>> result = toArrayList(resultSet);
        DefaultTableModel aModel = (DefaultTableModel) jTable.getModel();
        aModel.getDataVector().removeAllElements();
        aModel.fireTableStructureChanged();
        for (int i = 0; i < result.size(); i++) {
            Object[] object = result.get(i).toArray();
            aModel.addRow(object);
            jTable.setRowHeight(i, ROW_HEIGHT);

        }
        jTable.setModel(aModel);
    }

    public ArrayList<ArrayList<Object>> toArrayList(ResultSet resultSet) {
        try {
            ArrayList<ArrayList<Object>> table;
            int columnCount = resultSet.getMetaData().getColumnCount();
            if (resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) {
                table = new ArrayList<>();
            } else {
                resultSet.last();
                table = new ArrayList<>(resultSet.getRow());
                resultSet.beforeFirst();
            }
            for (ArrayList<Object> row; resultSet.next(); table.add(row)) {
                row = new ArrayList<>(columnCount);
                for (int c = 1; c <= columnCount; ++c) {
                    row.add(resultSet.getString(c).intern());
                }
            }
            return table;
        } catch (SQLException e) {
            return null;
        }
    }

}
