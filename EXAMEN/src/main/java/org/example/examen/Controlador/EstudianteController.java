package org.example.examen.Controlador;

import org.example.examen.Repository.CarreraRepository;
import org.example.examen.Repository.UsuarioRepository;
import org.example.examen.modelo.Carrera;
import org.example.examen.modelo.CarreraId;
import org.example.examen.modelo.Estudiante;
import org.example.examen.Repository.EstudianteRepository;
import org.example.examen.modelo.Usuario;
import org.example.examen.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.examen.service.EstudianteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteRepository.findAll();

        List<Map<String, Object>> estudiantesConCarrera = estudiantes.stream().map(estudiante -> {
            Map<String, Object> estudianteMap = new HashMap<>();
            estudianteMap.put("id", estudiante.getId());
            estudianteMap.put("nombreEstudiante", estudiante.getNombreEstudiante());
            estudianteMap.put("apellidoEstudiante", estudiante.getApellidoEstudiante());
            estudianteMap.put("carreraId", estudiante.getCarreraId());

            // Buscar el nombre de la carrera usando carreraId
            Optional<Carrera> carrera = carreraRepository.findByCarreraId(estudiante.getCarreraId());
            carrera.ifPresent(value -> estudianteMap.put("nombreCarrera", value.getNombreCarrera()));

            return estudianteMap;
        }).collect(Collectors.toList());

        model.addAttribute("estudiantes", estudiantesConCarrera);
        return "estudiante/index";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("estudiante", new Estudiante());

        List<Carrera> carreras = carreraRepository.findAll();
        model.addAttribute("carreras", carreras);

        return "estudiante/agregar";
    }

    @PostMapping("/agregar")
    public String agregarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid estudiante ID: " + id));
        model.addAttribute("estudiante", estudiante);
        return "estudiante/editar";
    }

    @PostMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Integer id, @ModelAttribute Estudiante estudiante) {
        estudiante.setId(id);
        estudianteRepository.save(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleEstudiante(@PathVariable Integer id, Model model) {
        Estudiante estudiante = estudianteService.getEstudianteById(id);
        Carrera carrera = carreraService.getCarreraById(estudiante.getCarreraId());
        Usuario usuario = usuarioRepository.findById(estudiante.getUsuarioId()).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("carrera", carrera);
        model.addAttribute("nombreUsuario", usuario.getUser());
        return "estudiante/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id) {
        estudianteRepository.deleteById(id);
        return "redirect:/estudiantes";
    }
}
