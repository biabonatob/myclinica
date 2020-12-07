/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;


public class AuxiliarReceitaEspecial {
    
    
    private String data;
    private String nomepaciente;
    private String nomemedico;
    private String crm;
    private String uf;
    private String prescricao;
    private String cidade;
    private String endereco;
    private String telefone;
    private String ufe;

    public AuxiliarReceitaEspecial(String data, String nomepaciente, String nomemedico, String crm, String uf, String prescricao, String cidade, String endereco, String telefone, String ufe) {
        this.data = data;
        this.nomepaciente = nomepaciente;
        this.nomemedico = nomemedico;
        this.crm = crm;
        this.uf = uf;
        this.prescricao = prescricao;
        this.cidade = cidade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.ufe = ufe;
    }
   
      AuxiliarReceitaEspecial(){} 

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomepaciente() {
        return nomepaciente;
    }

    public void setNomepaciente(String nomepaciente) {
        this.nomepaciente = nomepaciente;
    }

    public String getNomemedico() {
        return nomemedico;
    }

    public void setNomemedico(String nomemedico) {
        this.nomemedico = nomemedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUfe() {
        return ufe;
    }

    public void setUfe(String ufe) {
        this.ufe = ufe;
    }
      
      
    
}
