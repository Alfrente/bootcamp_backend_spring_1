package com.arroyo.crud.utils;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender {
    private String ul;
    private Page<?> page;
    private List<PageItem> paginas;
    private int numElementosPorPaginas;
    private int paginaActual;
    private int totalPagina;

    public PageRender(String ul, Page<?> page) {
        this.ul = ul;
        this.page = page;
        this.paginas = new ArrayList<>();

        numElementosPorPaginas = 6;
        totalPagina = page.getTotalPages();
        paginaActual = page.getNumber() + 1;

        int desde, hasta;
        if (totalPagina <= numElementosPorPaginas) {
            desde = 1;
            hasta = totalPagina;
        } else {
            if (paginaActual <= numElementosPorPaginas) {
                desde = 1;
                hasta = numElementosPorPaginas;
            } else if (paginaActual >= totalPagina - numElementosPorPaginas / 2) {
                desde = totalPagina - numElementosPorPaginas + 1;
                hasta = numElementosPorPaginas;
            }else {
                desde = paginaActual - numElementosPorPaginas / 2;
                hasta = numElementosPorPaginas;
            }

        }

    }
}
