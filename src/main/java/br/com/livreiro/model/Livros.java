package br.com.livreiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livros {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "editora")
  private String editora;

  @Column(name = "autor")
  private String autor;

  @Column(name = "isbn")
  private String isbn;

  public Livros(){}

  public Livros(String titulo, String descricao, String editora, String autor, String isbn) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.editora = editora;
    this.autor = autor;
    this.isbn = isbn;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getEditora() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora = editora;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  @Override
  public String toString() {
    return "Livros [autor=" + autor + ", descricao=" + descricao + ", editora=" + editora + ", id=" + id + ", isbn="
        + isbn + ", titulo=" + titulo + "]";
  }

  
}
