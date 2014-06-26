/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.eventos.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * JSON { "name" : "joao", "passwd" : "haha", "requestType": "nextEvents" }
 * requestTypes: newEvent, nextEvents
 * Criar uma API qu seja possivel receber requisicoes para criar novos eventos e
 * também recuperar dados de eventos, como por exemplo proximos eventos que o usuario
 * esta cadastradoou convidado
 * 
 * Pesquisar como usar bookstrak.
 * 
 * RESTful ws
 * 
 * WRONG GET api/adicionaUsuario?nome=xpto&age=31
 * 
 * GET api/Events
 * GET api/Events/1
 * POST api/Events
 * PUT api/Events/1
 * DELETE api/Events/1
 * 
 * GET api/Users/1/Events  
 * 
 * $.ajax(method: "POST")
 * 
 * autenticacao
 * 
 * basica - servletfilter, jsf face listnet
 * 
 * api jaas
 * 
 * spring security
 *   roles
 *   permission
 * 
 * 
 * 
 */
public class WS {
    
    public String get() {
        return "OK";
    }
    
}
