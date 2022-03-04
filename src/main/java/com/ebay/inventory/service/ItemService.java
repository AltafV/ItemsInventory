package com.ebay.inventory.service;

import com.ebay.inventory.entity.Item;
import com.ebay.inventory.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    public Optional<Item> findItemById(Long itemId) {
        log.info("Inside findItemById of ItemService");
        return itemRepository.findByItemId(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> saveAll(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public Optional<Item> updateItem(Item item) {
        return itemRepository.findByItemId(item.getItemId());
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    public void deleteById(long id) {
        itemRepository.deleteById(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
}
