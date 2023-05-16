package inventory.service;

import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class InventoryServiceTest {

    InventoryRepository inventoryRepository;
    InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        inventoryRepository = new InventoryRepository();
        inventoryService = new InventoryService(inventoryRepository);
    }

    @Tag("price tests")
    @DisplayName("parametrized test for invalid price value")
    @ParameterizedTest
    @ValueSource(doubles = { 0.00 })
    void invalidPriceValueAddInhousePart(Double price) {
        try{
            inventoryService.addInhousePart("Denumire", price, 50, 1, 100, 123);
            fail();
        }
        catch(InventoryService.ServiceException serviceException){
            assertTrue(true);
        }
    }

    @Tag("price tests")
    @DisplayName("parametrized test for invalid price type")
    @ParameterizedTest
    @ValueSource(strings = { "zece ron" })
    void invalidPriceTypeAddInhousePart(String price) {
        try{
            inventoryService.addInhousePart("Denumire", Double.parseDouble(price), 50, 1, 100, 123);
            fail();
        }
        catch(Exception typeException){
            assertTrue(true);
        }
    }

    @Tag("price tests")
    @DisplayName("parametrized test for valid price")
    @ParameterizedTest
    @ValueSource(doubles = { 10.2, 0.01, 0.02 })
    void validPriceAddInhousePart(Double price) {
        try{
            inventoryService.addInhousePart("Denumire", price, 50, 1, 100, 123);
            assertTrue(true);
        }
        catch(InventoryService.ServiceException serviceException){
            fail();
        }
    }

    @Tag("inStock_tests")
    @DisplayName("parametrized test for valid inStock")
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 50, 99, 100 })
    void validInStockAddInhousePart(Integer inStock) {
        try{
            inventoryService.addInhousePart("Denumire", 100, inStock, 1, 100, 123);
            assertTrue(true);
        }
        catch(InventoryService.ServiceException serviceException){
            fail();
        }
    }

    @Tag("inStock_tests")
    @DisplayName("parametrized test for invalid inStock value")
    @ParameterizedTest
    @ValueSource(ints = { -5, 0, 101, 300 })
    void invalidInStockValueAddInhousePart(Integer inStock) {
        try{
            inventoryService.addInhousePart("Denumire", 100, inStock, 1, 100, 123);
            fail();
        }
        catch(InventoryService.ServiceException serviceException){
            assertTrue(true);
        }
    }

    @Tag("inStock_tests")
    @DisplayName("parametrized test for invalid inStock type")
    @ParameterizedTest
    @ValueSource(doubles = { 1.5 })
    void invalidInStockTypeAddInhousePart(Double inStock) {
        try{
            inventoryService.addInhousePart("Denumire", 100, Integer.parseInt(inStock.toString()), 1, 100, 123);
            fail();
        }
        catch(Exception exception){
            assertTrue(true);
        }
    }
}