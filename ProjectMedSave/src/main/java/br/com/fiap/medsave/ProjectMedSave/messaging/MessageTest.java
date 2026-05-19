package br.com.fiap.medsave.ProjectMedSave.messaging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/api/v4/messages")
@Tag(name = "MessageTest", description = "Endpoints para testar se o serviço está funcionando corretamente")
public class MessageTest {

    @GetMapping
    @Operation(summary = "Teste de ping", method = "GET")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Ok");
    }
}
