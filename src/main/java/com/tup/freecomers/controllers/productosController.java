package com.tup.freecomers.controllers;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tup.freecomers.models.*;
import com.tup.freecomers.repositories.*;


@RestController
@RequestMapping
public class productosController {
    
    @Autowired
  private productosRepository prodRepository;

  @PostMapping("/add") // Map ONLY POST Requests
  public String addNewprod(@RequestParam long id,@RequestParam String nombre, @RequestParam Float precio, @RequestParam String pais) {
    // @RequestParam means it is a parameter from the GET or POST request

    productos productos = new productos();
    productos.setId(id);
    productos.setNombre(nombre);
    productos.setPrecio(precio);
    productos.setPais(pais);
    prodRepository.save(productos);
    return "Saved";
  }

  @PostMapping("/delete/{id}") // Map ONLY POST Requests
  public String deleteProdById(@PathVariable Long id) {
    // @RequestParam means it is a parameter from the GET or POST request

    prodRepository.deleteById(id);
    return "Deleted";
  }

  @GetMapping("/{id}")
  public String findProdById(@PathVariable Long id) {
    // @PathVariable indica que el parámetro id, de tipo Long, es una
    // variable que viene en la URI.
    /**
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html#findById-ID-
     * Optional<T> findById(ID id)
     */
     
    String resp = """
      <style>
        #productos {
          font-family: Arial, Helvetica, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }
        body{
          background-image: url('images/bg_freecomers.png');
          background-repeat: no-repeat;
          background-position: center, center;
          background-size: 1660px  900px;
          background-attachment: fixed;
          background-opacity: 0.4;
        }

        #productos td, #users th {
          border: 1px solid #ddd;
          padding: 8px;
        }
        #productos tr:nth-child(even){background-color: #f2f2f2;}
        #productos tr:hover {background-color: #ddd;}
        #productos th {
          padding-top: 12px;
          padding-bottom: 12px;
          text-align: left;
          background-color: #04AA6D;
          color: white;
        }
      </style>
      <table id ='productos'>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Precio</th>
          <th>Pais</th>
        </tr>
    """;
     
     productos prod = prodRepository.findById(id).get();

     resp += "<tr>"
          + "<td>" + prod.getId() + "</td>"
          + "<td>" + prod.getNombre() + "</td>"
          + "<td>" + prod.getPrecio() + "</td>"
          + "<td>" + prod.getPais() + "</td>"
          + "</tr>";
    
    
     
          return resp + "</table>";
  }

  @GetMapping("/all")
  public String getAllProd() {
    // This returns a JSON or XML with the users
    Iterable<productos> iterable = prodRepository.findAll();
    /**
     * Lo que viene a continuación se llama text block, 
     * y es tipo String. El Manual de Java los describe en 
     * la sección 3.10.6 Text Blocks.
     * 
     * La variable resp es de tipo String, y le vamos a asignar un bloque de texto.
     * Ese bloque de texto es todo que lo que está contenido entre los dos
     * delimitadores: el de apertura y el de cierre.
     * El delimitador de apertura es la triple comilla """ que está a la
     * derecha del igual.
     * El delimitador de cierre es la triple comilla """ que está al final.
     * Todo es seguido por el punto y coma, porque es el final de una sentencia.
     * 
     * No es buen estilo incluir cadenas largas en un archivo de código fuente.
     * Esto lo hago solo para no introducir una complicación que no agregaría
     * nada a los conceptos que estoy discutiendo ahora.
     * 
     * Comenzamos por poner unos estilos CSS, para que la tabla quede más linda.
     * 
     * Cuando terminamos con los estilos, arrancamos con el HTML de la
     * tabla misma. Lo primero que hacemos es generar una fila y en las
     * celdas de esa fila poner los encabezados, que son los nombres de
     * las columnas o campos de la tabla que está en la base de datos.
     */
    String resp = """
          <style>
            #productos {
              font-family: Arial, Helvetica, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }
            #productos td, #users th {
              border: 1px solid #ddd;
              padding: 8px;
            }
            body{
              background-image: url('images/bg_freecomers.png');
              background-repeat: no-repeat;
              background-position: center, center;
              background-size: 100%;
              background-attachment: fixed;
            }

            #productos tr:nth-child(n){background-color: #ffffffe0;}
            #productos tr:hover {background-color: #ddd;}
            #productos th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #04AA6D;
              color: white;
            }
          </style>
          <table id ='productos'>
            <tr>
            {border-bottom: 1px solid #ddd;}
              <th>Id</th>
              <th>Nombre</th>
              <th>Precio</th>
              <th>Pais</th>
            </tr>
        """;
        /**
         * Ya terminé con la fila de los encabezados, y ahora tengo que
         * generar el cuerpo de la tabla, una fila por cada registro.
         * No puedo usar forEach() con una función lambda
         * porque el scope de las variables no lo permite.
         * Por eso uso el for mejorado, para recorrer el objeto iterable.
         */
    for (productos prod : iterable) {
      resp += "<tr>"
          + "<td>" + prod.getId() + "</td>"
          + "<td>" + prod.getNombre() + "</td>"
          + "<td>" + prod.getPrecio() + "</td>"
          + "<td>" + prod.getPais() + "</td>"
          + "</tr>";
    }
    return resp + "</table>";
  }

  @GetMapping("")
  public String Bienvenido() {
    return "Bienvenido a Freecomers";
  }
}

