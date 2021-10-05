package net.javaguides.springboot.controller;

import net.javaguides.springboot.constants.RabbitMQConstants;
import net.javaguides.springboot.dto.PrecoDTO;
import net.javaguides.springboot.service.ServiceRabbitMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("preco")
public class PrecoController {
	
	@Autowired
	private ServiceRabbitMQ serviceRabbitMQ;
	
	@PutMapping
	private ResponseEntity<PrecoDTO> alteraPreco(@RequestBody PrecoDTO  precoDTO) {
		System.out.println("EstoqueController - alterar preco");
		this.serviceRabbitMQ.enviaMensagem(RabbitMQConstants.FILA_PRECO, precoDTO);
		return new ResponseEntity<PrecoDTO>(HttpStatus.OK);
	}
}
