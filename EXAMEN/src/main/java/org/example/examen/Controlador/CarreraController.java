package org.example.examen.Controlador;

import org.example.examen.Repository.DepartamentoRepository;
import org.example.examen.modelo.Carrera;
import org.example.examen.modelo.CarreraId;
import org.example.examen.Repository.CarreraRepository;
import org.example.examen.modelo.Departamento;
import org.example.examen.service.CarreraService;
import org.example.examen.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private DepartamentoService departamentoService;
    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public String listarCarreras(Model model) {
        List<Carrera> carreras = carreraRepository.findAll();
        // Obtener los nombres de los departamentos para las carreras
        Map<Integer, String> departamentosMap = new HashMap<>();
        for (Carrera carrera : carreras) {
            Integer departamentoId = carrera.getId().getDepartamentoId();
            if (!departamentosMap.containsKey(departamentoId)) {
                Departamento departamento = departamentoService.getDepartamentoById(departamentoId);
                departamentosMap.put(departamentoId, departamento.getNombre());
            }
        }
        model.addAttribute("carreras", carreras);
        model.addAttribute("departamentos", departamentosMap);
        return "carrera/index";
    }


    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("carrera", new Carrera());

        List<Carrera> carreras = carreraRepository.findAll();
        model.addAttribute("carreras", carreras);

        List<Departamento> departamentos = departamentoRepository.findAll(); // Obtén los departamentos
        model.addAttribute("departamentos", departamentos);

        return "carrera/agregar";
    }

    @PostMapping("/agregar")
    public String agregarCarrera(@ModelAttribute Carrera carrera) {
        carreraRepository.save(carrera);
        return "redirect:/carreras";
    }

    @GetMapping("/editar/{carreraId}/{departamentoId}")
    public String mostrarFormularioEditar(@PathVariable Integer carreraId, @PathVariable Integer departamentoId, Model model) {
        CarreraId id = new CarreraId();
        id.setCarreraId(carreraId);
        id.setDepartamentoId(departamentoId);

        Carrera carrera = carreraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid carrera ID: " + id));
        model.addAttribute("carrera", carrera);
        return "carrera/editar";
    }

    @PostMapping("/editar")
    public String editarCarrera(@ModelAttribute Carrera carrera) {
        carreraRepository.save(carrera);
        return "redirect:/carreras";
    }

    @GetMapping("/detalle/{carreraId}/{departamentoId}")
    public String detalleCarrera(@PathVariable Integer carreraId, @PathVariable Integer departamentoId, Model model) {
        CarreraId id = new CarreraId();
        id.setCarreraId(carreraId);
        id.setDepartamentoId(departamentoId);

        Carrera carrera = carreraService.getCarreraById(carreraId);

        // Obtener el nombre del departamento
        Departamento departamento = departamentoService.getDepartamentoById(departamentoId);

        model.addAttribute("carrera", carrera);
        model.addAttribute("nombreDepartamento", departamento.getNombre());
        return "carrera/detalle";
    }

    @GetMapping("/eliminar/{carreraId}/{departamentoId}")
    public String eliminarCarrera(@PathVariable Integer carreraId, @PathVariable Integer departamentoId) {
        CarreraId id = new CarreraId();
        id.setCarreraId(carreraId);
        id.setDepartamentoId(departamentoId);
        carreraRepository.deleteById(id);
        return "redirect:/carreras";
    }
}
