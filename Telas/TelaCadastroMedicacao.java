/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Controle.Conexao;
import Utilitarios.Msg;
import Utilitarios.Utils;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TelaCadastroMedicacao extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaCadastroMedicacao() {
        initComponents();
        listarMedicacao();
        populaJComboBoxViasAcesso();
        populaJComboBoxIntervalo();
        populaJComboBoxComoTomar();
        populaJComboBoxTipo();
        populaJComboBoxConforme();
        setIcon();
        txtCodigo.setVisible(false);
        conexao = Conexao.conector();
        PreparedStatement ST = null;
        letrasemNegrito();

    }

    //-----------------------------------METODOS------------------------------------------------------------------//
    public static String horaAtual() {
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("dd/MM/YYYY");
        return formatoHora.format(hora);
    }

    private void habilitaCampos() {

        txtNomeMedicação.setEnabled(true);
        JcComoTomar.setEnabled(true);
        jCViaAcesso.setEnabled(true);
        txtQuantidadeComprimidos.setEnabled(true);
        JcTipo.setEnabled(true);
        jCVezesDia.setEnabled(true);
        jCDurante.setEnabled(true);
        txtDiasDuracao.setEnabled(true);
        jCPeriodo.setEnabled(true);
        jCorientacaoMedica.setEnabled(true);
        jCIntervalo.setEnabled(true);

    }

    private void desaabilitaCampos() {

        txtNomeMedicação.setEnabled(false);
        JcComoTomar.setEnabled(false);
        jCViaAcesso.setEnabled(false);
        txtQuantidadeComprimidos.setEnabled(false);
        JcTipo.setEnabled(false);
        jCVezesDia.setEnabled(false);
        jCDurante.setEnabled(false);
        txtDiasDuracao.setEnabled(false);
        jCPeriodo.setEnabled(false);
        jCorientacaoMedica.setEnabled(false);
        jCIntervalo.setEnabled(false);

    }

    private void limpar() {

        txtNomeMedicação.setText("");
        JcComoTomar.setSelectedIndex(0);
        jCViaAcesso.setSelectedIndex(0);
        txtQuantidadeComprimidos.setText("");
        JcTipo.setSelectedIndex(0);
        jCVezesDia.setSelectedIndex(0);
        jCDurante.setSelectedIndex(0);
        txtDiasDuracao.setText("");
        jCPeriodo.setSelectedIndex(0);
        jCorientacaoMedica.setSelectedIndex(0);
        jCIntervalo.setSelectedIndex(0);
    }

    // metodo para colocar a letra em negrito
    Font fonte = new Font("Arial", Font.BOLD, 12);

    private void letrasemNegrito() {

        txtNomeMedicação.setFont(fonte);
        txtNomeMedicação.setFont(fonte);
        JcComoTomar.setFont(fonte);
        jCViaAcesso.setFont(fonte);
        txtQuantidadeComprimidos.setFont(fonte);
        JcTipo.setFont(fonte);
        jCVezesDia.setFont(fonte);
        jCDurante.setFont(fonte);
        txtDiasDuracao.setFont(fonte);
        jCPeriodo.setFont(fonte);
        jCorientacaoMedica.setFont(fonte);
        jCIntervalo.setFont(fonte);

    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Hospital.png")));
    }

    // icone do joptionPane
    Icon icon = new javax.swing.ImageIcon(getClass().getResource("/Imagens/ok.png"));

    // icone do joptionPane
    Icon iconExcluir = new javax.swing.ImageIcon(getClass().getResource("/Imagens/Excluido.png"));

    private void SalvarMedicacao() {
        Conexao conec = new Conexao();
        String sql = "insert into tb_medicacao(NomeMedicacao,ViaAcesso,ComoTomar,Quantidade,Tipo,VesesDia,Durante,DiasDuracao,Periodo,Intervalo,Orientacao)values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeMedicação.getText());
            pst.setString(2, jCViaAcesso.getSelectedItem().toString());
            pst.setString(3, JcComoTomar.getSelectedItem().toString());
            pst.setString(4, txtQuantidadeComprimidos.getText());
            pst.setString(5, JcTipo.getSelectedItem().toString());
            pst.setString(6, jCVezesDia.getSelectedItem().toString());
            pst.setString(7, jCDurante.getSelectedItem().toString());
            pst.setString(8, txtDiasDuracao.getText());
            pst.setString(9, jCPeriodo.getSelectedItem().toString());
            pst.setString(10, jCIntervalo.getSelectedItem().toString());
            pst.setString(11, jCorientacaoMedica.getSelectedItem().toString());
            //validação dos campos obrigatórios
            if (txtNomeMedicação.getText().trim().equals("") || txtNomeMedicação.getText().trim().equals("")) {

                JOptionPane.showMessageDialog(null, "Os dados devem ser preenchidos!", "Alerta", JOptionPane.WARNING_MESSAGE);

            } else {

                //A linha abaixo atualiza a tabela usuarios com os dados do formulario
                // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Medicação " + txtNomeMedicação.getText().toUpperCase() + " salvo com sucesso.", "Inclusão", JOptionPane.INFORMATION_MESSAGE, icon);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

    public void alterarMedicacao() {
        Conexao conec = new Conexao();
        String sql = "Update tb_medicacao set NomeMedicacao= ?,ViaAcesso= ?,ComoTomar= ?,Quantidade= ?,Tipo= ?,VesesDia= ?,Durante= ?,DiasDuracao= ?,Periodo= ?,Intervalo= ?,Orientacao= ? where codigo = ? ";

        try {
            pst = Conexao.conector().prepareStatement(sql);
            pst.setString(1, txtNomeMedicação.getText());
            pst.setString(2, jCViaAcesso.getSelectedItem().toString());
            pst.setString(3, JcComoTomar.getSelectedItem().toString());
            pst.setString(4, txtQuantidadeComprimidos.getText());
            pst.setString(5, JcTipo.getSelectedItem().toString());
            pst.setString(6, jCVezesDia.getSelectedItem().toString());
            pst.setString(7, jCDurante.getSelectedItem().toString());
            pst.setString(8, txtDiasDuracao.getText());
            pst.setString(9, jCPeriodo.getSelectedItem().toString());
            pst.setString(10, jCIntervalo.getSelectedItem().toString());
            pst.setString(11, jCorientacaoMedica.getSelectedItem().toString());
            pst.setInt(12, Integer.parseInt(txtCodigo.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, " Medicação " + txtNomeMedicação.getText().toUpperCase() + " Atualizado com sucesso.", "Inclusão", JOptionPane.INFORMATION_MESSAGE, icon);
            listarMedicacao();
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

    public void removerMedicacao() {
        Conexao conec = new Conexao();
        String sql = "delete from tb_medicacao where codigo=?";

        try {
            pst = Conexao.conector().prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodigo.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(rootPane, " Medicação " + txtNomeMedicação.getText().toUpperCase() + " Excluido com sucesso.", "Inclusão", JOptionPane.INFORMATION_MESSAGE, iconExcluir);
            listarMedicacao();
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

    // lista na tabelinha 
    public void listarMedicacao() {
        Conexao conec = new Conexao();
        DefaultTableModel model = (DefaultTableModel) tabelaMedicacao.getModel();
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
   

    public void mostrarItens() {
        int seleciona = tabelaMedicacao.getSelectedRow();
        txtCodigo.setText(tabelaMedicacao.getModel().getValueAt(seleciona, 0).toString());
        txtNomeMedicação.setText(tabelaMedicacao.getModel().getValueAt(seleciona, 1).toString());
        jCViaAcesso.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 2).toString());
        JcComoTomar.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 3).toString());
        txtQuantidadeComprimidos.setText(tabelaMedicacao.getModel().getValueAt(seleciona, 4).toString());
        JcTipo.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 5).toString());
        jCVezesDia.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 6).toString());
        jCDurante.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 7).toString());
        txtDiasDuracao.setText(tabelaMedicacao.getModel().getValueAt(seleciona, 8).toString());
        jCPeriodo.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 9).toString());
        jCIntervalo.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 10).toString());
        jCorientacaoMedica.setSelectedItem(tabelaMedicacao.getModel().getValueAt(seleciona, 11).toString());
    }
    
    
    
    
    
     public void  pesquisarMedicacao() {
        DefaultTableModel model = (DefaultTableModel) tabelaMedicacao.getModel();
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

        jPanel8 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        JcComoTomar = new javax.swing.JComboBox<>();
        txtQuantidadeComprimidos = new javax.swing.JTextField();
        JcTipo = new javax.swing.JComboBox<>();
        jCVezesDia = new javax.swing.JComboBox<>();
        jCDurante = new javax.swing.JComboBox<>();
        txtDiasDuracao = new javax.swing.JTextField();
        jCPeriodo = new javax.swing.JComboBox<>();
        jCorientacaoMedica = new javax.swing.JComboBox<>();
        jCIntervalo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMedicacao = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCViaAcesso = new javax.swing.JComboBox<>();
        txtNomeMedicação = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Medicação");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNovo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(51, 51, 51));
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/Novo.png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(51, 51, 51));
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/Salvar.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(51, 51, 51));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/Editar.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(51, 51, 51));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagensTelas/excluir.png"))); // NOI18N
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        txtCodigo.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(253, 253, 253)
                    .addComponent(btnExcluir)
                    .addContainerGap(608, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEditar, btnSalvar});

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Posologia Padrão"));

        JcComoTomar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione >" }));
        JcComoTomar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JcComoTomar.setEnabled(false);
        JcComoTomar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JcComoTomarMouseClicked(evt);
            }
        });

        txtQuantidadeComprimidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantidadeComprimidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtQuantidadeComprimidos.setEnabled(false);

        JcTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione >" }));
        JcTipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JcTipo.setEnabled(false);

        jCVezesDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 vez ao dia", "2 vezes ao dia", "3 vezes ao dia", "4 vezes ao dia", "5 vezes ao dia", "Dose Única" }));
        jCVezesDia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCVezesDia.setEnabled(false);

        jCDurante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Durante", "a cada", "por" }));
        jCDurante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCDurante.setEnabled(false);

        txtDiasDuracao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasDuracao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDiasDuracao.setEnabled(false);

        jCPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia(s)", "Tarde", "Noite" }));
        jCPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCPeriodo.setEnabled(false);

        jCorientacaoMedica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione >" }));
        jCorientacaoMedica.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCorientacaoMedica.setEnabled(false);

        jCIntervalo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione >" }));
        jCIntervalo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCIntervalo.setEnabled(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Create.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Create.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Create.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Create.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JcComoTomar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeComprimidos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCorientacaoMedica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JcTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCVezesDia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCDurante, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiasDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCIntervalo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCVezesDia, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JcComoTomar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCPeriodo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JcTipo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtQuantidadeComprimidos, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCDurante, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDiasDuracao, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCorientacaoMedica, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelaMedicacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nome Medicação", "Via Acesso", "Como Tomar", "QTD", "Tipo", "Vezes ao dia", "Durante", "Dias", "Periodo", "Intervalo", "Orientação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaMedicacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMedicacaoMouseClicked(evt);
            }
        });
        tabelaMedicacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaMedicacaoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMedicacao);
        if (tabelaMedicacao.getColumnModel().getColumnCount() > 0) {
            tabelaMedicacao.getColumnModel().getColumn(0).setMinWidth(0);
            tabelaMedicacao.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabelaMedicacao.getColumnModel().getColumn(0).setMaxWidth(0);
            tabelaMedicacao.getColumnModel().getColumn(2).setMinWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(2).setMaxWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(3).setMinWidth(100);
            tabelaMedicacao.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabelaMedicacao.getColumnModel().getColumn(3).setMaxWidth(100);
            tabelaMedicacao.getColumnModel().getColumn(4).setMinWidth(50);
            tabelaMedicacao.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabelaMedicacao.getColumnModel().getColumn(4).setMaxWidth(50);
            tabelaMedicacao.getColumnModel().getColumn(5).setMinWidth(110);
            tabelaMedicacao.getColumnModel().getColumn(5).setPreferredWidth(110);
            tabelaMedicacao.getColumnModel().getColumn(5).setMaxWidth(110);
            tabelaMedicacao.getColumnModel().getColumn(6).setMinWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(6).setPreferredWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(6).setMaxWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(7).setMinWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(7).setPreferredWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(7).setMaxWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(8).setMinWidth(50);
            tabelaMedicacao.getColumnModel().getColumn(8).setPreferredWidth(50);
            tabelaMedicacao.getColumnModel().getColumn(8).setMaxWidth(50);
            tabelaMedicacao.getColumnModel().getColumn(9).setMinWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(9).setPreferredWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(9).setMaxWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(10).setMinWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(10).setPreferredWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(10).setMaxWidth(80);
            tabelaMedicacao.getColumnModel().getColumn(11).setMinWidth(100);
            tabelaMedicacao.getColumnModel().getColumn(11).setPreferredWidth(100);
            tabelaMedicacao.getColumnModel().getColumn(11).setMaxWidth(100);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Create.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, -1, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Via de Admininstração:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 13, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nome Medicamento:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jCViaAcesso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Selecione >" }));
        jCViaAcesso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCViaAcesso.setEnabled(false);
        jPanel3.add(jCViaAcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 34, 280, 40));

        txtNomeMedicação.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNomeMedicação.setEnabled(false);
        jPanel3.add(txtNomeMedicação, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 34, 665, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Search.png"))); // NOI18N

        txtPesquisar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtPesquisar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1072, 598));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        txtNomeMedicação.requestFocus();
        btnSalvar.setEnabled(true);
        habilitaCampos();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        SalvarMedicacao();
        listarMedicacao();
        limpar();
        desaabilitaCampos();
        btnSalvar.setEnabled(false);
        btnNovo.setEnabled(true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int i = tabelaMedicacao.getSelectedRow();
        if (i < 0) {
            Msg.alerta(this, "Selecione um Registro para alteração!");
            return;
        }
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            alterarMedicacao();
            listarMedicacao();
            desaabilitaCampos();
            limpar();
            btnNovo.setEnabled(true);
            

        } else {

            this.dispose();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int i = tabelaMedicacao.getSelectedRow();
        if (i < 0) {
            Msg.alerta(this, "Selecione um Registro para exclusão!");
            return;
        }

        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            removerMedicacao();
            listarMedicacao();
            desaabilitaCampos();
            limpar();
            btnNovo.setEnabled(true);
            

        } else {

            this.dispose();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TelaCadastroViasAdministracao tela = new TelaCadastroViasAdministracao();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TelaCadastroIntervaloMedicamentos tela = new TelaCadastroIntervaloMedicamentos();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabelaMedicacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMedicacaoMouseClicked
        mostrarItens();

    }//GEN-LAST:event_tabelaMedicacaoMouseClicked

    private void tabelaMedicacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaMedicacaoKeyPressed
        mostrarItens();
    }//GEN-LAST:event_tabelaMedicacaoKeyPressed

    private void txtPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyPressed

    }//GEN-LAST:event_txtPesquisarKeyPressed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased

        pesquisarMedicacao();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       TelaCadastroConformeMedico tela = new TelaCadastroConformeMedico();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
               
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       TelaCadastroTipoUnidade tela = new TelaCadastroTipoUnidade();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
               
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        TelaCadastroModoTomar tela = new TelaCadastroModoTomar();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void JcComoTomarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JcComoTomarMouseClicked
      
    }//GEN-LAST:event_JcComoTomarMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCadastroMedicacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedicacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedicacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroMedicacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroMedicacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JcComoTomar;
    private javax.swing.JComboBox<String> JcTipo;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jCDurante;
    private javax.swing.JComboBox<String> jCIntervalo;
    private javax.swing.JComboBox<String> jCPeriodo;
    private javax.swing.JComboBox<String> jCVezesDia;
    private javax.swing.JComboBox<String> jCViaAcesso;
    private javax.swing.JComboBox<String> jCorientacaoMedica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMedicacao;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDiasDuracao;
    private javax.swing.JTextField txtNomeMedicação;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtQuantidadeComprimidos;
    // End of variables declaration//GEN-END:variables

    private void atualizarTabela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void populaJComboBoxViasAcesso() {
        String sql = "Select *from tb_vias_acesso";
        try {
            pst = Conexao.conector().prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                jCViaAcesso.addItem(rs.getString("ViasAcesso"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void populaJComboBoxIntervalo() {
        String sql = "Select *from tb_intervalo_medicamentos";
        try {
            pst = Conexao.conector().prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                jCIntervalo.addItem(rs.getString("Intervalo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    public void populaJComboBoxComoTomar() {
        String sql = "Select *from tb_modo_tomar";
        try {
            pst = Conexao.conector().prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                JcComoTomar.addItem(rs.getString("Tomar"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    
    public void populaJComboBoxTipo() {
        String sql = "Select *from tb_tipo";
        try {
            pst = Conexao.conector().prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                JcTipo.addItem(rs.getString("Tipo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    public void populaJComboBoxConforme() {
        String sql = "Select *from tb_conforme";
        try {
            pst = Conexao.conector().prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                jCorientacaoMedica.addItem(rs.getString("Conforme"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    


    //metodo converte data
    private java.sql.Date convertDateSalvar(java.util.Date var) throws SQLException {
        int year = var.getDate();
        int month = var.getMonth();
        int day = var.getYear();
        return new java.sql.Date(day, month, year);
    }

}
