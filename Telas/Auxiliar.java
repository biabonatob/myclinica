/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;


 public class Auxiliar {
 private String data;
 private String nome;    
 private String exame; 
 private String contraste;

    public Auxiliar(String data, String nome, String exame, String contraste) {
        this.data = data;
        this.nome = nome;
        this.exame = exame;
        this.contraste = contraste;
    }

   
public Auxiliar() {}    

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

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getContraste() {
        return contraste;
    }

    public void setContraste(String contraste) {
        this.contraste = contraste;
    }

   
}
