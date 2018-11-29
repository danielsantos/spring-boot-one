package com.danielrocha.springbootone;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;


	@GetMapping("/")
	public ModelAndView index(String msg) {
		
		ModelAndView mv = new ModelAndView("index");
		
		mv.addObject("listaUsuarios", usuarioRepository.buscarAtivos());
		mv.addObject("usuario", new Usuario());
		
		if (msg != null) {
			mv.addObject("msgSucesso", msg);
		}
		
		return mv;
		
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", new Usuario());
		mv.addObject("listaDepartamentos", departamentoRepository.findAll());
		
		return mv;
		
	}
	
	@PostMapping("/save")
	public ModelAndView save(Usuario usuario) {
		
		usuario.setDataCadastro(new Date());
		usuarioRepository.save(usuario);
		
		return index("Usuário cadastro com sucesso!");
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		
		usuarioRepository.excluirLogicamente(id);
		
		return index("Usuário excluído com sucesso!");
		
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {

		Usuario usuario = usuarioRepository.getOne(id);
		
		ModelAndView mv = new ModelAndView("alteracao");
		mv.addObject("usuario", usuario);
		mv.addObject("listaDepartamentos", departamentoRepository.findAll());
		
		return mv;
		
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Usuario usuario) {
		
		Usuario usuarioBanco = usuarioRepository.getOne(usuario.getId());
		usuario.setDataCadastro(usuarioBanco.getDataCadastro());
		
		usuarioRepository.save(usuario);
		
		return index("Usuário alterado com sucesso!");
		
	}
	
}
