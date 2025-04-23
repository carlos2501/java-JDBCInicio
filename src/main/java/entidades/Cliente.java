package entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
        private int codigoCliente;
        private String nombreCliente;
        private String nombreContacto;
        private String apellidoContacto;
        private String telefono;
        private String fax;
        private String lineaDireccion1;
        private String lineaDireccion2;
        private String ciudad;
        private String region;
        private String pais;
        private String codigoPostal;
        private Integer repVentas; // Puede ser nulo
        private Float limiteCredito; // Manejo de decimales para valores monetarios

    public String toString() {
        return codigoCliente + " - " + nombreCliente;
    }

    public String toFicha() {
        return """
                1 - Código %d
                2 - Nombre %s
                3 - Nombre del contacto %s
                4 - Apellido del contacto %s
                5 - Teléfono %s
                6 - Fax %s
                7 - Dirección 1 %s
                8 - Dirección 2 %s
                9 - Ciudad %s
                10 - Región %s
                11 - Pais %s
                12 - Codigo Postal %s
                13 - Rep de Ventas %d
                14 - Limite de Crédito %f
                """.formatted(
                        codigoCliente,
                        nombreCliente,
                        nombreContacto,
                        apellidoContacto,
                        telefono,
                        fax,
                        lineaDireccion1,
                        lineaDireccion2,
                        ciudad,
                        region,
                        pais,
                        codigoPostal,
                        repVentas,
                        limiteCredito
        );
    }
}

