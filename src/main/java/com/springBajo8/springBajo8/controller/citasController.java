package com.springBajo8.springBajo8.controller;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/citas")
public class citasController {

    @Autowired
    private IcitasReactivaService icitasReactivaService;

    //endpoint del servicio ya creado
    @PostMapping
    public Mono<citasDTOReactiva> postCita (@RequestBody citasDTOReactiva citasDTOReactiva){
        return icitasReactivaService.save(citasDTOReactiva);
    }

    @DeleteMapping("/{id}")
    public Mono<citasDTOReactiva> deleteCita(@PathVariable("id") String id){
        return icitasReactivaService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<citasDTOReactiva> updateCita(@PathVariable("id") String id , @RequestBody citasDTOReactiva cita){
        return icitasReactivaService.update(id,cita);
    }

    @GetMapping("/paciente/{id}")
    public Flux<citasDTOReactiva> getPacienteById(@PathVariable("id") String idPaciente){
        return icitasReactivaService.findByIdPaciente(idPaciente);
    }

    @GetMapping
    public Flux<citasDTOReactiva> getAllCitas(){
        return icitasReactivaService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<citasDTOReactiva> getCitaById(@PathVariable String id){
        return icitasReactivaService.findById(id);
    }

    //Endpoint servicios solicitados
    @PutMapping("/cancelar/{id}")
    public Mono<citasDTOReactiva> cancelarCita(@PathVariable String id){
        return icitasReactivaService.cancelarCita(id);
    }

    @GetMapping("/fecha/{fecha}")
    public Flux<citasDTOReactiva> getCitasByFecha(@PathVariable LocalDate fecha){
        return icitasReactivaService.findByFecha(fecha);
    }

    @GetMapping("/{id}/{medico}")
    public Mono<citasDTOReactiva> getCitaMedico(@PathVariable("id") String id,@PathVariable("medico") String nombreMedico){
        return icitasReactivaService.findByMedico(id, nombreMedico);
    }

}