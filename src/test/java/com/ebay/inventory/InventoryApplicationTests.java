package com.ebay.inventory;

import com.ebay.inventory.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllItems() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/items",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetItemById() {
		Item item = restTemplate.getForObject(getRootUrl() + "/items/1", Item.class);
		System.out.println(item.getItemId());
		Assert.assertNotNull(item);
	}

	@Test
	public void testCreateItem() {
		Item item = new Item();
		item.setItemId(1L);
		item.setTitle("Title");
		item.setItemCondition("Condition");
		item.setPrice(BigDecimal.valueOf(10.33));
		item.setQuantity(5);
		item.setImageURL(Collections.singletonList("https://url"));
		item.setItemSpecifics("Specifics");
		item.setDescription("Description");

		ResponseEntity<Item> postResponse = restTemplate.postForEntity(getRootUrl() + "/items", item, Item.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost() {
		int id = 1;
		Item item = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
		item.setItemId(1L);
		item.setTitle("Title");
		item.setItemCondition("Condition");
		item.setPrice(BigDecimal.valueOf(10.33));
		item.setQuantity(5);
		item.setImageURL(Collections.singletonList("https://url"));
		item.setItemSpecifics("Specifics");
		item.setDescription("Description");

		restTemplate.put(getRootUrl() + "/items/" + id, item);

		Item updatedItem = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
		Assert.assertNotNull(updatedItem);
	}

	@Test
	public void testDeletePost() {
		int id = 2;
		Item item = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
		Assert.assertNotNull(item);

		restTemplate.delete(getRootUrl() + "/items/" + id);

		try {
			item = restTemplate.getForObject(getRootUrl() + "/items/" + id, Item.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
