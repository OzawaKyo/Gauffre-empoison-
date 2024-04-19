public class Gauffre {
    private boolean empoisonnée;
    private boolean croquée;
    private boolean hovered;

    Gauffre() {
        this.empoisonnée = false;
        this.croquée = false;
        this.hovered = false;
    }

    public boolean isEmpoisonnée() {
        return empoisonnée;
    }

    public void setEmpoisonnée(boolean empoisonnée) {
        this.empoisonnée = empoisonnée;
    }

    public boolean isCroquée() {
        return croquée;
    }

    public void setCroquée(boolean croquée) {
        this.croquée = croquée;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}
