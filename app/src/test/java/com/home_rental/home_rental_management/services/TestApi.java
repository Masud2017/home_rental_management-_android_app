package com.home_rental.home_rental_management.services;

import com.home_rental.home_rental_management.Models.HomeModelResponse;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestApi {
    @Test
    public void testGetHomeList() throws IOException {
        Api api = new Api();

        List<HomeModelResponse> homeModelResponseList = api.getHomeList();

        Assert.assertTrue(homeModelResponseList.size() > 1);

        HomeModelResponse firstItem = homeModelResponseList.get(0);

        Assert.assertEquals("home",firstItem.getName().trim());
        Assert.assertEquals("escription",firstItem.getDesc().trim());

    }
}
