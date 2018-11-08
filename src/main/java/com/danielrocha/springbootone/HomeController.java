package com.danielrocha.springbootone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("index");
		
		mv.addObject("listaUsuarios", usuarioRepository.findAll());
		mv.addObject("usuario", new Usuario());
		
		return mv;
		
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", new Usuario());
		
		return mv;
		
	}
	
	@PostMapping("/save")
	public ModelAndView save(Usuario usuario) {
		
		usuarioRepository.save(usuario);
		
		return index();
		
	}
	
}
