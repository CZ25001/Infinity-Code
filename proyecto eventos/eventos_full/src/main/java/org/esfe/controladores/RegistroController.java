package org.esfe.controladores;
import org.esfe.servicios.implementaciones.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.esfe.modelos.Usuario;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro/create";
    }
}
