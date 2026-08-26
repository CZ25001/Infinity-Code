package org.esfe.contoladores;

import org.esfe.modelos.Docente;
import org.esfe.modelos.DocenteGrupo;
import org.esfe.modelos.Grupo;
import org.esfe.servicios.interfaces.IDocenteGrupoService;
import org.esfe.servicios.interfaces.IDocenteService;
import org.esfe.servicios.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.InterfaceAddress;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/asignaciones")
public class DocenteGrupoContoller {
    @Autowired
    private IDocenteGrupoService docenteGrupoService;

    @Autowired
    private IGrupoService grupoService;

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

        Page<DocenteGrupo> asignaciones = docenteGrupoService.buscarTodosPaginados(pageable);
        model.addAttribute("asignaciones", asignaciones);

        int totalPage = asignaciones.getTotalPages();
        if(totalPage > 0)
        {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumber);
        }

        return "asignacion/index";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("docentes", docenteService.obtenerTodos());
        model.addAttribute("grupos", grupoService.obtenerTodos());

        return "asignacion/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer docenteId, @RequestParam Integer grupoId,
                       @RequestParam Integer anio, @RequestParam String ciclo,
                       RedirectAttributes attributes)
    {
        Docente docente = docenteService.buscarPorId(docenteId).get();
        Grupo grupo = grupoService.buscarPorId(grupoId).get();

        if(docente != null && grupo != null)
        {
            DocenteGrupo docenteGrupo = new DocenteGrupo();
            docenteGrupo.setDocente(docente);
            docenteGrupo.setGrupo(grupo);
            docenteGrupo.setAnio(anio);
            docenteGrupo.setCiclo(ciclo);

            docenteGrupoService.createOrEdit(docenteGrupo);
            attributes.addFlashAttribute("msg",
                    "Asignacion creada correctamente");
        }

        return "redirect:/asignaciones";
    }
}
