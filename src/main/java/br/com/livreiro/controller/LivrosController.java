package br.com.livreiro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  }

  @GetMapping("/livros/{id}")
  public ResponseEntity<Livros> getLivrosById(@PathVariable("id") Long id) {
    Optional<Livros> livrosDados = livrosRepository.findById(id);
    
    if(livrosDados.isPresent())
      return new ResponseEntity<>(livrosDados.get(), HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/livros")
  public ResponseEntity<Livros> createLivros(@RequestBody Livros livros){
    try {
      Livros li = livrosRepository.save(new Livros(
        livros.getTitulo(),
        livros.getDescricao(),
        livros.getEditora(),
        livros.getAutor(),
        livros.getIsbn()
      ));

      return new ResponseEntity<>(li, HttpStatus.CREATED);
      
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/livros/{id}")
  public ResponseEntity<Livros> UpdateLivros(@PathVariable long id, @RequestBody Livros livros ) {
    Optional<Livros> livrosDados = livrosRepository.findById(id);

    if(livrosDados.isPresent()) {
      Livros li = livrosDados.get();
      li.setTitulo(livros.getTitulo());
      li.setDescricao(livros.getDescricao());
      li.setEditora(livros.getEditora());
      li.setIsbn(livros.getIsbn());
      li.setAutor(livros.getAutor());
      livrosRepository.save(li);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/livros/{id}")
  public ResponseEntity<HttpStatus> deleteLivros(@PathVariable("id") long id) {
    Optional<Livros> livrosDados = livrosRepository.findById(id);
    
    if(livrosDados.isPresent()){
      try {
        livrosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/livros")
  public ResponseEntity<HttpStatus> deleteAllLivros() {
    try {
      livrosRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/livros/editora/{editora}")
  public ResponseEntity<List<Livros>> findByEditora(@PathVariable("editora") String editora) {
    try {
      List<Livros> livros = livrosRepository.findByEditora(editora);
      if(livros.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(livros, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
