package org.esfe.contoladores;

import jakarta.validation.Valid;
import org.esfe.modelos.Docente;
import org.esfe.modelos.Grupo;
import org.esfe.servicios.interfaces.IDocenteService;
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
@RequestMapping("/docentes")
public class DocenteController {
    @Autowired
    private IDocenteService docenteService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size)
    {
        //Si no viene numero de pagina le asigna 0
        int currentPage = page.orElse(1) -1;
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Docente> docentes = docenteService.buscarTodosPaginados(pageable);
        model.addAttribute("docentes", docentes);

        int totalPage = docentes.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumber);
        }

        return "docente/index";
    }

    @GetMapping("/create")
    public String crear(Docente docente)
    {
        return "docente/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("docente") Docente docente, BindingResult result, Model model,
                       RedirectAttributes attributes)
    {
        if(result.hasErrors())
        {
            model.addAttribute(docente);
            attributes.addFlashAttribute("msg",
                    "No se pudo guardar debido a un error.");
            return "docente/create";
        }

        docenteService.createOrEdit(docente);
        attributes.addFlashAttribute("msg",
                "Docente creado correctamente");
        return "redirect:/docentes";
    }
}
