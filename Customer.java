package cursoSpringBoot.domain;

//El paquete domain (o a veces llamado model) se refiere al corazón de tu aplicación. 
//Contiene las clases que representan los conceptos y datos fundamentales de tu negocio.
//Estas clases no se preocupan por cómo se muestran los datos en una página web o cómo se guardan en una base de datos; 
//su única responsabilidad es definir la estructura y el comportamiento de los objetos del negocio.
/*Clase Java tipo POJO, son clases normales que únicamente
contienen los atributos de un objeto, a su vez los getter y
setter y por supuesto el constructor o constructores a utilizar
 */
public class Customer {
//Crear los atributos
    private int ID;
    private String name;
    private String username;
    private String password;

    //Constructor para poblar las variables anteriores

    public Customer(String password, int ID, String username, String name) {
        this.password = password;
        this.ID = ID;
        this.username = username;
        this.name = name;
    }
    //Getter and setter para tener acceso a las variables. . .
    //. . . previamente creadas

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
