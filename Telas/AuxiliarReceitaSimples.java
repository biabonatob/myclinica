/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;


public class AuxiliarReceitaSimples {
    
    private String data;
    private String nome;
    private String prescricao;

    public AuxiliarReceitaSimples(String data, String nome, String prescricao) {
        this.data = data;
        this.nome = nome;
        this.prescricao = prescricao;
    }
    
    AuxiliarReceitaSimples(){}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }
    
    
     
    
}
