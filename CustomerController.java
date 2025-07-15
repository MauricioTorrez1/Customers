package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Customer> getCustomers() {
        return customers;
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
    public Customer getCliente(@PathVariable String username){
        for (Customer c : customers){
            if(c.getUsername().equalsIgnoreCase(username)){
                return c;
            }
        }
        return null;
    }
    //Nota: Un navegador web (localhost) no puede atender solicitudes del tipo POST se atienden con un probador de API´s en este caso Postman.
    //Método para agregar (create) nuevos clientes a la db
    //@PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public Customer postCliente(@RequestBody Customer customer){
        customers.add(customer);
        return customer;

    }
    // Método para actualizar (update) todos los datos de un cliente dado su ID
    //@PutMapping
    @RequestMapping(method = RequestMethod.PUT)
    public Customer putCliente(@RequestBody Customer customer){
        for (Customer c : customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return c;

            }

        }
        return null;
    }
    /*
    Método para eliminar (delete) un cliente dando como
    parametro el id del cliente a eliminar
    */
    //@DeleteMapping("/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Customer deleteCliente(@PathVariable int id){
        for(Customer c : customers){
            if (c.getID() == id){
                customers.remove(c);

                return c;
            }
        }
        return null;
    }
    // Metodo todo para aplicar modificaciones parciales (patch) a un recurso a diferencia de PUT que reemplaza por completo el recurso.
    //@PatchMapping
    @RequestMapping(method = RequestMethod.PATCH)
    public Customer patchCliente(@RequestBody Customer customer){
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
                return c;
            }

        }
        return null;

    }


}
