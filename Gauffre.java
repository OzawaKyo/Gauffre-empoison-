import java.awt.*;

public class Gauffre {
    private boolean empoisonnée;
    private boolean croquée;
    private boolean hovered;
    private Color playerColor;

    Gauffre() {
        this.empoisonnée = false;
        this.croquée = false;
        this.hovered = false;
        this.playerColor = null;
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

    public Color getPlayerColor(){
        return playerColor;
    }
    public void setPlayerColor(int player){
        if (player == 1){
            playerColor = Color.blue.darker();
        }
        else{
            playerColor = Color.red.darker();
        }
    }
}
