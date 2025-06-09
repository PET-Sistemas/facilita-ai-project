package com.UFMSPetSistemas.getpet.controller.usuario;

import java.util.List;

import com.UFMSPetSistemas.getpet.controller.usuario.dto.AtualizarUsuarioDTO;
import com.UFMSPetSistemas.getpet.controller.usuario.dto.CadastroUsuarioDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.UFMSPetSistemas.getpet.model.entities.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Endpoints para gerenciamento de usuários")
public interface IntUsuarioController {
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        operationId = "cadastrarUsuario", 
        summary = "Cadastrar um novo usuário.", 
        description = "Recebe os dados de um novo usuário e o salva no banco de dados.",
        tags = { "Usuário" },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do novo usuário",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class),
                examples = @ExampleObject(
                    name = "Exemplo de usuário",
                    summary = "JSON válido para criação de usuário",
                    value = "{\n" +
                            "  \"nomeCompleto\": \"João da Silva\",\n" +
                            "  \"dataNascimento\": \"1990-01-01\",\n" +
                            "  \"endereco\": \"Rua das Flores, 123\",\n" +
                            "  \"cidade\": \"Campo Grande\",\n" +
                            "  \"uf\": \"MS\",\n" +
                            "  \"email\": \"joao@example.com\",\n" +
                            "  \"telefone\": \"67999998888\",\n" +
                            "  \"senha\": \"senha123\"\n" +
                            "}"
                )
            )
        ),
        responses = { 
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.", content = @Content(
                    examples = {@ExampleObject(
                            name = "Novo Usuário Exemplo João",
                            summary = "",
                            description = "Usuário cadastrado com todos os campos e servicos em null.",
                            value = "{" +
                                    "  \"id\": 7,\n" +
                                    "  \"nomeCompleto\": \"João da Silva\",\n" +
                                    "  \"dataNascimento\": \"1990-01-01T00:00:00.000+00:00\",\n" +
                                    "  \"endereco\": \"Rua das Flores, 123\",\n" +
                                    "  \"cidade\": \"Campo Grande\",\n" +
                                    "  \"uf\": \"MS\",\n" +
                                    "  \"email\": \"joao@example.com\",\n" +
                                    "  \"telefone\": \"67999998888\",\n" +
                                    "  \"senha\": \"senha123\",\n" +
                                    "  \"servicos\": null,\n" +
                                    "  \"servicosPrestados\": null,\n" +
                                    "  \"servicosContratados\": null\n" +
                                    "}")
                    },
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Usuario.class)
                )
            ),
            @ApiResponse(responseCode = "400", description = "Json inválido."),
        }
    )
    @ResponseBody
    ResponseEntity<?> cadastrarUsuario(@RequestBody CadastroUsuarioDTO novoColaborador);
    
    @GetMapping(path = "/todos")
    @Operation(
        operationId = "listarUsuarios", 
        summary = "Listar todos os usuários", 
        description = "Retorna uma lista com todos os usuários cadastrados.",
        tags = { "Usuário" },
        responses = { 
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso.", content = @Content(schema = @Schema(implementation = Usuario.class))),
        }
    )
    public List<Usuario> listarUsuarios();

    @GetMapping(path = "/id")
    @Operation(
        operationId = "buscarPorId", 
        summary = "Buscar usuário por ID", 
        description = "Busca um usuário pelo seu ID.",
        tags = { "Usuário" },
        parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "ID do usuário a ser buscado", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso.", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "ID inválido.")
        }
    )
    public ResponseEntity<?> buscarPorId(@RequestParam Long id);

    @GetMapping(path = "/nome")
    @Operation(
        operationId = "buscarPorNome", 
        summary = "Buscar usuários por nome", 
        description = "Busca usuários cujo nome contenha o valor informado.",
        tags = { "Usuário" },
        parameters = {
            @Parameter(in = ParameterIn.PATH, name = "nome", description = "Nome ou parte do nome do usuário", required = true)
        },
        responses = { 
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso.", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Nome inválido.")
        }
    )
    public ResponseEntity<?> buscarPorNome(@RequestParam String nome);

    @GetMapping(path = "/endereco")
    @Operation(
        operationId = "buscarPorEndereco", 
        summary = "Buscar usuários por endereço", 
        description = "Busca usuários cujo endereço contenha o valor informado.",
        tags = { "Usuário" },
        parameters = {
            @Parameter(in = ParameterIn.PATH, name = "endereco", description = "Endereço ou parte do endereço do usuário", required = true)
        },
        responses = { 
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Endereço inválido.")
        }
    )
    public ResponseEntity<?> buscarPorEndereco(@RequestParam String endereco);

    @PutMapping(path = "/")
    @Operation(
        operationId = "putUsuario", 
        summary = "Atualizar um usuário", 
        description = "Atualiza os dados de um usuário existente.",
        tags = { "Usuário" },
        parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "ID do usuário a ser atualizado", required = true)
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do usuário a ser atualizado",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class),
                examples = @ExampleObject(
                    name = "Exemplo de usuário",
                    summary = "JSON válido para atualização de usuário",
                    value = "{\n" +
                            "  \"nomeCompleto\": \"João da Silva\",\n" +
                            "  \"dataNascimento\": \"1990-01-01\",\n" +
                            "  \"endereco\": \"Rua das Flores, 123\",\n" +
                            "  \"cidade\": \"Campo Grande\",\n" +
                            "  \"uf\": \"MS\",\n" +
                            "  \"email\": \"Tlq7t@example.com\",\n" +
                            "  \"telefone\": \"(99) 99999-9999\",\n" +
                            "  \"senha\": \"senha123\"\n" +
                            "}"
                )
            )
        ),
        responses = { 
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.", content = @Content(schema = @Schema(implementation = Usuario.class))),
            //@ApiResponse(responseCode = "400", description = "ID inválido."),
            //@ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "422", description = "Erro ao atualizar usuário.")
        }
    )
    public ResponseEntity<?> putUsuario(@RequestBody AtualizarUsuarioDTO newColaborador, @RequestParam Long id);

    @DeleteMapping(path = "/")
    @Operation(
        operationId = "deleteColaborador", 
        summary = "Deletar um usuário", 
        description = "Remove um usuário do banco de dados pelo ID.",
        tags = { "Usuário" },
        parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", description = "ID do usuário a ser deletado", required = true)
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "ID do usuário a ser deletado",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class),
                examples = @ExampleObject(
                    name = "Exemplo de ID",
                    summary = "ID válido para deleção de usuário",
                    value = "{\n" +
                            "  \"id\": 1\n" +
                            "}"
                )
            )
        ),
        responses = { 
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.", content = @Content(schema = @Schema(implementation = Usuario.class))),
        }
    )
    public void deleteColaborador(@RequestParam Long id);
}
