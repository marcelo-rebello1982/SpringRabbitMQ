package net.javaguides.springboot.controller;

import net.javaguides.springboot.constants.RabbitMQConstants;
import net.javaguides.springboot.dto.EstoqueDTO;
import net.javaguides.springboot.service.ServiceRabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estoque")
public class EstoqueController {
	
	@Autowired
	private ServiceRabbitMQ rabbitMQService;
	
	@PutMapping
	private ResponseEntity<EstoqueDTO> alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
		System.out.println(EstoqueDTO.codigoProduto);
		this.rabbitMQService.enviaMensagem(RabbitMQConstants.FILA_ESTOQUE, estoqueDTO);
		return new ResponseEntity<EstoqueDTO>(HttpStatus.OK);
	}
}
