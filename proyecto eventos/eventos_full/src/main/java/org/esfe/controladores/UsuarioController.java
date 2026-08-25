package org.esfe.controladores;

import org.esfe.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.esfe.servicios.interfaces.IUsuarioService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {

        model.addAttribute("usuarios",
                usuarioService.listarTodos());

        return "usuario/index";
    }

    @GetMapping("/create")
    public String create(Usuario usuario) {return "usuario/create";}

    @PostMapping("save")
    public String save(Usuario usuario, BindingResult result, Model model,
                       RedirectAttributes attributes)
    {
        if(result.hasErrors())
        {
            model.addAttribute(usuario);
            attributes.addFlashAttribute("error",
                    "No se pudo guardar debido a un error.");
            return "usuario/create";
        }

        usuarioService.createOrEdit(usuario);
        attributes.addFlashAttribute("msg",
                "Usuario Creado Exitosamente");

        return "redirect:/usuarios";
    }

    @GetMapping("/details/{id}")
    public String detalle(@PathVariable("id") Integer id, Model model)
    {
        Usuario usuario = usuarioService.buscarPorId(id).get();
        model.addAttribute("usuario", usuario);
        return "usuario/details";
    }
    @PostMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer id, Model model)
    {
        Usuario usuario = usuarioService.buscarPorId(id).get();
        model.addAttribute("usuario", usuario);
        return "usuario/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model)
    {
        Usuario usuario = usuarioService.buscarPorId(id).get();
        model.addAttribute("usuario", usuario);
        return "usuario/delete";
    }
    @PostMapping("/delete")
    public String eliminar(Usuario usuario, RedirectAttributes attributes)
    {
        usuarioService.eliminarPorId(usuario.getId());
        attributes.addFlashAttribute("msg",
                "Usuario eliminado correctamente ");
        return "redirect:/usuarios";
    }

}
