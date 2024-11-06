package com.claucio.bluefood.infrastructure.web.controller;

import com.claucio.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import com.claucio.bluefood.domain.restaurante.CategoryRestaurante;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.List;

public class ControllerHelper {

    public static void setEditMode(Model model, boolean isEdit){
        model.addAttribute("editMode", isEdit);
    }

    public static void  addCategoriasToResquest(CategoriaRestauranteRepository repository, Model model){

        List<CategoryRestaurante> categorias = repository.findAll(Sort.by("name"));
        model.addAttribute("categorias", categorias);
        // usar


    }
}
