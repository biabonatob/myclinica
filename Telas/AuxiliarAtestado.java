/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

public class AuxiliarAtestado {
    
 private String motivo;
 private String nomeacompanhante;
 private String nome;    
 private String cpf; 
 private String das; 
 private String as; 
 private String data;

    public AuxiliarAtestado(String motivo, String nomeacompanhante, String nome, String cpf, String das, String as, String data) {
        this.motivo = motivo;
        this.nomeacompanhante = nomeacompanhante;
        this.nome = nome;
        this.cpf = cpf;
        this.das = das;
        this.as = as;
        this.data = data;
    }

   
 AuxiliarAtestado(){}

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeacompanhante() {
        return nomeacompanhante;
    }

    public void setNomeacompanhante(String nomeacompanhante) {
        this.nomeacompanhante = nomeacompanhante;
    }
 
}
