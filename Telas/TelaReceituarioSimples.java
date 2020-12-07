/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

//import Controle.Conexao;
import Controle.Conexao;
import Utilitarios.ValidaEnter;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
//import net.proteanit.sql.DbUtils;


public class TelaReceituarioSimples extends javax.swing.JFrame {

    String setar_dados = "";
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaRelatorio
     */
    public TelaReceituarioSimples() {
        initComponents();
       
        areaPrescricao.setLineWrap(true);
        areaPrescricao.setWrapStyleWord(true);
        ValidaEnter ve = new ValidaEnter();
        ve.ValidaEnterPainel(jPanel4);
        setIcon();
        conexao = Conexao.conector();
        labelMensagem.setVisible(false);
        

    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Hospital.png")));
    }

    public void listarNomePaciente() {
        Conexao conec = new Conexao();
        String sql = "Select NomePaciente from tbpaciente order by NomePaciente Asc";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            TabelaNomePaciente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        } finally {
            try {
                rs.close();
                pst.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    //--------------------------Métodos--------------------------------------
    private void habilitaCampos() {

        //txtCodigo.setEnabled(false);
        txtDataAtestado.setEnabled(true);
        txtNomePaciente.setEnabled(false);
        btnPesquisarPaciente.setEnabled(true);
       

    }

    private void desaabilitaCampos() {

        txtDataAtestado.setEnabled(false);
        txtNomePaciente.setEnabled(false);
        btnPesquisarPaciente.setEnabled(false);
        

    }

    private void limparCampos() {

        //txtDataAtestado.getEditor().setText("");
        txtNomePaciente.setText("");
        btnPesquisarPaciente.setText("");
        areaPrescricao.setText("");

    }

    // metodo para colocar a letra em negrito
    Font fonte = new Font("Arial", Font.BOLD, 12);

    private void letrasemNegrito() {

        txtDataAtestado.setFont(fonte);
        txtNomePaciente.setFont(fonte);
        btnPesquisarPaciente.setFont(fonte);

    }

    // lista na tabelinha 
    public void listarMedicacao() {
        Conexao conec = new Conexao();
        DefaultTableModel model = (DefaultTableModel) tabelaNomeMedicacao.getModel();
        model.setNumRows(0);
        String sql = "Select codigo,NomeMedicacao,ViaAcesso,ComoTomar,Quantidade,Tipo,VesesDia,Durante,DiasDuracao,Periodo,Intervalo,Orientacao from tb_medicacao order by NomeMedicacao ASC";
        try {
            pst = Conexao.conector().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("codigo"),
                    rs.getString("NomeMedicacao"),
                    rs.getString("ViaAcesso"),
                    rs.getString("ComoTomar"),
                    rs.getString("Quantidade"),
                    rs.getString("Tipo"),
                    rs.getString("VesesDia"),
                    rs.getString("Durante"),
                    rs.getString("DiasDuracao"),
                    rs.getString("Periodo"),
                    rs.getString("Intervalo"),
                    rs.getString("Orientacao"),});

            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        } finally {
            try {
                rs.close();
                pst.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    public void pesquisarMedicacao() {
        Conexao conec = new Conexao();
        DefaultTableModel model = (DefaultTableModel) tabelaNomeMedicacao.getModel();
        model.setNumRows(0);
        //conexão com o banco de dados
        String sql = "Select *from tb_medicacao where NomeMedicacao like ?";

        try {
            pst = Conexao.conector().prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("codigo"),
                    rs.getString("NomeMedicacao"),
                    rs.getString("ViaAcesso"),
                    rs.getString("ComoTomar"),
                    rs.getString("Quantidade"),
                    rs.getString("Tipo"),
                    rs.getString("VesesDia"),
                    rs.getString("Durante"),
                    rs.getString("DiasDuracao"),
                    rs.getString("Periodo"),
                    rs.getString("Intervalo"),
                    rs.getString("Orientacao"),});
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        } finally {
            try {
                rs.close();
                pst.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }
    
    
    
     public void pesquisarPacientes() {
        Conexao conec = new Conexao();
        DefaultTableModel model = (DefaultTableModel) TabelaNomePaciente.getModel();
        model.setNumRows(0);
        //conexão com o banco de dados
       String sql = "Select *from tbpaciente where NomePaciente like ?";

        try {
            pst = Conexao.conector().prepareStatement(sql);
            pst.setString(1, txtPesquisar1.getText() + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                rs.getString("NomePaciente"),
                   });
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error);
        } finally {
            try {
                rs.close();
                pst.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LUPA_NOME_PACIENTE = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaNomePaciente = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisar1 = new javax.swing.JTextField();
        LUPA_NOME_MEDICACAO = new javax.swing.JDialog();
        txtPesquisar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaNomeMedicacao = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSair1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNomePaciente = new javax.swing.JTextField();
        btnPesquisarPaciente = new javax.swing.JButton();
        txtDataAtestado = new org.jdesktop.swingx.JXDatePicker();
        jXTitledPanel2 = new org.jdesktop.swingx.JXTitledPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaPrescricao = new javax.swing.JTextArea();
        btnMedicacao = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();

        LUPA_NOME_PACIENTE.setModal(true);

        TabelaNomePaciente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TabelaNomePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nome Paciente"
            }
        ));
        TabelaNomePaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaNomePacienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TabelaNomePaciente);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Pesquisar por Nome:");

        txtPesquisar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisar1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout LUPA_NOME_PACIENTELayout = new javax.swing.GroupLayout(LUPA_NOME_PACIENTE.getContentPane());
        LUPA_NOME_PACIENTE.getContentPane().setLayout(LUPA_NOME_PACIENTELayout);
        LUPA_NOME_PACIENTELayout.setHorizontalGroup(
            LUPA_NOME_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LUPA_NOME_PACIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LUPA_NOME_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txtPesquisar1)
                    .addGroup(LUPA_NOME_PACIENTELayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 205, Short.MAX_VALUE)))
                .addContainerGap())
        );
        LUPA_NOME_PACIENTELayout.setVerticalGroup(
            LUPA_NOME_PACIENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LUPA_NOME_PACIENTELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Pesquisar Medicação por Nome:");

        tabelaNomeMedicacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome Medicação ", "Vias de Acesso", "ComoTomar", "Quantidade", "Tipo", "VesesDia", "Durante", "DiasDuracao", "Periodo", "Intervalo", "Orientacao"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaNomeMedicacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaNomeMedicacaoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelaNomeMedicacao);
        if (tabelaNomeMedicacao.getColumnModel().getColumnCount() > 0) {
            tabelaNomeMedicacao.getColumnModel().getColumn(0).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(0).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(2).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(2).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(2).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(3).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(3).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(3).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(4).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(4).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(4).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(5).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(5).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(6).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(6).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(6).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(7).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(7).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(7).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(8).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(8).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(8).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(9).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(9).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(9).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(10).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(10).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(10).setMaxWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(11).setMinWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(11).setPreferredWidth(0);
            tabelaNomeMedicacao.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        javax.swing.GroupLayout LUPA_NOME_MEDICACAOLayout = new javax.swing.GroupLayout(LUPA_NOME_MEDICACAO.getContentPane());
        LUPA_NOME_MEDICACAO.getContentPane().setLayout(LUPA_NOME_MEDICACAOLayout);
        LUPA_NOME_MEDICACAOLayout.setHorizontalGroup(
            LUPA_NOME_MEDICACAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LUPA_NOME_MEDICACAOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LUPA_NOME_MEDICACAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisar)
                    .addGroup(LUPA_NOME_MEDICACAOLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 489, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(LUPA_NOME_MEDICACAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LUPA_NOME_MEDICACAOLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        LUPA_NOME_MEDICACAOLayout.setVerticalGroup(
            LUPA_NOME_MEDICACAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LUPA_NOME_MEDICACAOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
            .addGroup(LUPA_NOME_MEDICACAOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LUPA_NOME_MEDICACAOLayout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Receituário Simples");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Receituário Simples");

        btnSair1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSair1.setForeground(new java.awt.Color(51, 51, 51));
        btnSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/Sair.png"))); // NOI18N
        btnSair1.setText("SAIR");
        btnSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
                .addComponent(btnSair1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 800, 60));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/Print_24x24.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(51, 51, 51));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/Novo.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 539, Short.MAX_VALUE)
                .addComponent(btnImprimir)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 800, 90));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 800, 10));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Data do Atendimento:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Nome Paciente:");

        txtNomePaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNomePaciente.setEnabled(false);

        btnPesquisarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Search.png"))); // NOI18N
        btnPesquisarPaciente.setEnabled(false);
        btnPesquisarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarPacienteActionPerformed(evt);
            }
        });

        txtDataAtestado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDataAtestado.setEnabled(false);

        jXTitledPanel2.setTitle("Medicações");

        areaPrescricao.setColumns(20);
        areaPrescricao.setRows(5);
        areaPrescricao.setEnabled(false);
        areaPrescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                areaPrescricaoFocusGained(evt);
            }
        });
        areaPrescricao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                areaPrescricaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(areaPrescricao);

        javax.swing.GroupLayout jXTitledPanel2Layout = new javax.swing.GroupLayout(jXTitledPanel2.getContentContainer());
        jXTitledPanel2.getContentContainer().setLayout(jXTitledPanel2Layout);
        jXTitledPanel2Layout.setHorizontalGroup(
            jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jXTitledPanel2Layout.setVerticalGroup(
            jXTitledPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jXTitledPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtDataAtestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesquisarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPesquisarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtNomePaciente)
                    .addComponent(txtDataAtestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXTitledPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 800, 360));

        btnMedicacao.setBackground(new java.awt.Color(153, 204, 255));
        btnMedicacao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMedicacao.setForeground(new java.awt.Color(51, 51, 51));
        btnMedicacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Search.png"))); // NOI18N
        btnMedicacao.setText("SELECIONAR MEDICAÇÕES");
        btnMedicacao.setEnabled(false);
        btnMedicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicacaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnMedicacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 230, 33));

        labelMensagem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 0, 0));
        getContentPane().add(labelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 460, 160, 30));

        setSize(new java.awt.Dimension(837, 641));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair1ActionPerformed

        //Pergunta se o usuário deseja realmente sair do sistema
        int sair = JOptionPane.showConfirmDialog(null, "DESEJA SAIR DA TELA RECEITA SIMPLES ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSair1ActionPerformed

    private void btnPesquisarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarPacienteActionPerformed

        LUPA_NOME_PACIENTE.setSize(new java.awt.Dimension(400, 300));
        LUPA_NOME_PACIENTE.setLocationRelativeTo(null);
        listarNomePaciente();
        LUPA_NOME_PACIENTE.setVisible(true);
        txtDataAtestado.requestFocus();
    }//GEN-LAST:event_btnPesquisarPacienteActionPerformed

    private void TabelaNomePacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaNomePacienteMouseClicked
        // seleciona a linha da tabela e seta nos campos
        int linha = TabelaNomePaciente.getSelectedRow();
        if (linha >= 0) {
            // numero da coluna da tabelinha
            txtNomePaciente.setText(TabelaNomePaciente.getValueAt(linha, 0).toString());
            LUPA_NOME_PACIENTE.setVisible(false);
        }
    }//GEN-LAST:event_TabelaNomePacienteMouseClicked

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        letrasemNegrito();
        areaPrescricao.setEnabled(true);
        areaPrescricao.setFont(fonte);
        habilitaCampos();
        limparCampos();
        letrasemNegrito();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

        int sair = JOptionPane.showConfirmDialog(null, "As medicações estão corretas ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            
        final Aguarde carregando = new Aguarde();
        carregando.setVisible(true);
        Thread t = new Thread() {
            public void run() {

                try {
                    ImpressaoReceitaSimples imprime = new ImpressaoReceitaSimples();
                    List listade_dados = GetDados();
                    imprime.Imprime_Relatorio(listade_dados);
                    carregando.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                desaabilitaCampos();
                limparCampos();
                btnNovo.setEnabled(true);
                btnImprimir.setEnabled(false);
                areaPrescricao.setEnabled(false);
                labelMensagem.setVisible(false);
            }
        };
        t.start();    

        } else {

            areaPrescricao.requestFocus();
        }
  

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void areaPrescricaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaPrescricaoMouseClicked

    }//GEN-LAST:event_areaPrescricaoMouseClicked

    private void areaPrescricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_areaPrescricaoFocusGained
        btnImprimir.setEnabled(true);
        labelMensagem.setVisible(true);
        labelMensagem.setText("Selecione a Medicação ->");
        btnMedicacao.setEnabled(true);
        btnNovo.setEnabled(false);

    }//GEN-LAST:event_areaPrescricaoFocusGained

    private void btnMedicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicacaoActionPerformed
        LUPA_NOME_MEDICACAO.setSize(new java.awt.Dimension(500, 400));
        LUPA_NOME_MEDICACAO.setLocationRelativeTo(null);
        listarMedicacao();
        LUPA_NOME_MEDICACAO.setVisible(true);
        areaPrescricao.requestFocus();
    }//GEN-LAST:event_btnMedicacaoActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        pesquisarMedicacao();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tabelaNomeMedicacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaNomeMedicacaoMouseClicked

        // seleciona a linha da tabela e seta nos campos sem quebra de linha
        int linha = tabelaNomeMedicacao.getSelectedRow();
        if (linha >= 0) {
            // esse codigo faz com que as linhas selecionadas da tabela fiquem uma abaixo da outra
            //String dados0 = (tabelaNomeMedicacao.getValueAt(linha, 0).toString());
            String dados1 = (tabelaNomeMedicacao.getValueAt(linha, 1).toString());
            String dados2 = (tabelaNomeMedicacao.getValueAt(linha, 2).toString());
            String dados3 = (tabelaNomeMedicacao.getValueAt(linha, 3).toString());
            String dados4 = (tabelaNomeMedicacao.getValueAt(linha, 4).toString());
            String dados5 = (tabelaNomeMedicacao.getValueAt(linha, 5).toString());
            String dados6 = (tabelaNomeMedicacao.getValueAt(linha, 6).toString());
            String dados7 = (tabelaNomeMedicacao.getValueAt(linha, 7).toString());
            String dados8 = (tabelaNomeMedicacao.getValueAt(linha, 8).toString());
            String dados9 = (tabelaNomeMedicacao.getValueAt(linha, 9).toString());
            String dados10 = (tabelaNomeMedicacao.getValueAt(linha, 10).toString());
            String dados11 = (tabelaNomeMedicacao.getValueAt(linha, 11).toString());

            //setar_dados = setar_dados + "\n" + dados0;
            setar_dados = setar_dados + "\n" + dados1;
            setar_dados = setar_dados + "  " + dados2;
            setar_dados = setar_dados + "  " + dados3;
            setar_dados = setar_dados + "  " + dados4;
            setar_dados = setar_dados + "  " + dados5;
            setar_dados = setar_dados + "  " + dados6;
            setar_dados = setar_dados + "  " + dados7;
            setar_dados = setar_dados + "  " + dados8;
            setar_dados = setar_dados + "  " + dados9;
            setar_dados = setar_dados + " " + dados10;
            setar_dados = setar_dados + " " + dados11+".";
            areaPrescricao.setText(setar_dados);
           // LUPA_NOME_MEDICACAO.setVisible(false);
        }


    }//GEN-LAST:event_tabelaNomeMedicacaoMouseClicked

    private void txtPesquisar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisar1KeyReleased
        pesquisarPacientes();
    }//GEN-LAST:event_txtPesquisar1KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReceituarioSimples().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog LUPA_NOME_MEDICACAO;
    private javax.swing.JDialog LUPA_NOME_PACIENTE;
    private javax.swing.JTable TabelaNomePaciente;
    private javax.swing.JTextArea areaPrescricao;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMedicacao;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarPaciente;
    private javax.swing.JButton btnSair1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel2;
    private javax.swing.JLabel labelMensagem;
    private javax.swing.JTable tabelaNomeMedicacao;
    private org.jdesktop.swingx.JXDatePicker txtDataAtestado;
    private javax.swing.JTextField txtNomePaciente;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtPesquisar1;
    // End of variables declaration//GEN-END:variables

    public List GetDados() {

        List lista = new ArrayList();

//for (int i = 0; i < 10; i++) {
        AuxiliarReceitaSimples print = new AuxiliarReceitaSimples();
//print.setCodigo("");
        print.setData(txtDataAtestado.getEditor().getText());
        print.setNome(txtNomePaciente.getText());
        print.setPrescricao(areaPrescricao.getText());

        lista.add(print);
        return lista;
    }

   

}
