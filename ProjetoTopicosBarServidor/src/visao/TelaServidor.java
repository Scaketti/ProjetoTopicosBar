/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.util.ArrayList;
import negocio.ClienteCaixa;
import negocio.ClienteTerminal;
import negocio.Servidor;

/**
 *
 * @author Scaketti
 */
public class TelaServidor extends javax.swing.JFrame {

    Servidor servidor;
    private String logMsgClienteTerminal = "";
    private String logMsgClienteCaixa = "";
    
    private ArrayList<ClienteTerminal> cTerminalConectados = new ArrayList();
    private ArrayList<ClienteCaixa> cCaixaConectados = new ArrayList();
    
    /**
     * Creates new form Servidor
     */
    public TelaServidor() {
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

        lblTipoMovimentacao = new javax.swing.JLabel();
        cmbTipoMovimentacao = new javax.swing.JComboBox<>();
        pnlMovimentacao = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMovimentacao = new javax.swing.JTextArea();
        btnConfServidor = new javax.swing.JButton();
        btnClientesConectados = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTipoMovimentacao.setText("Tipo de Movimentação:");

        cmbTipoMovimentacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes", "Atendentes" }));
        cmbTipoMovimentacao.setEnabled(false);
        cmbTipoMovimentacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoMovimentacaoItemStateChanged(evt);
            }
        });

        pnlMovimentacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Movimentação"));

        txtMovimentacao.setColumns(20);
        txtMovimentacao.setRows(5);
        txtMovimentacao.setEnabled(false);
        jScrollPane1.setViewportView(txtMovimentacao);

        javax.swing.GroupLayout pnlMovimentacaoLayout = new javax.swing.GroupLayout(pnlMovimentacao);
        pnlMovimentacao.setLayout(pnlMovimentacaoLayout);
        pnlMovimentacaoLayout.setHorizontalGroup(
            pnlMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimentacaoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1)
                .addGap(5, 5, 5))
        );
        pnlMovimentacaoLayout.setVerticalGroup(
            pnlMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimentacaoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        btnConfServidor.setText("Conf. do Servidor");
        btnConfServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfServidorActionPerformed(evt);
            }
        });

        btnClientesConectados.setText("Clientes Conectados");
        btnClientesConectados.setEnabled(false);
        btnClientesConectados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesConectadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(pnlMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTipoMovimentacao)
                .addGap(10, 10, 10)
                .addComponent(cmbTipoMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientesConectados, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfServidor)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfServidor)
                    .addComponent(btnClientesConectados)
                    .addComponent(cmbTipoMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoMovimentacao))
                .addGap(3, 3, 3)
                .addComponent(pnlMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfServidorActionPerformed
        TelaConfServidor conf = new TelaConfServidor(this);
        conf.setVisible(true);
    }//GEN-LAST:event_btnConfServidorActionPerformed

    private void btnClientesConectadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesConectadosActionPerformed
        TelaCConectados tCliente = new TelaCConectados(cTerminalConectados, cCaixaConectados);
        tCliente.setVisible(true);
    }//GEN-LAST:event_btnClientesConectadosActionPerformed

    private void cmbTipoMovimentacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoMovimentacaoItemStateChanged
        if(cmbTipoMovimentacao.getItemAt(cmbTipoMovimentacao.getSelectedIndex()).equals("Clientes")){
            txtMovimentacao.setText(getLogMsgClienteTerminal());
            System.out.println(getLogMsgClienteTerminal());
        }else {
            txtMovimentacao.setText(getLogMsgClienteCaixa());
            System.out.println(getLogMsgClienteCaixa());
        }
    }//GEN-LAST:event_cmbTipoMovimentacaoItemStateChanged

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
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientesConectados;
    private javax.swing.JButton btnConfServidor;
    private javax.swing.JComboBox<String> cmbTipoMovimentacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTipoMovimentacao;
    private javax.swing.JPanel pnlMovimentacao;
    private javax.swing.JTextArea txtMovimentacao;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the btnClientesConectados
     */
    public javax.swing.JButton getBtnClientesConectados() {
        return btnClientesConectados;
    }

    /**
     * @param btnClientesConectados the btnClientesConectados to set
     */
    public void setBtnClientesConectados(javax.swing.JButton btnClientesConectados) {
        this.btnClientesConectados = btnClientesConectados;
    }

    /**
     * @return the cmbTipoMovimentacao
     */
    public javax.swing.JComboBox<String> getCmbTipoMovimentacao() {
        return cmbTipoMovimentacao;
    }

    /**
     * @param cmbTipoMovimentacao the cmbTipoMovimentacao to set
     */
    public void setCmbTipoMovimentacao(javax.swing.JComboBox<String> cmbTipoMovimentacao) {
        this.cmbTipoMovimentacao = cmbTipoMovimentacao;
    }

    /**
     * @return the pnlMovimentacao
     */
    public javax.swing.JPanel getPnlMovimentacao() {
        return pnlMovimentacao;
    }

    /**
     * @param pnlMovimentacao the pnlMovimentacao to set
     */
    public void setPnlMovimentacao(javax.swing.JPanel pnlMovimentacao) {
        this.pnlMovimentacao = pnlMovimentacao;
    }

    /**
     * @return the txtMovimentacao
     */
    public javax.swing.JTextArea getTxtMovimentacao() {
        return txtMovimentacao;
    }

    /**
     * @param txtMovimentacao the txtMovimentacao to set
     */
    public void setTxtMovimentacao(javax.swing.JTextArea txtMovimentacao) {
        this.txtMovimentacao = txtMovimentacao;
    }

    /**
     * @return the cTerminalConectados
     */
    public ArrayList<ClienteTerminal> getcTerminalConectados() {
        return cTerminalConectados;
    }

    /**
     * @param cTerminalConectados the cTerminalConectados to set
     */
    public void setcTerminalConectados(ArrayList<ClienteTerminal> cTerminalConectados) {
        this.cTerminalConectados = cTerminalConectados;
    }

    /**
     * @return the cCaixaConectados
     */
    public ArrayList<ClienteCaixa> getcCaixaConectados() {
        return cCaixaConectados;
    }

    /**
     * @param cCaixaConectados the cCaixaConectados to set
     */
    public void setcCaixaConectados(ArrayList<ClienteCaixa> cCaixaConectados) {
        this.cCaixaConectados = cCaixaConectados;
    }

    /**
     * @return the logMsgClienteTerminal
     */
    public String getLogMsgClienteTerminal() {
        return logMsgClienteTerminal;
    }

    /**
     * @param logMsgClienteTerminal the logMsgClienteTerminal to set
     */
    public void setLogMsgClienteTerminal(String logMsgClienteTerminal) {
        this.logMsgClienteTerminal = logMsgClienteTerminal;
    }

    /**
     * @return the logMsgClienteCaixa
     */
    public String getLogMsgClienteCaixa() {
        return logMsgClienteCaixa;
    }

    /**
     * @param logMsgClienteCaixa the logMsgClienteCaixa to set
     */
    public void setLogMsgClienteCaixa(String logMsgClienteCaixa) {
        this.logMsgClienteCaixa = logMsgClienteCaixa;
    }
}
