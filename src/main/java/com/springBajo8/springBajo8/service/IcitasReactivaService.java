package com.springBajo8.springBajo8.service;

//import com.yoandypv.reactivestack.messages.domain.Message;
import com.springBajo8.springBajo8.domain.MedicoDto;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface IcitasReactivaService {
    Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva);

    Mono<citasDTOReactiva> delete(String id);

    Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva);

    Flux<citasDTOReactiva> findByIdPaciente(String idPaciente);

    Flux<citasDTOReactiva> findAll();

    Mono<citasDTOReactiva> findById(String id);

    Mono<citasDTOReactiva> cancelarCita(String id);

    Flux<citasDTOReactiva> findByFecha(LocalDate fecha);

    Mono<citasDTOReactiva> findByMedico(String id, String nombreMedico);

    Flux<citasDTOReactiva> consultarCitaPorFechaYHora(LocalDate fecha, String hora);

    Mono<MedicoDto> consultarMedicoCita(String id);
}