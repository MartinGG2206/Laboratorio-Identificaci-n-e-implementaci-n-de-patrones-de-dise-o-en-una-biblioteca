package biblioteca;

// Implementación temporal para compilar la estación 3.
// Se sustituirá por el código original del profesor en la estación 7.
public class Pendiente implements EstadoReserva {
    @Override
    public String nombre() {
        return "Pendiente";
    }

    @Override
    public void confirmar(Reserva reserva) {
        throw new UnsupportedOperationException("Transición pendiente de implementar en la estación 7.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new UnsupportedOperationException("Transición pendiente de implementar en la estación 7.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        throw new UnsupportedOperationException("Transición pendiente de implementar en la estación 7.");
    }
}
