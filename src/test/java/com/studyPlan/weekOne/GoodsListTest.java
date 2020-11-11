package com.studyPlan.weekOne;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyPlan.weekOne.entity.GoodsItem;
import com.studyPlan.weekOne.repository.GoodsItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodsListTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private GoodsItemRepository goodsItemRepository;

    @BeforeEach
    void init(){
        goodsItemRepository.deleteAll();
    }

    @Nested
    class addGoodsItem {
        @Test
        void should_success_when_new_goods_item_info_valid() throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                                            .name("apple")
                                            .unitPrice(5.00)
                                            .shop("fruitApartment")
                                            .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String goodsJson = objectMapper.writeValueAsString(newGoods);

            mockMvc.perform(post("/goodsItem")
                    .content(goodsJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }

        @Test
        void should_error_when_new_goods_item_lack_name() throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .unitPrice(5.00)
                    .shop("fruitApartment")
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String goodsJson = objectMapper.writeValueAsString(newGoods);

            mockMvc.perform(post("/goodsItem")
                    .content(goodsJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void should_error_when_new_goods_item_lack_shop_name() throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .name("apple")
                    .unitPrice(5.00)
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String goodsJson = objectMapper.writeValueAsString(newGoods);

            mockMvc.perform(post("/goodsItem")
                    .content(goodsJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void should_error_when_new_goods_item_unit_price_too_high() throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .name("apple")
                    .shop("fruitApartment")
                    .unitPrice(999999999.99)
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String goodsJson = objectMapper.writeValueAsString(newGoods);

            mockMvc.perform(post("/goodsItem")
                    .content(goodsJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void should_error_when_new_goods_item_lack_unit_price() throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .name("apple")
                    .shop("fruitApartment")
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String goodsJson = objectMapper.writeValueAsString(newGoods);

            mockMvc.perform(post("/goodsItem")
                    .content(goodsJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class getAllGoodsItem {
        @Test
        void should_success () throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .name("apple")
                    .unitPrice(5.00)
                    .shop("fruitApartment")
                    .build();
            GoodsItem anotherGoods = GoodsItem.builder()
                    .name("carrot")
                    .unitPrice(4.00)
                    .shop("vegetableApartment")
                    .build();
            goodsItemRepository.save(newGoods);
            goodsItemRepository.save(anotherGoods);
            mockMvc.perform(get("/goodsItem"))
                    .andExpect(jsonPath("$[0].name",is("apple")))
                    .andExpect(jsonPath("$[1].name",is("carrot")))
                    .andExpect(jsonPath("$[0].unitPrice",is(5.00)))
                    .andExpect(jsonPath("$[1].unitPrice",is(4.00)))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class deleteGoodsItemById {
        @Test
        void should_success_when_goods_item_exist () throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .name("apple")
                    .unitPrice(5.00)
                    .shop("fruitApartment")
                    .build();
            goodsItemRepository.save(newGoods);
            Integer targetGoodsId = newGoods.getId();
            mockMvc.perform(delete("/goodsItem/"+targetGoodsId))
                    .andExpect(status().isNoContent());
        }

        @Test
        void should_success_when_goods_item_not_exist () throws Exception {
            GoodsItem newGoods = GoodsItem.builder()
                    .name("apple")
                    .unitPrice(5.00)
                    .shop("fruitApartment")
                    .build();
            goodsItemRepository.save(newGoods);
            mockMvc.perform(delete("/goodsItem/22"))
                    .andExpect(jsonPath("$.message",is("该商品不存在")))
                    .andExpect(jsonPath("$.code",is(404)))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class updateGoodsItem {
        @Test
        void should_success_when_goods_item_exist() throws Exception {
            GoodsItem oldGoods = GoodsItem.builder()
                    .name("apple")
                    .unitPrice(5.00)
                    .shop("fruitApartment")
                    .build();
            goodsItemRepository.save(oldGoods);
            GoodsItem newGoods = GoodsItem.builder()
                    .id(oldGoods.getId())
                    .name("carrot")
                    .unitPrice(4.00)
                    .shop("vegetableApartment")
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String newGoodsJson = objectMapper.writeValueAsString(newGoods);

            mockMvc.perform(patch("/goodsItem")
                    .content(newGoodsJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            mockMvc.perform(get("/goodsItem"))
                    .andExpect(jsonPath("$[0].name",is("carrot")))
                    .andExpect(jsonPath("$[0].unitPrice",is(4.00)))
                    .andExpect(jsonPath("$[0].shop",is("vegetableApartment")))
                    .andExpect(jsonPath("$[0].id",is(oldGoods.getId())))
                    .andExpect(status().isOk());
        }
    }
}
