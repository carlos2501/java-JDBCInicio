package entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Oficina {
    private String codigoOficina;      // PRIMARY KEY
    private String ciudad;
    private String pais;
    private String region;            // Puede ser NULL
    private String codigoPostal;
    private String telefono;
    private String lineaDireccion1;
    private String lineaDireccion2;   // Puede ser NULL
}
