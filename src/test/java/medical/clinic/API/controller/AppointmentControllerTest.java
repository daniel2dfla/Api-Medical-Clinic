package medical.clinic.API.controller;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.dto.appointment.DataAppointmentQueryDTO;
import medical.clinic.API.dto.doctor.SpecialtyDTO;
import medical.clinic.API.usecase.AppointmentScheduling;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private JacksonTester<DataAppointmentDTO> dataAppointmentDTOJSON;

    @Autowired
    private JacksonTester<DataAppointmentQueryDTO> dataDetailsAppointmentDTOJSON;

    @MockBean
    private AppointmentScheduling appointmentSchedule;

    @Test
    @DisplayName("It should return http code 400 when the information is wrong.")
    @WithMockUser
    void appointmentScenario1() throws Exception {
        var response = mvc.perform(post("/appointment"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return http code 200 when the information is correct.")
    @WithMockUser
    void appointmentScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = SpecialtyDTO.gynecology;

        var dataAppointmentQuery = new DataAppointmentQueryDTO(null, 2l, 5l, date);

        when(appointmentSchedule.appointment(any())).thenReturn(dataAppointmentQuery);

        var response = mvc
                .perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataAppointmentDTOJSON.write(
                                new DataAppointmentDTO(2l, 5l, date, specialty )
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = dataDetailsAppointmentDTOJSON.write(
                dataAppointmentQuery
        ).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}