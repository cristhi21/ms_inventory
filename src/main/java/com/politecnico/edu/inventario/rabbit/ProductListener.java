package com.politecnico.edu.inventario.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.politecnico.edu.inventario.models.Datos;
import com.politecnico.edu.inventario.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ProductListener {

    private final ProductService productService;

    @RabbitListener(id = "receiver-inventary", queues = "${inventary.rabbitmq.queue}", ackMode = "MANUAL")
    public void onMessage(String message) {
        Datos datos = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            datos = objectMapper.readValue(message, Datos.class);
            System.out.println("ID: " + datos.getId());
            System.out.println("Cantidad: " + datos.getStock());
            productService.udpateStock(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
