package ru.FattAkvarium.taskForT1Consulting.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.FattAkvarium.taskForT1Consulting.dto.IncomingStringDTO;
import ru.FattAkvarium.taskForT1Consulting.model.IncomingString;
import ru.FattAkvarium.taskForT1Consulting.service.IncomingStringService;
import ru.FattAkvarium.taskForT1Consulting.util.IncomingStringBadRequestException;
import ru.FattAkvarium.taskForT1Consulting.util.IncomingStringErrorResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enter")
public class IncomingStringController {
    private final IncomingStringService incomingStringService;

    private final ModelMapper mapper;

    @Autowired
    public IncomingStringController(IncomingStringService incomingStringService, ModelMapper mapper) {
        this.incomingStringService = incomingStringService;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Long>> creatingQueryAndGettingItsResult(@RequestBody @Valid IncomingStringDTO incomingStringDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new IncomingStringBadRequestException(errorMessage.toString());
        }

        incomingStringService.save(convertToIncomingString(incomingStringDTO));

        final Map<String, Long> mapForRequest = incomingStringService.returnFillAndSortedMap();
        return mapForRequest != null && !mapForRequest.isEmpty() ? new ResponseEntity<>(mapForRequest, HttpStatus.OK)
                                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<IncomingStringErrorResponse> handleException(IncomingStringBadRequestException exception) {
        IncomingStringErrorResponse response = new IncomingStringErrorResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private IncomingString convertToIncomingString (IncomingStringDTO incomingStringDTO) {
        return mapper.map(incomingStringDTO, IncomingString.class);
    }
}
