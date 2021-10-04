package br.com.springboot.curso_jdev.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev.model.Usuario;
import br.com.springboot.curso_jdev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /*INJEÇÃO DE DEPENDÊNCIAS, USA OS RECURSOS DO USUARIO REPOSITORY QUE PEGA INFO DO BD*/
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso de Sringboot API: " + name + "!";
    }
    
    @RequestMapping(value ="/helloworld/{nome}", method = RequestMethod.GET) /*MAPEAMENTO DE REQUISIÇÃO, PEGA A URL E INTERSEPTA OS VALORES DA MSM*/
    @ResponseStatus(HttpStatus.OK)    /*RESPOSTA PADRÃO*/
    public String retronaHelloWorld(@PathVariable String nome) {
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	    	
    	usuarioRepository.save(usuario); /*GRAVA NO BANCO DE DADOS*/
    	
    	return "Hello World " + nome;
    }
    
    @GetMapping(value = "listartodos") /*PRIMEIRO MÉTODO DE API*/
    @ResponseBody /*RETORNA OS DADOS PARA O CORPO DA RESPOSTA*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*EXECUTA A CONSULTA NO BANCO DE DADOS*/
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*RETORNA A LISTA EM JSON*/
    }
    
    @PostMapping(value = "salvar")   /*MAPEIA A URL*/
    @ResponseBody   /*DESCRIÇÃO DA RESPOSTA*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){  /*RECEBE OS DADOS PARA SALVAR*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }

    @DeleteMapping(value = "delete")   /*MAPEIA A URL*/
    @ResponseBody   /*DESCRIÇÃO DA RESPOSTA*/
    public ResponseEntity<String> delete(@RequestParam Long iduser){  /*RECEBE OS DADOS PARA SALVAR*/
    	
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
    	
    }

    @GetMapping(value = "buscaruserid")   /*MAPEIA A URL*/
    @ResponseBody   /*DESCRIÇÃO DA RESPOSTA*/
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){  /*RECEBE OS DADOS PARA CONSULTAR*/
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "atualizar")   /*MAPEIA A URL*/
    @ResponseBody   /*DESCRIÇÃO DA RESPOSTA*/
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){  /*RECEBE OS DADOS PARA SALVAR*/
    	
    	/*if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
    	}
    	*/
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome")   /*MAPEIA A URL*/
    @ResponseBody   /*DESCRIÇÃO DA RESPOSTA*/
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){  /*RECEBE OS DADOS PARA CONSULTAR*/
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim());
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
    
}
