package entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private int codigoEmpleado;  // PRIMARY KEY
    private String nombre;
    private String apellido1;
    private String apellido2;    // Puede ser NULL
    private String extension;
    private String email;
    private String codigoOficina; // FOREIGN KEY hacia oficina (codigo_oficina)
    private Integer codigoJefe;   // FOREIGN KEY hacia empleado (codigo_empleado), puede ser NULL
    private String puesto;        // Puede ser NULL

    public String toString() {
        return codigoEmpleado + " - " + nombre + " " + apellido1 + " - " + codigoOficina;
    }
}


