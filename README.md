# Customers
Práctica que simula una base de datos de clientes por medio de una lista en JAVA donde se implementan las diferentes requests HTTP como lo son:
- POST (Create)
- GET (Read)
- PUT/PATCH (Update)
- DELETE (Delete)
  
Se implementan las notaciones:
- @GetMapping para obtener los recursos (clientes)
- @PostMapping para crear un nuevo recurso 
- @PutMapping para actualizar totalmente un recurso
- @PatchMapping para actualizar parcialmente un recurso
- @DeleteMapping para eliminar un recurso
- @RequestMapping a nivel de clase para la unificación de URL´s (endpoints) y a nivel de método para sustituir las notaciones anteriores (get,post,put,etc...)
