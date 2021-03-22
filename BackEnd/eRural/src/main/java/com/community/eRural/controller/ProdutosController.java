package com.community.eRural.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.eRural.Repository.ProdutosRepository;
import com.community.eRural.model.Produtos;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository repository;
	//ProdutosRepository resposta = new repository
	
	@GetMapping
	public ResponseEntity<List<Produtos>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos>GetById(@PathVariable long id)//@PathVariable é utilizado quando o valor da variavel é passado diretamente da URL
	{
		return repository.findById(id).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>>GetByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> post(@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtos));
	}//inclui dados no db.
	
	@PutMapping
	public ResponseEntity<Produtos> put(@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produtos));
	}//Modifica dados no db.
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}
	
	}
