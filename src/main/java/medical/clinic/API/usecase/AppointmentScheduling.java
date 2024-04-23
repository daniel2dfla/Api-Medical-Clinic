package medical.clinic.API.usecase;

import medical.clinic.API.dto.appointment.ConsultationCancellationDataDTO;
import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.dto.appointment.DataAppointmentQueryDTO;
import medical.clinic.API.entity.Appointment;
import medical.clinic.API.entity.Doctor;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.AppointmentRepository;
import medical.clinic.API.interfaces.DoctorRepository;
import medical.clinic.API.interfaces.PatientRepository;
import medical.clinic.API.usecase.validations.appointment.ValidatorAppointmentScheduling;
import medical.clinic.API.usecase.validations.cancellation.ValidatorQueryCancellation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduling {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorAppointmentScheduling> validators;

    @Autowired
    private List<ValidatorQueryCancellation> validadoresCancelamento;

    public DataAppointmentQueryDTO appointment(@NotNull DataAppointmentDTO data){

        if(!patientRepository.existsById(data.idPatient())){
            throw new ValidationException("Patient ID does not exist");
        }

        if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationException("Doctor ID does not exist");
        }

        validators.forEach(v -> v.validation(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = chooseDoctor(data);
        if(doctor == null){
            throw new ValidationException("There is no doctor available on that date.");
        }

        var appointment = new Appointment(null, doctor, patient, data.date(), null );
        appointmentRepository.save(appointment);

        return new DataAppointmentQueryDTO(appointment);
    }

    private Doctor chooseDoctor(DataAppointmentDTO data) {
        if(data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if(data.specialty() == null){
            throw new ValidationException("Doctor specialty not provided, and you did not pass a Doctor ID.");
        }

        return doctorRepository.chooseFreeRandomDoctorOnDate(data.specialty(), data.date());
    }

    public void cancel(ConsultationCancellationDataDTO data) {
        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("Query ID entered does not exist!");
        }

        validadoresCancelamento.forEach(v -> v.validation(data));

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }
}
