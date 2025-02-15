package com.springBajo8.springBajo8.service.impl;

//import com.yoandypv.reactivestack.messages.domain.Message;
//import com.yoandypv.reactivestack.messages.repository.MessageRepository;
//import com.yoandypv.reactivestack.messages.service.MessageService;

import com.springBajo8.springBajo8.domain.MedicoDto;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));

    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return this.IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return this.IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }


    @Override
    public Flux<citasDTOReactiva> findAll() {
        return this.IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return this.IcitasReactivaRepository.findById(id);
    }

    //Service bussines
    @Override
    public Mono<citasDTOReactiva> cancelarCita(String id) {
        return this.IcitasReactivaRepository
                .findById(id)
                .flatMap(cita -> {
                    cita.setEstadoReservaCita("0");
                    return save(cita);
                }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByFecha(LocalDate fecha) {
        return null;
    }

    @Override
    public Mono<citasDTOReactiva> findByMedico(String id, String nombreMedico) {
        return null;
    }

    @Override
    public Flux<citasDTOReactiva> consultarCitaPorFechaYHora(LocalDate fecha, String hora) {
        return this.IcitasReactivaRepository
                .findAll()
                .filter(citas -> citas.getFechaReservaCita().equals(fecha))
                .filter(citas -> citas.getHoraReservaCita().equals(hora))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<MedicoDto> consultarMedicoCita(String id) {
        return IcitasReactivaRepository
                .findById(id)
                .flatMap(medico -> {
                    return Mono.just(new MedicoDto(medico.getNombreMedico(), medico.getApellidosMedico()));
                });
    }
}