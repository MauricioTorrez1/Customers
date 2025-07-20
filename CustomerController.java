package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Clase donde se implementa la lógica web para manejar las peticiones web HTTP(@Controller).
@RestController
//@RequesMapping permite la unificación de rutas tanto a nivel de método o de clase.
@RequestMapping("/clientes") //A nivel de clase
public class CustomerController {

    //La lista no debe de estar dentro del controlador
    //Lista que simula una base de datos (database)
    private List<Customer> customers; {
        customers = new ArrayList<>(Arrays.asList(
           new Customer("contraseña123", 123, "gerardo1", "Gerardo López"),
           new Customer("contra2456", 456, "alegarcia1", "Alejandra García"),
           new Customer("secreto789", 987, "lauras", "Laura Sanchéz"),
           new Customer("contra$eñadificil", 445, "carlosm", "Carlos Martinéz")
           ));
    }


    // @GetMapping("/clientes")
    //@GetMapping unificada con @RequestMapping
    @RequestMapping(method = RequestMethod.GET) //@RequestMapping a nivel de método
    public ResponseEntity<List<Customer>> getCustomers() {

        return ResponseEntity.ok(customers);//Usando ResponseEntity para un control total sobre la respuesta HTTP
        //return customers;
    }

    /**
     * El enrutamiento url permite definir como las solicitudes http se asignan
     * a métodos de controlador especificos en función de las rutas de acceso definidas
     * y los patrones de url definidas
     * @param username
     * @return
     */
  
    //Los métodos pueden tener la misma ruta base(endpoint) siempre y cuando se coloque un diferenciador en este caso un parametro {username}
    
    //Método para recuperar (get o leer) un recurso o coleccion de recursos.
    //@GetMapping("/{username}")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)//@RequestMapping a nivel de método con parametro web
    public ResponseEntity<?> getCliente(@PathVariable String username){
        for (Customer c : customers){
            if(c.getUsername().equalsIgnoreCase(username)){

                return ResponseEntity.ok(c);
                //return c;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con username: " + username);//NOT FOUND 404
        //return null;
    }
    //Nota: Un navegador web (localhost) no puede atender solicitudes del tipo POST se atienden con un probador de API´s en este caso Postman.
    //Método para agregar (create) nuevos clientes a la db
    //@PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()//Permite obtener la URI de la solicitud actual; toma la URL base de la petición
                .path("/{username}") //Agrega segmento de ruta adicional a la URI base
                .buildAndExpand(customer.getUsername()) //Tomar el valor proporcionado y lo inserta en la ruta
                .toUri(); //Finaliza la construccion y convierte la ruta construida en un objeto URI

        //return ResponseEntity.created(location).build(); //Creando y mostrando solo la URL
        return ResponseEntity.created(location).body(customer); //Creando y monstrando la URL y al JSON con la información del nuevo cliente
        //return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente: " + customer.getName()); *En caso de mostrar un mensaje con .body
        //return customer;

    }
    // Método para actualizar (update) todos los datos de un cliente dado su ID
    //@PutMapping
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build();
                //return ResponseEntity.ok("Cliente modificado satisfactoriamente: " + customer.getUsername()); en caso de que quisieramos poner un mensaje
                //return c;

            }

        }
        return ResponseEntity.notFound().build();//Sin mensaje solo .build 
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID no encontrado : " + customer.getID());
    }
    
    //Método para eliminar (delete) un cliente dando como parametro el id del cliente a eliminar
    //@DeleteMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        for(Customer c : customers){
            if (c.getID() == id){
                customers.remove(c);

               return ResponseEntity.noContent().build();//No es necesario poner el .body porque se usa el status 204 NOT CONTENT
            }
        }
        return ResponseEntity.notFound().build();
    }
    // Metodo todo para aplicar modificaciones parciales (patch) a un recurso a diferencia de PUT que reemplaza por completo el recurso.
    //@PatchMapping
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if (c.getID() == customer.getID()){

                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }

                if (customer.getUsername() != null){
                    c.setUsername(customer.getUsername());

                }
                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());

                }
                return ResponseEntity.ok("Cliente parcialmente modificado satisfactoriamente con el ID: " + customer.getID());
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + customer.getID());

    }


}
