package ru.sber.beautifulcode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.sber.beautifulcode.controller.handler.CheckBracketsExceptionHandler;
import ru.sber.beautifulcode.model.Text;
import ru.sber.beautifulcode.service.TextHandlingService;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CheckBracketsControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private TextHandlingService textHandlingService;

    private MockMvc mvc;

    private static Stream<Arguments> provideValidRequest() {
        return Stream.of(
                Arguments.of("bu-ga-(ga)", false),
                Arguments.of("1+1", false),
                Arguments.of("test", true),
                Arguments.of("e x a m p l e", true)
        );
    }

    private static Stream<Arguments> provideInvalidRequest() {
        return Stream.of(
                Arguments.of(new Text("")),
                Arguments.of(new Text(null)),
                Arguments.of(new Text("   "))
        );
    }

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new CheckBracketsController(textHandlingService))
                .setControllerAdvice(new CheckBracketsExceptionHandler())
                .build();
    }

    @ParameterizedTest
    @MethodSource("provideValidRequest")
    void checkBrackets_whenSendValidRequest() throws Exception {
        Text request = new Text("example");

        mvc.perform(post("/api/checkBrackets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());

        Mockito.verify(textHandlingService).checkText(request);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRequest")
    void checkBrackets_whenSendInvalidRequest(Text invalidRequest) throws Exception {
        mvc.perform(post("/api/checkBrackets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest))
        ).andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(textHandlingService);
    }
}

