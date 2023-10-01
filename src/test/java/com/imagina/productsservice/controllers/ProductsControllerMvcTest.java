package com.imagina.productsservice.controllers;

import com.imagina.productsservice.dtos.ReadCategoryDto;
import com.imagina.productsservice.dtos.ReadProductDto;
import com.imagina.productsservice.services.ProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
public class ProductsControllerMvcTest {
    final List<ReadCategoryDto> categoriesDto = List.of(
            new ReadCategoryDto(1L, "CategoryA", "categoryA description"),
            new ReadCategoryDto(2L, "CategoryB", "categoryB description"),
            new ReadCategoryDto(3L, "CategoryC", "categoryC description")
    );
    final List<ReadProductDto> productsDto = List.of(
            new ReadProductDto(1L, "productA", 1000, "productA description", 200D, categoriesDto.get(0), null),
            new ReadProductDto(1L, "productB", 1001, "productB description", 2000D, categoriesDto.get(1), null),
            new ReadProductDto(1L, "productC", 1002, "productC description", 20D, categoriesDto.get(2), null)
    );
    int page = 0;
    int size = 5;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    @Test
    public void testGetAll() throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        when(productsService.findAll(null, page, size)).thenReturn(
                new PageImpl<>(productsDto.subList(page * size, productsDto.size() > size ? size - 1 : productsDto.size()), pageable, productsDto.size())
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].id").isNotEmpty());
    }
}
