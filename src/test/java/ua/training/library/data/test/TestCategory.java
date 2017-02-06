package ua.training.library.data.test;

import ua.training.library.model.entity.Category;

public enum TestCategory {
    SCIENTIFIC(1, "Scientific literature"), FICTION(2, "Fiction");

    public Category category;

    private TestCategory(int id, String name) {
        category = new Category(id, name);
    }
}
