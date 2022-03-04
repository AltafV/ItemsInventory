package com.ebay.inventory.controller;

import com.ebay.inventory.entity.Item;
import com.ebay.inventory.exception.ResourceNotFoundException;
import com.ebay.inventory.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @param itemId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public Item findItemsById(@PathVariable("id") Long itemId) throws ResourceNotFoundException {
        log.info("Inside findItemsById method of ItemController");
        Item result =
                itemService.findItemById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: " + itemId));
        return result;
    }

    /**
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/")
    public List<Item> getAllItems() throws ResourceNotFoundException {
        return itemService.findAll();
    }

    /**
     * @param itemId
     * @param item
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable("id") Long itemId, @RequestBody Item item) throws ResourceNotFoundException {
        log.info("Inside updateItem method of ItemController");
        Item result =
                itemService.findItemById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: " + itemId));
        result.setItemId(item.getItemId());
        result.setTitle(item.getTitle());
        result.setItemCondition(item.getItemCondition());
        result.setPrice(item.getPrice());
        result.setQuantity(item.getQuantity());
        result.setImageURL(item.getImageURL());
        result.setItemSpecifics(item.getItemSpecifics());
        result.setDescription(item.getDescription());
        final Item updatedItem = itemService.saveItem(result);
        return itemService.updateItem(updatedItem).get();
    }

    /**
     * @param items
     * @return
     */
    @PostMapping("/")
    public List<Item> saveItem(@RequestBody List<Item> items) {
        log.info("Inside saveItem method of ItemController");
        return itemService.saveAll(items);
    }

    /**
     * @param itemId
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/{itemId}")
    public Map<String, Boolean> deleteAllItems(@PathVariable("itemId") long itemId) throws ResourceNotFoundException {
        Item result =
                itemService.findItemById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: " + itemId));
        itemService.deleteById(result.getItemId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * @return
     */
    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllItems() {
        try {
            itemService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
