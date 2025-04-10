package modelos;

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

        // Constructor vacío
        public Cliente() {
        }

        // Constructor con todos los campos
        public Cliente(int codigoCliente, String nombreCliente, String nombreContacto, String apellidoContacto,
                       String telefono, String fax, String lineaDireccion1, String lineaDireccion2,
                       String ciudad, String region, String pais, String codigoPostal,
                       Integer repVentas, Float limiteCredito) {
            this.codigoCliente = codigoCliente;
            this.nombreCliente = nombreCliente;
            this.nombreContacto = nombreContacto;
            this.apellidoContacto = apellidoContacto;
            this.telefono = telefono;
            this.fax = fax;
            this.lineaDireccion1 = lineaDireccion1;
            this.lineaDireccion2 = lineaDireccion2;
            this.ciudad = ciudad;
            this.region = region;
            this.pais = pais;
            this.codigoPostal = codigoPostal;
            this.repVentas = repVentas;
            this.limiteCredito = limiteCredito;
        }

        // Getters y Setters
        public int getCodigoCliente() {
            return codigoCliente;
        }

        public void setCodigoCliente(int codigoCliente) {
            this.codigoCliente = codigoCliente;
        }

        public String getNombreCliente() {
            return nombreCliente;
        }

        public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }

        public String getNombreContacto() {
            return nombreContacto;
        }

        public void setNombreContacto(String nombreContacto) {
            this.nombreContacto = nombreContacto;
        }

        public String getApellidoContacto() {
            return apellidoContacto;
        }

        public void setApellidoContacto(String apellidoContacto) {
            this.apellidoContacto = apellidoContacto;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getLineaDireccion1() {
            return lineaDireccion1;
        }

        public void setLineaDireccion1(String lineaDireccion1) {
            this.lineaDireccion1 = lineaDireccion1;
        }

        public String getLineaDireccion2() {
            return lineaDireccion2;
        }

        public void setLineaDireccion2(String lineaDireccion2) {
            this.lineaDireccion2 = lineaDireccion2;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
        }

        public Integer getRepVentas() {
            return repVentas;
        }

        public void setRepVentas(Integer repVentas) {
            this.repVentas = repVentas;
        }

        public Float getLimiteCredito() {
            return limiteCredito;
        }

        public void setLimiteCredito(Float limiteCredito) {
            this.limiteCredito = limiteCredito;
        }

        // Método toString (útil para depuración)
        @Override
        public String toString() {
            return "Cliente{" +
                    "codigoCliente=" + codigoCliente +
                    ", nombreCliente='" + nombreCliente + '\'' +
                    ", nombreContacto='" + nombreContacto + '\'' +
                    ", apellidoContacto='" + apellidoContacto + '\'' +
                    ", telefono='" + telefono + '\'' +
                    ", fax='" + fax + '\'' +
                    ", lineaDireccion1='" + lineaDireccion1 + '\'' +
                    ", lineaDireccion2='" + lineaDireccion2 + '\'' +
                    ", ciudad='" + ciudad + '\'' +
                    ", region='" + region + '\'' +
                    ", pais='" + pais + '\'' +
                    ", codigoPostal='" + codigoPostal + '\'' +
                    ", repVentas=" + repVentas +
                    ", limiteCredito=" + limiteCredito +
                    '}';
        }
}

