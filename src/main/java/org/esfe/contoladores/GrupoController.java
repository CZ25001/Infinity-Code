package org.esfe.contoladores;

import org.esfe.modelos.Grupo;
import org.esfe.servicios.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private IGrupoService grupoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size)
    {
        //Si no viene numero de pagina le asigna 0
        int currentPage = page.orElse(1) -1;
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Grupo> grupos = grupoService.buscarTodosPaginados(pageable);
        model.addAttribute("grupos", grupos);

        int totalPage = grupos.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumber);
        }

        return "grupo/index";
    }

    @GetMapping("/create")
    public String create(Grupo grupo)
    {
        return "grupo/create";
    }

    @PostMapping("/save")
    public String save(Grupo grupo, BindingResult result, Model model,
                       RedirectAttributes attributes)
    {
        if(result.hasErrors())
        {
            model.addAttribute(grupo);
            attributes.addFlashAttribute("error",
                    "No se pudo guardar debido a un error.");
            return "grupo/create";
        }

        grupoService.createOrEdit(grupo);
        attributes.addFlashAttribute("msg",
                "Grupo Creado Exitosamente");

        return "redirect:/grupos";
    }

    @GetMapping("/details/{id}")
    public String detalle(@PathVariable("id") Integer id, Model model)
    {
        Grupo grupo = grupoService.buscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/details";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer id, Model model)
    {
        Grupo grupo = grupoService.buscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model)
    {
        Grupo grupo = grupoService.buscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/delete";
    }

    @PostMapping("/delete")
    public String eliminar(Grupo grupo, RedirectAttributes attributes)
    {
        grupoService.eliminarPorId(grupo.getId());
        attributes.addFlashAttribute("msg",
                "Grupo eliminado correctamente ");
        return "redirect:/grupos";
    }
}
