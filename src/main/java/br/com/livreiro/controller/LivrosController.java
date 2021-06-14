package br.com.livreiro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.livreiro.model.Livros;
import br.com.livreiro.repository.LivrosRepository;

@RestController
@RequestMapping("/api")
public class LivrosController {
  
  @Autowired
  LivrosRepository livrosRepository;

  @GetMapping("/livros")
  public ResponseEntity<List<Livros>> getAllLivros(@RequestParam(required = false) String titulo) {
    
    try {
      List<Livros> livros = new ArrayList<Livros>();
      if(titulo == null)
        livrosRepository.findAll().forEach(livros::add);
      else
        livrosRepository.findByTitulo(titulo).forEach(livros::add);

      if(livros.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(livros, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // return true
  }
}
