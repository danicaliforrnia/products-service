package com.imagina.productsservice.services;

import com.imagina.productsservice.dtos.InputProductDto;
import com.imagina.productsservice.dtos.ReadCategoryDto;
import com.imagina.productsservice.dtos.ReadProductDto;
import com.imagina.productsservice.entities.Category;
import com.imagina.productsservice.entities.Product;
import com.imagina.productsservice.exceptions.CategoryNotFoundException;
import com.imagina.productsservice.exceptions.ProductNotFoundException;
import com.imagina.productsservice.repositories.CategoryRepository;
import com.imagina.productsservice.repositories.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {
    final List<Category> categories = List.of(
            new Category(1L, "CategoryA", "categoryA description"),
            new Category(2L, "CategoryB", "categoryB description"),
            new Category(3L, "CategoryC", "categoryC description")
    );
    final List<ReadCategoryDto> categoriesDto = List.of(
            new ReadCategoryDto(categories.get(0).getId(), categories.get(0).getName(), categories.get(0).getDescription()),
            new ReadCategoryDto(categories.get(1).getId(), categories.get(1).getName(), categories.get(1).getDescription()),
            new ReadCategoryDto(categories.get(2).getId(), categories.get(2).getName(), categories.get(2).getDescription())
    );
    final List<Product> products = List.of(
            new Product(1L, "productA", 1000, "productA description", 200D, categories.get(0), null),
            new Product(2L, "productB", 1001, "productB description", 100D, categories.get(1), null),
            new Product(3L, "productC", 1002, "productC description", 20D, categories.get(2), null)
    );
    final List<ReadProductDto> productsDto = List.of(
            new ReadProductDto(products.get(0).getId(), products.get(0).getName(), products.get(0).getCode(), products.get(0).getDescription(), products.get(0).getPrice(), categoriesDto.get(0), null),
            new ReadProductDto(products.get(1).getId(), products.get(1).getName(), products.get(1).getCode(), products.get(1).getDescription(), products.get(1).getPrice(), categoriesDto.get(1), null),
            new ReadProductDto(products.get(2).getId(), products.get(2).getName(), products.get(2).getCode(), products.get(2).getDescription(), products.get(2).getPrice(), categoriesDto.get(2), null)
    );
    InputProductDto inputProductDto = new InputProductDto(
            products.get(0).getName(),
            products.get(0).getCode(),
            products.get(0).getDescription(),
            products.get(0).getPrice(),
            products.get(0).getCategory().getId()
    );
    int page = 0;
    int size = 5;

    @InjectMocks
    private ProductsService productsService;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void shouldReturnAllPage() {
        Pageable pageable = PageRequest.of(page, size);

        when(productsRepository.findAll(pageable)).thenReturn(
                new PageImpl<>(products.subList(page * size, products.size() > size ? size - 1 : products.size()), pageable, products.size())
        );

        Page<ReadProductDto> productsPage = productsService.findAll(null, page, size);

        assertNotNull(productsPage);
        assertEquals(products.size(), productsPage.getTotalElements());
        assertEquals((int) Math.ceil((double) products.size() / size), productsPage.getTotalPages());
        assertTrue(productsPage.getContent().size() <= size);
    }

    @Test
    public void shouldReturnPageByNameContaining() {
        String name = products.get(0).getName();
        Pageable pageable = PageRequest.of(page, size);

        when(productsRepository.findAllByNameContainingIgnoreCase(name, pageable)).thenReturn(
                new PageImpl<>(
                        products
                                .subList(page * size, products.size() > size ? size - 1 : products.size())
                                .stream()
                                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                                .collect(Collectors.toList()),
                        pageable,
                        products.size()
                )
        );
        when(modelMapper.map(ArgumentMatchers.any(Product.class), ArgumentMatchers.eq(ReadProductDto.class))).thenReturn(productsDto.get(0));

        Page<ReadProductDto> productsPage = productsService.findAll(name, page, size);

        assertNotNull(productsPage);
        assertEquals((int) Math.ceil((double) products.size() / size), productsPage.getTotalPages());
        assertTrue(productsPage.getContent().size() <= size);
        assertEquals(products.get(0).getName(), productsPage.getContent().get(0).getName());
    }

    @Test
    public void shouldReturnProductById() {
        Product product = products.get(0);

        when(productsRepository.findById(product.getId())).thenReturn(Optional.of(products.get(0)));
        when(modelMapper.map(ArgumentMatchers.any(Product.class), ArgumentMatchers.eq(ReadProductDto.class))).thenReturn(productsDto.get(0));

        ReadProductDto productDto = productsService.findById(product.getId());

        assertNotNull(productDto);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getCode(), productDto.getCode());
        assertEquals(product.getDescription(), productDto.getDescription());
    }

    @Test
    public void shouldCreateProduct() {
        when(modelMapper.map(ArgumentMatchers.any(InputProductDto.class), ArgumentMatchers.eq(Product.class))).thenReturn(products.get(0));
        when(categoryRepository.findById(inputProductDto.getCategory())).thenReturn(Optional.of(categories.get(0)));
        when(productsRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(products.get(0));
        when(modelMapper.map(ArgumentMatchers.any(Product.class), ArgumentMatchers.eq(ReadProductDto.class))).thenReturn(productsDto.get(0));

        ReadProductDto productDto = productsService.create(inputProductDto);

        verify(productsRepository).save(products.get(0));
        assertNotNull(productDto);
        assertEquals(productDto.getName(), inputProductDto.getName());
        assertEquals(productDto.getCode(), inputProductDto.getCode());
        assertEquals(productDto.getDescription(), inputProductDto.getDescription());
    }

    @Test
    public void shouldThrowCategoryNotFoundExceptionOnCreate() {
        when(modelMapper.map(ArgumentMatchers.any(InputProductDto.class), ArgumentMatchers.eq(Product.class))).thenReturn(products.get(0));
        when(categoryRepository.findById(inputProductDto.getCategory())).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, () -> productsService.create(inputProductDto));
    }

    @Test
    public void shouldUpdateUser() {
        when(productsRepository.findById(products.get(0).getId())).thenReturn(Optional.of(products.get(0)));
        doNothing().when(modelMapper).map(isA(InputProductDto.class), isA(Product.class));
        when(categoryRepository.findById(inputProductDto.getCategory())).thenReturn(Optional.of(categories.get(0)));
        when(productsRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(products.get(0));
        when(modelMapper.map(ArgumentMatchers.any(Product.class), ArgumentMatchers.eq(ReadProductDto.class))).thenReturn(productsDto.get(0));

        ReadProductDto productDto = productsService.update(products.get(0).getId(), inputProductDto);

        verify(productsRepository).findById(products.get(0).getId());
        verify(productsRepository).save(products.get(0));
        assertNotNull(productDto);
        assertEquals(productDto.getName(), inputProductDto.getName());
        assertEquals(productDto.getCode(), inputProductDto.getCode());
        assertEquals(productDto.getDescription(), inputProductDto.getDescription());
    }

    @Test
    public void shouldThrowProductNotFoundExceptionOnUpdate() {
        var inputProductDto = new InputProductDto(products.get(0).getName(), products.get(0).getCode(), products.get(0).getDescription(), products.get(0).getPrice(), products.get(0).getCategory().getId());
        when(productsRepository.findById(products.get(0).getId())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productsService.update(products.get(0).getId(), inputProductDto));
    }

}
