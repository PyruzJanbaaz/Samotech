package com.pyruz.samotech.shared.handler.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyruz.samotech.shared.model.dto.base.BaseDTO;
import com.pyruz.samotech.shared.model.dto.base.MetaDTO;
import com.pyruz.samotech.shared.utility.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@ControllerAdvice
@RestController
public class ServiceExceptionHandler {

    static final Logger logger = LoggerFactory.getLogger("samotech");

    final ApplicationProperties applicationProperties;

    public ServiceExceptionHandler(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    // --> ServiceLevelValidation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.error(ex.getMessage());
        BaseDTO baseDTO = new BaseDTO();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            MetaDTO metaDTO = new MetaDTO(
                    applicationProperties.getCode("validation-error-code"),
                    String.format(
                            applicationProperties.getProperty("validation-error-text"),
                            applicationProperties.getProperty(error.getField())
                    )
            );
            baseDTO.setMeta(metaDTO);
        }
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

    // --> Custom exceptions
    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(ServiceException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                BaseDTO.builder()
                        .meta(
                                MetaDTO.builder()
                                        .code(ex.getCode())
                                        .message(ex.getMessage())
                                        .build()
                        )
                        .build()
                , ex.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus());
    }

    // --> Handler not found exceptions
    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(NoHandlerFoundException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                BaseDTO.builder()
                        .meta(
                                MetaDTO.builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .message(ex.getMessage())
                                        .build()
                        )
                        .build()
                , HttpStatus.NOT_FOUND
        );
    }

    // --> General exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(Exception ex) throws IOException {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                new ObjectMapper().readValue((((HttpServerErrorException) ex).getResponseBodyAsString()), BaseDTO.class), ((HttpServerErrorException) ex).getStatusCode()
        );

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<BaseDTO> handleMissingParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        logger.error(ex.getMessage());
        Object convertedFieldName = applicationProperties.getProperty(ex.getParameterName());
        return new ResponseEntity<>(
                BaseDTO.builder()
                        .meta(
                                MetaDTO.builder()
                                        .code(applicationProperties.getCode("missing-parameter-code"))
                                        .message(
                                                String.format(
                                                        applicationProperties.getProperty("missing-parameter-text"),
                                                        convertedFieldName == null ? ex.getParameterName() : convertedFieldName.toString()
                                                )
                                        )
                                        .build()
                        )
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    // --> Runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(RuntimeException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                BaseDTO.builder()
                        .meta(
                                MetaDTO.builder()
                                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .message(applicationProperties.getProperty("unknown-error-text"))
                                        .build()
                        )
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // --> Server or machine errors
    @ExceptionHandler(Error.class)
    public final ResponseEntity<BaseDTO> handleAllExceptions(Error ex, WebRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                BaseDTO.builder()
                        .meta(
                                MetaDTO.builder()
                                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .message(ex.getMessage())
                                        .build()
                        )
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // --> Server or machine errors
    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<BaseDTO> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) throws IOException {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
                new ObjectMapper().readValue(ex.getStatusText(), BaseDTO.class), ex.getStatusCode()
        );
    }

}
