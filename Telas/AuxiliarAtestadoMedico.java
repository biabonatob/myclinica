/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;


 public class AuxiliarAtestadoMedico {
 private String nome;    
 private String cpf; 
 private String das;
 private String as;
 private String necessita;
 private String data;
 private String cid;
 
    public AuxiliarAtestadoMedico(String nome, String cpf, String das, String as, String necessita, String data, String cid) {
        this.nome = nome;
        this.cpf = cpf;
        this.das = das;
        this.as = as;
        this.necessita = necessita;
        this.data = data;
        this.cid = cid;
    }
 
 
public AuxiliarAtestadoMedico() {}    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDas() {
        return das;
    }

    public void setDas(String das) {
        this.das = das;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getNecessita() {
        return necessita;
    }

    public void setNecessita(String necessita) {
        this.necessita = necessita;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}
