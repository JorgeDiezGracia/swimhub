package com.svalero.swimhub.service;

import com.svalero.swimhub.domain.Category;
import com.svalero.swimhub.exception.CategoryNotFoundException;
import com.svalero.swimhub.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void findAll_returnsList() {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Benjamín");

        when(categoryRepository.findAll()).thenReturn(List.of(c1));

        List<Category> result = categoryService.findAll();

        assertEquals(1, result.size());
        assertEquals("Benjamín", result.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void findById_existingId_returnsCategory() throws CategoryNotFoundException {
        Category c = new Category();
        c.setId(1L);
        c.setName("Benjamín");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(c));

        Category result = categoryService.findById(1L);

        assertEquals("Benjamín", result.getName());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.findById(99L));
    }
}
