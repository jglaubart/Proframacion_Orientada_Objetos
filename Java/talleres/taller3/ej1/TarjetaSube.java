package talleres.taller3.ej1;

public abstract class TarjetaSube {
    private Central central;
    public TarjetaSube(Central central) {
        this.central = central;
    }

    protected double ConsultarPrecio(){
        return central.ConsultarPrecio();
    }
    abstract protected boolean PuedeViajar();
    abstract public void viajar();
}
