package org.example.examen.Controlador;

import org.example.examen.modelo.Tipousuario;
import org.example.examen.modelo.Usuario;
import org.example.examen.Repository.TipousuarioRepository;
import org.example.examen.Repository.UsuarioRepository;
import org.example.examen.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipousuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario/index";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Tipousuario> tipos = tipoUsuarioRepository.findAll();
        model.addAttribute("tipos", tipos);
        return "usuario/agregar";
    }

    @PostMapping("/agregar")
    public String agregarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid usuario ID: " + id));
        model.addAttribute("usuario", usuario);
        List<Tipousuario> tipos = tipoUsuarioRepository.findAll();
        model.addAttribute("tipos", tipos);
        return "usuario/editar";
    }

    @PostMapping("/editar")
    public String editarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalleUsuario(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "usuario/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
