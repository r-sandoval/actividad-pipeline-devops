/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vehiculos.VehiculosRest.controllers;

import com.vehiculos.VehiculosRest.models.VehiculoModel;
import com.vehiculos.VehiculosRest.services.VehiculoService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Villacura
 */
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    
    @Autowired
    private VehiculoService vehiculoService;
    
    @GetMapping
    public ResponseEntity<ArrayList<VehiculoModel>> getAutos() {
        ArrayList<VehiculoModel> listaAutos = this.vehiculoService.getVehiculos();
        return new ResponseEntity<>(listaAutos, HttpStatus.OK);
    }
   
    @PostMapping
    public ResponseEntity<VehiculoModel> guardarAuto(@RequestBody VehiculoModel vehiculo) {
        VehiculoModel nuevoVehiculo = this.vehiculoService.saveAuto(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }
}
