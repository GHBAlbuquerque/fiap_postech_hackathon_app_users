package com.fiap.hackathon.core.usecase;

import com.fiap.hackathon.common.exceptions.custom.*;
import com.fiap.hackathon.common.interfaces.gateways.AuthenticationGateway;
import com.fiap.hackathon.common.interfaces.gateways.PatientGateway;
import com.fiap.hackathon.common.interfaces.usecase.PatientUseCase;
import com.fiap.hackathon.core.entity.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatientUseCaseImpl implements PatientUseCase {

    private static final Logger logger = LogManager.getLogger(PatientUseCaseImpl.class);

    @Override
    public Patient register(Patient patient, PatientGateway patientGateway, AuthenticationGateway authenticationGateway) throws AlreadyRegisteredException, IdentityProviderException, CreateEntityException {
        logger.info("Starting PATIENT creation...");

        try {
            validateInformationInUse(patient.getCpf(), patient.getEmail(), patientGateway);

            authenticationGateway.createUserAuthentication(
                    patient.getCpf(),
                    patient.getPassword(),
                    patient.getEmail());

            patient.setIsActive(Boolean.TRUE);

            logger.info("Starting PATIENT successful...");

            return patientGateway.save(patient);

        } catch (Exception ex) {
            logger.error("PATIENT creation failed.");

            throw new CreateEntityException(
                    ExceptionCodes.USER_08_USER_CREATION,
                    ex.getMessage()
            );
        }
    }

    @Override
    public Patient getPatientById(String id, PatientGateway patientGateway) throws EntitySearchException {
        logger.info("Getting patient by id {}", id);

        return patientGateway.getPatientById(id);
    }

    @Override
    public Boolean validateInformationInUse(String email, String cpf, PatientGateway patientGateway) throws EntitySearchException, AlreadyRegisteredException {
        final var entityUsingEmail = patientGateway.getPatientByEmail(email);
        final var entityUsingCpf = patientGateway.getPatientByCpf(cpf);

        if (entityUsingEmail != null || entityUsingCpf != null) {
            throw new AlreadyRegisteredException(
                    ExceptionCodes.USER_02_ALREADY_REGISTERED,
                    String.format("Couldn't complete registration for user. Informations %s and %s may be already in use", email, cpf)
            );
        }

        return true;
    }
}
