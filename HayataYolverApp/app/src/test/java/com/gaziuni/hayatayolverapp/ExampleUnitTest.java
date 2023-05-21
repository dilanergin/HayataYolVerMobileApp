package com.gaziuni.hayatayolverapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    private UserActivity userActivity;

    @Mock
    private LocationManager locationManager;

    @Mock
    private Geocoder geocoder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        geocoder = Mockito.mock(Geocoder.class);
    }
    @Test
    public void testGetCurrentLocation() throws IOException {
        Location location=mock(Location.class);
        when(location.getLatitude()).thenReturn(41.0082);
        when(location.getLongitude()).thenReturn(28.9784);

        List<Address> addressList = new ArrayList<>();
        Address address = mock(Address.class);
        when(address.getLatitude()).thenReturn(41.0082);
        when(address.getLongitude()).thenReturn(28.9784);
        when(address.getLocality()).thenReturn("Istanbul");
        when(address.getCountryName()).thenReturn("Turkey");
        when(address.getAddressLine(0)).thenReturn("Sample Address");
        addressList.add(address);

        try {
            when(geocoder.getFromLocation(anyDouble(), anyDouble(), anyInt())).thenReturn(addressList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userActivity.currentLocation();
        verify(locationManager).getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        verify(geocoder).getFromLocation(41.0082, 28.9784, 1);

    }




}