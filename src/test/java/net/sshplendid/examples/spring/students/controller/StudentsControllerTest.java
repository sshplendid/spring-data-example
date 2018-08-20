package net.sshplendid.examples.spring.students.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentsController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
//@AutoConfigureMockMvc
public class StudentsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].lastName").isString())
                .andExpect(jsonPath("$[0].age").isNumber())
                .andExpect(jsonPath("$[0].gender").isString())
                .andDo(document("students/get", responseFields(
                        fieldWithPath("[].id").description("Student identifier"),
                        fieldWithPath("[].firstName").description("The first name of the student"),
                        fieldWithPath("[].lastName").description("The last name of the student"),
                        fieldWithPath("[].age").description("The ageof the student"),
                        fieldWithPath("[].gender").description("The gender of the student").optional()
                )));

    }
    @Test
    public void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/students")
                            .param("namelike", "gon")
                            .param("age", "1"))
                .andDo(print())
                .andDo(document("students/getempty", requestParameters(
                        parameterWithName("age").description("나이 조건").optional(),
                        parameterWithName("namelike").description("이름 like 검색").optional())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").doesNotExist())
                .andExpect(jsonPath("$[0].name").doesNotExist())
                .andExpect(jsonPath("$[0].age").doesNotExist())
                .andExpect(jsonPath("$[0].gender").doesNotExist())
                .andDo(document("students/getempty", responseFields(
                        fieldWithPath("[]").description("Student list")
//                        fieldWithPath("[].id").description("Student identifier"),
//                        fieldWithPath("[].name").description("The name of the student"),
//                        fieldWithPath("[].age").description("The ageof the student"),
//                        fieldWithPath("[].gender").description("The gender of the student").optional()
                )))
                ;

    }

}
